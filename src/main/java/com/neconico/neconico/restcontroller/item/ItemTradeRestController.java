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

    private SessionUser user = new SessionUser();


    @PostMapping("/trade/{itemId}/request")
    public void tradeRequest(@PathVariable("itemId")Long itemId){
        tradeService.tradeRequest(user.getUserId(), itemId);
    }

    @PostMapping("/trade/{tradeId}/response")
    public void tradeResponse(@PathVariable("tradeId")Long tradeId, @RequestParam(name = "status") String status) {
        user.setUserId(3L);
        System.out.println(tradeId);
        tradeService.tradeResponse(user.getUserId(), tradeId, status);
    }


}
