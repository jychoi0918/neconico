package com.neconico.neconico.mapper.item;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemFavoriteMapper {

    //좋아요 등록
    void insertFavorite(@Param("itemId") Long itemId, @Param("userId") Long userId);

    //좋아요 취소
    void deleteFavorite(@Param("itemId") Long itemId, @Param("userId") Long userId);

    //본인이 체크 했는지 안했는지를 확인
    boolean selectFavoriteCheckByItemAndUser(@Param("itemId") Long itemId, @Param("userId") Long userId);

    //아이템에 대해서 좋아요 숫자를 확인
    String selectFavoriteCountByItem(@Param("itemId") Long itemId);

}
