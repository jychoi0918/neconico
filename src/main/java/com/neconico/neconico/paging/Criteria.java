package com.neconico.neconico.paging;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class Criteria {

    private long currentPage;       //현재 페이지
    private long contentPerPage;    //한 페이지에 나올 게시글 수
    private String sortingColumn;  //정렬할 열
    private String requestOrder;   //정렬

    public long getPageStart() {
        return (this.currentPage - 1) * contentPerPage;
    }
}


