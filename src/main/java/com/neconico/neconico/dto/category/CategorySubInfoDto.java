package com.neconico.neconico.dto.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter @Setter
@Alias("categorySubInfoDto")
@NoArgsConstructor
public class CategorySubInfoDto {
    private Long categorySubId;
    private Long categoryMainId;
    private String name;
}
