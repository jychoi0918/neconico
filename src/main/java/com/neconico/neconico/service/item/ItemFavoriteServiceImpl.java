package com.neconico.neconico.service.item;

import com.neconico.neconico.mapper.item.ItemFavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemFavoriteServiceImpl implements ItemFavoriteService {

    private final ItemFavoriteMapper itemFavoriteMapper;

    @Override
    @Transactional
    public void addFavorite(Long itemId, Long userId) {
        if (checkItemFavorite(itemId, userId)) {
            throw new IllegalArgumentException("Favorite Exist");
        }
        itemFavoriteMapper.insertFavorite(itemId, userId);
    }

    @Override
    @Transactional
    public void cancelFavorite(Long itemId, Long userId) {
        if(!checkItemFavorite(itemId,userId)){
            throw new IllegalArgumentException("Favorite Not Exist");
        }
        itemFavoriteMapper.deleteFavorite(itemId, userId);
    }

    @Override
    public boolean checkItemFavorite(Long itemId, Long userId){
        return itemFavoriteMapper.selectFavoriteCheckByItemAndUser(itemId, userId);
    }

    @Override
    public String countItemFavorite(Long itemId){
        return itemFavoriteMapper.selectFavoriteCountByItem(itemId);
    }

}
