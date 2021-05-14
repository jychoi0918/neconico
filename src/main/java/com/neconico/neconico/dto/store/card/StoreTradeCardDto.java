package com.neconico.neconico.dto.store.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Alias("storetradecarddto")
public class StoreTradeCardDto {

    //quetion
    private final Long tradeId;
    private final String buyerName;
    private final String tradeStatus;
    private final LocalDateTime createdDate;

    //item
    private final Long itemId;
    private final String title;
    private final String price;
    private final String itemImg;
    private final LocalDateTime createdTime;
    private final String status;
    private final String views;

}
