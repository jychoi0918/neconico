package com.neconico.neconico.mapper.store;

import com.neconico.neconico.dto.store.StoreItemSortingDto;
import com.neconico.neconico.vo.item.ItemCardVo;
import com.neconico.neconico.vo.store.StoreQuestionCardVo;
import com.neconico.neconico.vo.store.StoreReviewCardVo;
import com.neconico.neconico.vo.store.StoreTradeCardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreItemListMapper {

    List<ItemCardVo> selectStoreMyItemList(StoreItemSortingDto storeItemPagingDto);

    List<ItemCardVo> selectStoreFavoriteList(StoreItemSortingDto storeItemPagingDto);

    List<ItemCardVo> selectStoreSoldItemList(StoreItemSortingDto storeItemPagingDto);

    List<ItemCardVo> selectStorePurchasedItemList(StoreItemSortingDto storeItemPagingDto);

    List<StoreQuestionCardVo> selectStoreQuestionList(StoreItemSortingDto storeItemPagingDto);

    List<StoreReviewCardVo> selectStoreReviewList(StoreItemSortingDto storeItemPagingDto);

    List<StoreTradeCardVo> selectStoreTradeList(StoreItemSortingDto storeItemPagingDto);
}
