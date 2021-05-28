package com.neconico.neconico.dto.store.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Alias("storeinfocard")
public class StoreInfoCardDto {

    private String storeName;
    private String storeImgUrl;
    private String storeInfo;
    private String created;
    private Long soldCount;

}
