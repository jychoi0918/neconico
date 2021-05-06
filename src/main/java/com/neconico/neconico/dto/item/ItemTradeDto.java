package com.neconico.neconico.dto.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Builder
@Alias("itemTradeDto")
public class ItemTradeDto {

    //TRADE TABLE
    private Long tradeId;
    private Long itemId;
    private Long buyerId;
    private LocalDateTime requestCreated;
    private LocalDateTime responseCreated;
    private String tradeStatus;
    //ITEM TABLE
    private String itemName;
    private String itemImgPath;
    private Long sellerId;
    //USER TABLE
    private String buyerName;
    private String sellerName;

    public ItemTradeDto(Long tradeId, Long itemId, Long buyerId, LocalDateTime requestCreated, LocalDateTime responseCreated, String tradeStatus, String itemName, String itemImgPath, Long sellerId, String buyerName, String sellerName) {
        this.tradeId = tradeId;
        this.itemId = itemId;
        this.buyerId = buyerId;
        this.requestCreated = requestCreated;
        this.responseCreated = responseCreated;
        this.tradeStatus = tradeStatus;
        this.itemName = itemName;
        this.itemImgPath = itemImgPath;
        this.sellerId = sellerId;
        this.buyerName = buyerName;
        this.sellerName = sellerName;
    }
}
