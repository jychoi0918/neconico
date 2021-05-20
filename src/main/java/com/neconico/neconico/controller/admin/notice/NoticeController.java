package com.neconico.neconico.controller.admin.notice;


import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeReturnDto;
import com.neconico.neconico.dto.admin.notice.NoticeStatusDto;
import com.neconico.neconico.dto.admin.notice.NoticeViewDto;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;
import com.neconico.neconico.service.admin.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class NoticeController {

    private final NoticeService noticeService;
    private Long sessionUserId = 50L;





    @GetMapping("/notices")//리스트 가져오기
    public String list(Model model, Criteria cri) {

        model.addAttribute("notices", noticeService.selectAllNotices(cri));
        model.addAttribute("pageMaker", new Pagination(cri, noticeService.countTable(), 3));

        return "admin/notice/notices";
    }





    //상세보기
    @GetMapping("/notice/{noticeId}")
    public String notice(@PathVariable long noticeId, Model model) {

        NoticeViewDto NoticeViewDto = noticeService.selectNotice(noticeId);
        model.addAttribute("notice", NoticeViewDto);


        return "admin/notice/notice";

    }



    //등록보기
    @GetMapping("/notice/add")
    public String addForm() {
        return "admin/notice/noticeAdd";
    }






    //등록하기
    @PostMapping("/notice/add")
    public String addNotice(@ModelAttribute("notice") NoticeDto notice) {

        //Login 어떻게 쓰는 거지
      /* SessionUser sessionUser,
       notice.setUserId(sessionUser.getUserId());*/
        notice.setUserId(sessionUserId);
        noticeService.insertNotice(notice);
        
        return "redirect:/admin/notices";
    }





    //수정하기 폼
    @GetMapping("/notice/{noticeId}/edit")
    public String editNoticeForm(@PathVariable Long noticeId, Model model) {

        NoticeViewDto NoticeViewDto = noticeService.selectNotice(noticeId);
        model.addAttribute("notice", NoticeViewDto);

        return "admin/notice/noticeEdit";
    }





    @PostMapping("/notice/{noticeId}/edit")
    public String editNotice(@PathVariable Long noticeId, @ModelAttribute NoticeDto noticeDto) {

        noticeService.updateNotice(noticeId,noticeDto);

        return "redirect:/admin/notice/{noticeId}";
    }





    @PostMapping("/notice/{noticeId}/delete")
    public String delete(@PathVariable Long noticeId){

        noticeService.deleteNotice(noticeId);

        return "redirect:/admin/notices";
    }




    @PutMapping("/notices/status/edit")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void changeStatus(@RequestBody NoticeStatusDto noticeStatusDto) {

        noticeService.updateNoticeStatus(noticeStatusDto);
    }
}




