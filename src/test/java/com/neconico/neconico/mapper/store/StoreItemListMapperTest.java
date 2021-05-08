package com.neconico.neconico.mapper.store;

import com.neconico.neconico.dto.store.StoreItemPagingDto;
import com.neconico.neconico.vo.item.ItemCardVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreItemListMapperTest {

    @Autowired
    StoreItemListMapper storeItemListMapper;

    // 필요한거 - 유저ID, 종류, 페이지당 게시글 숫자, 페이지 수, 정렬 종류, 정렬 순서
    // menu name - ITEM, SALE_ITEM, PURCHASE_ITEM, FAVORITE
    // sortKind - CREATED_DATE, HITS, PRICE
    // sortOrder - ASC, DESC
    // 가져올꺼 수 - startRow = (n-1)*pageNum+1 ~ endRow = n*pageNum

    Long userId = 7L;

    @ParameterizedTest
    @CsvSource({"ITEM, CREATED_DATE, ASC, 1, 10",
            "SALE_ITEM, HITS, DESC, 20, 10"})
    @DisplayName("내상품, 판매된 상품, 구매한 상품, 찜한상품 가져오기")
    void selectStoreItemList(String menuName, String sortKind, String sortOrder, Long startRow, Long countRow) {
        //given
        StoreItemPagingDto pagingDto = new StoreItemPagingDto(userId, menuName, sortKind, sortOrder, startRow, countRow);

        //when
        List<ItemCardVo> actual = storeItemListMapper.selectStoreItemList(pagingDto);

        //then
        assertThat(actual.size()).as("배열 크기 확인").isEqualTo(countRow.intValue());
        assertThat(sortingTest(actual, sortKind, sortOrder)).as("정렬 확인").isTrue();
    }

    @Test
    @DisplayName("")
    void selectStoreQuestionList() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("")
    void test() {
        //given

        //when

        //then
    }

    static boolean sortingTest(List<ItemCardVo> input, String sortKind, String sortOrder) {
        for (int i = 0; i < input.size() - 1; i++) {
            if (sortKind.equals("CREATED_DATE")) {
                LocalDateTime a = input.get(i).getCreatedTime();
                LocalDateTime b = input.get(i + 1).getCreatedTime();
                if (sortOrder.equals("ASC")) {
                    if (b.isBefore(a)) return false;
                } else if (sortOrder.equals("DESC")) {
                    if (a.isBefore(b)) return false;
                }
            } else {
                int a = 0, b = 0;
                if (sortKind.equals("HITS")) {
                    a = Integer.parseInt(input.get(i).getViews());
                    b = Integer.parseInt(input.get(i + 1).getViews());
                } else if (sortKind.equals("PRICE")) {
                    a = Integer.parseInt(input.get(i).getPrice());
                    b = Integer.parseInt(input.get(i + 1).getPrice());
                }
                if (sortOrder.equals("ASC")) {
                    if (a > b) return false;
                } else if (sortOrder.equals("DESC")) {
                    if (a < b) return false;
                }
            }
        }
        return true;
    }


}



