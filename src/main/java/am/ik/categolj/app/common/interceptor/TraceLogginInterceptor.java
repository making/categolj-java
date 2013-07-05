package am.ik.categolj.app.common.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TraceLogginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory
            .getLogger(TraceLogginInterceptor.class);

    private static final String START_ATTR = TraceLogginInterceptor.class
            .getName() + ".startTime";
    private static final String HANDLING_ATTR = TraceLogginInterceptor.class
            .getName() + ".handlingTime";

    private static final long DEFAULT_WARN_MILLS = TimeUnit.SECONDS.toMillis(3);

    private long warnHandlingMilis = DEFAULT_WARN_MILLS;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (logger.isTraceEnabled()) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method m = handlerMethod.getMethod();
            logger.trace("[START CONTROLLER] {}.{}({})", new Object[] {
                    m.getDeclaringClass().getSimpleName(), m.getName(),
                    buildMethodParams(handlerMethod) });
        }
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_ATTR, startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        boolean isStarted = (request.getAttribute(START_ATTR) != null);
        long startTime = isStarted ? (Long) request.getAttribute(START_ATTR)
                : -1;
        long handlingTime = System.currentTimeMillis() - startTime;
        if (isStarted) {
            request.removeAttribute(START_ATTR);
            request.setAttribute(HANDLING_ATTR, handlingTime);
        }
        boolean isWarnHandling = (handlingTime > warnHandlingMilis);

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method m = handlerMethod.getMethod();
            Object view = null;
            Map<String, Object> model = null;
            if (modelAndView != null) {
                view = modelAndView.getView();
                model = modelAndView.getModel();
                if (view == null) {
                    view = modelAndView.getViewName();
                }
            }

            logger.trace(
                    "[END CONTROLLER  ] {}.{}({})-> view={}, model={}",
                    new Object[] { m.getDeclaringClass().getSimpleName(),
                            m.getName(), buildMethodParams(handlerMethod),
                            view, model });
            if (isStarted) {
                String handlingTimeMessage = "[HANDLING TIME   ] {}.{}({})-> {} ms";
                if (isWarnHandling) {
                    logger.warn(handlingTimeMessage + " > {}", new Object[] {
                            m.getDeclaringClass().getSimpleName(), m.getName(),
                            buildMethodParams(handlerMethod), handlingTime,
                            warnHandlingMilis });
                } else {
                    logger.trace(handlingTimeMessage, new Object[] {
                            m.getDeclaringClass().getSimpleName(), m.getName(),
                            buildMethodParams(handlerMethod), handlingTime });
                }
            }
        } else if (isStarted) {
            String handlingTimeMessage = "[HANDLING TIME   ]-> {} ms";
            if (isWarnHandling) {
                logger.warn(handlingTimeMessage + " > {}", handlingTime,
                        warnHandlingMilis);
            } else {
                logger.trace(handlingTimeMessage, handlingTime);
            }
        }
    }

    protected static String buildMethodParams(HandlerMethod handlerMethod) {
        MethodParameter[] params = handlerMethod.getMethodParameters();
        List<String> lst = new ArrayList<String>(params.length);
        for (MethodParameter p : params) {
            lst.add(p.getParameterType().getSimpleName());
        }
        return StringUtils.collectionToCommaDelimitedString(lst);
    }
}
