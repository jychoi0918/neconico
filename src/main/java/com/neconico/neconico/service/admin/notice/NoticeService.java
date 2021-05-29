package com.neconico.neconico.service.admin.notice;

import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeReturnDto;
import com.neconico.neconico.dto.admin.notice.NoticeStatusDto;
import com.neconico.neconico.dto.admin.notice.NoticeViewDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;

public interface NoticeService {



    //insert
    void insertNotice(NoticeDto noticeDto);

    //count => 페이징 할때 필요할 수도!
    long countTable();

    //delete
    void deleteNotice(Long noticeId);

    //update
    void updateNotice(Long noticeId,NoticeDto noticeDto);

    //selectByPaging
    List<NoticeViewDto> selectAllNotices(Criteria criteria);

    //select one Notice
    NoticeViewDto selectNotice(Long noticeId);

    //update noticeStatus
    void updateNoticeStatus(NoticeStatusDto noticeStatusDto);

    List<NoticeViewDto> selectNoticing(Criteria criteria);
}
