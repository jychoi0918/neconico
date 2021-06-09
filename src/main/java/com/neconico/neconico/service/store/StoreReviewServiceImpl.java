package com.neconico.neconico.service.store;

import com.neconico.neconico.mapper.store.StoreReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreReviewServiceImpl implements StoreReviewService{

    private final StoreReviewMapper storeReviewMapper;

    @Override
    public void createStoreReview(Long purchasedId, String content) {
        storeReviewMapper.insertStoreReview(purchasedId, content);
    }

    @Override
    public void updateStoreReview(Long purchasedId, String content) {
        storeReviewMapper.updateStoreReview(purchasedId, content);
    }

    @Override
    public void deleteStoreReview(Long purchasedId) {
        storeReviewMapper.deleteStoreReview(purchasedId);
    }
}
