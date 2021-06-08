package com.neconico.neconico.restcontroller.item;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.service.item.ItemTradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ItemTradeRestController {

    private final ItemTradeService tradeService;

    @PostMapping("/trade/{itemId}/request")
    public void requestTrade(@PathVariable("itemId") Long itemId, @LoginUser SessionUser user){
        tradeService.requestTrade(user.getUserId(), itemId);
    }

    @PostMapping("/trade/{tradeId}/response")
    public void responseTrade(@PathVariable("tradeId") Long tradeId,
                              @RequestParam(name = "status") String status,
                              @LoginUser SessionUser user) {
        tradeService.responseTrade(user.getUserId(), tradeId, status);
    }


}
