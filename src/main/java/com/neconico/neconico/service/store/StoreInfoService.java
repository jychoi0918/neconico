package com.neconico.neconico.service.store;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.store.StoreInfoDto;
import com.neconico.neconico.dto.store.card.StoreInfoCardDto;
import com.neconico.neconico.dto.users.SessionUser;

import java.io.IOException;

public interface StoreInfoService {

    StoreInfoCardDto findStoreInfo(SessionUser user);
    void createStoreInfo(StoreInfoDto storeInfoDto);
    void updateStoreInfo(StoreInfoDto storeInfoDto);
    void updateStoreImg(FileResultInfoDto fileResultInfoDto, StoreInfoDto storeInfoDto) throws IOException;
    SessionUser getSessionUserInfoByAccountId(String accountId);
    StoreInfoDto findStoreInfoByUserId(Long userId);

}
