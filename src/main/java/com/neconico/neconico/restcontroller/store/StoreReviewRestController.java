package com.neconico.neconico.restcontroller.store;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.service.store.StoreReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StoreReviewRestController {

    private final StoreReviewService storeReviewService;

    @PostMapping("/mystore/review/{purchasedId}/new")
    public void storeReviewCreate(@PathVariable("purchasedId") Long purchasedId, @RequestParam(name = "content") String content){
        storeReviewService.createStoreReview(purchasedId, content);
    }

    @PostMapping("/mystore/review/{purchasedId}/edit")
    public void storeReviewModify(@PathVariable("purchasedId") Long purchasedId, @RequestParam(name = "content") String content) {
        storeReviewService.updateStoreReview(purchasedId, content);
    }

    @PostMapping("/mystore/review/{purchasedId}/delete")
    public void storeReviewDelete(@PathVariable("purchasedId") Long purchasedId){
        storeReviewService.deleteStoreReview(purchasedId);
    }

}
