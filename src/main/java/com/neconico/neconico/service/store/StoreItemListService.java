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

    List<ItemCardVo> getStoreMyItemList(StoreItemSortingDto storeItemSortingDto){
        return storeItemListMapper.selectStoreMyItemList(storeItemSortingDto);
    }
    List<ItemCardVo> getStoreFavoriteList(StoreItemSortingDto storeItemSortingDto){
        return storeItemListMapper.selectStoreFavoriteList(storeItemSortingDto);
    }
    List<ItemCardVo> getStoreSoldItemList(StoreItemSortingDto storeItemSortingDto){
        return storeItemListMapper.selectStoreSoldItemList(storeItemSortingDto);
    }
    List<StoreQuestionCardVo> getStoreQuestionList(StoreItemSortingDto storeItemSortingDto){
        return storeItemListMapper.selectStoreQuestionList(storeItemSortingDto);
    }
    List<StoreReviewCardVo> getStoreReviewList(StoreItemSortingDto storeItemSortingDto){
        return storeItemListMapper.selectStoreReviewList(storeItemSortingDto);
    }
    List<StoreTradeCardVo> getStoreTradeList(StoreItemSortingDto storeItemSortingDto){
        return storeItemListMapper.selectStoreTradeList(storeItemSortingDto);
    }

}
