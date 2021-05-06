package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.item.ItemTradeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class ItemTradeMapperTest {

    @Autowired
    ItemTradeMapper itemTradeMapper;



    @Test
    @DisplayName("트레이트 요청 test")
    void insertItemTradeRequestByUserAndItemTest() {
        Long buyerId = 1L;
        Long itemId = 6L;

        //given
        List<ItemTradeDto> init = itemTradeMapper.selectItemTradeListByBuyer(buyerId);

        //when
        itemTradeMapper.insertItemTradeRequestByBuyerAndItem(buyerId, itemId);
        List<ItemTradeDto> result = itemTradeMapper.selectItemTradeListByBuyer(buyerId);

        //then
        assertThat(result.size() - init.size()).isEqualTo(1);
    }


    @Test
    @DisplayName("트레이드 상태 변화 TEST")
    void updateItemTradeResponseByTradeAndItemTest() {
        //given
        Long tradeId = 1L;
        Long itemId = 6L;
        ItemTradeDto init = itemTradeMapper.selectItemTradeOneByTrade(tradeId);
        String status = "완료";

        //when
        itemTradeMapper.updateItemTradeResponseByTradeAndItem(tradeId, status);
        ItemTradeDto result = itemTradeMapper.selectItemTradeOneByTrade(tradeId);

        //then
        assertThat(result.getResponseCreated()).isNotEqualTo(init.getResponseCreated());
        assertThat(result.getTradeStatus()).isNotEqualTo(init.getTradeStatus());
    }

    @Test
    @DisplayName("기존에 거래요청인게 존재하는지")
    void selectItemTradeOneByBuyerAndItemTest() {
        //given
        Long itemId1 = 1L;
        Long userId1 = 6L;
        itemTradeMapper.insertItemTradeRequestByBuyerAndItem(userId1, itemId1);

        Long itemId2 = 0L;
        Long userId2 = 0L;

        //when
        boolean resultTrue = itemTradeMapper.selectItemTradeOneByBuyerAndItem(userId1, itemId1);
        boolean resultFalse = itemTradeMapper.selectItemTradeOneByBuyerAndItem(userId2, itemId2);
        //then

        assertThat(resultTrue).isTrue();
        assertThat(resultFalse).isFalse();
    }
}