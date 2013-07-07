package am.ik.categolj.app.common.interceptor;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import am.ik.categolj.domain.common.consts.Const;
import am.ik.categolj.domain.model.Link;
import am.ik.categolj.domain.service.link.LinkService;

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
