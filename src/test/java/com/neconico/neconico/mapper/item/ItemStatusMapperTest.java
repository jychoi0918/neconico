package com.neconico.neconico.mapper.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ItemStatusMapperTest {

    @Autowired
    ItemStatusMapper itemStatusMapper;

    Long itemId = 1L;

    @Test
    @DisplayName("아이템 상태 조회")
    void selectItemStatusByItemIdTest() {
        //given
        Long itemId = 2L;
        String status = "판매 중";
        //when
        String result = itemStatusMapper.selectItemStatusByItemId(itemId);
        //then
        assertThat(result).isEqualTo(status);
    }

    @Test
    @DisplayName("아이템 상태 업데이트")
    void updateItemStatusTest() {
        //given
        String status = "거래 완료";

        //when
        itemStatusMapper.updateItemStatus(itemId, status);
        String result = itemStatusMapper.selectItemStatusByItemId(itemId);


        //then
        assertThat(result).isEqualTo(status);
    }



}