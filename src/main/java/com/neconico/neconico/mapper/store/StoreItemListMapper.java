package com.neconico.neconico.mapper.store;

import com.neconico.neconico.dto.item.card.ItemPurchasedCardDto;
import com.neconico.neconico.dto.store.StoreItemSortingDto;
import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.dto.store.card.StoreQuestionCardDto;
import com.neconico.neconico.dto.store.card.StoreReviewCardDto;
import com.neconico.neconico.dto.store.card.StoreTradeCardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoreItemListMapper {

    List<ItemCardDto> selectStoreMyItemList(StoreItemSortingDto storeItemPagingDto);

    Long countStoreMyItem(@Param("userId") Long userId);

    List<ItemCardDto> selectStoreFavoriteList(StoreItemSortingDto storeItemPagingDto);

    Long countStoreFavoriteItem(@Param("userId") Long userId);

    List<ItemCardDto> selectStoreSoldItemList(StoreItemSortingDto storeItemPagingDto);

    Long countStoreSoldItem(@Param("userId") Long userId);

    List<ItemPurchasedCardDto> selectStorePurchasedItemList(StoreItemSortingDto storeItemPagingDto);

    Long countStorePurchasedItem(@Param("userId") Long userId);

    List<StoreQuestionCardDto> selectStoreQuestionList(StoreItemSortingDto storeItemPagingDto);

    Long countStoreQuestion(@Param("userId") Long userId);

    List<StoreReviewCardDto> selectStoreReviewList(StoreItemSortingDto storeItemPagingDto);

    Long countStoreReview(@Param("userId") Long userId);

    List<StoreTradeCardDto> selectStoreTradeList(StoreItemSortingDto storeItemPagingDto);

    Long countStoreTrade(@Param("userId") Long userId);

}
