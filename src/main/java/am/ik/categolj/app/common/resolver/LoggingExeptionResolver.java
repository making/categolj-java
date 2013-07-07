package am.ik.categolj.app.common.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import am.ik.categolj.domain.common.consts.LogId;
import am.ik.categolj.domain.common.exception.NoSuchEntryException;
import am.ik.yalf.logger.Logger;

public class LoggingExeptionResolver extends SimpleMappingExceptionResolver {
    private static final Logger logger = Logger
            .getLogger(LoggingExeptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        logger.fatal(LogId.FCTGL001, ex);
        if (ex instanceof NoSuchEntryException) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return super.doResolveException(request, response, handler, ex);
    }
}
