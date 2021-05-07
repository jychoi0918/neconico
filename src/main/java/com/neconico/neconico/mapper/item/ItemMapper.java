package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.item.ItemInfoDto;
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
}
