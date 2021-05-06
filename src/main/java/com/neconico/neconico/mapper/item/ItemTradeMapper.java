package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.item.ItemTradeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemTradeMapper {

    //판매자가 거래요청 내역 조회
    List<ItemTradeDto> selectItemTradeListBySeller(@Param("sellerId") Long sellerId);
    //구매자가 거래요청 내역 조회
    List<ItemTradeDto> selectItemTradeListByBuyer(@Param("buyerId") Long buyerId);

    //구매자 ID와 아이템 ID를 통해서 거래 진행중인 내역 있는지 확인
    boolean selectItemTradeOneByBuyerAndItem(@Param("buyerId") Long buyerId, @Param("itemId") Long itemId);

    //tradeId를 통해서 거래 상태 확인
    ItemTradeDto selectItemTradeOneByTrade(@Param("tradeId") Long tradeId);

    //구매자가 거래요청 신청
    void insertItemTradeRequestByBuyerAndItem(@Param("buyerId") Long buyerId, @Param("itemId") Long itemId);

    //판매자가 거래요청 응답
    void updateItemTradeResponseByTradeAndItem(@Param("tradeId") Long tradeId, @Param("status") String status);

    //상품 판매자 확인
    Long selectItemSellerByItemId(@Param("itemId") Long itemId);


}
