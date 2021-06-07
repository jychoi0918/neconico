package com.neconico.neconico.service.admin.item;

import com.neconico.neconico.dto.admin.item.CategoryMainCountDto;
import com.neconico.neconico.dto.admin.item.CategorySubCountDto;
import com.neconico.neconico.dto.admin.item.ItemCategoryInfoDto;
import com.neconico.neconico.mapper.admin.item.ItemAdminMapper;
import com.neconico.neconico.mapper.category.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemAdminServiceImpl implements ItemAdminService{

    private final ItemAdminMapper itemAdminMapper;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryMainCountDto> countItemByMainCategory() {
        return itemAdminMapper.countItemByMainCategory();
    }

    @Override
    public List<CategorySubCountDto> countItemBySubCategory() {
        return itemAdminMapper.countItemBySubCategory();
    }

    @Override
    public List<ItemCategoryInfoDto> findItemCategoryInfoAll() {
        return itemAdminMapper.countItemForStats();
    }

    @Override
    public Map countItemBySaleStatus(String saleStatus) { return itemAdminMapper.countItemBySaleStatus(saleStatus); }

    @Override
    @Transactional
    public void insertCategoryMain(String mainName) {
        categoryMapper.insertCategoryMain(mainName);
    }

    @Override
    @Transactional
    public void updateMainCategory(Long mainId, String mainName) { categoryMapper.updateCategoryMainByIdAndName(mainId, mainName); }

    @Override
    @Transactional
    public void insertSubCategory(Long mainId, String subName) { categoryMapper.insertCategorySub(mainId, subName); }

    @Override
    @Transactional
    public void updateSubCategoryByIdAndName(Long subId, String subName) { categoryMapper.updateCategorySubByIdAndName(subId, subName); }
}
