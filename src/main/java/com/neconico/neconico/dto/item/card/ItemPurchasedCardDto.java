package com.neconico.neconico.dto.item.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Alias("itempurchasedcarddto")
public class ItemPurchasedCardDto {

    private final Long itemId;
    private final String title;
    private final String price;
    private final String itemImg;
    private final LocalDateTime createdTime;
    private final String status;
    private final String views;

    private final Long purchaseId;
    private final String content;

}
