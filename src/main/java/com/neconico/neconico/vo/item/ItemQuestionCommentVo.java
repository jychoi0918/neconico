package com.neconico.neconico.vo.item;

import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Alias("itemquestioncommentvo")
public class ItemQuestionCommentVo {

    private Long questionCommentId;
    private Long writerId;
    private String writerName;
    private String content;
    private LocalDateTime createdDate;

    public ItemQuestionCommentVo(Long questionCommentId, Long writerId, String writerName, String content, LocalDateTime createdDate) {
        this.questionCommentId = questionCommentId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.content = content;
        this.createdDate = createdDate;
    }
}
