package com.neconico.neconico.service.category;

import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.dto.category.CategorySubInfoDto;

import java.util.List;

public interface CategoryService {

    List<CategoryInfoDto> findCategoryInfoAll();

    List<CategorySubInfoDto> findCategorySubAll();

    List<CategorySubInfoDto> findCategorySubAllByMainName(String mainName);
}
