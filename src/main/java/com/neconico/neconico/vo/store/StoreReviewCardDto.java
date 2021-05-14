package com.neconico.neconico.vo.store;

import com.neconico.neconico.vo.item.ItemCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Alias("storereviewcarddto")
//상품 문의, 후기 카드
public class StoreReviewCardDto {

    //storereview
    private final Long reviewId;
    private final String writerName;
    private final String content;
    private final LocalDateTime replyCreatedTime;

    //item
    private ItemCardDto item;

}
