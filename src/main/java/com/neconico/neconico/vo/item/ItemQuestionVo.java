package com.neconico.neconico.vo.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Alias("itemquestionvo")
public class ItemQuestionVo {

    private Long itemQuestionId;
    private Long writerId;
    private String writerName;
    private String content;
    private LocalDateTime createdDate;

    private List<ItemQuestionCommentVo> commentList;

    public ItemQuestionVo(Long itemQuestionId, Long writerId, String writerName, String content, LocalDateTime createdDate, List<ItemQuestionCommentVo> commentList) {
        this.itemQuestionId = itemQuestionId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.content = content;
        this.createdDate = createdDate;
        this.commentList = commentList;
    }
}
