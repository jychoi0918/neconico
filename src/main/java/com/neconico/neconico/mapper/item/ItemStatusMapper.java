package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.item.ItemStatusDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemStatusMapper {

    String selectItemStatusByItemId(@Param("itemId") Long itemId);

    void updateItemStatus(ItemStatusDto itemStatusDto);

    void insertPurchaseItem(@Param("itemId") Long itemId, @Param("buyerId") Long buyerId);

    void insertSaleItem(@Param("itemId") Long itemId, @Param("sellerId") Long sellerId);

}
