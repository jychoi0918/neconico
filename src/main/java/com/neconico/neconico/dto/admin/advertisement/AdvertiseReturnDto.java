package com.neconico.neconico.dto.admin.advertisement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;


@Getter @Setter
@ToString @Slf4j
@NoArgsConstructor
@Alias("AdvertiseReturnDto")
public class AdvertiseReturnDto {

    private long advertisementId;
    private long userId;
    private String accountId;
    private String title;
    private String startDate;
    private String endDate;
    private String adImgUrl;
    private String url;
    private String imgFileName;
    private String adStatus;


}
