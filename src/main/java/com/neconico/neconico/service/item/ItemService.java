package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;

public interface ItemService {

    ItemInfoDto findItemByItemId(Long itemId);

    Long insertItem(FileResultInfoDto fileResultInfoDto,
                    Long subId,
                    ItemInfoDto itemInfoDto);

    void changeItemInfo(FileResultInfoDto fileResultInfoDto,
                        ItemInfoDto itemInfoDto);

    void removeItem(Long itemId);

}
