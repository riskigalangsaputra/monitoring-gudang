package com.nusamandiri.monitoringgudang.services;

import com.nusamandiri.monitoringgudang.dao.UserDao;
import com.nusamandiri.monitoringgudang.entity.security.Permission;
import com.nusamandiri.monitoringgudang.entity.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author galang
 */
@Transactional
@Service("customSerDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    public CustomUserDetailsService() {
        super();
    }

    private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        try {
            Optional<User> user = userDao.findByUsernameAndActive(username, true);
            if (!user.isPresent()) {
                logger.error("Unable To Find User with username / Account inactive or Blocked : [{}]", username);
                throw new DisabledException("inactive Account");
            }

            return new org.springframework.security.core.userdetails.User(
                    user.get().getUsername(),
                    user.get().getUserPassword().getPassword(),
                    user.get().getActive(), true, true, true, getAuthorities(user.get()));

        } catch (final Exception e) {
            throw e;
        }
    }

    private final Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return getGrantedAuthorities(user);
    }

    private final List<GrantedAuthority> getGrantedAuthorities(User user) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Permission permission : user.getRole().getPermissions()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermissionValue());
            authorities.add(authority);
        }
        return authorities;
    }
}
