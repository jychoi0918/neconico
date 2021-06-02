package com.neconico.neconico.mapper.admin.advertisement;



import com.neconico.neconico.dto.admin.advertisement.AdvertInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertStatusDto;
import com.neconico.neconico.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertiseMapper {

    List<AdvertReturnDto> selectAdverts(Criteria cri);

    List<AdvertReturnDto> selectPublicAdverts(String advertStatus);

    AdvertReturnDto selectAdvertByAdvertId(Long advertisementId);

    Long countTotalAdverts();

    void insertAdvert(AdvertInfoDto advertInfoDto);

    void deleteAdvert(Long advertisementId);

    void updateAdvert(AdvertReturnDto advertReturnDto);

    void updateStatus(AdvertStatusDto advertStatusDto);


}
