package com.neconico.neconico.maker;

import com.neconico.neconico.dto.store.StoreInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreInfoMaker {

    public static StoreInfoDto createStoreInfoDtoByUserId(Long userId) {
        return new StoreInfoDto(userId, createStoreName(userId), "", "", "");
    }

    private static String createStoreName(Long userId) {
        return userId + "사용자" + UUID.randomUUID().toString().substring(0, 6);
    }


}
