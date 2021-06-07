package com.neconico.neconico.restcontroller.store;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.store.StoreInfoDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.file.process.S3FileProcess;
import com.neconico.neconico.service.file.FileService;
import com.neconico.neconico.service.store.StoreInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class StoreInfoRestController {

    private final StoreInfoService storeInfoService;
    private final FileService fileService;

    @PostMapping("/mystore/name/edit")
    public void modifyStoreName(@RequestParam(name = "name") String name, @LoginUser SessionUser user) {
        storeInfoService.updateStoreInfo(new StoreInfoDto(user.getUserId(), name, null, null, null));
    }

    @PostMapping("/mystore/content/edit")
    public void modifyStoreContent(@RequestParam(name = "content") String content, @LoginUser SessionUser user) {
        storeInfoService.updateStoreInfo(new StoreInfoDto(user.getUserId(), null, null, content, null));
    }

    @PostMapping("/mystore/img/edit")
    public void modifyStoreImg(@RequestParam("storeImg") MultipartFile multipartFiles, @LoginUser SessionUser user) throws IOException {
        StoreInfoDto currentStoreInfo = storeInfoService.findStoreInfoByUserId(user.getUserId());

        fileService.setFileProcess(new S3FileProcess(FilePolicy.STORE));
        FileResultInfoDto result = fileService.uploadFiles(multipartFiles);

        if(!currentStoreInfo.getStoreImgName().equals("")) {
            fileService.deleteFiles(currentStoreInfo.getStoreImgName());
        }

        storeInfoService.updateStoreImg(result, currentStoreInfo);
    }

}
