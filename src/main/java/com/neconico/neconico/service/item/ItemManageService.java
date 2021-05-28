package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.dto.store.StoreItemSortingDto;
import com.neconico.neconico.mapper.store.StoreItemListMapper;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemManageService {

    @Autowired
    StoreItemListMapper storeItemListMapper;

    private final int pageSize = 10;

    public Criteria setCriteria(int currentPage, String sortingColumn, String requestOrder) {
        Criteria cri = new Criteria();
        cri.setCurrentPage(currentPage);
        cri.setContentPerPage(pageSize);
        cri.setSortingColumn(sortingColumn);
        cri.setRequestOrder(requestOrder);
        return cri;
    }

    public Pagination setPagiantion(Long userId, Criteria cri) {
        Pagination pagination = new Pagination(cri, storeItemListMapper.countStoreMyItem(userId).intValue(), pageSize);
        return pagination;
    }

    @Transactional(readOnly = true)
    public List<ItemCardDto> getStoreMyItemList(Long userId, Criteria cri) {
        StoreItemSortingDto storeItemSortingDto = new StoreItemSortingDto(userId,
                cri.getSortingColumn(),
                cri.getRequestOrder(),
                Long.valueOf(cri.getPageStart()),
                Long.valueOf(cri.getContentPerPage()));
        return storeItemListMapper.selectStoreMyItemList(storeItemSortingDto);
    }


}
