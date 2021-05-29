package com.neconico.neconico.controller.admin.advertisement;


import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseStatusDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.file.process.S3FileProcess;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;
import com.neconico.neconico.service.admin.advertisement.AdvertiseService;
import com.neconico.neconico.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdvertiseController {

    private final AdvertiseService adService;

    private final FileService fileService;


    //================================================================================

    @GetMapping("/client")
    public String client(Model model) {
        List<AdvertiseReturnDto> adlist = adService.selectAdvertising();

        model.addAttribute("adverts", adlist);


        return "admin/advertisement/ad_index";
    }
    //********************************************************************************


    //광고 리스트 출력
    @GetMapping("/advert/list")
    public String adList(@ModelAttribute("cri") Criteria cri, Model model) {

        List<AdvertiseReturnDto> list = adService.selectAllAd(cri);
        Pagination page = new Pagination(cri, adService.countTable(), 10);
        model.addAttribute("adList", list);
        model.addAttribute("pageMaker", page);


        return "admin/advertisement/advert_list";
    }


    //상태 변경 : AJAX로 데이터 변경
    @PutMapping("/advert/status")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateStatus(@RequestBody AdvertiseStatusDto advertiseStatusDto) {
        adService.updateStatus(advertiseStatusDto);

    }


    //상세 광고 출력
    @GetMapping("/advert/{advertiseId}")
    public String adDetail(@PathVariable Long advertiseId, Model model) {

        AdvertiseReturnDto ad = adService.selectAd(advertiseId);
        model.addAttribute("ad", ad);


        return "admin/advertisement/advert_detail";
    }


    //등록 폼 출력
    @GetMapping("/advert/add")
    public String addAdForm(Model model) {

        model.addAttribute("advertiseDto", new AdvertiseInfoDto());

        return "admin/advertisement/advert_add";
    }


    //등록 데이터 전송
    @PostMapping("/advert/add")
    public String addAd(@RequestParam("imgFile") MultipartFile multipartFile,
                        @ModelAttribute AdvertiseInfoDto advertiseInfoDto,
                        @LoginUser SessionUser sessionUser) throws Exception {

        advertiseInfoDto.setUserId(sessionUser.getUserId());


        fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(multipartFile);
        adService.insertAd(fileResultInfoDto, advertiseInfoDto);


        return "redirect:/admin/advert/list";
    }


    //삭제하기
    @PostMapping("/advert/delete")
    public String deleteAd(@RequestParam(value = "advertisementId", required = false) String advertisementId,
                           @RequestParam(value = "imgFileName", required = false) String imgFileName) throws IllegalArgumentException {


        fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));
        fileService.deleteFiles(imgFileName);
        adService.deleteAd(Long.parseLong(advertisementId));

        return "redirect:/admin/advert/list";

    }


    //수정하기 폼 출력
    @GetMapping("/advert/edit/{advertisementId}")
    public String editAdForm(@PathVariable("advertisementId") Long advertisementId, Model model) {

        AdvertiseReturnDto advertiseReturnDto = adService.selectAd(advertisementId);
        model.addAttribute("ad", advertiseReturnDto);


        return "admin/advertisement/advert_edit";
    }


    //수정 데이터 전송
    @PostMapping("/advert/edit/{advertisementId}")
    public String editAdvert(@RequestParam("imgFile") MultipartFile multipartFile,
                             @ModelAttribute("advertiseInfoDto") AdvertiseReturnDto advertiseReturnDto) throws IOException, IllegalStateException, IllegalArgumentException {

        if(multipartFile.isEmpty()) {
            adService.updateAd(new FileResultInfoDto(null, null), advertiseReturnDto);

            return "redirect:/admin/advert/{advertisementId}";
        }

        fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));
        fileService.deleteFiles(advertiseReturnDto.getImgFileName());
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(multipartFile);
        adService.updateAd(fileResultInfoDto, advertiseReturnDto);
        return "redirect:/admin/advert/{advertisementId}";
    }


}
