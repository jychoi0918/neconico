package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<ItemInfoDto> selectItems();

    ItemInfoDto selectItemByItemId(@Param("itemId") Long itemId);

    void insertItems(ItemInfoDto itemInfoDto);

    void updateItemInfo(ItemInfoDto itemInfoDto);

    void deleteItem(@Param("itemId") Long itemId);


    List<ItemInfoDto> selectItemBySearch(@Param("criteria") Criteria criteria,  //main 검색바 검색
                                         @Param("search") SearchInfoDto searchInfoDto);

    int selectTotalItemCount(); //item 총 개수 count
}
