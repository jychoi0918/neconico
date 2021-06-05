package com.neconico.neconico.service.admin.notice;

import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeStatusDto;
import com.neconico.neconico.dto.admin.notice.NoticeViewDto;
import com.neconico.neconico.mapper.admin.notice.NoticeMapper;
import com.neconico.neconico.paging.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService {


    private final NoticeMapper noticeMapper;

    @Override
    public List<NoticeViewDto> selectAllNotices(Criteria cri) {
        setCriteria(cri);
        return noticeMapper.selectNotices(cri)
                .stream()
                .map(NoticeViewDto::new)
                .collect(Collectors.toList());
    }


    @Override
    public List<NoticeViewDto> selectPublicNotices(Criteria cri) {
        setCriteria(cri);

        return noticeMapper.selectPublicNotices(cri, NoticeStatus.PUBLIC.getNoticeStatus())
                .stream()
                .map(NoticeViewDto::new)
                .collect(Collectors.toList());
    }


    @Override
    public NoticeViewDto selectNoticeByNoticeId(Long noticeId) {

        return new NoticeViewDto(noticeMapper.selectNoticeByNoticeId(noticeId));
    }


    @Override
    @Transactional
    public void insertNotice(NoticeDto noticeDto) {

        noticeDto.setCreatedDate(LocalDateTime.now());
        noticeDto.setModifiedDate(LocalDateTime.now());
        noticeDto.setNoticeStatus(NoticeStatus.HIDDEN.getNoticeStatus());

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
    @Transactional
    public void deleteNotice(Long noticeId) {
        noticeMapper.deleteNotice(noticeId);
    }


    @Override
    @Transactional
    public void updateNoticeStatus(NoticeStatusDto noticeStatusDto) {
        noticeMapper.updateStatus(noticeStatusDto);
    }

    @Override
    public Long countAllNotices() {
        return noticeMapper.countTotalNotices();
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
