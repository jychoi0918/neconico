package com.neconico.neconico.mapper.store;


import com.neconico.neconico.dto.store.StoreItemSortingDto;
import com.neconico.neconico.vo.item.ItemCardVo;
import com.neconico.neconico.vo.store.StoreQuestionCardVo;
import com.neconico.neconico.vo.store.StoreReviewCardVo;
import com.neconico.neconico.vo.store.StoreTradeCardVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreItemListMapperTest {

    @Autowired
    StoreItemListMapper storeItemListMapper;

    // 필요한거 - 유저ID, 종류, 페이지당 게시글 숫자, 페이지 수, 정렬 종류, 정렬 순서
    // menu name - ITEM, SALE_ITEM, PURCHASE_ITEM, FAVORITE
    // sortKind - price, created, views
    // sortOrder - ASC, DESC
    // 가져올꺼 수 - startRow = (n-1)*pageNum+1 ~ endRow = n*pageNum

    @ParameterizedTest(name = "유저아이디 {0}, 정렬종류 {1}, 정렬순서 {2}, 시작위치 {3}, 가져올갯수 {4}")
    @DisplayName("내상품 가져오기 테스트")
    @CsvSource({"7, 'PRICE', 'ASC', 20, 20",
            "7, 'VIEWS', 'DESC', 10, 20"})
    void selectStoreMyItemListTest(Long userId, String sortKind, String sortOrder, Long startRow, Long countRow) {
        //given
        StoreItemSortingDto input = new StoreItemSortingDto(userId, sortKind, sortOrder, startRow, countRow);
        //when
        List<ItemCardVo> result = storeItemListMapper.selectStoreMyItemList(input);
        //then
        assertThat(result.size()).isEqualTo(countRow.intValue());
        assertThat(sortingTest(result, sortKind, sortOrder)).isTrue();
    }

    @ParameterizedTest(name = "유저아이디 {0}, 정렬종류 {1}, 정순서 {2}, 시작위치 {3}, 가져올갯수 {4}")
    @DisplayName("좋아요 가져오기 테스트")
    @CsvSource({"1, 'PRICE', 'ASC', 20, 10",
            "1, 'VIEWS', 'DESC', 10, 20"})
    void selectStoreFavoriteListTest(Long userId, String sortKind, String sortOrder, Long startRow, Long countRow) {
        //given
        StoreItemSortingDto input = new StoreItemSortingDto(userId, sortKind, sortOrder, startRow, countRow);
        //when
        List<ItemCardVo> result = storeItemListMapper.selectStoreFavoriteList(input);
        //then
        assertThat(result.size()).isEqualTo(countRow.intValue());
        assertThat(sortingTest(result, sortKind, sortOrder)).isTrue();
    }

    @ParameterizedTest(name = "유저아이디 {0}, 정렬종류 {1}, 정렬순서 {2}, 시작위치 {3}, 가져올갯수 {4}")
    @DisplayName("판매된 상품 테스트")
    @CsvSource({"7, 'PRICE', 'ASC', 20, 40",
            "7, 'VIEWS', 'DESC', 10, 30"})
    void selectStoreSoldItemListTest(Long userId, String sortKind, String sortOrder, Long startRow, Long countRow) {
        //given
        StoreItemSortingDto input = new StoreItemSortingDto(userId, sortKind, sortOrder, startRow, countRow);
        //when
        List<ItemCardVo> result = storeItemListMapper.selectStoreSoldItemList(input);
        //then
        assertThat(result.size()).isEqualTo(countRow.intValue());
        assertThat(sortingTest(result, sortKind, sortOrder)).isTrue();
    }

    @ParameterizedTest(name = "유저아이디 {0}, 정렬종류 {1}, 정렬순서 {2}, 시작위치 {3}, 가져올갯수 {4}")
    @DisplayName("구매한 상품 테스트")
    @CsvSource({"3, 'PRICE', 'ASC', 0, 60"})
    void selectStorePurchasedItemList(Long userId, String sortKind, String sortOrder, Long startRow, Long countRow) {
        //given
        StoreItemSortingDto input = new StoreItemSortingDto(userId, sortKind, sortOrder, startRow, countRow);
        //when
        List<ItemCardVo> result = storeItemListMapper.selectStorePurchasedItemList(input);
        //then
        assertThat(result.size()).isEqualTo(countRow.intValue());
        assertThat(sortingTest(result, sortKind, sortOrder)).isTrue();
    }

    @ParameterizedTest(name = "유저아이디 {0}, 정렬종류 {1}, 정렬순서 {2}, 시작위치 {3}, 가져올갯수 {4}, db에 존재하는 질문 갯수 {5} ")
    @DisplayName("구매한 상품 테스트")
    @CsvSource({"1, 'PRICE', 'ASC', 0, 10, 10"})
    void selectStoreQuestionListTest(Long userId, String sortKind, String sortOrder, Long startRow, Long countRow, int count) {
        //given
        StoreItemSortingDto input = new StoreItemSortingDto(userId, sortKind, sortOrder, startRow, countRow);
        //when
        List<StoreQuestionCardVo> result = storeItemListMapper.selectStoreQuestionList(input);
        //then

        assertThat(result.size()).isEqualTo(count);

        List<ItemCardVo> cardResult = new ArrayList<>();
        result.forEach(o -> cardResult.add(o.getItem()));
        assertThat(sortingTest(cardResult, sortKind, sortOrder)).isTrue();
    }

    @ParameterizedTest(name = "유저아이디 {0}, 정렬종류 {1}, 정렬순서 {2}, 시작위치 {3}, 가져올갯수 {4}, 데이터 갯수 {5} ")
    @DisplayName("상점 후기 테스트")
    @CsvSource({"7, 'PRICE', 'ASC', 10, 10, 10"})
    void selectStoreReviewListTest(Long userId, String sortKind, String sortOrder, Long startRow, Long countRow, int count) {
        //given
        StoreItemSortingDto input = new StoreItemSortingDto(userId, sortKind, sortOrder, startRow, countRow);
        //when
        List<StoreReviewCardVo> result = storeItemListMapper.selectStoreReviewList(input);
        //then
        assertThat(result.size()).isEqualTo(count);

        List<ItemCardVo> cardResult = new ArrayList<>();
        result.forEach(o -> cardResult.add(o.getItem()));
        assertThat(sortingTest(cardResult, sortKind, sortOrder)).isTrue();
    }

    @ParameterizedTest(name = "유저아이디 {0}, 정렬종류 {1}, 정렬순서 {2}, 시작위치 {3}, 가져올갯수 {4}, 데이터 갯수 {5} ")
    @DisplayName("상점 거래 테스트")
    @CsvSource({"2, 'PRICE', 'ASC', 0, 10, 5"})
    void selectStoreTradeListTest(Long userId, String sortKind, String sortOrder, Long startRow, Long countRow, int count) {
        //given
        StoreItemSortingDto input = new StoreItemSortingDto(userId, sortKind, sortOrder, startRow, countRow);
        //when
        List<StoreTradeCardVo> result = storeItemListMapper.selectStoreTradeList(input);
        //then
        assertThat(result.size()).isEqualTo(count);

        List<ItemCardVo> cardResult = new ArrayList<>();
        result.forEach(o -> cardResult.add(o.getItem()));
        assertThat(sortingTest(cardResult, sortKind, sortOrder)).isTrue();
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



