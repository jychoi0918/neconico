package com.neconico.neconico.service.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertiseReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseStatusDto;
import com.neconico.neconico.paging.Criteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdvertiseServiceTest {

    @Autowired
    AdvertiseService advertiseService;

    @Test
    @DisplayName("selectTest")
    void selectTest(){

          //given
        List<AdvertiseReturnDto> adList = new ArrayList<>();
        Criteria cri = new Criteria();

          //when
        adList = advertiseService.selectAllAd(cri);


         //then
        assertThat(adList.size()).isEqualTo(10);

    }


    /*@Test
    @DisplayName("insertTest")        
    void insertTest(){
        
          //given
        long before = advertiseService.countTable();


        AdvertiseDto adDto = new AdvertiseDto();
        adDto.setTitle("부드러운 머릿결");
        adDto.setUrl("www.CJY.com");
        adDto.setAdImgUrl("C://");
        adDto.setStartDate(LocalDateTime.of(2021,10,20,0,0));
        adDto.setEndDate(LocalDateTime.of(2021,11,30,0,0));
        adDto.setImgFileName("jy.png");


        //when
        advertiseService.insertAd(adDto);
        long after = advertiseService.countTable();
          
          //then
        assertThat(before).isEqualTo(after-1);
    }*/

    @Test
    @DisplayName("deleteTest")
    void deleteTest(){

          //given
            Long deleteId= 1L;


          //when
            advertiseService.deleteAd(deleteId);

          //then
            assertThat(advertiseService.selectAd(deleteId)).isNull();
    }

    @Test
    @DisplayName("statusUpdateTest")
    void statusUpdateTest(){

          //given
        AdvertiseStatusDto adStatus = new AdvertiseStatusDto();
        adStatus.setAdvertisementId(4l);
        adStatus.setAdStatus("공개");


          //when
          advertiseService.updateStatus(adStatus);

          //then
        assertThat(advertiseService.selectAd(4l).getAdStatus()).isEqualTo("공개");


    }



}