package com.neconico.neconico.mapper.category;

import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.dto.category.CategorySubInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    @DisplayName("카테고리 모든정보를 DB에서 가져온다.")
    void find_category_info_all() {
        //given
        List<CategoryInfoDto> categoryInfoDtoList = categoryMapper.selectCategoryInfos();

        //when
        CategoryInfoDto categoryInfoDto = categoryInfoDtoList.get(0);

        //then
        assertThat(categoryInfoDtoList)
                .isNotNull();

        assertThat(categoryInfoDto)
                .extracting("categorySubInfoDtoList")
                .isNotNull();

    }

    @Test
    @DisplayName("카테고리 중분류를 DB에서 가져온다.")
    void find_category_sub_all() {
        List<CategorySubInfoDto> categorySubInfoDtoList = categoryMapper.selectCategorySubs();

        assertThat(categorySubInfoDtoList).isNotEmpty();

    }

    @ParameterizedTest(name = "{index} -> 카테고리가 {0}일때")
    @DisplayName("카테고리 이름이 주어졌을때 DB에서 주어진 이름에 해당하는 카테고리 정보를 가져온다.")
    @ValueSource(
            strings = {"여성의류", "남성의류", "신발", "컴퓨터", "모바일 제품",
            "카메라", "도서", "티켓", "음반", "주방용품", "식류", "가구"})
    void given_a_category_find_category_sub(String subName) {
        //given
        CategorySubInfoDto categorySubInfoDto = categoryMapper.selectCategorySubByName(subName);

        //when
        String findSubName = categorySubInfoDto.getName();

        //then
        assertThat(findSubName).isEqualTo(subName);

    }

    @ParameterizedTest(name = "{index} -> 카테고리 {0}가 대분류에 추가될때")
    @DisplayName("대분류 카테고리에 새로운 대분류 추가")
    @ValueSource(strings = {"문구", "여행쿠폰", "취미/반려"})
    void given_a_category_inser_category_main(String mainName) {
        //given
        List<CategoryInfoDto> currentCategoryMains = categoryMapper.selectCategoryInfos();
        categoryMapper.insertCategoryMain(mainName);

        //when
        List<CategoryInfoDto> categoryInfoDtos = categoryMapper.selectCategoryInfos();
        int cateGorySize = categoryInfoDtos.size();

        //then
        assertThat(cateGorySize).isGreaterThan(currentCategoryMains.size());

    }

    @ParameterizedTest(name = "{index} -> mainId가 {0}이고 추가될 중분류가 {1}일때")
    @DisplayName("중분류 카테고리에 새로운 중분류 추가")
    @CsvSource({"1, '언더웨어'","2, '음향기기'","3, '쥬얼리'","4, '침구/커튼'"})
    void add_a_new_subcategory_to_the_subcategory(Long mainId, String subName) {

        categoryMapper.insertCategorySub(mainId, subName);
        CategorySubInfoDto categorySubInfoDto = categoryMapper.selectCategorySubByName(subName);

        assertThat(categorySubInfoDto).isNotNull();

    }

    @ParameterizedTest(name = "{index} -> 대분류 아이디가 {0}이고 바꿀 이름이 {1}일때")
    @DisplayName("대분류 카테고리 이름변경")
    @CsvSource({"1, '패션의류'", "2, '컴퓨타'", "3, '뷰티'", "4, '홈데코'"})
    void change_the_name_of_the_main_category (Long mainId, String mainName) {
        //given
        categoryMapper.updateCategoryMainByIdAndName(mainId, mainName);

        //when
        List<CategoryInfoDto> categoryInfoDtoList = categoryMapper.selectCategoryInfos();

        //then
        assertThat(categoryInfoDtoList)
                .extracting("name")
                .contains(mainName);

    }

    @ParameterizedTest(name = "{index} -> 중분류 아이디가 {0}이고 바꿀 이름이 {1}일때")
    @DisplayName("중분류 카테고리 이름변경")
    @CsvSource(
            {"1, '언더웨어'", "2, '유아동의류'", "3, '가방/잡화'", "4, '모바일/테블릿'",
            "5, '게임'", "6, '음향기기'", "7, '조명.인테리어'", "8, '건강/의료용품'",
            "9, '악기/취미'"})
    void change_the_name_of_the_sub_category(Long subId, String subName) {
        //given
        categoryMapper.updateCategorySubByIdAndName(subId, subName);

        //when
        List<CategorySubInfoDto> categorySubInfoDtoList = categoryMapper.selectCategorySubs();

        //then
        assertThat(categorySubInfoDtoList)
                .extracting("name")
                .contains(subName);
    }
}