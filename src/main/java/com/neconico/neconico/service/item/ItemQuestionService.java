package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.item.ItemQuestionDto;
import com.neconico.neconico.dto.item.ItemQuestionResponseDto;
import com.neconico.neconico.mapper.item.ItemQuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemQuestionService {

    private final ItemQuestionMapper itemQuestionMapper;

    @Transactional
    public ItemQuestionResponseDto createItemQuestion(Long itemId, Long userId, String content) {
        ItemQuestionDto itemQuestionDto = new ItemQuestionDto(itemId, userId, content);
        itemQuestionMapper.insertItemQuestion(itemQuestionDto);
        return getItemQuestion(itemQuestionDto.getId());
    }


    @Transactional
    public ItemQuestionResponseDto modifyItemQuestion(Long itemQuestionId, String content) {
        itemQuestionMapper.updateItemQuestion(itemQuestionId, content);
        return getItemQuestion(itemQuestionId);
    }

    @Transactional
    public void deleteItemQuestion(Long itemQuestionId) {
        itemQuestionMapper.deleteItemQuestion(itemQuestionId);
    }

    public ItemQuestionResponseDto getItemQuestion(Long itemQuestionId) {
        return itemQuestionMapper.selectItemQuestionResponseById(itemQuestionId);
    }

}
