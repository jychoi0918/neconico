package com.neconico.neconico.restcontroller.store;

import com.neconico.neconico.dto.store.StoreItemRequestDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.service.store.StoreItemListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class MyStoreItemListRestController {

    private final StoreItemListService listService;

    private SessionUser user = new SessionUser();

    @PostMapping("/mystore/list/myItem")
    public HashMap<String, Object> getMyItemList(@RequestBody StoreItemRequestDto request) {
        user.setUserId(3L);
        HashMap<String, Object> itemList = listService.createMyItemList(user.getUserId(), request);
        return itemList;
    }

    @PostMapping("/mystore/list/purchasedItem")
    public HashMap<String, Object> getPurchasedItemList(@RequestBody StoreItemRequestDto request) {
        HashMap<String, Object> itemList = listService.createPurchaseItemList(user.getUserId(), request);
        return itemList;
    }

    @PostMapping("/mystore/list/favoriteItem")
    public HashMap<String, Object> getFavoriteItemList(@RequestBody StoreItemRequestDto request) {
        HashMap<String, Object> itemList = listService.createFavoriteItemList(user.getUserId(), request);
        return itemList;
    }

    @PostMapping("/mystore/list/tradeItem")
    public HashMap<String, Object> getTradeItemList(@RequestBody StoreItemRequestDto request) {
        HashMap<String, Object> itemList = listService.createTradeList(user.getUserId(), request);
        return itemList;
    }

    @PostMapping("/mystore/list/questionItem")
    public HashMap<String, Object> getQuestionItemList(@RequestBody StoreItemRequestDto request) {
        HashMap<String, Object> itemList = listService.createQuestionList(user.getUserId(), request);
        return itemList;
    }


    @PostMapping("/mystore/list/storeReview")
    public HashMap<String, Object> getStoreReviewLIst(@RequestBody StoreItemRequestDto request) {
        user.setUserId(7L);
        HashMap<String, Object> itemList = listService.createReviewList(user.getUserId(), request);
        return itemList;
    }
}
