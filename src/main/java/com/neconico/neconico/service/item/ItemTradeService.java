package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.item.ItemTradeDto;
import com.neconico.neconico.mapper.item.ItemStatusMapper;
import com.neconico.neconico.mapper.item.ItemTradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTradeService {

    @Autowired
    ItemStatusMapper itemStatusMapper;

    @Autowired
    ItemTradeMapper itemTradeMapper;

    void tradeRequest(Long buyerId, Long itemId) {
        //상품 판매자 = 구매자 인 경우
        if(itemTradeMapper.selectItemSellerByItemId(itemId)==buyerId){
            System.out.println("판매자와 구매자가 같습니다.");
            throw new IllegalArgumentException("Buyer equal Seller");
        }
        //상품 상태 확인 -> 판매 중이 아니면 진행안됩니다.
        if(!itemStatusMapper.selectItemStatusByItemId(itemId).equals("판매 중")) {
            System.out.println("현재 상품 상태가 판매 중이 아닙니다.");
            throw new IllegalArgumentException("Not item Status is not Sale");
        }

        //거래중인 내역 존재 유무 확인
        if (itemTradeMapper.selectItemTradeOneByBuyerAndItem(buyerId, itemId)) {
            System.out.println("현재 거래중인 내역이 있습니다.");
            throw new IllegalArgumentException("Now this item is trading");
        }

        //TRADE 테이블에 거래요청을 만들어준다. 그리고 상품의 상태를 거래 중으로 변경해준다.
        itemTradeMapper.insertItemTradeRequestByBuyerAndItem(buyerId, itemId);
        itemStatusMapper.updateItemStatus(itemId, "거래 중");
    }

    void tradeResponse(Long sellerId, Long tradeId, String status) {
        ItemTradeDto tradeInfo = itemTradeMapper.selectItemTradeOneByTrade(tradeId);

        //만약 sellerId가 거래의 판매자와 다를경우
        if (!sellerId.equals(tradeInfo.getSellerId())) {
            System.out.println("당신("
                    + sellerId
                    + ")은 판매자("
                    + tradeInfo.getSellerId()
                    + "가 아닙니다.");
            throw new IllegalArgumentException("Not Matching YourId And SellerId");
        }

        //상태에 따라 분기 나눠줌
        switch (status) {
            case "취소":
                itemTradeMapper.updateItemTradeResponseByTradeAndItem(tradeId, "취소");
                itemStatusMapper.updateItemStatus(tradeInfo.getItemId(),  "판매 중");
                break;
            case "완료":
                itemTradeMapper.updateItemTradeResponseByTradeAndItem(tradeId, "완료");
                itemStatusMapper.insertSaleItem(tradeInfo.getItemId(), tradeInfo.getSellerId());
                itemStatusMapper.insertPurchaseItem(tradeInfo.getItemId(), tradeInfo.getBuyerId());
                itemStatusMapper.updateItemStatus(tradeInfo.getItemId(), "거래 완료");
                break;
            default:
                System.out.println("상태 입력 오류");
                throw new IllegalArgumentException("Input Invalid Status");
        }
    }




}
