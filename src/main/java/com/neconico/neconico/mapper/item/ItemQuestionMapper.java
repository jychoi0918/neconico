package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.item.ItemQuestionDto;
import com.neconico.neconico.vo.item.ItemQuestionCardDto;
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

    void insertItemQuestion(ItemQuestionDto itemQuestionDto);

    void insertItemQuestionComment(ItemQuestionDto itemQuestionDto);

    void updateItemQuestion(@Param("objectId") Long objectId, @Param("content") String content, @Param("kind") String kind);

    void deleteItemQuestion(@Param("objectId") Long objectId, @Param("kind") String kind);

}
