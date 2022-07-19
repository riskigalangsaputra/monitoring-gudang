package com.nusamandiri.monitoringgudang.controller.master;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author galang
 */
@Controller
@RequestMapping("/master/karyawan")
public class KaryawanController {

    private static final String LIST = "master/karyawan/list";
    private static final String FORM = "master/karyawan/form";

    @GetMapping()
    public String list() {
        return LIST;
    }

}
