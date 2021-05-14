package com.neconico.neconico.dto.item;

import com.neconico.neconico.dto.category.CategorySubInfoDto;
import com.neconico.neconico.dto.store.StoreInquireInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Alias("itemInquireInfoDto")
public class ItemInquireInfoDto {
    private Long itemId;
    private String title;
    private String content;
    private String price;
    private String itemImgUrls;
    private String area;
    private String itemStatus;
    private int views;
    private LocalDateTime createdDate;
    private String saleStatus;
    private String shippingPrice;

    //카테고리 정보
    private CategorySubInfoDto categorySubInfoDto;

    //상점정보
    private StoreInquireInfoDto storeInquireInfoDto;
    //상품문의
    private List<ItemQuestionInquireDto> itemQuestionInquireDtoList;





}
