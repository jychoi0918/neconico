package com.neconico.neconico.controller.item;

import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;
import com.neconico.neconico.service.item.ItemManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ItemManageController {

    private final ItemManageService itemManageService;

    private Long sessionUserId = 7L;

    @GetMapping("/item/manage")
    public String itemManage(Model model,
                             @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                             @RequestParam(value = "sortingColumn", defaultValue = "created") String sortingColumn,
                             @RequestParam(value = "requestOrder", defaultValue = "DESC") String requestOrder) {

        Criteria cri = itemManageService.setCriteria(currentPage, sortingColumn, requestOrder);
        Pagination pagenation = itemManageService.setPagiantion(sessionUserId, cri);

        model.addAttribute("itemList", itemManageService.getStoreMyItemList(sessionUserId, cri));
        model.addAttribute("pagenation", pagenation);

        System.out.println(pagenation.getCriteria().toString());

        return "item/item_manage";
    }
}
