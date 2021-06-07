package com.neconico.neconico.dto.admin.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@Alias("itemCategorySubInfoDto")
@NoArgsConstructor
public class ItemCategorySubInfoDto {
    private Long categorySubId;
    private String name;
    private Long num;
}
