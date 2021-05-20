package com.neconico.neconico.service.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertiseDto;
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
    void insertAd(FileResultInfoDto fileResultInfoDto, AdvertiseDto advertiseDto) throws Exception;

    void deleteAd(Long noticeId);

    void updateStatus(AdvertiseStatusDto advertiseStatusDto);


}
