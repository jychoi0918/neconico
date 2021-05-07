package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.item.ItemQuestionDto;
import com.neconico.neconico.vo.item.ItemQuestionVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemQuestionMapperTest {

    @Autowired
    ItemQuestionMapper itemQuestionMapper;

    Long itemId = 1L;
    Long testId = 2L;
    Long testQuestionId = 1L;
    ItemQuestionDto testQuestion = new ItemQuestionDto(itemId, testId, "testContent");
    int initQuestionSize = 10;
    ItemQuestionDto testQuestionComment = new ItemQuestionDto(testQuestionId, testId, "testContent");

    @Test
    @DisplayName("문의 및 댓글 한개 가져오기")
    void selectQuestionNCommentOne() {
        //given
        ItemQuestionDto question = new ItemQuestionDto(1L, 2L, "content1");
        ItemQuestionDto comment = new ItemQuestionDto(2L, 3L, "content101");

        //when
        ItemQuestionDto questionResult = itemQuestionMapper.selectItemQuestionById(1L, "ITEM_QUESTION");
        ItemQuestionDto commentResult = itemQuestionMapper.selectItemQuestionById(1L, "QUESTION_COMMENT");

        //then
        assertThat(questionResult).usingRecursiveComparison().isEqualTo(question);
        assertThat(commentResult).usingRecursiveComparison().isEqualTo(comment);

    }


    @Test
    @DisplayName("문의 리스트 가져오기 테스트")
    void selectListTest() {
        //when
        List<ItemQuestionVo> list = itemQuestionMapper.selectItemQuestionListByItemID(itemId);

        assertThat(list.size()).isEqualTo(initQuestionSize);
        assertThat(list.get(1).getCommentList().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("상품 문의 작성 테스트")
    void insertItemQuestionTest() {
        //given
        int init = itemQuestionMapper.selectItemQuestionListByItemID(itemId).size();
        //when
        itemQuestionMapper.insertItemQuestion(testQuestion);
        int result = itemQuestionMapper.selectItemQuestionListByItemID(itemId).size();

        //then
        assertThat(result - init).isEqualTo(1);
    }

    @Test
    @DisplayName("상품 문의 작성 테스트")
    void insertItemQuestionCommentTest() {
        //given
        int init = itemQuestionMapper.selectItemQuestionListByItemID(itemId).get(0).getCommentList().size();
        //when
        itemQuestionMapper.insertItemQuestionComment(testQuestionComment);
        int result = itemQuestionMapper.selectItemQuestionListByItemID(itemId).get(0).getCommentList().size();

        //then
        assertThat(result - init).isEqualTo(1);
    }

    Long existId = 1L;

    @Test
    @DisplayName("업데이트")
    void updateTest() {
        //given
        ItemQuestionDto questionInit = itemQuestionMapper.selectItemQuestionById(existId, "ITEM_QUESTION");
        ItemQuestionDto commentInit = itemQuestionMapper.selectItemQuestionById(existId, "QUESTION_COMMENT");
        //when
        itemQuestionMapper.updateItemQuestion(existId, "changeQuestion", "ITEM_QUESTION");
        itemQuestionMapper.updateItemQuestion(existId, "changeComment", "QUESTION_COMMENT");
        //then
        ItemQuestionDto questionResult = itemQuestionMapper.selectItemQuestionById(existId, "ITEM_QUESTION");
        ItemQuestionDto commentResult = itemQuestionMapper.selectItemQuestionById(existId, "QUESTION_COMMENT");

        assertThat(questionResult).usingRecursiveComparison().isNotEqualTo(questionInit);
        assertThat(commentResult).usingRecursiveComparison().isNotEqualTo(commentInit);
    }

    @Test
    @DisplayName("삭제")
    void deleteTest() {
        //given
        ItemQuestionDto questionInit = itemQuestionMapper.selectItemQuestionById(existId, "ITEM_QUESTION");
        ItemQuestionDto commentInit = itemQuestionMapper.selectItemQuestionById(existId, "QUESTION_COMMENT");
        //when
        itemQuestionMapper.deleteItemQuestion(existId, "ITEM_QUESTION");
        itemQuestionMapper.deleteItemQuestion(existId, "QUESTION_COMMENT");

        ItemQuestionDto questionResult = itemQuestionMapper.selectItemQuestionById(existId, "ITEM_QUESTION");
        ItemQuestionDto commentResult = itemQuestionMapper.selectItemQuestionById(existId, "QUESTION_COMMENT");

        //then
        assertThat(questionInit).isNotNull();
        assertThat(commentInit).isNotNull();
        assertThat(questionResult).isNull();
        assertThat(commentResult).isNull();
    }


}