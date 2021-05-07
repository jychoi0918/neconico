package com.neconico.neconico.dto.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter @Setter
@Alias("categoryMainInfoDto")
@NoArgsConstructor
public class CategoryInfoDto {
    private Long categoryMainId;
    private String name;
    private List<CategorySubInfoDto> categorySubInfoDtoList;
}
