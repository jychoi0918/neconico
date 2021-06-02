package com.neconico.neconico.mapper.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertStatusDto;
import com.neconico.neconico.paging.Criteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AdvertiseMapperTest {


    @Autowired
    AdvertiseMapper advertiseMapper;


    @Test
    @DisplayName("select test")
    void selectByPagingTest(){

          //given
        List<AdvertReturnDto> adList = new ArrayList<>();
        Criteria cri = new Criteria();
        cri.setSortingColumn("advertisementId");
        cri.setContentPerPage(10);
        cri.setCurrentPage(1);
        cri.setRequestOrder("desc");

        //when
        adList= advertiseMapper.selectAdverts(cri);

        //then
        assertThat(adList.size()).isEqualTo(10);


    }
    @Test
    @DisplayName("countTest")
    void countTest(){

          //given
        Criteria cri = new Criteria();
        cri.setCurrentPage(1);
        cri.setContentPerPage(10000);
        cri.setSortingColumn("advertisementId");
        cri.setRequestOrder("desc");


        List<AdvertReturnDto> adList = advertiseMapper.selectAdverts(cri);

          //when
        long count = advertiseMapper.countTotalAdverts();

          //then
        assertThat(count).isEqualTo(adList.size());

    }
    @Test
    @DisplayName("insertMapperTest")
    void insertTest(){

          //given

        long countBefore = advertiseMapper.countTotalAdverts();

        AdvertInfoDto ad = new AdvertInfoDto();
        ad.setUserId(5L);
        ad.setTitle("광고");
        ad.setUrl("www.ggomi.is.love");
        ad.setAdImgUrl("C://adImg");
        ad.setImgFileName("광고사진");
        ad.setStartDate("2021-10-20");
        ad.setEndDate("2021-10-20");
        ad.setAdStatus("숨김");

          //when
        advertiseMapper.insertAdvert(ad);

        long countAfter = advertiseMapper.countTotalAdverts();

          //then
          assertThat(countBefore).isEqualTo(countAfter-1);
    }


    @Test
    @DisplayName("selectAdTest")
    void selectAdTest(){

        //given
        Long adId = 4L;

        //when
        AdvertReturnDto adRT = advertiseMapper.selectAdvertByAdvertId(adId);


        //then
        assertThat(adRT.getAdvertisementId()).isEqualTo(adId);

    }


    @Test
    @DisplayName("delete Test")
    void deleteTest(){

          //given
        Long adId = 3L;

          //when
          advertiseMapper.deleteAdvert(adId);

          //then
        assertThat(advertiseMapper.selectAdvertByAdvertId(adId)).isNull();
    }
    @Test
    @DisplayName("updateTest")
    void updateTest(){

          //given
        Long advertiseId = 3L;
        AdvertReturnDto ad = new AdvertReturnDto();
        ad.setAdvertisementId(advertiseId);
        ad.setTitle("광고 수정");
        ad.setUrl("www.gg.is.love");
        ad.setAdImgUrl("C://adImg");
        ad.setImgFileName("광고사진");
        ad.setStartDate("2021-10-20");
        ad.setEndDate("2021-10-20");
        ad.setAdStatus("숨김");

        //when
        advertiseMapper.updateAdvert(ad);


          //then
        assertThat(advertiseMapper.selectAdvertByAdvertId(advertiseId).getTitle()).isEqualTo("광고 수정");
    }



    @Test
    @DisplayName("updateStatus Test")
    void updateStatusTest(){

          //given
        AdvertStatusDto adStatus = new AdvertStatusDto();
        adStatus.setAdvertisementId(4l);
        adStatus.setAdStatus("공개");


        //when
        advertiseMapper.updateStatus(adStatus);

          //then
        assertThat(advertiseMapper.selectAdvertByAdvertId(4l).getAdStatus()).isEqualTo("공개");


    }

    @Test
    @DisplayName("selectMainAdvert Test")
    void selectMainAdvertTest(){

          //given
         String adStatus = "광고중";

          //when
          List<AdvertReturnDto> adlist = advertiseMapper.selectPublicAdverts(adStatus);

          //then
          assertThat(adlist).anyMatch(a -> a.getAdStatus().equals(adStatus));
    }


}