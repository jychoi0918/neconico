package com.neconico.neconico.service.item;

public interface ItemTradeService {

    void requestTrade(Long buyerId, Long itemId);
    void responseTrade(Long sellerId, Long tradeId, String status);

}
