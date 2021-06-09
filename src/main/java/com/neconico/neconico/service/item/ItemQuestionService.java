package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.item.ItemQuestionResponseDto;

public interface ItemQuestionService {

    ItemQuestionResponseDto createItemQuestion(Long itemId, Long userId, String content);
    ItemQuestionResponseDto modifyItemQuestion(Long itemQuestionId, String content);
    void deleteItemQuestion(Long itemQuestionId);
    ItemQuestionResponseDto getItemQuestion(Long itemQuestionId);
}
