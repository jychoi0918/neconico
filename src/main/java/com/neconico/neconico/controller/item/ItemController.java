package com.neconico.neconico.controller.item;

import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.mapper.item.ItemMapper;
import com.neconico.neconico.service.category.CategoryService;
import com.neconico.neconico.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final CategoryService categoryService;

    @GetMapping("/item/new")
    public String createItem(Model model) {
        List<CategoryInfoDto> categoryInfoAll = categoryService.findCategoryInfoAll();
        model.addAttribute("itemFrom", new ItemInfoDto());
        model.addAttribute("categoryList", categoryInfoAll);
        return "item/new_item";
    }
}
