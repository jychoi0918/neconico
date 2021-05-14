package com.neconico.neconico.vo.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Alias("itemcarddto")
public class ItemCardDto {

    private final Long itemId;
    private final String title;
    private final String price;
    private final String itemImg;
    private final LocalDateTime createdTime;
    private final String status;
    private final String views;

}