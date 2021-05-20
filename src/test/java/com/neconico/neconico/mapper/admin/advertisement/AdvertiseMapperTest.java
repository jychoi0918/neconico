package com.neconico.neconico.mapper.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertiseDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseStatusDto;
import com.neconico.neconico.paging.Criteria;
import org.assertj.core.api.Assertions;
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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdvertiseMapperTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    AdvertiseMapper advertiseMapper;


    @Test
    @DisplayName("select test")
    void selectByPagingTest(){

          //given
        List<AdvertiseReturnDto> adList = new ArrayList<>();
        Criteria cri = new Criteria();
        cri.setSortingColumn("advertisementId");
        cri.setContentPerPage(10);
        cri.setCurrentPage(1);
        cri.setRequestOrder("desc");

        //when
        adList= advertiseMapper.selectByPaging(cri);
        for (AdvertiseReturnDto advertiseReturnDto : adList) {
            log.info("Ad DTO={}",advertiseReturnDto);
        }

        //then
        assertThat(adList.size()).isEqualTo(10);


    }
    @Test
    @DisplayName("countTest")
    void countTest(){

          //given
        List<AdvertiseReturnDto> adList = advertiseMapper.selectAll();

          //when
        long count = advertiseMapper.countTable();

          //then
        assertThat(count).isEqualTo(adList.size());

    }
    @Test
    @DisplayName("insertMapperTest")
    void insertTest(){

          //given

        long countBefore = advertiseMapper.countTable();

        AdvertiseDto ad = new AdvertiseDto();
        ad.setUserId(5L);
        ad.setTitle("광고");
        ad.setUrl("www.ggomi.is.love");
        ad.setAdImgUrl("C://adImg");
        ad.setImgFileName("광고사진");
        ad.setStartDate(LocalDateTime.now());
        ad.setEndDate(LocalDateTime.of(2021,10,20,18,0));
        ad.setAdStatus("숨김");

          //when
        advertiseMapper.insertAd(ad);

        long countAfter = advertiseMapper.countTable();

          //then
          assertThat(countBefore).isEqualTo(countAfter-1);
    }


    @Test
    @DisplayName("selectAdTest")
    void selectAdTest(){

        //given
        Long adId = 4L;

        //when
        AdvertiseReturnDto adRT = advertiseMapper.selectAd(adId);
        log.info("log info={}",adRT);


        //then
        assertThat(adRT.getAdvertisementId()).isEqualTo(adId);

    }


    @Test
    @DisplayName("delete Test")
    void deleteTest(){

          //given
        Long adId = 3L;

          //when
          advertiseMapper.deleteAd(adId);

          //then
        assertThat(advertiseMapper.selectAd(adId)).isNull();
    }
    @Test
    @DisplayName("updateTest")
    void updateTest(){

          //given
        Long advertiseId = 3L;
        AdvertiseDto ad = new AdvertiseDto();
        ad.setAdvertisementId(advertiseId);
        ad.setTitle("광고 수정");
        ad.setUrl("www.gg.is.love");
        ad.setAdImgUrl("C://adImg");
        ad.setImgFileName("광고사진");
        ad.setStartDate(LocalDateTime.now());
        ad.setEndDate(LocalDateTime.of(2021,10,20,18,0));
        ad.setAdStatus("숨김");

        //when
        advertiseMapper.updateAd(ad);


          //then
        assertThat(advertiseMapper.selectAd(advertiseId).getTitle()).isEqualTo("광고 수정");
    }



    @Test
    @DisplayName("updateStatus Test")
    void updateStatusTest(){

          //given
        AdvertiseStatusDto adStatus = new AdvertiseStatusDto();
        adStatus.setAdvertisementId(4l);
        adStatus.setAdStatus("공개");


        //when
        advertiseMapper.updateStatus(adStatus);

          //then
        assertThat(advertiseMapper.selectAd(4l).getAdStatus()).isEqualTo("공개");


    }


}