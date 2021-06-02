package com.neconico.neconico.service.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertStatusDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;

public interface AdvertiseService {


    List<AdvertReturnDto> selectPublicAdverts();

    List<AdvertReturnDto> selectAllAdverts(Criteria cri);

    Long countAllAdverts();

    AdvertReturnDto selectAdvertByAdvertId(Long advertisementId);

    void insertAdvert(FileResultInfoDto fileResultInfoDto, AdvertInfoDto advertInfoDto);

    void deleteAdvert(Long advertisementId);

    void updateAdvert(FileResultInfoDto fileResultInfoDto, AdvertReturnDto advertReturnDto);

    void updateStatus(AdvertStatusDto advertStatusDto);


}
