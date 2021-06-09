package com.neconico.neconico.dto.admin.advertisement;

import lombok.*;
import org.apache.ibatis.type.Alias;



@Getter @Setter
@NoArgsConstructor
@Alias("AdvertInfoDto")
public class AdvertInfoDto {

    private Long advertisementId;
    private Long userId;
    private String title;
    private String startDate;
    private String endDate;
    private String adImgUrl;
    private String url;
    private String imgFileName;
    private String adStatus;



}
