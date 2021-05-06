package com.neconico.neconico.service.store;

import com.neconico.neconico.dto.store.StoreInfoDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreInfoServiceTest {

    @Autowired
    StoreInfoService storeInfoService;

    //DB에 테스트용으로 미리 만들어놓은 정보
    StoreInfoDto origin = new StoreInfoDto(1L,
            "storename1",
            "storeimgurl1",
            "storeinfo1",
            "storeimgname1");

    @Test
    @DisplayName("유저 업데이트")
    void updateStoreInfo() {
        //given
        StoreInfoDto change = new StoreInfoDto(origin.getUserId(), null, null,"test", null);

        //when
        storeInfoService.updateStoreInfo(change);
        StoreInfoDto result = storeInfoService.findStoreInfo(origin.getUserId());

        //then

        assertThat(result).usingRecursiveComparison().isNotEqualTo(origin);
    }

    @Test
    @DisplayName("유저 업데이트 동일 이름 체크")
    void updateStoreInfoCheckExistStoreName() {
        //given
        StoreInfoDto change = new StoreInfoDto(origin.getUserId(), origin.getStoreName(), null, null, null);

        //then
        assertThatThrownBy(() -> storeInfoService.updateStoreInfo(change)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Exist same storename");
    }

}