package com.neconico.neconico.restcontroller.item;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.service.item.ItemFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ItemFavoriteRestController {

    private final ItemFavoriteService itemFavoriteService;

    @PostMapping("/favorite/{itemId}/add")
    public void itemFavoriteAdd(@PathVariable("itemId") Long itemId, @LoginUser SessionUser user){
        itemFavoriteService.addFavorite(itemId, user.getUserId());
    }

    @PostMapping("/favorite/{itemId}/cancel")
    public void itemFavoriteCancel(@PathVariable("itemId") Long itemId, @LoginUser SessionUser user){
        itemFavoriteService.cancelFavorite(itemId, user.getUserId());
    }

    @GetMapping("/favorite/{itemId}/check")
    public boolean itemFavoriteCheck(@PathVariable("itemId") Long itemId, @LoginUser SessionUser user){
        return itemFavoriteService.checkItemFavorite(itemId, user.getUserId());
    }

    @GetMapping("/favorite/{itemId}/count")
    public String itemFavoriteCount(@PathVariable("itemId") Long itemId){
        return itemFavoriteService.countItemFavorite(itemId);
    }



}
