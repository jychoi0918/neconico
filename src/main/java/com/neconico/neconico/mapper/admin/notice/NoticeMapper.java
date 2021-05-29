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

    //select
    List<NoticeReturnDto> selectByPaging(@Param("cri")Criteria criteria);

    List<NoticeReturnDto> selectNoticing(@Param("cri")Criteria criteria, @Param("noticeStatus")String noticeStatus);

    //count
    public long countTable();

    //select one notice
    public NoticeReturnDto selectNotice(@Param("noticeId") Long noticeId);

    //insert
    void insertNotice(@Param("noticeDto")NoticeDto noticeDto);

    //delete
    public void deleteNotice(@Param("noticeId")Long noticeId);

    //update
    public void updateNotice(@Param("noticeDto")NoticeDto noticeDto);

    //statusUpdate
    public void updateStatus(NoticeStatusDto noticeStatusDto);


}
