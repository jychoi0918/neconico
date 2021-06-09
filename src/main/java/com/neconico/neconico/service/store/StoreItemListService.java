package com.neconico.neconico.service.store;

import com.neconico.neconico.dto.store.StoreItemRequestDto;

import java.util.Map;

public interface StoreItemListService {

    Map<String, Long> createItemListCount(Long userId);
    Map<String, Object> createMyItemList(Long userId, StoreItemRequestDto request);
    Map<String, Object> createPurchaseItemList(Long userId, StoreItemRequestDto request);
    Map<String, Object> createFavoriteItemList(Long userId, StoreItemRequestDto request);
    Map<String, Object> createTradeList(Long userId, StoreItemRequestDto request);
    Map<String, Object> createQuestionList(Long userId, StoreItemRequestDto request);
    Map<String, Object> createReviewList(Long userId, StoreItemRequestDto request);
}
