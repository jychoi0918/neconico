package com.neconico.neconico.controller.admin.advertisement;


import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.admin.advertisement.AdvertInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertStatusDto;
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

    private final AdvertiseService advertService;

    private final FileService fileService;



    //광고 리스트 출력
    @GetMapping("/advert/list")
    public String adList(@ModelAttribute("cri") Criteria cri, Model model) {

        List<AdvertReturnDto> advertList = advertService.selectAllAdverts(cri);
        Pagination page = new Pagination(cri, advertService.countAllAdverts(), 10);
        model.addAttribute("adList", advertList);
        model.addAttribute("pageMaker", page);


        return "admin/advertisement/advert_list";
    }


    //광고 상태 변경
    @PutMapping("/advert/status")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateStatus(@RequestBody AdvertStatusDto advertStatusDto) {
        advertService.updateStatus(advertStatusDto);

    }


    //상세 광고 출력
    @GetMapping("/advert/{advertiseId}")
    public String adDetail(@PathVariable Long advertiseId, Model model) {

        AdvertReturnDto ad = advertService.selectAdvertByAdvertId(advertiseId);
        model.addAttribute("ad", ad);


        return "admin/advertisement/advert_detail";
    }


    //등록 폼 출력
    @GetMapping("/advert/new")
    public String addAdForm(Model model) {

        model.addAttribute("advertiseDto", new AdvertInfoDto());

        return "admin/advertisement/advert_add";
    }


    //등록 데이터 전송
    @PostMapping("/advert/new")
    public String addAd(@RequestParam("imgFile") MultipartFile multipartFile,
                        @ModelAttribute AdvertInfoDto advertInfoDto,
                        @LoginUser SessionUser sessionUser) throws Exception {

        advertInfoDto.setUserId(sessionUser.getUserId());


        fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(multipartFile);
        advertService.insertAdvert(fileResultInfoDto, advertInfoDto);


        return "redirect:/admin/advert/list";
    }


    //삭제하기
    @PostMapping("/advert/delete")
    public String deleteAd(@RequestParam(value = "advertisementId") String advertisementId,
                           @RequestParam(value = "imgFileName") String imgFileName) throws IllegalArgumentException {


        fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));
        fileService.deleteFiles(imgFileName);
        advertService.deleteAdvert(Long.parseLong(advertisementId));

        return "redirect:/admin/advert/list";

    }



    //수정하기 폼 출력
    @GetMapping("/advert/{advertisementId}/edit")
    public String editAdForm(@PathVariable("advertisementId") Long advertisementId, Model model) {

        model.addAttribute("ad", advertService.selectAdvertByAdvertId(advertisementId));


        return "admin/advertisement/advert_edit";
    }


    //수정 데이터 전송
    @PostMapping("/advert/{advertisementId}/edit")
    public String editAdvert(@RequestParam("imgFile") MultipartFile multipartFile,
                             @ModelAttribute("advertiseReturnDto") AdvertReturnDto advertReturnDto) throws Exception {

        if(multipartFile.isEmpty()) {
            advertService.updateAdvert(new FileResultInfoDto(null, null), advertReturnDto);

            return "redirect:/admin/advert/{advertisementId}";
        }

        fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));
        fileService.deleteFiles(advertReturnDto.getImgFileName());
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(multipartFile);

        advertService.updateAdvert(fileResultInfoDto, advertReturnDto);

        return "redirect:/admin/advert/{advertisementId}";
    }

}
