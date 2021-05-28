package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.item.ItemQuestionDto;
import com.neconico.neconico.dto.item.ItemQuestionResponseDto;
import com.neconico.neconico.dto.item.QuestionCommentResponseDto;
import com.neconico.neconico.dto.item.card.ItemQuestionCardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//kind는 분기 -> 문의, 문의 댓글
//              * insert는 auto_increment때문에 못나눠줌
//objectId는 대상 ID -> 문의일떄 문의 ID, 댓글일때 댓글 ID

@Mapper
public interface ItemQuestionMapper {

    ItemQuestionDto selectItemQuestionById(@Param("objectId") Long objectId, @Param("kind") String kind);

    List<ItemQuestionCardDto> selectItemQuestionListByItemID(@Param("itemId") Long itemId);

    ItemQuestionResponseDto selectItemQuestionResponseById(@Param("itemQuestionId") Long itemQuestionId);

    void insertItemQuestion(ItemQuestionDto itemQuestionDto);

    void updateItemQuestion(@Param("itemQuestionId") Long itemQuestionId, @Param("content") String content);

    void deleteItemQuestion(@Param("itemQuestionId") Long itemQuestionId);

    QuestionCommentResponseDto selectQuestionCommentById(@Param("questionCommentId") Long itemQuestionId);

    void insertQuestionComment(ItemQuestionDto itemQuestionDto);

    void updateQuestionComment(@Param("questionCommentId") Long questionCommentId, @Param("content") String content);

    void deleteQuestionComment(@Param("questionCommentId") Long questionCommentId);

}
