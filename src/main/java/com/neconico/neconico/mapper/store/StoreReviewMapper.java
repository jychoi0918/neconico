package com.neconico.neconico.mapper.store;

import com.neconico.neconico.dto.store.StoreInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreReviewMapper {

    void insertStoreReview(@Param("purchaseId") Long purchaseId, @Param("content") String content);

    void updateStoreReview(@Param("purchaseId") Long purchaseId, @Param("content") String content);

    void deleteStoreReview(@Param("purchaseId") Long purchaseId);
}
