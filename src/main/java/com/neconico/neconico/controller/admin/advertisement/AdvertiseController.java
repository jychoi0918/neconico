package com.neconico.neconico.controller.admin.advertisement;


import com.neconico.neconico.dto.admin.advertisement.AdvertiseInfoDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseReturnDto;
import com.neconico.neconico.dto.admin.advertisement.AdvertiseStatusDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
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

    private long SessionUser = 3L;


    @GetMapping("/adverts")
    public String adList(@ModelAttribute("cri")Criteria cri, Model model){

        List<AdvertiseReturnDto> list = adService.selectAllAd(cri);
        Pagination page = new Pagination(cri,adService.countTable(),10);
        model.addAttribute("adList",list);
        model.addAttribute("pageMaker",page);


        return "admin/advertisement/adverts";
    }





    @GetMapping("/advert/{advertiseId}")
    public String adDetail(@PathVariable long advertiseId,Model model){

        AdvertiseReturnDto ad = adService.selectAd(advertiseId);
        model.addAttribute("ad",ad);


        return "admin/advertisement/advert_detail";
    }





    @PutMapping("/advert/status")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateStatus(@RequestBody AdvertiseStatusDto advertiseStatusDto){
        adService.updateStatus(advertiseStatusDto);
        System.out.println("advertiseStatusDto.toString() = " + advertiseStatusDto.toString());

    }




    //등록 폼 가져오기
    @GetMapping("/advert/add")
    public String addAdForm(Model model){


        model.addAttribute("advertiseDto", new AdvertiseInfoDto());
        return "admin/advertisement/advert_register";
    }




    //등록
    @PostMapping("/advert/add")
    public String addAd(@RequestParam("imgFile")MultipartFile multipartFile,
                        @ModelAttribute AdvertiseInfoDto advertiseInfoDto) throws Exception{
        advertiseInfoDto.setUserId(SessionUser);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("multi="+multipartFile );
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));

        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(multipartFile);

        adService.insertAd(fileResultInfoDto, advertiseInfoDto);




        return "redirect:/admin/adverts";
    }


    //삭제하기
    @PostMapping("/advert/delete")
    public String deleteAd(@RequestParam(value = "advertisementId",required = false) String advertisementId,
                           @RequestParam(value = "imgFileName",required = false) String imgFileName) throws IllegalArgumentException{

        System.out.println("=========================================================");
        System.out.println("adID = "+advertisementId+"img="+ imgFileName);



        fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));
        fileService.deleteFiles(imgFileName);
        adService.deleteAd(Long.parseLong(advertisementId));

        return "redirect:/admin/advert/list";

    }


    //수정하기

    @GetMapping("/advert/edit/{advertisementId}")
    public String editAdForm(@PathVariable("advertisementId") Long advertisementId, Model model){

        AdvertiseReturnDto advertiseReturnDto = adService.selectAd(advertisementId);

            model.addAttribute("ad",advertiseReturnDto);




        return "admin/advertisement/advert_edit";
    }

    @PostMapping("/advert/edit/{advertisementId}")
    public String editAdvert(@RequestParam("imgFile") MultipartFile multipartFile,
                             @PathVariable("advertisementId")Long advertisementId,
                             @ModelAttribute("advertiseInfoDto")AdvertiseInfoDto advertiseInfoDto) throws IOException, IllegalStateException, IllegalArgumentException{
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("advertiseInfoDto.getImgFileName="+advertiseInfoDto.getImgFileName());
        System.out.println("getOtherElement"+advertiseInfoDto.getEndDate());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        advertiseInfoDto.setAdvertisementId(advertisementId);


        if(advertiseInfoDto.getImgFileName().equals("same")){

            adService.updateAdSamePicture(advertiseInfoDto);
        }else {


            fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));

            fileService.deleteFiles(advertiseInfoDto.getImgFileName());

            FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(multipartFile);

            adService.updateAd(fileResultInfoDto, advertiseInfoDto);
        }
        return "redirect:/admin/advert/{advertisementId}";
    }


}
