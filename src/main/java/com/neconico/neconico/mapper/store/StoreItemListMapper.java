package com.neconico.neconico.mapper.store;

import com.neconico.neconico.dto.store.StoreItemSortingDto;
import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.dto.store.card.StoreQuestionCardDto;
import com.neconico.neconico.dto.store.card.StoreReviewCardDto;
import com.neconico.neconico.dto.store.card.StoreTradeCardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreItemListMapper {

    List<ItemCardDto> selectStoreMyItemList(StoreItemSortingDto storeItemPagingDto);

    List<ItemCardDto> selectStoreFavoriteList(StoreItemSortingDto storeItemPagingDto);

    List<ItemCardDto> selectStoreSoldItemList(StoreItemSortingDto storeItemPagingDto);

    List<ItemCardDto> selectStorePurchasedItemList(StoreItemSortingDto storeItemPagingDto);

    List<StoreQuestionCardDto> selectStoreQuestionList(StoreItemSortingDto storeItemPagingDto);

    List<StoreReviewCardDto> selectStoreReviewList(StoreItemSortingDto storeItemPagingDto);

    List<StoreTradeCardDto> selectStoreTradeList(StoreItemSortingDto storeItemPagingDto);
}
