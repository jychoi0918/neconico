package com.neconico.neconico.service.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertiseInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseStatusDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;

public interface AdvertiseService {

    //select
    List<AdvertiseReturnDto> selectAllAd(Criteria cri);

    long countTable();

    AdvertiseReturnDto selectAd(Long noticeId);

    //insert
    void insertAd(FileResultInfoDto fileResultInfoDto, AdvertiseInfoDto advertiseInfoDto) throws Exception;


    void deleteAd(Long noticeId);



    void updateAd(FileResultInfoDto fileResultInfoDto, AdvertiseInfoDto advertiseInfoDto);

    void updateAdSamePicture(AdvertiseInfoDto advertiseInfoDto);

    void updateStatus(AdvertiseStatusDto advertiseStatusDto);


}
