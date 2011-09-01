package am.ik.categolj.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import am.ik.categolj.common.Const;
import am.ik.categolj.service.AuthenticationService;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    @Inject
    protected AuthenticationService authenticationService;

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            Object user = authenticationService.checkLogin();
            if (user != null) {
                modelAndView.addObject(Const.LOGIN_USER_ATTR, user);
            }
        }
    }

}
