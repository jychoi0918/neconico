package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.mapper.item.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultItemService implements ItemService{

    private final ItemMapper itemMapper;

    @Override
    public ItemInfoDto findItemByItemId(Long itemId) {
        return itemMapper.selectItemByItemId(itemId);
    }

    @Override
    @Transactional
    public Long insertItem(FileResultInfoDto fileResultInfoDto,
                           Long subId,
                           ItemInfoDto itemInfoDto) {

        itemInfoDto.setCategorySubId(subId);
        itemInfoDto.setItemImgUrls(fileResultInfoDto.getFileUrls());
        itemInfoDto.setImgFileNames(fileResultInfoDto.getFileNames());
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
}
