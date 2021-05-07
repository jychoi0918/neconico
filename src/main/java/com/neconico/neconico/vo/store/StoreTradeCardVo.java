package com.neconico.neconico.vo.store;

import com.neconico.neconico.vo.item.ItemCardVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Alias("storetradecardvo")
public class StoreTradeCardVo {

    //item
    private ItemCardVo item;

    //quetion
    private Long tradeId;
    private String buyerName;
    private String tradeStatus;
    private LocalDateTime requestTime;
    private LocalDateTime responseTime;

    public StoreTradeCardVo(ItemCardVo item, Long tradeId, String buyerName, String tradeStatus, LocalDateTime requestTime, LocalDateTime responseTime) {
        this.item = item;
        this.tradeId = tradeId;
        this.buyerName = buyerName;
        this.tradeStatus = tradeStatus;
        this.requestTime = requestTime;
        this.responseTime = responseTime;
    }
}
