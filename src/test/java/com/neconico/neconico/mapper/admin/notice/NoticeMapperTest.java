package com.neconico.neconico.mapper.admin.notice;

import com.neconico.neconico.dto.admin.notice.NoticeDto;
import com.neconico.neconico.dto.admin.notice.NoticeReturnDto;
import com.neconico.neconico.paging.Criteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class NoticeMapperTest {

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
   NoticeMapper noticeMapper;


    @Test
    @DisplayName("selectByPaging Test")
    void selectByPagingTest() {

        //given
        List<NoticeReturnDto> notices = new ArrayList<>();
        Criteria cri = new Criteria();
        cri.setCurrentPage(2);
        cri.setContentPerPage(10);
        cri.setSortingColumn("noticeId");
        cri.setRequestOrder("desc");
        //when
        notices =  noticeMapper.selectByPaging(cri);
        for (NoticeReturnDto notice : notices) {
            log.info("notice={}",notice);
            
        }

        //then
        assertThat(notices.size()).isEqualTo(10);

    }

    @Test
    @DisplayName("insert 테스트")
    void insertTest() {
        //given
        NoticeDto noticeDto = new NoticeDto();

        //when
        for (int i = 0; i < 20; i++) {
            noticeDto.setUserId(1L + i);
            noticeDto.setTitle("오눙이" + i);
            noticeDto.setContent("오눙도 행복하세요!");
            noticeDto.setCreatedDate(LocalDateTime.now());
            noticeDto.setModifiedDate(LocalDateTime.now());
            noticeMapper.insertNotice(noticeDto);
        }
        //then
        assertThat(noticeMapper.countTable()).isEqualTo(20);



    }


    @Test
    @DisplayName("delete테스트")
    void deleteTest() {

        //given
        Long noticeId = 25L;

        //when
        noticeMapper.deleteNotice(noticeId);
        NoticeReturnDto result = noticeMapper.selectNotice(noticeId);

        //then
        assertThat(result).isNull();
    }


    @Test
    @DisplayName("update 테스트")
    void updateTest() {

        //given
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setNoticeId(25L);
        noticeDto.setTitle("수정한 제목");
        noticeDto.setContent("수정한 내용");
        noticeDto.setModifiedDate(LocalDateTime.now());

        //when
        noticeMapper.updateNotice(noticeDto);
        System.out.println("notice = " + noticeDto);

        //then
        assertThat(noticeMapper.selectNotice(25L).getContent()).isEqualTo("수정한 내용");
    }
    @Test
    @DisplayName("selectAdTest")
    void selectAdTest(){

          //given
          NoticeReturnDto notice = new NoticeReturnDto();

          //when
        notice = noticeMapper.selectNotice(22L);
          log.info("notice={}",notice);

          //then


    }
    @Test
    @DisplayName("")
    void test(){

          //given


          //when


          //then

    }




}

