package com.neconico.neconico.service.item;

public interface ItemFavoriteService {

    void addFavorite(Long itemId, Long userId);
    void cancelFavorite(Long itemId, Long userId);
    boolean checkItemFavorite(Long itemId, Long userId);
    String countItemFavorite(Long itemId);

}
