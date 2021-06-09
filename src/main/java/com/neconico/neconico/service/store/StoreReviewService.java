package com.neconico.neconico.service.store;

public interface StoreReviewService {

    void createStoreReview(Long purchasedId, String content);
    void updateStoreReview(Long purchasedId, String content);
    void deleteStoreReview(Long purchasedId);

}
