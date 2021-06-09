package com.neconico.neconico.controller;

import com.neconico.neconico.dto.admin.advertisement.AdvertReturnDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.dto.item.card.ItemCardSearchViewDto;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;
import com.neconico.neconico.service.admin.advertisement.AdvertiseService;
import com.neconico.neconico.service.admin.notice.NoticeService;
import com.neconico.neconico.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemService itemService;
    private final AdvertiseService advertiseService;
    private final NoticeService noticeService;

    @GetMapping("/")
    public String main(Model model) {
        List<ItemCardSearchViewDto> itemCardSearchViewDtoList = itemService.searchItems(new Criteria(), new SearchInfoDto());

        List<AdvertReturnDto> advertReturnDtoList = advertiseService.selectPublicAdverts();

        model.addAttribute("advertList", advertReturnDtoList);
        model.addAttribute("itemCardList", itemCardSearchViewDtoList);
        return "index";
    }


    //footer
    @GetMapping("/notice")
    public String noticeList(Model model, Criteria cri) {

        model.addAttribute("notices", noticeService.selectPublicNotices(cri));
        model.addAttribute("pageMaker", new Pagination(cri, noticeService.countPublicNotices(), 5L));

        return "footer/notice_public";

    }

    @GetMapping("/notice/{noticeId}")
    public String noticeView(@PathVariable Long noticeId, Model model) {

        model.addAttribute("notice", noticeService.selectNoticeByNoticeId(noticeId));

        return "footer/notice_public_detail";
    }

    @GetMapping("/adinquiry")
    public String adInquiry() {
        return "footer/ad_inquiry";
    }

    @GetMapping("/request/question")
    public String question() {
        return "footer/question";
    }

    @GetMapping("/policy")
    public String operationPolicy() {
        return "footer/operation_policy";
    }
}