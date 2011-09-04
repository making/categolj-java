package am.ik.categolj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SetMdcInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        MDC.put("req", request.getParameterMap().toString());
        return super.preHandle(request, response, handler);
    }
}
