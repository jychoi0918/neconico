package com.neconico.neconico.controller;

import com.neconico.neconico.dto.admin.advertisement.AdvertReturnDto;
import com.neconico.neconico.service.admin.advertisement.AdvertiseService;
import com.neconico.neconico.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;

    private final AdvertiseService advertiseService;

    @GetMapping("/")
    public String main(Model model) {
        List<AdvertReturnDto> adverts = advertiseService.selectPublicAdverts();

        model.addAttribute("adverts", adverts);
        return "index";
    }
}
