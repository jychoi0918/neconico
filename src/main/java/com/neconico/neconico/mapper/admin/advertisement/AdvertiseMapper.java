package com.neconico.neconico.mapper.admin.advertisement;



import com.neconico.neconico.dto.admin.advertisement.AdvertiseInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseStatusDto;
import com.neconico.neconico.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertiseMapper {

    //select
    List<AdvertiseReturnDto> selectByPaging(Criteria cri);

    List<AdvertiseReturnDto> selectAll();

    AdvertiseReturnDto selectAd(long advertiseId);

    Long countTable();

    void insertAd(AdvertiseInfoDto adDto);

    void deleteAd(long advertiseId);

    void updateAd(AdvertiseReturnDto advertiseReturnDto);

    void updateStatus(AdvertiseStatusDto advertiseStatusDto);

    List<AdvertiseReturnDto> selectAdvertising(String adStatus);
}
