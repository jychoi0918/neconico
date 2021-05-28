package com.neconico.neconico.service.store;

import com.neconico.neconico.dto.store.StoreItemRequestDto;
import com.neconico.neconico.dto.store.StoreItemSortingDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.mapper.store.StoreItemListMapper;
import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.dto.store.card.StoreQuestionCardDto;
import com.neconico.neconico.dto.store.card.StoreReviewCardDto;
import com.neconico.neconico.dto.store.card.StoreTradeCardDto;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreItemListService {

    private final StoreItemListMapper listMapper;


    //Paging 크기
    private final int pageSize = 10;
    //아이템 카드 20개 나열
    private final int cardPerPage = 20;
    //아이템 카드 외 10개 나열
    private final int otherCardPerPage = 10;



    public HashMap<String, Long> createItemListCount(Long userId){
        HashMap<String, Long> itemCountMap = new HashMap<>();
        itemCountMap.put("myItemCount", listMapper.countStoreMyItem(userId));
        itemCountMap.put("purchaseItemCount", listMapper.countStorePurchasedItem(userId));
        itemCountMap.put("favoriteItemCount", listMapper.countStoreFavoriteItem(userId));
        itemCountMap.put("tradeCount", listMapper.countStoreTrade(userId));
        itemCountMap.put("questionCount", listMapper.countStoreQuestion(userId));
        itemCountMap.put("storeReviewCount", listMapper.countStoreReview(userId));
        return itemCountMap;
    }

    public HashMap<String, Object> createMyItemList(Long userId, StoreItemRequestDto request){
        Criteria cri = createCri(request, cardPerPage);
        HashMap itemListMap = new HashMap<>();
        itemListMap.put("pagination",
                new Pagination(cri, listMapper.countStoreMyItem(userId).intValue(), pageSize));
        itemListMap.put("itemList", listMapper.selectStoreMyItemList(adaptSortingDto(userId, cri)));
        return itemListMap;
    }

    public HashMap<String, Object> createPurchaseItemList(Long userId, StoreItemRequestDto request){
        Criteria cri = createCri(request, cardPerPage);
        HashMap itemListMap = new HashMap<>();
        itemListMap.put("pagination",
                new Pagination(cri, listMapper.countStorePurchasedItem(userId).intValue(), pageSize));
        itemListMap.put("itemList", listMapper.selectStorePurchasedItemList(adaptSortingDto(userId, cri)));
        return itemListMap;
    }

    public HashMap<String, Object> createFavoriteItemList(Long userId, StoreItemRequestDto request){
        Criteria cri = createCri(request, cardPerPage);
        HashMap itemListMap = new HashMap<>();
        itemListMap.put("pagination",
                new Pagination(cri, listMapper.countStoreFavoriteItem(userId).intValue(), pageSize));
        itemListMap.put("itemList", listMapper.selectStoreFavoriteList(adaptSortingDto(userId, cri)));
        return itemListMap;
    }

    public HashMap<String, Object> createTradeList(Long userId, StoreItemRequestDto request){
        Criteria cri = createCri(request, otherCardPerPage);
        HashMap itemListMap = new HashMap<>();
        itemListMap.put("pagination",
                new Pagination(cri, listMapper.countStoreTrade(userId).intValue(), pageSize));
        itemListMap.put("itemList", listMapper.selectStoreTradeList(adaptSortingDto(userId, cri)));
        return itemListMap;
    }

    public HashMap<String, Object> createQuestionList(Long userId, StoreItemRequestDto request){
        Criteria cri = createCri(request, otherCardPerPage);
        HashMap itemListMap = new HashMap<>();
        itemListMap.put("pagination",
                new Pagination(cri, listMapper.countStoreQuestion(userId).intValue(), pageSize));
        itemListMap.put("itemList", listMapper.selectStoreQuestionList(adaptSortingDto(userId, cri)));
        return itemListMap;
    }

    public HashMap<String, Object> createReviewList(Long userId, StoreItemRequestDto request){
        Criteria cri = createCri(request, otherCardPerPage);
        HashMap itemListMap = new HashMap<>();
        itemListMap.put("pagination",
                new Pagination(cri, listMapper.countStoreReview(userId).intValue(), pageSize));
        itemListMap.put("itemList", listMapper.selectStoreReviewList(adaptSortingDto(userId, cri)));
        return itemListMap;
    }

    public List<ItemCardDto> getStoreMyItemList(Long userId, Criteria cri) {
        StoreItemSortingDto storeItemSortingDto = new StoreItemSortingDto(userId,
                cri.getSortingColumn(),
                cri.getRequestOrder(),
                Long.valueOf(cri.getContentPerPage()),
                Long.valueOf(cri.getContentPerPage()));
        return listMapper.selectStoreMyItemList(storeItemSortingDto);
    }

    private Criteria createCri(StoreItemRequestDto request, int contentPerPage) {
        Criteria cri = new Criteria();
        cri.setCurrentPage(request.getCurrentPage());
        cri.setContentPerPage(contentPerPage);
        cri.setSortingColumn(request.getSortingColumn());
        cri.setRequestOrder(request.getRequestOrder());
        return cri;
    }

    // userId + Criteria -> SortingDto
    private StoreItemSortingDto adaptSortingDto(Long userId, Criteria cri) {
        return new StoreItemSortingDto(userId,
                cri.getSortingColumn(),
                cri.getRequestOrder(),
                Long.valueOf(cri.getPageStart()),
                Long.valueOf(cri.getContentPerPage()));
    }

}
