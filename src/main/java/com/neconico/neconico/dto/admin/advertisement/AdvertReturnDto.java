package com.neconico.neconico.dto.admin.advertisement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;


@Getter @Setter
@ToString
@NoArgsConstructor
@Alias("AdvertReturnDto")
public class AdvertReturnDto {

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
