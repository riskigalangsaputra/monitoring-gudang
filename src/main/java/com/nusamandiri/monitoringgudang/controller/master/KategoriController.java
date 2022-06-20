package com.nusamandiri.monitoringgudang.controller.master;

import com.nusamandiri.monitoringgudang.dto.CommonSearchDto;
import com.nusamandiri.monitoringgudang.entity.Kategori;
import com.nusamandiri.monitoringgudang.services.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author galang
 */
@Controller
@RequestMapping("/master/kategori")
public class KategoriController {

    private static final String LIST = "master/kategori/list";
    private static final String FORM = "master/kategori/form";

    @Autowired
    private KategoriService kategoriService;

    @GetMapping
    public String list(@PageableDefault Pageable pageable, ModelMap mm, CommonSearchDto params) {
        mm.addAttribute("datas", kategoriService.getKategoriPage(params, pageable));
        mm.addAttribute("params", params);
        return LIST;
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) String id, ModelMap mm) {
        Optional<Kategori> kategori = kategoriService.findById(id);
        mm.addAttribute("kategori", kategori.orElseGet(Kategori::new));
        return FORM;
    }

    @PostMapping("/form")
    public String processForm(@Valid Kategori kategori, BindingResult bindingResult, ModelMap mm, RedirectAttributes redir) {

        kategoriService.validateKategori(kategori, bindingResult);
        if (bindingResult.hasErrors()) {
            mm.addAttribute("kategori", kategori);
            return FORM;
        }

        try {
            kategoriService.save(kategori);
            redir.addFlashAttribute("success", "Data telah disimpan");
            return "redirect:/master/kategori";
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("kategori", kategori);
            mm.addAttribute("error", e.getMessage());
            return FORM;
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
        try {
            kategoriService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Data telah dihapus");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/master/kategori";
    }

}
