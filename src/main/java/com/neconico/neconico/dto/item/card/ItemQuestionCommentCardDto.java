package com.neconico.neconico.dto.item.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Alias("itemquestioncommentcarddto")
public class ItemQuestionCommentCardDto {

    private final Long commentId;
    private final Long commentWriterId;
    private final String commentWriterName;
    private final String commentContent;
    private final LocalDateTime commentCreatedDate;

}
