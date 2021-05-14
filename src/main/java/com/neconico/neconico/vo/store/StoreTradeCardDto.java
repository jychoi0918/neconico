package com.neconico.neconico.vo.store;

import com.neconico.neconico.vo.item.ItemCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Alias("storetradecarddto")
public class StoreTradeCardDto {

    //quetion
    private final Long tradeId;
    private final String buyerName;
    private final String tradeStatus;
    private final LocalDateTime createdDate;

    //item
    private ItemCardDto item;

}
