package com.neconico.neconico.dto.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@NoArgsConstructor
@Alias("storeInquireInfoDto")
public class StoreInquireInfoDto {
    private Long userId;
    private String storeName;
    private String storeImgPath;
    private String accountId;
}
