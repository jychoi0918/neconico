package com.neconico.neconico.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("storeinfodto")
public class StoreInfoDto {

    private Long userId;
    private String storeName;
    private String storeImgUrl;
    private String storeInfo;
    private String storeImgName;

    public StoreInfoDto(Long userId, String storeName, String storeImgUrl, String storeInfo, String storeImgName) {
        this.userId = userId;
        this.storeName = storeName;
        this.storeImgUrl = storeImgUrl;
        this.storeInfo = storeInfo;
        this.storeImgName = storeImgName;
    }
}
