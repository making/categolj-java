package am.ik.categolj.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class LoggingExeptionResolver extends SimpleMappingExceptionResolver {
    private static final Logger logger = LoggerFactory
            .getLogger(LoggingExeptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        logger.warn("Exceptin occured!", ex);
        return super.doResolveException(request, response, handler, ex);
    }
}
