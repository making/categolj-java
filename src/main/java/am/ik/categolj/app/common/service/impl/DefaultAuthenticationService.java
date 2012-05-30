package am.ik.categolj.app.common.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import am.ik.categolj.app.common.domain.User;
import am.ik.categolj.app.common.service.AuthenticationService;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    @Override
    public User checkLogin() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        User user = null;
        if (auth != null && !"anonymousUser".equals(auth.getName())) {
            user = new User();
            user.setName(auth.getName());
        }
        return user;
    }
}
