package com.neconico.neconico.service.item;

import com.neconico.neconico.mapper.item.ItemFavoriteMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class ItemFavoriteService {

    private final ItemFavoriteMapper itemFavoriteMapper;

    public void addFavorite(Long itemId, Long userId) {
        if (checkItemFavorite(itemId, userId)) {
            throw new IllegalArgumentException("Favorite Exist");
        }
        itemFavoriteMapper.insertFavorite(itemId, userId);
    }

    public void cancelFavorite(Long itemId, Long userId) {
        if(!checkItemFavorite(itemId,userId)){
            throw new IllegalArgumentException("Favorite Not Exist");
        }
        itemFavoriteMapper.deleteFavorite(itemId, userId);
    }


    public boolean checkItemFavorite(Long itemId, Long userId){
        return itemFavoriteMapper.selectFavoriteCheckByItemAndUser(itemId, userId);
    }

    public String countItemFavorite(Long itemId){
        return itemFavoriteMapper.selectFavoriteCountByItem(itemId);
    }




}
