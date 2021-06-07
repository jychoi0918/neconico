package com.neconico.neconico.controller.admin.item;

import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.service.admin.item.ItemAdminService;
import com.neconico.neconico.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ItemAdminController {

    private final ItemAdminService itemAdminService;
    private final CategoryService categoryService;

    //아이템 카테고리별 개수 리스트
    @GetMapping("/admin/item/count")
    public String itemListByCategory(Model model) {

        List<CategoryInfoDto> categoryList = categoryService.findCategoryInfoAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("itemListByMainCategory", itemAdminService.countItemByMainCategory());
        model.addAttribute("itemListBySubCategory", itemAdminService.countItemBySubCategory());
        model.addAttribute("countItemCategory",itemAdminService.findItemCategoryInfoAll());

        return "admin/item/item_count";
    }

    //아이템 거래 상태별 리스트
    @GetMapping("/admin/item/status")
    public String itemListByStatus(Model model, String saleStatus) {

        model.addAttribute("itemListOnSale", itemAdminService.countItemBySaleStatus(saleStatus = "판매 중"));
        model.addAttribute("itemListSoldOut", itemAdminService.countItemBySaleStatus(saleStatus = "거래 완료"));

        return "admin/item/item_status";
    }

    //카테고리 추가&수정
    @GetMapping("/admin/item/category")
    public String itemList(Model model, String saleStatus) {

        List<CategoryInfoDto> categoryList = categoryService.findCategoryInfoAll();
        model.addAttribute("categoryList", categoryList);

        return "admin/item/item_category";
    }

    @PostMapping("/admin/item/category/new")
    public String insertMainCategory(@RequestParam(name = "mainName") String mainName) {

        itemAdminService.insertCategoryMain(mainName);

        return "redirect:/admin/item/category";

    }

    @PostMapping("/admin/item/category/update")
    public String updateMainCategory(@RequestParam(name = "mainId") Long mainId,
                                     @RequestParam(name = "mainName") String mainName) {

        itemAdminService.updateMainCategory(mainId, mainName);

        return "redirect:/admin/item/category";
    }

    @PostMapping("/admin/item/subcategory/new")
    public String insertSubCategory(@RequestParam(value = "mainId") String mainId,
                                    @RequestParam(value = "subName") String subName) {

        itemAdminService.insertSubCategory(Long.parseLong(mainId), subName);

        return "redirect:/admin/item/category";
    }

    @PostMapping("/admin/item/subcategory/update")
    public String updateSubCategory(@RequestParam(name = "subId") Long subId,
                                    @RequestParam(name = "subName") String subName) {

        itemAdminService.updateSubCategoryByIdAndName(subId, subName);

        return "redirect:/admin/item/category";
    }

}
