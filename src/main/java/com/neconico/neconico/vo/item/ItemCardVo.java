package com.neconico.neconico.vo.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Alias("itemcardvo")
public class ItemCardVo {

    private Long itemId;
    private String title;
    private String price;
    private String itemImg;
    private LocalDateTime createdTime;
    private String status;
    private String views;

    public ItemCardVo(Long itemId, String title, String price, String itemImg, LocalDateTime createdTime, String status, String views) {
        this.itemId = itemId;
        this.title = title;
        this.price = price;
        this.itemImg = itemImg;
        this.createdTime = createdTime;
        this.status = status;
        this.views = views;
    }
}