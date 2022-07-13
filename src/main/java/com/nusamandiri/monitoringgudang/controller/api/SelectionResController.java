package com.nusamandiri.monitoringgudang.controller.api;

import com.nusamandiri.monitoringgudang.dto.CommonSearchDto;
import com.nusamandiri.monitoringgudang.dto.selections.Select2Dto;
import com.nusamandiri.monitoringgudang.services.AlatKerjaService;
import com.nusamandiri.monitoringgudang.services.KategoriService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author galang
 */
@Slf4j
@RestController
@RequestMapping("/api/selection")
public class SelectionResController {

    @Autowired private KategoriService kategoriService;
    @Autowired private AlatKerjaService alatKerjaService;

    @GetMapping("/kategori")
    public Select2Dto findKategori(
            CommonSearchDto params,
            @PageableDefault Pageable pageable) {
        try {
            return new Select2Dto(kategoriService.getKategoriPage(params, substractOnePage(pageable)));
        } catch (Exception e) {
            log.error("[SELECT2-API-ERROR] KATEGORI : {}", e.getMessage(), e);
        }
        return null;
    }

    @GetMapping("/alatkerja")
    public Select2Dto findKota(
            CommonSearchDto params,
            @PageableDefault Pageable pageable) {
        try {
            return new Select2Dto(alatKerjaService.getAlatKerjaPage(params, substractOnePage(pageable)));
        } catch (Exception e) {
            log.error("[SELECT2-API-ERROR] ALAT KERJA : {}", e.getMessage(), e);
        }
        return null;
    }

    public PageRequest substractOnePage(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
    }
}
