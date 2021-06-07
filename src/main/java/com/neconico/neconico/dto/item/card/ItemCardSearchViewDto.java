package com.neconico.neconico.dto.item.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Alias("itemCardSearchViewDto")
public class ItemCardSearchViewDto {
    private Long itemId;
    private String title;
    private String price;
    private String itemImg;
    private LocalDateTime createdTime;
    private String itemStatus;
    private String saleStatus;
    private String views;
    private String betweenDate;

}
