package com.neconico.neconico.dto.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("storeitempagingdto")
public class StoreItemPagingDto {

    private Long userId;
    private String menuName;
    private String sortKind;
    private String sortOrder;
    private Long startRow;
    private Long countRow;

    public StoreItemPagingDto(Long userId, String menuName, String sortKind, String sortOrder, Long startRow, Long countRow) {
        this.userId = userId;
        this.menuName = menuName;
        this.sortKind = sortKind;
        this.sortOrder = sortOrder;
        this.startRow = startRow;
        this.countRow = countRow;
    }
}
