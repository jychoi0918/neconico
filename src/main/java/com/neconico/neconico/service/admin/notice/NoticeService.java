package com.neconico.neconico.service.admin.notice;

import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeReturnDto;
import com.neconico.neconico.dto.admin.notice.NoticeStatusDto;
import com.neconico.neconico.dto.admin.notice.NoticeViewDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;

public interface NoticeService {



    void insertNotice(NoticeDto noticeDto);

    Long countAllNotices();

    List<NoticeViewDto> selectAllNotices(Criteria criteria);

    void deleteNotice(Long noticeId);

    void updateNotice(Long noticeId,NoticeDto noticeDto);

    NoticeViewDto selectNoticeByNoticeId(Long noticeId);

    void updateNoticeStatus(NoticeStatusDto noticeStatusDto);

    List<NoticeViewDto> selectPublicNotices(Criteria criteria);
}
