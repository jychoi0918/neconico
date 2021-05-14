package com.neconico.neconico.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@AllArgsConstructor
@Alias("itemquestiondto")
public class ItemQuestionDto {
    private Long objectId;
    private Long userId;
    private String content;

}
