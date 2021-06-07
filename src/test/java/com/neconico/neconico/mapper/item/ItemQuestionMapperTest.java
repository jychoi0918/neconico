package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.item.ItemQuestionDto;
import com.neconico.neconico.dto.item.ItemQuestionResponseDto;
import com.neconico.neconico.dto.item.card.ItemQuestionCardDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        List<ItemQuestionCardDto> list = itemQuestionMapper.selectItemQuestionListByItemID(itemId);

        assertThat(list.size()).isEqualTo(initQuestionSize);
        assertThat(list.get(1).getCommentList().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("상품문의 삽입")
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
    @DisplayName("문의 선택")
    void selectItemQuestionResponseById() {
        ItemQuestionResponseDto result = itemQuestionMapper.selectItemQuestionResponseById(6L);

        assertThat(result.getContent()).isEqualTo("content6");
    }

    @Test
    @DisplayName("문의 삽입")
    void insertItemQuestion() {
        ItemQuestionDto itemQuestionDto = new ItemQuestionDto(1L,7L,"test");
        itemQuestionMapper.insertItemQuestion(itemQuestionDto);
        Long id = itemQuestionDto.getId();

        ItemQuestionResponseDto result = itemQuestionMapper.selectItemQuestionResponseById(id);

        assertThat(result.getContent()).isEqualTo("test");
    }

    @Test
    @DisplayName("문의 수정")
    void updateItemQuestion() {
        ItemQuestionDto itemQuestionDto = new ItemQuestionDto(1L,7L,"test");
        itemQuestionMapper.insertItemQuestion(itemQuestionDto);
        Long id = itemQuestionDto.getId();

        itemQuestionMapper.updateItemQuestion(id, "update");

        ItemQuestionResponseDto result = itemQuestionMapper.selectItemQuestionResponseById(id);

        assertThat(result.getContent()).isEqualTo("update");

    }

    @Test
    @DisplayName("문의 삭제")
    void deleteItemQuestion() {
        ItemQuestionDto itemQuestionDto = new ItemQuestionDto(1L,7L,"test");
        itemQuestionMapper.insertItemQuestion(itemQuestionDto);
        Long id = itemQuestionDto.getId();

        itemQuestionMapper.deleteItemQuestion(id);

        ItemQuestionResponseDto result = itemQuestionMapper.selectItemQuestionResponseById(id);

        assertThat(result).isNull();
    }

}