package com.neconico.neconico.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Setter @Getter
@Alias("itemInfoDto")
@NoArgsConstructor
public class ItemInfoDto {
    private Long itemId;
    private Long userId;
    private Long categorySubId;
    private String title;
    private String content;
    private String price;
    private String itemImgUrls;
    private String area;
    private String itemStatus;
    private int hits;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String saleStatus;
    private String shippingPrice;
    private String imgFileNames;
}
