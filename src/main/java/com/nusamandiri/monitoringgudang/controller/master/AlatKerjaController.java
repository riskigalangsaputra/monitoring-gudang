package com.nusamandiri.monitoringgudang.controller.master;

import com.nusamandiri.monitoringgudang.dto.CommonSearchDto;
import com.nusamandiri.monitoringgudang.entity.AlatKerja;
import com.nusamandiri.monitoringgudang.entity.AlatKerjaDetail;
import com.nusamandiri.monitoringgudang.services.AlatKerjaService;
import com.nusamandiri.monitoringgudang.services.KategoriService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author galang
 */
@Slf4j
@Controller
@RequestMapping("/master/alat_kerja")
public class AlatKerjaController {

    private static final String LIST = "master/alat_kerja/list";
    private static final String FORM = "master/alat_kerja/form";

    @Autowired
    private AlatKerjaService alatKerjaService;

    @Autowired
    private KategoriService kategoriService;

    @GetMapping
    public String getAll(@PageableDefault Pageable pageable, ModelMap mm, CommonSearchDto params) {
        mm.addAttribute("datas", alatKerjaService.getAlatKerjaPage(params, pageable));
        mm.addAttribute("params", params);
        return LIST;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) String id, ModelMap mm) {
        Optional<AlatKerja> alatKerja = alatKerjaService.findById(id);
        mm.addAttribute("alatKerja", new AlatKerja());
        getKategori(mm);
        return FORM;
    }

    @PostMapping(value = "/form", params = {"addDetail"})
    public String addDetail(AlatKerja alatKerja, ModelMap modelMap) {
        alatKerja.getDetails().add(new AlatKerjaDetail());
        log.info("<< SIZE : {}", alatKerja.getDetails().size());
        modelMap.addAttribute("alatKerja", alatKerja);
        getKategori(modelMap);
        return FORM;
    }

    @PostMapping(value = "/form", params = {"removeDetail"})
    public String removeDetail(@RequestParam String removeDetail, AlatKerja alatKerja, ModelMap modelMap) {
        Integer index = Integer.valueOf(removeDetail);
        alatKerja.getDetails().remove(index.intValue());
        modelMap.addAttribute("alatKerja", alatKerja);
        getKategori(modelMap);
        return FORM;
    }

    private void getKategori(ModelMap mm) {
        mm.addAttribute("kategoris", kategoriService.getKategoriIterable());
    }
}
