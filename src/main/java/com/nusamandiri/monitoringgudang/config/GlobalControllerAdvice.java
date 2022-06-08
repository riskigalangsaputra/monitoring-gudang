package com.nusamandiri.monitoringgudang.config;

import com.nusamandiri.monitoringgudang.dao.UserDao;
import com.nusamandiri.monitoringgudang.entity.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

/**
 * @author galang
 */
@ControllerAdvice(basePackages = {"com.nusamandiri.monitoringgudang.controller"})
public class GlobalControllerAdvice {

    @Autowired
    private UserDao userDao;

    @ModelAttribute
    public void globalAttributes(Model model, Principal principal, Authentication authentication, HttpSession session) {
        if (principal != null) {
            Optional<User> user = userDao.findByUsername(principal.getName());
            model.addAttribute("userLoggedIn", user.get());
        }
    }
}
