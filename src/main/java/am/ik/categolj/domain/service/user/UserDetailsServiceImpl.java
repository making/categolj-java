package am.ik.categolj.domain.service.user;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import am.ik.categolj.domain.model.User;
import am.ik.categolj.domain.repository.user.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Inject
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        final User user = userRepository.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " is not found");
        }
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), auths);
    }

}
