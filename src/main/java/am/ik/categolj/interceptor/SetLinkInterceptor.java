package am.ik.categolj.interceptor;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import am.ik.categolj.common.Const;
import am.ik.categolj.entity.Link;
import am.ik.categolj.service.LinkService;

public class SetLinkInterceptor extends HandlerInterceptorAdapter {
    @Inject
    protected LinkService linkService;

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            List<Link> links = linkService.findAll();
            modelAndView.addObject(Const.LINKS_ATTR, links);
        }
    }
}
