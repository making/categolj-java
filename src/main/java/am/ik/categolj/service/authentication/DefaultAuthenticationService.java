package am.ik.categolj.service.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import am.ik.categolj.entity.User;
import am.ik.categolj.service.AuthenticationService;

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
