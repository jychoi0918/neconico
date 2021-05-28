package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.item.ItemQuestionDto;
import com.neconico.neconico.dto.item.ItemQuestionResponseDto;
import com.neconico.neconico.dto.item.QuestionCommentResponseDto;
import com.neconico.neconico.mapper.item.ItemQuestionMapper;
import com.neconico.neconico.dto.item.card.ItemQuestionCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public QuestionCommentResponseDto createQuestionCommentId(Long itemId,Long userId, String content) {
        ItemQuestionDto itemQuestionDto = new ItemQuestionDto(itemId, userId, content);
        itemQuestionMapper.insertQuestionComment(itemQuestionDto);
        return getQuestionComment(itemQuestionDto.getId());
    }

    @Transactional
    public QuestionCommentResponseDto modifyQuestionComment(Long questionCommentId, String content) {
        itemQuestionMapper.updateItemQuestion(questionCommentId, content);
        return getQuestionComment(questionCommentId);
    }

    @Transactional
    public void deleteQuestionComment(Long questionCommentId) {
        itemQuestionMapper.deleteItemQuestion(questionCommentId);
    }

    public QuestionCommentResponseDto getQuestionComment(Long questionCommentId) {
        return itemQuestionMapper.selectQuestionCommentById(questionCommentId);
    }


}
