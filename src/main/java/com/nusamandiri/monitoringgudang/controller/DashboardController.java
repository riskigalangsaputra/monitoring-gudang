package com.nusamandiri.monitoringgudang.controller;

import com.nusamandiri.monitoringgudang.dto.CommonSearchDto;
import com.nusamandiri.monitoringgudang.services.AlatKerjaService;
import com.nusamandiri.monitoringgudang.services.PeminjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author galang
 */
@Controller
public class DashboardController {

    @Autowired
    private PeminjamanService peminjamanService;

    @Autowired
    private AlatKerjaService alatKerjaService;

    @GetMapping("/")
    public String dashboard(@PageableDefault Pageable pageable, ModelMap mm, CommonSearchDto params) {
        mm.addAttribute("datas", peminjamanService.findByLast5Data());
        mm.addAttribute("alatkerjas", alatKerjaService.getAlatKerjaPage(params, pageable));
        return "dashboard";
    }
}
