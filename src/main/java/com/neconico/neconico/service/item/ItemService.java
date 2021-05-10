package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;

public interface ItemService {

    ItemInfoDto findItemByItemId(Long itemId);

    Long insertItem(FileResultInfoDto fileResultInfoDto,
                    Long subId,
                    ItemInfoDto itemInfoDto);

    void changeItemInfo(FileResultInfoDto fileResultInfoDto,
                        ItemInfoDto itemInfoDto);

    void removeItem(Long itemId);

    //아이템 조회회
   List<ItemInfoDto> searchItems(Criteria criteria, SearchInfoDto searchInfoDto);

    int countTotalItems();
}
