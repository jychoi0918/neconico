package com.neconico.neconico.service.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertiseDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseStatusDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.mapper.admin.advertisement.AdvertiseMapper;
import com.neconico.neconico.paging.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvertiseServiceImpl implements AdvertiseService {

    private final AdvertiseMapper adMapper;

    final long sessionUserId = 21L;

    //(메인페이지)목록 보기
    @Override
    public List<AdvertiseReturnDto> selectAllAd(Criteria cri) {

        return adMapper.selectByPaging(setCriteria(cri));
    }




    //하나의 광고글 보기 상세보기
    @Override
    public AdvertiseReturnDto selectAd(Long noticeId) {
        return adMapper.selectAd(noticeId);

    }




    //전체 광고글 세기
    @Override
    public long countTable() {
        adMapper.countTable();
        return adMapper.countTable();
    }


    @Override
    @Transactional
    public void updateStatus(AdvertiseStatusDto advertiseStatusDto) {
        adMapper.updateStatus(advertiseStatusDto);
    }

    //광고 등록
    @Override
    @Transactional
    public void insertAd(FileResultInfoDto fileResultInfoDto, AdvertiseDto advertiseDto) {

        advertiseDto.setAdStatus("숨김");
        adMapper.insertAd(setAdvertiseDto(fileResultInfoDto,advertiseDto));
    }

    private AdvertiseDto setAdvertiseDto(FileResultInfoDto fileResultInfoDto, AdvertiseDto advertiseDto) {

        advertiseDto.setAdImgUrl(fileResultInfoDto.getFileUrls());
        advertiseDto.setImgFileName(fileResultInfoDto.getFileNames());
        return advertiseDto;
    }




    //삭제하기
    @Override
    @Transactional
    public void deleteAd(Long noticeId) {
        adMapper.deleteAd(noticeId);
    }



    private Criteria setCriteria(Criteria cri) {
        cri.setSortingColumn("advertisementId");
        cri.setRequestOrder("desc");
        cri.setContentPerPage(10);

        if (cri.getCurrentPage() <= 0)
            cri.setCurrentPage(1);

        return cri;
    }
}