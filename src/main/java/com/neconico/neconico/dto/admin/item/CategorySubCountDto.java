package com.neconico.neconico.dto.admin.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@Alias("categorySubCountDto")
@NoArgsConstructor
@ToString
public class CategorySubCountDto {
    private String mainCategoryName;
    private String subCategoryName;
    private Long num;
}
