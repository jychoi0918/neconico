package com.neconico.neconico.restcontroller.store;

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
    private final SessionUser user = new SessionUser();

    @PostMapping("/mystore/name")
    public void modifyStoreName(@RequestParam(name = "name") String name) {
        user.setUserId(7L);
        storeInfoService.updateStoreInfo(new StoreInfoDto(user.getUserId(), name, null, null, null));
    }

    @PostMapping("/mystore/content")
    public void modfiyStoreContent(@RequestParam(name = "content") String content) {
        user.setUserId(7L);
        storeInfoService.updateStoreInfo(new StoreInfoDto(user.getUserId(), null, null, content, null));
    }

    public void modifyStoreImg() {

    }


}
