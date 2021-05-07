package com.neconico.neconico.vo.store;

import com.neconico.neconico.vo.item.ItemCardVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Alias("storereviewcardvo")
//상품 문의, 후기 카드
public class StoreReviewCardVo {

    //item
    private ItemCardVo item;

    //storereview
    private Long reviewId;
    private String writerName;
    private String content;
    private LocalDateTime replyCreatedTime;

    public StoreReviewCardVo(ItemCardVo item, Long reviewId, String writerName, String content, LocalDateTime replyCreatedTime) {
        this.item = item;
        this.reviewId = reviewId;
        this.writerName = writerName;
        this.content = content;
        this.replyCreatedTime = replyCreatedTime;
    }
}
