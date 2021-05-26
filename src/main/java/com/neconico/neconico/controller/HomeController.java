package com.neconico.neconico.controller;

import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.dto.item.card.ItemCardViewDto;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.service.category.CategoryService;
import com.neconico.neconico.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    @GetMapping("/")
    public String main(Model model) {
        List<ItemCardViewDto> itemCardViewDtoList = itemService.searchItems(new Criteria(), new SearchInfoDto());
        model.addAttribute("itemCardList", itemCardViewDtoList);
        return "index";
    }
}