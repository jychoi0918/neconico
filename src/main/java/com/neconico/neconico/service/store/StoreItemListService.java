package com.neconico.neconico.service.store;

import com.neconico.neconico.dto.store.StoreItemSortingDto;
import com.neconico.neconico.mapper.store.StoreItemListMapper;
import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.dto.store.card.StoreQuestionCardDto;
import com.neconico.neconico.dto.store.card.StoreReviewCardDto;
import com.neconico.neconico.dto.store.card.StoreTradeCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreItemListService {

    private final StoreItemListMapper storeItemListMapper;

    public List<ItemCardDto> getStoreMyItemList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreMyItemList(storeItemSortingDto);
    }

    public List<ItemCardDto> getStoreFavoriteList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreFavoriteList(storeItemSortingDto);
    }

    public List<ItemCardDto> getStoreSoldItemList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreSoldItemList(storeItemSortingDto);
    }

    public List<ItemCardDto> getStorePurchasedItemList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStorePurchasedItemList(storeItemSortingDto);
    }

    public List<StoreQuestionCardDto> getStoreQuestionList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreQuestionList(storeItemSortingDto);
    }

    public List<StoreReviewCardDto> getStoreReviewList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreReviewList(storeItemSortingDto);
    }

    public List<StoreTradeCardDto> getStoreTradeList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreTradeList(storeItemSortingDto);
    }

}
