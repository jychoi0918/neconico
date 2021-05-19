package com.neconico.neconico.controller;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String main(@LoginUser SessionUser sessionUser) {
        return "index";
    }
}
