package com.neconico.neconico.mapper.admin.notice;

import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeReturnDto;
import com.neconico.neconico.dto.admin.notice.NoticeStatusDto;
import com.neconico.neconico.dto.admin.notice.NoticeViewDto;
import com.neconico.neconico.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeReturnDto> selectNotices(@Param("cri")Criteria criteria);

    List<NoticeReturnDto> selectPublicNotices(@Param("cri")Criteria criteria, @Param("noticeStatus")String noticeStatus);

    long countTotalNotices(@Param("status")String status);

    NoticeReturnDto selectNoticeByNoticeId(@Param("noticeId") Long noticeId);

    void insertNotice(@Param("noticeDto")NoticeDto noticeDto);

    void deleteNotice(@Param("noticeId")Long noticeId);

    void updateNotice(@Param("noticeDto")NoticeDto noticeDto);

    void updateStatus(NoticeStatusDto noticeStatusDto);


}
