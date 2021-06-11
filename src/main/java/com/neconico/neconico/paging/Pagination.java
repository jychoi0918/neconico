package com.neconico.neconico.paging;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class Pagination {

    private long startPage;      //시작 페이지
    private long pageSize;       //하단에 나올 페이지 개수(예: << 6 7 8 9 10 >> 이 경우에는 5)
    private long endPage;        //(현재 페이지 기준으로 보이는) 끝 페이지 (예: << 6 7 8 9 10 >> 일 경우 10)
    private boolean hasNext;    //다음페이지 유무
    private boolean hasPrev;    //이전 페이지 유무
    private long totalPage;
    private long totalContent;   //전체 게시글
    private Criteria criteria;  //(현재 페이지 & 한 페이지에 보이는 게시글 수)

    //totalContent는 Mapper에서  int countTable() 을 통해 받는다.
    public Pagination(Criteria criteria, Long totalContent, Long pageSize) {
        this.criteria = criteria;
        this.totalContent = totalContent;
        this.pageSize = pageSize;


        //끝 페이지
        this.endPage = (long)(Math.ceil(criteria.getCurrentPage() * (1.0) / pageSize)) * pageSize;

        //첫 페이지
        this.startPage = endPage - (pageSize - 1);

        //전체 페이지 (전체 페이지의 끝)
        this.totalPage = (long)(Math.ceil((totalContent * 1.0) / criteria.getContentPerPage()));

        //진짜 끝 페이지<현재 보이는 페이지 끝 일 때
        this.endPage = totalPage <= endPage ? totalPage : endPage;

        //이전 페이지 존재 여부
        this.hasPrev = criteria.getCurrentPage() > 1;

        //다음 페이지 존재 여부
        this.hasNext = criteria.getCurrentPage() < this.totalPage;




    }
}
