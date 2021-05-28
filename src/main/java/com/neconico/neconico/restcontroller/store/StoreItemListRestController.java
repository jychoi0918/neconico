package com.neconico.neconico.restcontroller.store;

import com.neconico.neconico.dto.store.StoreItemRequestDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.service.store.StoreItemListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class StoreItemListRestController {

    private final StoreItemListService listService;

    @PostMapping("/store/{userId}/list/myItem")
    public HashMap<String, Object> getItemList(@PathVariable(name = "userId") Long userId, @RequestBody StoreItemRequestDto request) {
        HashMap<String, Object> itemList = listService.createMyItemList(userId, request);
        return itemList;
    }

    @PostMapping("/store/{userId}/list/storeReview")
    public HashMap<String, Object> getStoreReviewLIst(@PathVariable(name = "userId") Long userId, @RequestBody StoreItemRequestDto request) {
        HashMap<String, Object> itemList = listService.createReviewList(userId, request);
        return itemList;
    }
}
