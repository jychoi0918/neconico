package com.neconico.neconico.service.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class ItemFavoriteServiceTest {

    //디비에 있는 Favorite정보
    Long itemId = 6L;
    Long userId = 1L;
    //test용 유저
    Long testUser = 10L;


    @Autowired
    ItemFavoriteService itemFavoriteService;

    @Test
    @DisplayName("좋아요 추가")
    void addFavorite() {
        int init  = Integer.parseInt(itemFavoriteService.countItemFavorite(itemId));
        //when
        itemFavoriteService.addFavorite(itemId, testUser);
        int result = Integer.parseInt(itemFavoriteService.countItemFavorite(itemId));
        //then
        assertThat(result-init).isEqualTo(1);
    }

    @Test
    @DisplayName("이미 존재하는 좋아요 추가")
    void addFavoriteExist() {

        assertThatThrownBy(() -> itemFavoriteService.addFavorite(itemId, userId)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Favorite Exist");
    }


    @Test
    @DisplayName("좋아요 취소하기")
    void cancelFavorite() {
        //given
        int init  = Integer.parseInt(itemFavoriteService.countItemFavorite(itemId));
        //when
        itemFavoriteService.cancelFavorite(itemId, userId);
        int result = Integer.parseInt(itemFavoriteService.countItemFavorite(itemId));
        //then
        assertThat(init-result).isEqualTo(1);
    }

    @Test
    @DisplayName("좋아요 안되있는거 취소하기")
    void cancelFavoriteNotExist() {
        assertThatThrownBy(() -> itemFavoriteService.cancelFavorite(itemId, testUser)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Favorite Not Exist");
    }



}