package com.neconico.neconico.service.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertStatusDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.mapper.admin.advertisement.AdvertiseMapper;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.service.admin.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvertiseServiceImpl implements AdvertiseService {


    private final AdvertiseMapper advertMapper;


    @Override
    public List<AdvertReturnDto> selectAllAdverts(Criteria cri) {

        return advertMapper.selectAdverts(setCriteria(cri));
    }


    @Override
    public List<AdvertReturnDto> selectPublicAdverts() {
        return advertMapper.selectPublicAdverts(Status.ADVERTISING.getStatus());
    }


    @Override
    public AdvertReturnDto selectAdvertByAdvertId(Long noticeId) {
        return advertMapper.selectAdvertByAdvertId(noticeId);

    }


    @Override
    public Long countAllAdverts() {
        return advertMapper.countTotalAdverts();
    }


    @Override
    @Transactional
    public void updateStatus(AdvertStatusDto advertStatusDto) {
        advertMapper.updateStatus(advertStatusDto);
    }


    @Override
    @Transactional
    public void insertAdvert(FileResultInfoDto fileResultInfoDto, AdvertInfoDto advertInfoDto) {
        advertInfoDto.setAdStatus(Status.HIDDEN.getStatus());
        advertMapper.insertAdvert(setAdvertiseDto(fileResultInfoDto, advertInfoDto));
    }


    @Override
    @Transactional
    public void updateAdvert(FileResultInfoDto fileResultInfoDto, AdvertReturnDto advertReturnDto) {

        if (fileResultInfoDto.getFileNames() == null && fileResultInfoDto.getFileUrls() == null) {
            advertMapper.updateAdvert(advertReturnDto);
        } else {
            advertMapper.updateAdvert(setAdvertiseRTDto(fileResultInfoDto, advertReturnDto));
        }
    }


    @Override
    @Transactional
    public void deleteAdvert(Long noticeId) {
        advertMapper.deleteAdvert(noticeId);
    }


    //criteria 초기화
    private Criteria setCriteria(Criteria cri) {
        cri.setSortingColumn("advertisementId");
        cri.setRequestOrder("desc");
        cri.setContentPerPage(10);

        if (cri.getCurrentPage() <= 0)
            cri.setCurrentPage(1);

        return cri;
    }


    //insert 할 때 사용하는 setter
    private AdvertInfoDto setAdvertiseDto(FileResultInfoDto fileResultInfoDto, AdvertInfoDto advertInfoDto) {

        advertInfoDto.setAdImgUrl(fileResultInfoDto.getFileUrls());
        advertInfoDto.setImgFileName(fileResultInfoDto.getFileNames());

        return advertInfoDto;
    }

    //update 할 때 사용하는 setter
    private AdvertReturnDto setAdvertiseRTDto(FileResultInfoDto fileResultInfoDto, AdvertReturnDto advertReturnDto) {

        advertReturnDto.setAdImgUrl(fileResultInfoDto.getFileUrls());
        advertReturnDto.setImgFileName(fileResultInfoDto.getFileNames());

        return advertReturnDto;
    }


}