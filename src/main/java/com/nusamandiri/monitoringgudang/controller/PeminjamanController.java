package com.nusamandiri.monitoringgudang.controller;

import com.nusamandiri.monitoringgudang.dto.*;
import com.nusamandiri.monitoringgudang.entity.Peminjaman;
import com.nusamandiri.monitoringgudang.entity.PeminjamanDetail;
import com.nusamandiri.monitoringgudang.services.PeminjamanService;
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

import java.security.Principal;

/**
 * @author galang
 */
@Controller
@RequestMapping("/peminjaman")
public class PeminjamanController {

    private static final String LIST = "peminjaman/list";
    private static final String FORM = "peminjaman/form";
    private static final String TOLAK = "peminjaman/tolak";
    private static final String ON_GOING = "peminjaman/on_going";
    private static final String DONE = "peminjaman/done";
    private static final String DETAIL = "peminjaman/detail";

    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping
    public String list(@PageableDefault Pageable pageable, ModelMap mm, CommonSearchDto params, Principal principal) {
        mm.addAttribute("datas", peminjamanService.getPeminjamanPage(params, pageable, principal.getName()));
        mm.addAttribute("params", params);
        return LIST;
    }

    @GetMapping("/form")
    public String form(ModelMap modelMap) {
        modelMap.addAttribute("peminjamanDto", new PeminjamanDto());
        return FORM;
    }

    @GetMapping("/detail")
    public String detail(@RequestParam String id, ModelMap modelMap) {
        modelMap.addAttribute("peminjaman", peminjamanService.getPeminjamanById(id));
        return DETAIL;
    }

    @GetMapping("/tolak")
    public String tolak(@RequestParam String id, ModelMap modelMap) {
        modelMap.addAttribute("tolakDto", peminjamanService.setTolakDto(id));
        return TOLAK;
    }

    @PostMapping("/tolak")
    public String tolakProses(TolakDto tolakDto, BindingResult bindingResult, ModelMap mm, RedirectAttributes redir) {
        if (bindingResult.hasErrors()) {
            mm.addAttribute("tolakDto", tolakDto);
            return TOLAK;
        }

        try {
            peminjamanService.rejected(tolakDto);
            redir.addFlashAttribute("success", "Pengajuan berhasil di tolak");
            return "redirect:/peminjaman";
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("tolakDto", tolakDto);
            mm.addAttribute("error", e.getMessage());
            return TOLAK;
        }
    }

    @PostMapping(value = "/form", params = {"addDetail"})
    public String addDetail(PeminjamanDto peminjamanDto, ModelMap modelMap) {
        peminjamanDto.getDetails().add(new PeminjamanDetail());
        modelMap.addAttribute("peminjamanDto", peminjamanDto);
        return FORM;
    }

    @PostMapping(value = "/form", params = {"removeDetail"})
    public String removeDetail(@RequestParam String removeDetail, PeminjamanDto peminjamanDto, ModelMap modelMap) {
        Integer index = Integer.valueOf(removeDetail);
        peminjamanDto.getDetails().remove(index.intValue());
        modelMap.addAttribute("peminjamanDto", peminjamanDto);
        return FORM;
    }

    @PostMapping("/form")
    public String save(PeminjamanDto peminjamanDto, BindingResult bindingResult, ModelMap mm, RedirectAttributes redir, Principal principal) {
        if (bindingResult.hasErrors()) {
            mm.addAttribute("peminjamanDto", peminjamanDto);
            return FORM;
        }

        try {
            peminjamanService.createPeminjaman(peminjamanDto, principal.getName());
            redir.addFlashAttribute("success", "Permintaan berhasil diajukan");
            return "redirect:/peminjaman/form";
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("peminjamanDto", peminjamanDto);
            mm.addAttribute("error", e.getMessage());
            return FORM;
        }
    }

    @GetMapping("/approve")
    public String approve(@RequestParam String id, ModelMap mm, RedirectAttributes redir) {
        try {
            peminjamanService.approve(id);
            redir.addFlashAttribute("success", "Permintaan berhasil disetujui");
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("error", e.getMessage());
        }
        return "redirect:/peminjaman";
    }

    @GetMapping("/on_going")
    public String onGoing(@RequestParam String id, ModelMap mm) {
        mm.addAttribute("onGoingDto", peminjamanService.setOnGoingDto(id));
        return ON_GOING;
    }

    @PostMapping("/on_going")
    public String onGoingSave(OnGoingDto onGoingDto, BindingResult bindingResult, ModelMap mm, RedirectAttributes redir, Principal principal) {
        if (bindingResult.hasErrors()) {
            mm.addAttribute("onGoingDto", onGoingDto);
            return ON_GOING;
        }

        try {
            peminjamanService.onGoingProsess(onGoingDto);
            redir.addFlashAttribute("success", "Peminjaman sedang berlangsung");
            return "redirect:/peminjaman";
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("onGoingDto", onGoingDto);
            mm.addAttribute("error", e.getMessage());
            return ON_GOING;
        }
    }

    @GetMapping("/done")
    public String done(@RequestParam String id, ModelMap mm) {
        mm.addAttribute("doneDto", peminjamanService.setDoneDto(id));
        return DONE;
    }

    @PostMapping("/done")
    public String doneSave(DoneDto doneDto, BindingResult bindingResult, ModelMap mm, RedirectAttributes redir, Principal principal) {
        if (bindingResult.hasErrors()) {
            mm.addAttribute("doneDto", doneDto);
            return DONE;
        }

        try {
            peminjamanService.doneProsess(doneDto);
            redir.addFlashAttribute("success", "Peminjaman selesai");
            return "redirect:/peminjaman";
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("doneDto", doneDto);
            mm.addAttribute("error", e.getMessage());
            return DONE;
        }
    }
}
