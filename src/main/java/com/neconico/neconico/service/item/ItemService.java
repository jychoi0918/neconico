package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;

/**
 * main, 검색화면 item card
 * 아이템 등록, 수정, 삭제
 * 해당 item 카드 조회시 나올 item, store, 상품문의, 상품정보
 */
public interface ItemService {

    ItemInfoDto findItemByItemId(Long itemId);

    Long insertItem(FileResultInfoDto fileResultInfoDto,
                    Long subId,
                    ItemInfoDto itemInfoDto);

    void changeItemInfo(FileResultInfoDto fileResultInfoDto,
                        ItemInfoDto itemInfoDto);

    void removeItem(Long itemId);

    //아이템 조회
    List<ItemCardDto> searchItems(Criteria criteria, SearchInfoDto searchInfoDto);

    Long countTotalItems();
}
