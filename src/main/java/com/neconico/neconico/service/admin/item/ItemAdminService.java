package com.neconico.neconico.service.admin.item;

import com.neconico.neconico.dto.admin.item.CategoryMainCountDto;
import com.neconico.neconico.dto.admin.item.CategorySubCountDto;
import com.neconico.neconico.dto.admin.item.ItemCategoryInfoDto;

import java.util.List;
import java.util.Map;

public interface ItemAdminService {

    // 상품 등록 수
    List<CategoryMainCountDto> countItemByMainCategory();

    List<CategorySubCountDto> countItemBySubCategory();

    List<ItemCategoryInfoDto> findItemCategoryInfoAll();

    // 거래 완료 비율
    Map<String, Long> countItemBySaleStatus(String saleStatus);

    //카테고리 추가&수정
    void insertCategoryMain(String mainName);

    void updateMainCategory(Long mainId, String mainName);

    void insertSubCategory(Long mainId, String subName);

    void updateSubCategoryByIdAndName(Long subId, String subName);


}
