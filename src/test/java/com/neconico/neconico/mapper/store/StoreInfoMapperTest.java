package com.neconico.neconico.mapper.store;

import com.neconico.neconico.dto.store.StoreInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class StoreInfoMapperTest {

    @Autowired
    StoreInfoMapper storeInfoMapper;

    //미리 DB에 저장되어있는 테스트용 데이터
    Long userId = 1L;
    String storeName = "storename1";
    String storeImgUrl = "storeimgurl1";
    String storeInfo = "storeinfo1";
    String storeImgName = "storeimgname1";

    //test를 위한 데이터
    Long testId = 2L;
    String testName = "storename2";
    String testImgUrl = "storeimgurl2";
    String testInfo = "storeinfo2";
    String testImgName = "storeimgname2";

    @Test
    @DisplayName("상점 정보 조회")
    void selectStoreInfoByUser() {
        //given
        StoreInfoDto origin = new StoreInfoDto(userId, storeName, storeImgUrl, storeInfo, storeImgName);

        //when
        StoreInfoDto result = storeInfoMapper.selectStoreInfoByUser(userId);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(origin);

    }

    @Test
    @DisplayName("동일 상점 이름 존재 유무 확인")
    void selectStoreInfoByName() {
        boolean result = storeInfoMapper.selectStoreInfoByName(storeName);
        assertThat(result).isTrue();
    }



    @Test
    @DisplayName("상점 정보 삽입")
    void insertStoreInfo() {
        //given
        StoreInfoDto init = new StoreInfoDto(testId, testName, testImgUrl, testInfo, testImgName);

        //when
        storeInfoMapper.insertStoreInfo(init);
        StoreInfoDto result = storeInfoMapper.selectStoreInfoByUser(testId);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(init);
    }

    @Test
    @DisplayName("상점 정보 업데이트")
    void updateStoreInfo() {
        //given
        StoreInfoDto origin = storeInfoMapper.selectStoreInfoByUser(userId);
        StoreInfoDto change = new StoreInfoDto(userId, testName, testImgUrl, testInfo, testImgName);

        //when
        storeInfoMapper.updateStoreInfo(change);
        StoreInfoDto result = storeInfoMapper.selectStoreInfoByUser(userId);

        //then
        assertThat(result).usingRecursiveComparison().isNotEqualTo(origin);
        assertThat(result).usingRecursiveComparison().isEqualTo(change);
    }

    @Test
    @DisplayName("상점 정보 업데이트 Null 처리")
    void updateStoreInfoNullCheck() {
        //given
        StoreInfoDto change = new StoreInfoDto(userId, testName, testImgUrl, null, testImgName);
        storeInfoMapper.updateStoreInfo(change);

        //when
        StoreInfoDto result = storeInfoMapper.selectStoreInfoByUser(userId);
        assertThat(result.getStoreInfo()).isEqualTo(storeInfo);

    }



}