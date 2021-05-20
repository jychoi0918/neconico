package com.neconico.neconico.controller.admin.advertisement;


import com.neconico.neconico.dto.admin.advertisement.AdvertiseDto;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdvertiseController {

    private final AdvertiseService adService;

    private final FileService fileService;

    private long SessionUser = 3L;


    @GetMapping("/ads")
    public String adList(@ModelAttribute("cri")Criteria cri, Model model){

        List<AdvertiseReturnDto> list = adService.selectAllAd(cri);
        Pagination page = new Pagination(cri,adService.countTable(),10);
        model.addAttribute("adList",list);
        model.addAttribute("pageMaker",page);


        return"admin/advertisement/ads";
    }





    @GetMapping("/ad/{advertiseId}")
    public String adDetail(@PathVariable long advertiseId,Model model){

        AdvertiseReturnDto ad = adService.selectAd(advertiseId);
        model.addAttribute("ad",ad);


        return "admin/advertisement/ad";
    }





    @PutMapping("/ad/status")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateStatus(@RequestBody AdvertiseStatusDto advertiseStatusDto){
        adService.updateStatus(advertiseStatusDto);
        System.out.println("advertiseStatusDto.toString() = " + advertiseStatusDto.toString());

    }




    //등록 폼 가져오기
    @GetMapping("/ad/add")
    public String addAdForm(Model model){


        model.addAttribute("advertiseDto", new AdvertiseDto());
        return "/admin/advertisement/adAdd";
    }




    //등록
    @PostMapping("/ad/add")
    public String addAd(@RequestParam("imgFile")MultipartFile multipartFile, @ModelAttribute AdvertiseDto advertiseDto) throws Exception{

        fileService.setFileProcess(new S3FileProcess(FilePolicy.ADVERTISEMENT));

        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(multipartFile);

        adService.insertAd(fileResultInfoDto,advertiseDto);

        advertiseDto.setUserId(SessionUser);



        return "redirect:/admin/ads";
    }



}
