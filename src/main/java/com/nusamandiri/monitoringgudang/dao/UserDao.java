package com.nusamandiri.monitoringgudang.dao;

import com.nusamandiri.monitoringgudang.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author galang
 */
public interface UserDao extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndActive(String username, boolean b);

    Optional<User> findByUsername(String email);
}
