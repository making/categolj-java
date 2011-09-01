package am.ik.categolj.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import am.ik.categolj.common.LogId;
import am.ik.yalf.logger.Logger;

public class LoggingExeptionResolver extends SimpleMappingExceptionResolver {
    private static final Logger logger = Logger
            .getLogger(LoggingExeptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        logger.warn(LogId.WCTGL001, ex);
        return super.doResolveException(request, response, handler, ex);
    }
}
