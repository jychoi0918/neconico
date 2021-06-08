package com.neconico.neconico.dto.admin.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class SearchConditionDto {
    private String searchCondition;
    private String searchText;
}
