package com.nusamandiri.monitoringgudang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author galang
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public void index(){}

}
