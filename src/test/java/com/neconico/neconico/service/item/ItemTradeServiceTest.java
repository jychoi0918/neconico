package com.neconico.neconico.service.item;

import com.neconico.neconico.mapper.item.ItemStatusMapper;
import com.neconico.neconico.mapper.item.ItemTradeMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ItemTradeServiceTest {

    @SpringBootTest
    @Transactional
    @DisplayName("거래 요청 테스트")
    public static class Request{

        @Autowired
        ItemTradeService serviceT;

        @Autowired
        ItemTradeMapper itemTradeMapper;

        @Autowired
        ItemStatusMapper itemStatusMapper;

        @Test
        @DisplayName("상품 상태 판매 중 아님")
        void ItemStatusTest() {
            //given
            Long buyerId = 1L;
            Long itemId = 171L;

            assertThatThrownBy(() -> serviceT.requestTrade(buyerId, itemId)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Not item Status is not Sale");

        }

        @Test
        @DisplayName("판매자와 구매자가 같은경우")
        void BuyerEqualSellerTest() {
            //given
            Long buyerId = 1L;
            Long itemId = 1L;

            assertThatThrownBy(() -> serviceT.requestTrade(buyerId, itemId)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Buyer equal Seller");
        }

        @Test
        @DisplayName("똑같은 거래내역 존재 하는 경우")
        void ExistSameTrade() {
            //given
            Long buyerId = 1L;
            Long itemId = 6L;

            //then
            assertThatThrownBy(() -> serviceT.requestTrade(buyerId, itemId)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Now this item is trading");
        }

        @Test
        @DisplayName("정상 구매 요청")
        void NormalRequestTradeTest() {
            //given
            Long buyerId = 1L;
            Long itemId = 16L;
            assertThat(itemTradeMapper.selectItemTradeOneByBuyerAndItem(buyerId, itemId)).isFalse();

            //when
            serviceT.requestTrade(buyerId, itemId);

            //then
            assertThat(itemTradeMapper.selectItemTradeOneByBuyerAndItem(buyerId, itemId)).isTrue();

        }
    }


    @SpringBootTest
    @Transactional
    @DisplayName("거래 응답 테스트")
    public static class Response {

        @Autowired
        ItemTradeService serviceT;
        @Autowired
        ItemTradeMapper itemTradeMapper;
        @Autowired
        ItemStatusMapper statusDao;

        @Test
        @DisplayName("SellerId와 거래의 판매자가 다를경우")
        void tradeResponseTest() {
            Long sellerId = 1L;
            Long tradeId = 3L;
            String status = "완료";

            assertThatThrownBy(() -> serviceT.responseTrade(sellerId, tradeId, status)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Not Matching YourId And SellerId");

        }

        @Test
        @DisplayName("상태 잘못 입력한 경우")
        void test() {
            Long sellerId = 2L;
            Long tradeId = 3L;
            String status = "응가";

            assertThatThrownBy(() -> serviceT.responseTrade(sellerId, tradeId, status)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Input Invalid Status");
        }

        @Test
        @DisplayName("정상 입력")
        void NormalResponseTradeTest() {
            //given
            Long sellerId = 2L;
            Long tradeId = 1L;
            String status = "success";

            String init = itemTradeMapper.selectItemTradeOneByTrade(tradeId).getTradeStatus();

            //when
            serviceT.responseTrade(sellerId, tradeId, status);
            String result = itemTradeMapper.selectItemTradeOneByTrade(tradeId).getTradeStatus();
            //then
            assertThat(init).isEqualTo("요청");
            assertThat(result).isEqualTo("완료");
        }


    }
}