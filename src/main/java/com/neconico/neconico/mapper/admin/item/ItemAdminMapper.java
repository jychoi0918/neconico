package com.neconico.neconico.mapper.admin.item;

import com.neconico.neconico.dto.admin.item.CategoryMainCountDto;
import com.neconico.neconico.dto.admin.item.CategorySubCountDto;
import com.neconico.neconico.dto.admin.item.ItemCategoryInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemAdminMapper {

    // 상품 등록 수
    List<CategoryMainCountDto> countItemByMainCategory();

    List<CategorySubCountDto> countItemBySubCategory();

    List<ItemCategoryInfoDto> countItemForStats();

    // 거래 완료 비율
    Map<String, Long> countItemBySaleStatus(String saleStatus);

}
