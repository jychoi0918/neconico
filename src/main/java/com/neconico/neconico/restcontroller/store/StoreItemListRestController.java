package com.neconico.neconico.restcontroller.store;

import com.neconico.neconico.dto.store.StoreItemRequestDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.service.store.StoreInfoService;
import com.neconico.neconico.service.store.StoreItemListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class StoreItemListRestController {

    private final StoreItemListService listService;
    private final StoreInfoService infoService;

    @PostMapping("/store/{accountId}/list/myItem")
    public Map<String, Object> getItemList(@PathVariable(name = "accountId") String accountId, @RequestBody StoreItemRequestDto request) {
        SessionUser user = infoService.getSessionUserInfoByAccountId(accountId);
        Map<String, Object> itemList = listService.createMyItemList(user.getUserId(), request);
        return itemList;
    }

    @PostMapping("/store/{accountId}/list/storeReview")
    public Map<String, Object> getStoreReviewLIst(@PathVariable(name = "accountId") String accountId, @RequestBody StoreItemRequestDto request) {
        SessionUser user = infoService.getSessionUserInfoByAccountId(accountId);

        Map<String, Object> itemList = listService.createReviewList(user.getUserId(), request);
        return itemList;
    }
}
