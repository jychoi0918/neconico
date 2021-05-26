package com.neconico.neconico.service.item;

import com.neconico.neconico.Maker.ItemDateDifferenceMaker;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.item.ItemInquireInfoDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.dto.item.card.ItemCardViewDto;
import com.neconico.neconico.mapper.item.ItemMapper;
import com.neconico.neconico.paging.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultItemService implements ItemService{

    private final ItemMapper itemMapper;

    @Override
    public ItemInquireInfoDto findItemByItemId(Long itemId) {
        return itemMapper.selectItemByItemId(itemId);
    }

    @Override
    public ItemInfoDto findItemByItemIdForUpdate(Long itemId) {
        return itemMapper.selectItemByItemIdForUpdate(itemId);
    }

    @Override
    @Transactional
    public Long insertItem(FileResultInfoDto fileResultInfoDto,
                           Long subId,
                           ItemInfoDto itemInfoDto) {
        setDateAndFile(fileResultInfoDto, itemInfoDto);
        itemInfoDto.setCategorySubId(subId);
        itemInfoDto.setSaleStatus("판매 중");

        itemMapper.insertItems(itemInfoDto);

        return itemInfoDto.getItemId();
    }

    @Override
    @Transactional
    public void changeItemInfo(FileResultInfoDto fileResultInfoDto, ItemInfoDto itemInfoDto) {
        itemInfoDto.setItemImgUrls(fileResultInfoDto.getFileUrls());
        itemInfoDto.setImgFileNames(fileResultInfoDto.getFileNames());
        itemMapper.updateItemInfo(itemInfoDto);
    }

    @Override
    @Transactional
    public void removeItem(Long itemId) {
        itemMapper.deleteItem(itemId);
    }

    /**
     * main페이지 추천 상품 리스트
     * 상품 조건 검색
     */
    @Override
    public List<ItemCardViewDto> searchItems(Criteria criteria, SearchInfoDto searchInfoDto) {
        if( searchInfoDto.getSearchText() == null) {
            searchInfoDto.setSearchText("");
        }

        List<ItemCardDto> itemCardDtoList = itemMapper.selectItemBySearch(setCriteria(criteria), searchInfoDto);

        return createItemCardViewDto(itemCardDtoList);
    }

    @Override
    public Long countTotalItems() {
        return itemMapper.selectTotalItemCount();
    }

    private Criteria setCriteria(Criteria criteria) {
        int currentPage = criteria.getCurrentPage();
        currentPage = currentPage == 0 ? currentPage + 1 : currentPage;
        criteria.setCurrentPage(currentPage);

        //item page 설정
        criteria.setContentPerPage(20);
        criteria.setRequestOrder("desc");
        criteria.setSortingColumn("created_date");

        return criteria;
    }

    private void setDateAndFile(FileResultInfoDto fileResultInfoDto, ItemInfoDto itemInfoDto) {
        itemInfoDto.setItemImgUrls(fileResultInfoDto.getFileUrls());
        itemInfoDto.setImgFileNames(fileResultInfoDto.getFileNames());
        itemInfoDto.setCreatedDate(LocalDateTime.now());
        itemInfoDto.setModifiedDate(LocalDateTime.now());
    }

    private List<ItemCardViewDto> createItemCardViewDto(List<ItemCardDto> itemCardDtoList) {
        List<ItemCardViewDto> itemCardViewDtoList = itemCardDtoList.stream()
                .map(ItemCardViewDto::new)
                .collect(Collectors.toList());

        itemCardViewDtoList.stream()
                .forEach(i -> i.setBetweenDate(ItemDateDifferenceMaker.between(i.getCreatedTime())));

        itemCardViewDtoList.stream()
                .filter(i -> i.getTitle().length() > 14)
                .forEach(i -> i.setTitle(i.getTitle().substring(0, 11) + "..."));


        return itemCardViewDtoList;
    }

}
