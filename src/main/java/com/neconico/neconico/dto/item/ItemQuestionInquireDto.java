package com.neconico.neconico.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Alias("itemQuestionInquireDto")
public class ItemQuestionInquireDto {
    private Long itemQuestionId;
    private String content;
    private LocalDateTime createdDate;

    //해당 문의에 대한 댓글
    private List<ItemQuestionCommentInquireDto> commentInquireDtoList;

}
