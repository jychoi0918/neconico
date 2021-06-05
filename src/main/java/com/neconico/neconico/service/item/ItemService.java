package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.item.ItemInquireInfoDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.dto.item.card.ItemCardViewDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;

/**
 * main, 검색화면 item card
 * 아이템 등록, 수정, 삭제
 * 해당 item 카드 조회시 나올 item, store, 상품문의, 상품정보, 카테고리 정보
 */
public interface ItemService {

    ItemInquireInfoDto findItemByItemId(Long itemId);

    ItemInfoDto findItemByItemIdForUpdate(Long itemId);

    Long insertItem(FileResultInfoDto fileResultInfoDto,
                    Long subId,
                    ItemInfoDto itemInfoDto);

    String changeItemInfo(FileResultInfoDto fileResultInfoDto, String[] currentFiles,
                          ItemInfoDto itemInfoDto);

    void removeItem(Long itemId);

    /**
     * main페이지 추천 상품 리스트
     * 상품 조건 검색
     */
    List<ItemCardViewDto> searchItems(Criteria criteria, SearchInfoDto searchInfoDto);

    Long countTotalItems(SearchInfoDto searchInfoDto);

    List<ItemCardViewDto> searchItemsBySubCategoryId(Criteria criteria, Long subId);

    Long countTotalItemsBySubCategoryId(Long subId);

    //조회수 증가
    void incrementItemHits(Long itemId);
}
