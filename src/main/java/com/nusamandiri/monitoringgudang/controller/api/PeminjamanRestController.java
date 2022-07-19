package com.nusamandiri.monitoringgudang.controller.api;

import com.nusamandiri.monitoringgudang.dto.GrafikDto;
import com.nusamandiri.monitoringgudang.services.PeminjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author galang
 */
@RestController
@RequestMapping("/peminjaman")
public class PeminjamanRestController {

    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping("/countStataus")
    public List<GrafikDto> countGroupByStatus() {
        return peminjamanService.getDataGrafikGroupByStatus();
    }
}
