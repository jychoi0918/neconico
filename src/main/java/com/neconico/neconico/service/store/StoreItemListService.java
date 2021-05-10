package com.neconico.neconico.service.store;

import com.neconico.neconico.dto.store.StoreItemSortingDto;
import com.neconico.neconico.mapper.store.StoreItemListMapper;
import com.neconico.neconico.vo.item.ItemCardVo;
import com.neconico.neconico.vo.store.StoreQuestionCardVo;
import com.neconico.neconico.vo.store.StoreReviewCardVo;
import com.neconico.neconico.vo.store.StoreTradeCardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreItemListService {

    private final StoreItemListMapper storeItemListMapper;

    public List<ItemCardVo> getStoreMyItemList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreMyItemList(storeItemSortingDto);
    }

    public List<ItemCardVo> getStoreFavoriteList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreFavoriteList(storeItemSortingDto);
    }

    public List<ItemCardVo> getStoreSoldItemList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreSoldItemList(storeItemSortingDto);
    }

    public List<ItemCardVo> getStorePurchasedItemList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStorePurchasedItemList(storeItemSortingDto);
    }

    public List<StoreQuestionCardVo> getStoreQuestionList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreQuestionList(storeItemSortingDto);
    }

    public List<StoreReviewCardVo> getStoreReviewList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreReviewList(storeItemSortingDto);
    }

    public List<StoreTradeCardVo> getStoreTradeList(StoreItemSortingDto storeItemSortingDto) {
        return storeItemListMapper.selectStoreTradeList(storeItemSortingDto);
    }

}
