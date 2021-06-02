package com.neconico.neconico.service.admin.notice;

import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeViewDto;
import com.neconico.neconico.paging.Criteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class NoticeServiceTest {

    @Autowired
    NoticeService noticeService;
    private final Logger log = LoggerFactory.getLogger(getClass());


    @Test
    @DisplayName("selectTest")
    void selectTest() throws Exception{

          //given
        Criteria cri = new Criteria();
        List<NoticeViewDto> noticeList = new ArrayList<>();
        log.info("cri={}",cri);


        //when
        noticeList = noticeService.selectAllNotices(cri);
        for (NoticeViewDto notice : noticeList) {
            log.info("notice={}",notice);
        }

          //then
        assertThat(noticeList.size()).isEqualTo(cri.getContentPerPage());
    }
    @Test
    @DisplayName("insertTest")
    void insertTest() throws Exception {

          //given
       long count = noticeService.countAllNotices();

        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setUserId(3L);
        noticeDto.setTitle("I'm so hungry");
        noticeDto.setContent("why does my stomach is so noisy?");



          //when
        noticeService.insertNotice(noticeDto);

        long result = noticeService.countAllNotices();


          //then
        assertThat(count).isEqualTo(result-1);
    }
    @Test
    @DisplayName("deleteTest")
    void deleteTest(){

          //given
          Long noticeId = 3l;


          //when
            noticeService.deleteNotice(noticeId);

          //then
            assertThat(noticeService.selectNoticeByNoticeId(noticeId)).isNull();
    }
    @Test
    @DisplayName("updateTest")
    void updateTest(){

          //given
        Long noticeId =31L;
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setNoticeId(noticeId);
        noticeDto.setTitle("update");
        noticeDto.setContent("update content");



        //when
        noticeService.updateNotice(noticeId,noticeDto);
        NoticeViewDto result = noticeService.selectNoticeByNoticeId(noticeId);


          //then
        assertThat(result.getTitle()).isEqualTo(noticeDto.getTitle());

    }


}