package com.neconico.neconico.dto.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("storeitemsortingdto")
public class StoreItemSortingDto {

    private Long userId;
    private String sortKind;

    private String sortOrder;
    private Long startRow;
    private Long countRow;

    public StoreItemSortingDto(Long userId, String sortKind, String sortOrder, Long startRow, Long countRow) {
        this.userId = userId;
        this.sortKind = sortKind;
        this.sortOrder = sortOrder;
        this.startRow = startRow;
        this.countRow = countRow;
    }
}
