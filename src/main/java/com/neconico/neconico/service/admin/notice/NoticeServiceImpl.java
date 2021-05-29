package com.neconico.neconico.service.admin.notice;

import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeReturnDto;
import com.neconico.neconico.dto.admin.notice.NoticeStatusDto;
import com.neconico.neconico.dto.admin.notice.NoticeViewDto;
import com.neconico.neconico.mapper.admin.notice.NoticeMapper;
import com.neconico.neconico.paging.Criteria;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final NoticeMapper noticeMapper;

    @Override
    @Transactional
    public void insertNotice(NoticeDto noticeDto) {
        if (noticeDto.getTitle().replaceAll(" ", "") == null
                || noticeDto.getContent().replaceAll(" ", "") == null)
            throw new NullPointerException("Please fill up the blank");


        noticeDto.setCreatedDate(LocalDateTime.now());
        noticeDto.setModifiedDate(LocalDateTime.now());
        noticeDto.setNoticeStatus("숨김");

        noticeMapper.insertNotice(noticeDto);
    }


    @Override
    @Transactional
    public void updateNotice(Long noticeId, NoticeDto noticeDto) {

        noticeDto.setNoticeId(noticeId);
        noticeDto.setModifiedDate(LocalDateTime.now());
        noticeMapper.updateNotice(noticeDto);
    }

    @Override
    public List<NoticeViewDto> selectAllNotices(Criteria cri) {
        setCriteria(cri);
        return noticeMapper.selectByPaging(cri)
                .stream()
                .map(NoticeViewDto::new)
                .collect(Collectors.toList());
    }


    @Override
    public List<NoticeViewDto> selectNoticing(Criteria cri) {
        setCriteria(cri);
        String noticeStatus = "공개";


        return noticeMapper.selectByPaging(cri)
                .stream()
                .map(NoticeViewDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteNotice(Long noticeId) {
        noticeMapper.deleteNotice(noticeId);
    }


    @Override
    public NoticeViewDto selectNotice(Long noticeId) {

        return new NoticeViewDto(noticeMapper.selectNotice(noticeId));
    }

    @Override
    @Transactional
    public void updateNoticeStatus(NoticeStatusDto noticeStatusDto) {
        noticeMapper.updateStatus(noticeStatusDto);
    }

    @Override
    public long countTable() {
        return noticeMapper.countTable();
    }


    private Criteria setCriteria(Criteria cri) {
        cri.setSortingColumn("noticeId");
        cri.setRequestOrder("desc");
        cri.setContentPerPage(10);

        if (cri.getCurrentPage() <= 0)
            cri.setCurrentPage(1);

        return cri;
    }



}
