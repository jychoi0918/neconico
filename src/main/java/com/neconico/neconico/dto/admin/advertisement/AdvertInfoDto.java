package com.neconico.neconico.dto.admin.advertisement;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;


@Getter @Setter
@ToString
@NoArgsConstructor
@Alias("AdvertInfoDto")
public class AdvertInfoDto {

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
