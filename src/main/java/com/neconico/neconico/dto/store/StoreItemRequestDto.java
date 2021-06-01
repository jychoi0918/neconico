package com.neconico.neconico.dto.store;

import lombok.Data;

@Data
public class StoreItemRequestDto {
    private int currentPage;
    private String sortingColumn;
    private String requestOrder;
}
