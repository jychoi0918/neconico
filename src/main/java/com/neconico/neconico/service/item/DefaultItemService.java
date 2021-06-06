package com.neconico.neconico.service.item;

import com.neconico.neconico.Maker.ItemDateDifferenceMaker;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.item.ItemInquireInfoDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.dto.item.card.ItemCardSearchViewDto;
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
    public String changeItemInfo(FileResultInfoDto fileResultInfoDto, String[] currentImgUrls, ItemInfoDto itemInfoDto) {
        StringBuffer resultItemUrls = new StringBuffer();
        StringBuffer resultItemFileNames = new StringBuffer();
        StringBuffer deleteFileNames = new StringBuffer();

        String[] itemFileNames = itemInfoDto.getImgFileNames().split(">");

        itemInfoDto.setModifiedDate(LocalDateTime.now()); //변경시간 설정

        //새로 등록한 파일이 있을경우
        if(fileResultInfoDto != null) {
            setChangeFileAndUrlResult(
                    resultItemUrls, resultItemFileNames,
                    deleteFileNames, itemFileNames, currentImgUrls);

            //새로 등록한 파일 urls, names 추가
            resultItemUrls.append(fileResultInfoDto.getFileUrls());
            resultItemFileNames.append(fileResultInfoDto.getFileNames());

            itemInfoDto.setItemImgUrls(resultItemUrls.toString());
            itemInfoDto.setImgFileNames(resultItemFileNames.toString());

            itemMapper.updateItemInfo(itemInfoDto);

            return deleteFileNames.toString();
        }

        setChangeFileAndUrlResult(
                resultItemUrls, resultItemFileNames,
                deleteFileNames, itemFileNames, currentImgUrls);

        itemInfoDto.setItemImgUrls(
                resultItemUrls
                        .deleteCharAt(resultItemUrls.length() - 1) //마지막 '>' 제거
                        .toString());

        itemInfoDto.setImgFileNames(
                resultItemFileNames
                        .deleteCharAt(resultItemFileNames.length() - 1) //마지막 '>' 제거
                        .toString());

        itemMapper.updateItemInfo(itemInfoDto);

        return deleteFileNames.toString();
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
    public List<ItemCardSearchViewDto> searchItems(Criteria criteria, SearchInfoDto searchInfoDto) {
        if( searchInfoDto.getSearchText() == null) {
            searchInfoDto.setSearchText("");
        }

        List<ItemCardSearchViewDto> itemCardDtoList = itemMapper.selectItemBySearch(setCriteria(criteria), searchInfoDto);

        return createItemCardViewDto(itemCardDtoList);
    }

    @Override
    public Long countTotalItems(SearchInfoDto searchInfoDto) {
        return itemMapper.selectTotalItemCount(searchInfoDto);
    }

    @Override
    public List<ItemCardSearchViewDto> searchItemsBySubCategoryId(Criteria criteria, Long subId) {
        List<ItemCardSearchViewDto> itemCardDtoList = itemMapper.selectItemsBySubCategoryId(setCriteria(criteria), subId);

        return createItemCardViewDto(itemCardDtoList);
    }

    @Override
    public Long countTotalItemsBySubCategoryId(Long subId) {
        return itemMapper.selectTotalItemCountBySubCategoryId(subId);
    }

    @Override
    @Transactional
    public void incrementItemHits(Long itemId) {
        itemMapper.updateItemHits(itemId);
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

    private List<ItemCardSearchViewDto> createItemCardViewDto(List<ItemCardSearchViewDto> itemCardSearchViewDtoList) {

        itemCardSearchViewDtoList.stream()
                .forEach(i -> i.setBetweenDate(ItemDateDifferenceMaker.between(i.getCreatedTime())));

        itemCardSearchViewDtoList.stream()
                .filter(i -> i.getTitle().length() > 14)
                .forEach(i -> i.setTitle(i.getTitle().substring(0, 11) + "..."));

        return itemCardSearchViewDtoList;
    }

    private void setChangeFileAndUrlResult(StringBuffer resultItemUrls, StringBuffer resultItemFileNames, StringBuffer deleteFileNames,
                                           String[] itemFileNames, String[] currentImgUrls) {

        for (int i = 0; i < currentImgUrls.length; i++) {
            if ("".equals(currentImgUrls[i])) {
                deleteFileNames.append(itemFileNames[i]);
                deleteFileNames.append(">");
            } else {
                resultItemUrls.append(currentImgUrls[i]);
                resultItemUrls.append(">");
                resultItemFileNames.append(itemFileNames[i]);
                resultItemFileNames.append(">");
            }
        }
    }
}
