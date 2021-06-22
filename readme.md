![waving](https://capsule-render.vercel.app/api?type=waving&height=200&text=내꼬니꼬&fontAlign=70&fontAlignY=35&color=random)
![findId](./src/main/resources/static/img/logo/neconico_logo.png)
***
　중고나라, 번개장터, 당근마켓 등 중고거래 플랫폼에서 착안해, 중고 상품을 거래 할 수 있는 웹서비스를 기획 했습니다.  
 　판매자, 구매자, 관리자 관점에서 요구사항을 분석하고, 분석한 내용을 분담하여 서비스 구현을 진행했습니다.   

### 팀원
<a href="https://github.com/NecoNicoUsedTradeProject/neconico">
  <img src="https://contrib.rocks/image?repo=NecoNicoUsedTradeProject/neconico" />
</a>

# 사용 기술
***
### BACK-END
  * JAVA 11
  * GRADLE
  * SPRING
    * SPRING-BOOT
    * SPRING-SECURITY
  * MYBATIS
  * JUNIT5 AssertJ
    
### FRONT-END  
  * JAVASCRIPT
    * AJAX
    * JQUERY
### DB  
  * ~~ORACLE~~
  * MYSQL

# 요구사항
***
### 회원
* 회원 가입
* 로그인 및 로그아웃
* 개인정보 수정
* 판매, 구매, 찜 상품 확인
### 상품
* 상품 등록 및 수정
* 거래 요청
* 상품 찜하기
* 상품 검색
* 상품 문의 및 댓글
### 상점
* 내 상점과 다른 회원 상점 
* 상점 정보 등록 및 수정
* 거래 승인
* 상품 목록 및 정렬
### 관리자
* 회원 목록 조회
* 회원 그룹별 조회(연령/성별/지역)
* 카테고리별 등록 조회
* 거래 완료 비율 조회
* 카테고리 추가 및 수정
* 공지사항 작성, 수정, 숨기기
* 광고 작성 및 수정

# DB
***
![db](./img/db/db.JPG)  
<a href="https://www.erdcloud.com/d/wABSja6NBHZbJcNJY" target="_blank">상세 보기</a>

# 페이지 구현
***
### 메인

![main](./img/page/main/main.jpg)

## 로그인

![login](./img/page/login/login.JPG)

<details>
<summary>구현 기능 보기</summary>
<div markdown="1">

* 로그인 상태 유지


* 아이디 찾기
  
  ![findId](./img/page/login/findID.JPG)


* 비밀 번호 찾기
  
  ![findPw](./img/page/login/findPW.JPG)


</div>
</details>



### 회원가입

![store](./img/page/join/join.JPG)
<details>
<summary>구현 기능 보기</summary>
<div markdown="1">

* 아이디 중복 검사 기능


* 유효성 검사


* 주소 검색 기능

  ![searchlocation](./img/page/join/searchlocation.JPG)
  
  
* 이메일 인증 기능
  
  ![emailverify](./img/page/join/emailverify.JPG)
  ![emailverify1](./img/page/join/emailverify1.JPG)


</div>
</details>

### 상품등록

![item](./img/page/item/item.JPG)

### 상품관리

![itemmanage](./img/page/itemmanage/itemmanage.JPG)

### 상점

![store](./img/page/store/mystore_all.JPG)
<details>
<summary>구현 기능 보기</summary>
<div markdown="1">

* 상점 정보 수정 기능
  ![storeInfo](./img/page/store/storeinfo.JPG)
  

* 상품 정렬 기능
  ![sorting](./img/page/store/sorting.JPG)


* 페이징 기능
  ![paging](./img/page/store/paging.JPG)


* 상점 후기 작성 기능
  ![storeReview](./img/page/store/storeReview.JPG)
  ![storeReview2](./img/page/store/storeReview2.JPG)
  

* 상품 거래 기능
  ![trade](./img/page/store/trade.JPG)

</div>
</details>

### 관리자

![admin](./img/page/admin/admin_main.png)

<details>
<summary>구현 기능 보기</summary>
<div markdown="1">
<br></br>
<details>
<summary>회원관리</summary>
<div markdown="1">

* 회원관리

    * 회원 목록 조회
      <br></br>
      ![usersList](./img/page/admin/users/admin_user_list.gif)
      <br></br>

    * 회원 목록 그룹별 조회 (나이 / 성별 / 지역)
      <br></br>
      ![usersGroup](./img/page/admin/users/admin_user_group.gif)
      <br></br>

    * 회원 가입 추이 조회
      <br></br>
      ![usersRegistered](img/page/admin/users/admin_user_registered.gif)
      <br></br>
      <br></br>

</div>
</details>

<details>
<summary>상품관리</summary>
<div markdown="1">

* 상품관리
  <br></br>

    * 상품 카테고리별 등록 수 조회 (대분류 / 중분류)
      <br></br>
      ![itemsList](./img/page/admin/item/admin_item_static.gif)
      <br></br>

    * 상품 거래 완료 비율 조회
      <br></br>
      ![tradeRate](./img/page/admin/item/admin_item_tradestatus.gif)
      <br></br>

    * 카테고리 등록 및 수정 (대분류)
      <br></br>
      ![categoryCRUD](./img/page/admin/item/admin_item_categorymain.gif)
      <br></br>

    * 카테고리 등록 및 수정 (중분류)
      <br></br>
      ![subCategoryCRUD](./img/page/admin/item/admin_item_categorysub.gif)
      <br></br>

</div>
</details>

<details>
<summary>광고관리</summary>
<div markdown="1">


* 광고 조회/수정/삭제
  ![advertList](./img/page/admin/advert/advert_ud.gif)

<br>

* 광고 등록

![advertCRUD](./img/page/admin/advert/submit_advert.gif)

<br>

* 광고 숨김

![advertCRUD](./img/page/admin/advert/advert_hidden.gif)

<br>



</div>
</details>

<details>
<summary>공지사항</summary>
<div markdown="1">

<br>

* 공지사항 조회

![noticeList](./img/page/admin/notice/noticelist.PNG)

<br>

* 공지 등록, 수정, 삭제

![noticeCRUD](./img/page/admin/notice/submit_notice.gif)

<br>

* 공지사항 페이지 처리

![noticePaging](./img/page/admin/notice/notice_paging.gif)


</details>
<br>

* 관리자 권한 부여
  <br></br>
  ![adminAuthority](./img/page/admin/admin_changeauthority.gif)
</div>
</details>
