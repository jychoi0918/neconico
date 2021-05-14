package com.neconico.neconico.dto.item.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Alias("itemquestioncarddto")
public class ItemQuestionCardDto {

    private Long questionId;
    private Long questionWriterId;
    private String questionWriterName;
    private String questionContent;
    private LocalDateTime questionCreatedDate;

    private List<ItemQuestionCommentCardDto> commentList;

}
