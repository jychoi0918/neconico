package com.neconico.neconico.service.category;

import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.dto.category.CategorySubInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리의 모든 정보를 DB에서 가져온다.")
    void all_information_of_category_is_fetched_from_DB() throws Exception {
        List<CategoryInfoDto> categoryInfoDtoList = categoryService.findCategoryInfoAll();

        assertAll(
                () -> assertThat(categoryInfoDtoList)
                        .extracting("categorySubInfoDtoList")
                        .isNotNull(),
                () -> assertThat(categoryInfoDtoList).isNotNull()
        );
    }

    @Test
    @DisplayName("중분류 카테고리의 모든 정보를 DB에서 가져온다.")
    void all_information_of_the_middle_category_category_is_fetched_from_the_DB() throws Exception {

        List<CategorySubInfoDto> categorySubInfoDtoList = categoryService.findCategorySubAll();

        assertThat(categorySubInfoDtoList).isNotNull();
    }

}