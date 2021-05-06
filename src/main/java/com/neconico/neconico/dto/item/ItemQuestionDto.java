package com.neconico.neconico.dto.item;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Setter
@Getter
@Alias("itemquestiondto")
public class ItemQuestionDto {
    private Long objectId;
    private Long userId;
    private String content;

    public ItemQuestionDto(Long objectId, Long userId, String content) {
        this.objectId = objectId;
        this.userId = userId;
        this.content = content;
    }
}
