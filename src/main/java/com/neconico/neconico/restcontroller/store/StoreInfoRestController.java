package com.neconico.neconico.restcontroller.store;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.store.StoreInfoDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.service.store.StoreInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreInfoRestController {

    private final StoreInfoService storeInfoService;

    @PostMapping("/mystore/name")
    public void modifyStoreName(@RequestParam(name = "name") String name, @LoginUser SessionUser user) {
        storeInfoService.updateStoreInfo(new StoreInfoDto(user.getUserId(), name, null, null, null));
    }

    @PostMapping("/mystore/content")
    public void modfiyStoreContent(@RequestParam(name = "content") String content, @LoginUser SessionUser user) {
        storeInfoService.updateStoreInfo(new StoreInfoDto(user.getUserId(), null, null, content, null));
    }

    public void modifyStoreImg() {

    }


}
