package com.neconico.neconico.controller;

import com.neconico.neconico.dto.admin.advertisement.AdvertReturnDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.dto.item.card.ItemCardSearchViewDto;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.service.admin.advertisement.AdvertiseService;
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

    private final ItemService itemService;
    private final AdvertiseService advertiseService;

    @GetMapping("/")
    public String main(Model model) {
        List<ItemCardSearchViewDto> itemCardSearchViewDtoList = itemService.searchItems(new Criteria(), new SearchInfoDto());

        List<AdvertReturnDto> advertReturnDtoList = advertiseService.selectPublicAdverts();

        model.addAttribute("advertList", advertReturnDtoList);
        model.addAttribute("itemCardList", itemCardSearchViewDtoList);
        return "index";
    }
}