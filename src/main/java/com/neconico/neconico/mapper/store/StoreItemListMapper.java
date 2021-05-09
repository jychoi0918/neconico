package com.neconico.neconico.mapper.store;

import com.neconico.neconico.dto.store.StoreItemPagingDto;
import com.neconico.neconico.vo.item.ItemCardVo;
import com.neconico.neconico.vo.store.StoreQuestionCardVo;
import com.neconico.neconico.vo.store.StoreReviewCardVo;
import com.neconico.neconico.vo.store.StoreTradeCardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreItemListMapper {

    List<ItemCardVo> selectStoreItemList(StoreItemPagingDto storeItemPagingDto);

    List<ItemCardVo> selectStoreFavoriteList(StoreItemPagingDto storeItemPagingDto);

    List<ItemCardVo> selectStoreSoldItemList(StoreItemPagingDto storeItemPagingDto);

    List<StoreQuestionCardVo> selectStoreQuestionList(StoreItemPagingDto storeItemPagingDto);

    List<StoreReviewCardVo> selectStoreReviewList(StoreItemPagingDto storeItemPagingDto);

    List<StoreTradeCardVo> selectStoreTradeList(StoreItemPagingDto storeItemPagingDto);
}
