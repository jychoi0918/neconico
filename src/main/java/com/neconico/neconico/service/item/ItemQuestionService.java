package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.item.ItemQuestionDto;
import com.neconico.neconico.mapper.item.ItemQuestionMapper;
import com.neconico.neconico.vo.item.ItemQuestionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemQuestionService {

    private final ItemQuestionMapper itemQuestionMapper;

    @Transactional(readOnly = true)
    public List<ItemQuestionVo> getQuestion(Long itemId) {
        return itemQuestionMapper.selectItemQuestionListByItemID(itemId);
    }

    public void createQuestion(ItemQuestionDto inputQuestion) {
        itemQuestionMapper.insertItemQuestion(inputQuestion);
    }

    public void createComment(ItemQuestionDto inputComment) {
        itemQuestionMapper.insertItemQuestionComment(inputComment);
    }

    public void modifyQuestion(Long questionId, String content, String kind) {
        itemQuestionMapper.updateItemQuestion(questionId, content, kind);
    }

    public void deleteQuestion(Long questionId, String kind) {
        itemQuestionMapper.deleteItemQuestion(questionId, kind);
    }

}
