package com.neconico.neconico.dto.admin.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter @Setter
@Alias("itemCategoryMainInfoDto")
@NoArgsConstructor
public class ItemCategoryInfoDto {
    private Long categoryMainId;
    private String name;
    private List<ItemCategorySubInfoDto> itemCategorySubInfoDtos;
}
