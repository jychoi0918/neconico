package com.neconico.neconico.controller.admin.notice;


import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeStatusDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;
import com.neconico.neconico.service.admin.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class NoticeController {

    private final NoticeService noticeService;


    /* ====================================================================================
    여기 부분은 client 부분입니다.*/
    @GetMapping("/notice/list/client")//리스트 가져오기
    public String clientNoticeList(Model model, Criteria cri) {

        model.addAttribute("notices", noticeService.selectPublicNotices(cri));
        model.addAttribute("pageMaker", new Pagination(cri, noticeService.countAllNotices(), 5));

        return "mainpage/notice_public";
    }

    @GetMapping("/notice/{noticeId}/client")
    public String clientNotice(@PathVariable long noticeId, Model model) {


        model.addAttribute("notice", noticeService.selectNoticeByNoticeId(noticeId));


        return "mainpage/notice_view";

    }


    /* ====================================================================================*/

    @GetMapping("/notice/list")//리스트 가져오기
    public String list(Model model, Criteria cri) {

        model.addAttribute("notices", noticeService.selectAllNotices(cri));
        model.addAttribute("pageMaker", new Pagination(cri, noticeService.countAllNotices(), 5));

        return "admin/notice/notice_list";

    }


    //상세보기
    @GetMapping("/notice/{noticeId}")
    public String notice(@PathVariable long noticeId, Model model) {

        model.addAttribute("notice", noticeService.selectNoticeByNoticeId(noticeId));


        return "admin/notice/notice_detail";

    }


    //등록보기
    @GetMapping("/notice/new")
    public String addForm() {
        return "admin/notice/notice_add";
    }


    //등록하기
    @PostMapping("/notice/new")
    public String addNotice(@ModelAttribute("notice") NoticeDto notice,
                            @LoginUser SessionUser sessionUser) {

        //Login 어떻게 쓰는 거지

        notice.setUserId(sessionUser.getUserId());
        noticeService.insertNotice(notice);

        return "redirect:/admin/notice/list";
    }


    //수정하기 폼
    @GetMapping("/notice/{noticeId}/edit")
    public String editNoticeForm(@PathVariable Long noticeId, Model model) {

        model.addAttribute("notice", noticeService.selectNoticeByNoticeId(noticeId));

        return "admin/notice/notice_edit";
    }


    @PostMapping("/notice/{noticeId}/edit")
    public String editNotice(@PathVariable Long noticeId, @ModelAttribute NoticeDto noticeDto) {

        noticeService.updateNotice(noticeId, noticeDto);

        return "redirect:/admin/notice/{noticeId}";
    }


    @PostMapping("/notice/{noticeId}/delete")
    public String delete(@PathVariable Long noticeId) {

        noticeService.deleteNotice(noticeId);

        return "redirect:/admin/notice/list";
    }


    @PutMapping("/notices/status/edit")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void changeStatus(@RequestBody NoticeStatusDto noticeStatusDto) {

        noticeService.updateNoticeStatus(noticeStatusDto);
    }
}




