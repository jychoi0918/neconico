package com.neconico.neconico.mapper.category;

import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.dto.category.CategorySubInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //카테고리 main, sub All
    List<CategoryInfoDto> selectCategoryInfos();

    List<CategorySubInfoDto> selectCategorySubs();

    CategorySubInfoDto selectCategorySubByName(@Param("name") String subName);

    void insertCategoryMain(@Param("name") String mainName);

    void insertCategorySub(@Param("mainId") Long mainId,
                           @Param("name") String subName);

    void updateCategoryMainByIdAndName(@Param("mainId") Long mainId,
                                       @Param("name") String mainName);

    void updateCategorySubByIdAndName(@Param("subId") Long subId,
                                      @Param("name") String subName);

}
