package com.neconico.neconico.service.category;

import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.dto.category.CategorySubInfoDto;
import com.neconico.neconico.mapper.category.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("defaultCategoryService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService{

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryInfoDto> findCategoryInfoAll() {
        return categoryMapper.selectCategoryInfos();
    }

    @Override
    public List<CategorySubInfoDto> findCategorySubAll() {
        return categoryMapper.selectCategorySubs();
    }

    @Override
    public List<CategorySubInfoDto> findCategorySubAllByMainName(String mainName) {
        return categoryMapper.selectCategorySubsByMainName(mainName);
    }
}
