package com.neconico.neconico.mapper.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemFavoriteMapperTest {

    //디비에 있는 Favorite정보
    Long userId = 1L;
    Long itemId = 6L;


    @Autowired
    ItemFavoriteMapper itemFavoriteMapper;

    @Test
    @DisplayName("좋아요 했는지 확인")
    void selectFavoriteCheckByItemAndUser() {
        assertThat(itemFavoriteMapper.selectFavoriteCheckByItemAndUser(itemId,userId )).isTrue();
        assertThat(itemFavoriteMapper.selectFavoriteCheckByItemAndUser(0L,0L )).isFalse();
    }


    @Test
    @DisplayName("좋아요 추가")
    void insertFavorite() {
        //given
        Long testUser = 2L;
        Long testItem = 1L;
        assertThat(itemFavoriteMapper.selectFavoriteCheckByItemAndUser(testItem,testUser )).isFalse();
        //when
        itemFavoriteMapper.insertFavorite(testItem, testUser);
        //then
        assertThat(itemFavoriteMapper.selectFavoriteCheckByItemAndUser(testItem,testUser )).isTrue();
    }

    @Test
    @DisplayName("좋아요 취소")
    void deleteFavorite() {
        //given
        assertThat(itemFavoriteMapper.selectFavoriteCheckByItemAndUser(itemId,userId)).isTrue();
        //when
        itemFavoriteMapper.deleteFavorite(itemId, userId);
        //then
        assertThat(itemFavoriteMapper.selectFavoriteCheckByItemAndUser(itemId, userId)).isFalse();
    }


    @Test
    @DisplayName("좋아요 숫자 확인")
    void selectFavoriteCountByItem() {
        //given
        String init = itemFavoriteMapper.selectFavoriteCountByItem(itemId);
        //when
        itemFavoriteMapper.insertFavorite(itemId, 3L);
        String result = itemFavoriteMapper.selectFavoriteCountByItem(itemId);

        //then
        assertThat(init).isEqualTo("1");
        assertThat(result).isEqualTo("2");
    }
}