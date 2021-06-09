package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;

import java.util.List;

public interface ItemManageService{

    List<ItemCardDto> getStoreMyItemList(Long userId, Criteria cri);
    Criteria setCriteria(Long currentPage, String sortingColumn, String requestOrder);
    Pagination setPagiantion(Long userId, Criteria cri);

}
