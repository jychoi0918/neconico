package com.neconico.neconico.service.admin.notice;

import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeStatusDto;
import com.neconico.neconico.dto.admin.notice.NoticeViewDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;

public interface NoticeService {



    List<NoticeViewDto> selectPublicNotices(Criteria criteria);

    List<NoticeViewDto> selectAllNotices(Criteria criteria);

    Long countAllNotices();

    Long countPublicNotices();

    void insertNotice(NoticeDto noticeDto);

    void deleteNotice(Long noticeId);

    void updateNotice(Long noticeId, NoticeDto noticeDto);

    NoticeViewDto selectNoticeByNoticeId(Long noticeId);

    void updateNoticeStatus(NoticeStatusDto noticeStatusDto);


}
