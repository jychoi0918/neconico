package com.neconico.neconico.vo.store;

import com.neconico.neconico.vo.item.ItemCardVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Alias("storequestioncardvo")
//상품 문의, 후기 카드
public class StoreQuestionCardVo {

    //item
    private ItemCardVo item;

    //storereview
    private Long questionId;
    private String writerName;
    private String content;
    private LocalDateTime replyCreatedTime;

    public StoreQuestionCardVo(ItemCardVo item, Long questionId, String writerName, String content, LocalDateTime replyCreatedTime) {
        this.item = item;
        this.questionId = questionId;
        this.writerName = writerName;
        this.content = content;
        this.replyCreatedTime = replyCreatedTime;
    }
}
