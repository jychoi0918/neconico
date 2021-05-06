package com.neconico.neconico.mapper.store;

import com.neconico.neconico.dto.store.StoreInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreInfoMapper {

    StoreInfoDto selectStoreInfoByUser(@Param("userId") Long userId);

    //동일한 StoreName이 있는지 확인용
    boolean selectStoreInfoByName(@Param("storeName") String storeName);

    void insertStoreInfo(StoreInfoDto storeInfoDto);

    void updateStoreInfo(StoreInfoDto storeInfoDto);
}
