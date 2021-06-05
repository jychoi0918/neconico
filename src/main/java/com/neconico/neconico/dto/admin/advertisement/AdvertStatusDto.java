package com.neconico.neconico.dto.admin.advertisement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@NoArgsConstructor
@Alias("AdvertStatusDto")
public class
AdvertStatusDto {
    private long advertisementId;
    private String adStatus;
}
