package com.neconico.neconico.dto.item;

import com.neconico.neconico.dto.store.StoreInquireInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Alias("itemQuestionCommentInquireDto")
public class ItemQuestionCommentInquireDto {
    private Long questionCommentId;
    private String content;
    private LocalDateTime createdDate;

    //댓글을 쓴 스토어
    private StoreInquireInfoDto storeInquireInfoDto;
}
