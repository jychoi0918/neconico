package com.neconico.neconico.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@NoArgsConstructor
@Alias("itemquestiondto")
public class ItemQuestionDto {
    private Long id;
    private Long objectId;
    private Long userId;
    private String content;

    public ItemQuestionDto(Long objectId, Long userId, String content) {
        this.objectId = objectId;
        this.userId = userId;
        this.content = content;
    }
}
