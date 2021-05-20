package com.neconico.neconico.mapper.admin.advertisement;



import com.neconico.neconico.dto.admin.advertisement.AdvertiseDto;
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

    void insertAd(AdvertiseDto adDto);

    void deleteAd(long advertiseId);

    void updateAd(AdvertiseDto advertiseDto);

    void updateStatus(AdvertiseStatusDto advertiseStatusDto);
}
