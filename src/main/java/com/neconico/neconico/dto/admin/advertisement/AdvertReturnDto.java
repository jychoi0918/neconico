package com.neconico.neconico.dto.admin.advertisement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;



@Getter @Setter
@NoArgsConstructor
@Alias("AdvertReturnDto")
public class AdvertReturnDto {

    private Long advertisementId;
    private Long userId;
    private String accountId;
    private String title;
    private String startDate;
    private String endDate;
    private String adImgUrl;
    private String url;
    private String imgFileName;
    private String adStatus;


}
