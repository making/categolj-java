package am.ik.categolj.service.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import am.ik.categolj.service.AuthenticationService;
import am.ik.cateoglj.entity.User;

public class DefaultAuthenticationService implements AuthenticationService {
    protected static final Logger logger = LoggerFactory
            .getLogger(DefaultAuthenticationService.class);

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
