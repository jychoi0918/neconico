package com.neconico.neconico.dto.item.card;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ItemCardViewDto {
    private Long itemId;
    private String title;
    private String price;
    private String itemImg;
    private LocalDateTime createdTime;
    private String status;
    private String views;
    private String betweenDate;

    public ItemCardViewDto(ItemCardDto itemCardDto) {
        itemId = itemCardDto.getItemId();
        title = itemCardDto.getTitle();
        price = itemCardDto.getPrice();
        itemImg = itemCardDto.getItemImg();
        createdTime = itemCardDto.getCreatedTime();
        status = itemCardDto.getStatus();
        views = itemCardDto.getViews();
    }
}
