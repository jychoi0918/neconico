package com.neconico.neconico.dto.item;

import com.neconico.neconico.dto.store.StoreInquireInfoDto;
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
    private String betweenTime;

    //상품문의를 쓴 스토어
    private StoreInquireInfoDto storeInquireInfoDto;

}
