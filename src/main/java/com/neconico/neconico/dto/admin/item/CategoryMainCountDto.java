package com.neconico.neconico.dto.admin.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@Alias("categoryMainCountDto")
@NoArgsConstructor
@ToString
public class CategoryMainCountDto {
    private String mainCategoryName;
    private Long num;
}
