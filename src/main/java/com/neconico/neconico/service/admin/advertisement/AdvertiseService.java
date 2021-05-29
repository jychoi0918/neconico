package com.neconico.neconico.service.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertiseInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseStatusDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;

public interface AdvertiseService {


    //select

    //'광고중' 광고글 가져오기
    List<AdvertiseReturnDto> selectAdvertising();


    //광고 리스트 가져오기
    List<AdvertiseReturnDto> selectAllAd(Criteria cri);

    long countTable();

    AdvertiseReturnDto selectAd(Long advertisementId);



    //insert
    void insertAd(FileResultInfoDto fileResultInfoDto, AdvertiseInfoDto advertiseInfoDto) throws Exception;



    //delete
    void deleteAd(Long advertisementId);



    //update
    void updateAd(FileResultInfoDto fileResultInfoDto, AdvertiseReturnDto advertiseReturnDto);


/*    void updateAdSamePicture(AdvertiseInfoDto advertiseInfoDto);*/


    void updateStatus(AdvertiseStatusDto advertiseStatusDto);


}
