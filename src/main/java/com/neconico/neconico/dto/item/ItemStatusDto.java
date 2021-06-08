package com.neconico.neconico.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@AllArgsConstructor
@Alias("itemStatusDto")
public class ItemStatusDto {

    private Long itemId;
    private String status;
}
