package com.neconico.neconico.dto.admin.advertisement;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;


@Getter @Setter
@ToString
@NoArgsConstructor
@Alias("AdvertiseInfoDto")
public class AdvertiseInfoDto {

    private long advertisementId;
    private long userId;
    private String title;
    private String startDate;
    private String endDate;
    private String adImgUrl;
    private String url;
    private String imgFileName;
    private String adStatus;



}
