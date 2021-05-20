package com.neconico.neconico.dto.admin.advertisement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@NoArgsConstructor
@Alias("AdStatusDto")
public class
AdvertiseStatusDto {
    private long advertisementId;
    private String adStatus;
}
