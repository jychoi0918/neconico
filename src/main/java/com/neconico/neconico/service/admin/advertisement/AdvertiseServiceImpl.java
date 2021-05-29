package com.neconico.neconico.service.admin.advertisement;

import com.neconico.neconico.dto.admin.advertisement.AdvertiseInfoDto;
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

    final long sessionUserId = 40L;






    //(메인페이지)목록 보기
    @Override
    public List<AdvertiseReturnDto> selectAllAd(Criteria cri) {

        return adMapper.selectByPaging(setCriteria(cri));
    }





    //광고중인 광고만 가져오기
    @Override
    public List<AdvertiseReturnDto> selectAdvertising() {
        String adStatus = "광고중";
        return adMapper.selectAdvertising(adStatus);
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
    public void insertAd(FileResultInfoDto fileResultInfoDto, AdvertiseInfoDto advertiseInfoDto) {

        advertiseInfoDto.setAdStatus("숨김");
        adMapper.insertAd(setAdvertiseDto(fileResultInfoDto, advertiseInfoDto));
    }



    //만일 같은 사진일때
  /*  @Override
    @Transactional
    public void updateAdSamePicture(AdvertiseInfoDto advertiseInfoDto) {
        adMapper.updateAd(advertiseInfoDto);

    }*/


    //새로운 사진일 때
    @Override
    @Transactional
    public void updateAd(FileResultInfoDto fileResultInfoDto, AdvertiseReturnDto advertiseReturnDto) {

        if(fileResultInfoDto.getFileNames() == null && fileResultInfoDto.getFileUrls() == null){
            adMapper.updateAd(advertiseReturnDto);
        }else {

            adMapper.updateAd(setAdvertiseRTDto(fileResultInfoDto, advertiseReturnDto));
        }
    }





    //삭제하기
    @Override
    @Transactional
    public void deleteAd(Long noticeId) {
        adMapper.deleteAd(noticeId);
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

    //파일

    private AdvertiseInfoDto setAdvertiseDto(FileResultInfoDto fileResultInfoDto, AdvertiseInfoDto advertiseInfoDto) {

        advertiseInfoDto.setAdImgUrl(fileResultInfoDto.getFileUrls());
        advertiseInfoDto.setImgFileName(fileResultInfoDto.getFileNames());
        return advertiseInfoDto;
    }

    private AdvertiseReturnDto setAdvertiseRTDto(FileResultInfoDto fileResultInfoDto, AdvertiseReturnDto advertiseReturnDto) {

        advertiseReturnDto.setAdImgUrl(fileResultInfoDto.getFileUrls());
        advertiseReturnDto.setImgFileName(fileResultInfoDto.getFileNames());
        return advertiseReturnDto;
    }


}