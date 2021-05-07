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

    List<ItemQuestionVo> getQuestion(Long itemId) {
        return itemQuestionMapper.selectItemQuestionListByItemID(itemId);
    }

    void createQuestion(ItemQuestionDto inputQuestion) {
        itemQuestionMapper.insertItemQuestion(inputQuestion);
    }

    void createComment(ItemQuestionDto inputComment) {
        itemQuestionMapper.insertItemQuestionComment(inputComment);
    }

    void modifyQuestion(Long questionId, String content) {
        itemQuestionMapper.updateItemQuestion(questionId, content, "ITEM_QUESTION");
    }

    void modifyComment(Long commentId, String content) {
        itemQuestionMapper.updateItemQuestion(commentId, content, "QUESTION_COMMENT");
    }

    void deleteQuestion(Long questionId) {
        itemQuestionMapper.deleteItemQuestion(questionId, "ITEM_QUESTION");
    }

    void deleteComment(Long commentId) {
        itemQuestionMapper.deleteItemQuestion(commentId, "QUESTION_COMMENT");
    }

    boolean checkUserNWriterId(Long userId, Long writerId){
        if(userId == writerId) return true;
        return false;
    }


}
