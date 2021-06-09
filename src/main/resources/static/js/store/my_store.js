let currentMenu = 'myItem';
let currentPage = 1;
let sortingColumn = 'CREATED';
let requestOrder = 'DESC';

window.onload = function () {
    ajax();
}

function logVar() {
    console.log(currentMenu + "," + currentPage + "," + sortingColumn + "," + requestOrder);
}

function selectMenu(menu) {
    setVariable(menu, 1, 'CREATED', 'DESC');
    logVar();
    ajax();
}

function selectSortingColumn(column) {
    if (column == sortingColumn) {
        if (requestOrder == 'DESC') {
            setVariable(currentMenu, 1, column, 'ASC');
        } else {
            setVariable(currentMenu, 1, column, 'DESC');
        }

    } else {
        setVariable(currentMenu, 1, column, 'DESC');
    }
    logVar();
    ajax();
}

function selectPageButton(page) {
    setVariable(currentMenu, page, sortingColumn, requestOrder);
    logVar();
    ajax();
}

function setVariable(menu, page, column, order) {
    currentMenu = menu;
    currentPage = page;
    sortingColumn = column;
    requestOrder = order;
}

function createRequestDto() {
    let requestDto = new Object();
    requestDto.currentPage = currentPage;
    requestDto.sortingColumn = sortingColumn;
    requestDto.requestOrder = requestOrder;
    return requestDto;
}

//ajax 통신
function ajax() {
    let requestDto = createRequestDto();
    let reqData = JSON.stringify(requestDto);
    let httpRequest = new XMLHttpRequest();

    httpRequest.open("POST", "/mystore/list/" + currentMenu);
    httpRequest.setRequestHeader("Content-Type", "application/json");
    httpRequest.send(reqData);

    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let resp = JSON.parse(this.responseText);
            removeChildNode('itemListDiv');
            if ((resp.pagination.totalContent + 0) !== 0) {
                createItemCard(resp.itemList);
                createPageButton(resp.pagination);
            }
        }
    };

}

function removeChildNode(location) {
    let parent = document.getElementById(location);
    while (parent.hasChildNodes()) {
        parent.removeChild(parent.firstChild);
    }
}

function createItemCard(itemList) {
    let parent = document.getElementById('itemListDiv');

    switch (currentMenu) {
        case "tradeItem":
            createTradeList(parent, itemList);
            break;
        case "questionItem":
            createQuestionList(parent, itemList);
            break;
        case "storeReview":
            createStoreReviewList(parent, itemList);
            break;
        default :
            createItemCardList(parent, itemList);
            break;
    }


    function createTradeList(parent, itemList) {
        let table = document.createElement("table");

        let colgroup = document.createElement("colgroup");

        let col1 = document.createElement("col");
        col1.setAttribute("width", "30%");
        let col2 = document.createElement("col");
        col2.setAttribute("width", "20%");
        let col3 = document.createElement("col");
        col3.setAttribute("width", "20%");
        let col4 = document.createElement("col");
        col4.setAttribute("width", "19%");
        let col5 = document.createElement("col");
        col5.setAttribute("width", "11%");
        colgroup.append(col1, col2, col3, col4, col5);

        let tr = document.createElement("tr");
        let th1 = document.createElement("th");
        th1.innerText = '상품 이미지';
        let th2 = document.createElement("th");
        th2.innerText = '상품 이름';
        let th3 = document.createElement("th");
        th3.innerText = '상품 가격';
        let th4 = document.createElement("th");
        th4.innerText = '요청 정보';
        let th5 = document.createElement("th");
        th5.innerText = '거래 상태';
        tr.append(th1, th2, th3, th4, th5);
        table.append(colgroup, tr);

        itemList.forEach(function (item) {
            let item_tr = document.createElement("tr");
            let item_td1 = document.createElement("td");
            let img = document.createElement("img");
            img.setAttribute("src", item.itemImg.split('>')[0]);
            img.setAttribute("alt", "");
            img.setAttribute("class", "trade_card_img")
            item_td1.append(img);

            let item_td2 = document.createElement("td");
            let a = document.createElement("a");
            a.setAttribute("href", "/item/" + item.itemId);
            a.innerText = item.title;
            item_td2.append(a);

            let item_td3 = document.createElement("td");
            item_td3.innerText = transPrice(item.price);

            let item_td4 = document.createElement("td");
            let span1 = document.createElement("span");
            span1.innerText = item.buyerName;
            let br = document.createElement("br");
            let span2 = document.createElement("span");
            span2.innerText = calculateDay(item.createdTime);
            item_td4.append(span1, br, span2);

            let item_td5 = document.createElement("td");
            let div = document.createElement("div");
            div.setAttribute("className", "deal_btn");
            item_td5.append(div);

            item_tr.append(item_td1, item_td2, item_td3, item_td4, item_td5);

            if (item.tradeStatus == "요청") {
                let select = document.createElement("select");
                select.setAttribute("id", item.tradeId);
                let option1 = document.createElement("option");
                option1.innerText = '선택하세요';
                let option2 = document.createElement("option");
                option2.innerText = '거래 완료';
                option2.setAttribute("value", "success");
                let option3 = document.createElement("option");
                option3.innerText = '거래 취소';
                option3.setAttribute("value", "cancel");
                select.append(option1, option2, option3);

                let anchor = document.createElement("a");
                anchor.setAttribute("href", "javascript:" + "tradeSubmit(" + item.tradeId + ")");
                anchor.innerText = '확인';
                div.append(select, anchor);

            } else {
                let span = document.createElement("span");
                span.innerText = item.tradeStatus;
                div.append(span);
            }
            table.append(item_tr);
        });

        parent.append(table);
    }

    function createQuestionList(parent, itemList) {
        let table = document.createElement("table");

        let colgroup = document.createElement("colgroup");

        let col1 = document.createElement("col");
        col1.setAttribute("width", "20%");
        let col2 = document.createElement("col");
        col2.setAttribute("width", "15%");
        let col3 = document.createElement("col");
        col3.setAttribute("width", "10%");
        let col4 = document.createElement("col");
        col4.setAttribute("width", "12%");
        let col5 = document.createElement("col");
        col4.setAttribute("width", "33%");
        let col6 = document.createElement("col");
        col4.setAttribute("width", "10%");
        colgroup.append(col1, col2, col3, col4, col5, col6);

        let tr = document.createElement("tr");
        let th1 = document.createElement("th");
        th1.innerText = '상품 이미지';
        let th2 = document.createElement("th");
        th2.innerText = '상품 이름';
        let th3 = document.createElement("th");
        th3.innerText = '상품 가격';
        let th4 = document.createElement("th");
        th4.innerText = '작성자 닉네임';
        let th5 = document.createElement("th");
        th5.innerText = '작성 내용';
        let th6 = document.createElement("th");
        th6.innerText = '등록 시간';
        tr.append(th1, th2, th3, th4, th5, th6);
        table.append(colgroup, tr);

        itemList.forEach(function (item) {
            let itr = document.createElement("tr");
            table.append(itr);
            let itd1 = document.createElement("td");
            let img = document.createElement("img");
            img.setAttribute("src", item.itemImg.split('>')[0]);
            img.setAttribute("alt", "");
            itd1.append(img);

            let itd2 = document.createElement("td");
            let a = document.createElement("a");
            a.setAttribute("href", "/item/" + item.itemId);
            a.innerText = item.title;
            itd2.append(a);
            let itd3 = document.createElement("td");
            itd3.innerText = transPrice(item.price);
            let itd4 = document.createElement("td");
            itd4.innerText = item.writerName;
            let itd5 = document.createElement("td");
            itd5.innerText = item.content;
            let itd6 = document.createElement("td");
            let span = document.createElement("span");
            span.innerText = calculateDay(item.replyCreatedTime);
            itd6.append(span);

            itr.append(itd1, itd2, itd3, itd4, itd5, itd6);

            table.append(itr);
        });
        parent.append(table);
    }

    function createItemCardList(parent, itemList) {
        let ul = document.createElement("ul");
        ul.setAttribute("class", "my_store_prod_list");

        itemList.forEach(function (item) {
            //li 노드 생성
            let li = document.createElement("li");

            //a 노드 생성
            let anchor = document.createElement("a");
            anchor.setAttribute("href", "/item/" + item.itemId);


            let fig = document.createElement("figure");
            let div1 = document.createElement("div");
            div1.setAttribute("class","list_img");
            let img = document.createElement("img");
            img.setAttribute("src", item.itemImg.split('>')[0]);
            img.setAttribute("alt", "");
            div1.append(img);
            if (item.status == '거래 완료') {
                let statusDiv = document.createElement("div");
                statusDiv.setAttribute("class", "list_img_sold_out out");
                let p = document.createElement("p");
                p.setAttribute("align","center")
                p.innerText = '판매완료';
                statusDiv.append(p);
                div1.append(statusDiv);
            }
            fig.append(div1);

            let div = document.createElement("div");
            let h4 = document.createElement("h4");
            h4.innerText = item.title;
            let p = document.createElement("p");
            p.innerText = transPrice(item.price);
            let span = document.createElement("span");
            span.innerText = calculateDay(item.createdTime);
            div.append(h4, p, span);
            anchor.append(fig, div);

            li.append(anchor);
            ul.append(li);
        });
        parent.append(ul);
    }

    function createStoreReviewList(parent, itemList) {
        let table = document.createElement("table");
        parent.append(table);

        let colgroup = document.createElement("colgroup");

        let col1 = document.createElement("col");
        col1.setAttribute("width", "20%");
        let col2 = document.createElement("col");
        col2.setAttribute("width", "15%");
        let col3 = document.createElement("col");
        col3.setAttribute("width", "10%");
        let col4 = document.createElement("col");
        col4.setAttribute("width", "12%");
        let col5 = document.createElement("col");
        col4.setAttribute("width", "33%");
        let col6 = document.createElement("col");
        col4.setAttribute("width", "10%");
        colgroup.append(col1, col2, col3, col4, col5, col6);

        let tr = document.createElement("tr");
        let th1 = document.createElement("th");
        th1.innerText = '상품 이미지';
        let th2 = document.createElement("th");
        th2.innerText = '상품 이름';
        let th3 = document.createElement("th");
        th3.innerText = '상품 가격';
        let th4 = document.createElement("th");
        th4.innerText = '작성자 닉네임';
        let th5 = document.createElement("th");
        th5.innerText = '작성 내용';
        let th6 = document.createElement("th");
        th6.innerText = '등록 시간';
        tr.append(th1, th2, th3, th4, th5, th6);
        table.append(colgroup, tr);

        itemList.forEach(function (item) {
            let itr = document.createElement("tr");

            let itd1 = document.createElement("td");
            let img = document.createElement("img");
            img.setAttribute("src", item.itemImg.split('>')[0]);
            img.setAttribute("alt", "");
            itd1.append(img);
            let itd2 = document.createElement("td");
            let a = document.createElement("a");
            a.setAttribute("href", "/item/" + item.itemId);
            a.innerText = item.title;
            itd2.append(a);
            let itd3 = document.createElement("td");
            itd3.innerText = transPrice(item.price);
            let itd4 = document.createElement("td");
            itd4.innerText = item.writerName;
            let itd5 = document.createElement("td");
            itd5.innerText = item.content;
            let itd6 = document.createElement("td");
            itd6.innerText = calculateDay(item.replyCreatedTime);
            itr.append(itd1, itd2, itd3, itd4, itd5, itd6);

            table.append(itr);
        })
        ;
        parent.append(table);
    }
}

function createPageButton(pagination) {
    let parent = document.getElementById('itemListDiv');
    let div = document.createElement("div");
    div.setAttribute("class", "pagination");
    let ul = document.createElement('ul');

    //앞으로 가기
    let pli = document.createElement("li");
    let pa = document.createElement("a");
    pa.setAttribute("class", "page_prev");
    if (pagination.criteria.currentPage !== 1) {
        pa.setAttribute("href", "javascript:selectPageButton(" + (currentPage - 1) + ")");
    } else {
        pa.setAttribute("href", "#prev");
    }
    pli.append(pa);
    ul.append(pli);

    // 페이지 버튼
    for (let idx = pagination.startPage; idx < pagination.endPage + 1; idx++) {
        let li = document.createElement("li");
        let a = document.createElement("a");
        if (idx == currentPage) {
            a.setAttribute("class", "active");
        }
        a.innerText = idx + "";
        a.setAttribute("href", "javascript:selectPageButton(" + idx + ")");
        li.append(a);
        ul.append(li);
    }

    // 뒤로가기
    let nli = document.createElement("li");
    let na = document.createElement("a");
    na.setAttribute("class", "page_next");
    if (pagination.hasNext) {
        na.setAttribute("href", "javascript:selectPageButton(" + (currentPage + 1) + ")");
    } else {
        na.setAttribute("href", "#next");
    }
    nli.append(na);
    ul.append(nli);

    div.append(ul);
    parent.append(div);
}

function transPrice(price) {
    let text = price + "";
    return text.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '원';
}

//가져오는 날짜 형식 2021-04-29T10:13:18
//가져오는 날짜 형식 2021-04-29T10:13:18
function calculateDay(input) {
    let createTime = new Date(input);
    let nowTime = new Date();
    let time = Math.floor((nowTime.getTime() - createTime.getTime()) / 1000);
    if (time < 60) {
        return time + '초 전';
    } else {
        time = Math.floor(time / 60);
        if (time < 60) {
            return time + '분 전';
        } else {
            time = Math.floor(time / 60);
            if (time < 24) {
                return time + '시간 전';
            } else {
                time = Math.floor(time / 24);
                return time + '일 전';
            }
        }
    }
}

function tradeSubmit(tradeId) {
    let select = document.getElementById(tradeId);
    let status = select.options[select.selectedIndex].value;

    if (status == 'success' || status == 'cancel') {
        let httpRequest = new XMLHttpRequest();
        console.log(status);
        httpRequest.open('POST', "/trade/" + tradeId + "/response/");
        httpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        httpRequest.onreadystatechange = function () {
            if (this.status === 200) {
                ajax();
            }
        }
        httpRequest.send('status=' + status);
    } else {
        alert('거래상태를 선택한 후 클릭 해주세요');
    }
}

function clickStoreName(kind) {
    let parent = document.getElementById('storeName');
    if (kind == 0) {
        let storeName = parent.innerText.replace('상점명 변경', '').trim();
        removeChildNode('storeName');
        let input = document.createElement("input");
        input.setAttribute("value", storeName);
        let a = document.createElement("a");
        a.setAttribute("href", "javascript:clickStoreName(1)")
        a.innerText = '제출';

        parent.append(input, a);

    } else if (kind == 1) {
        let storeName = parent.firstChild.value;

        if(storeName.length > 10){
            alert("상점명은 최대 10자까지 지원합니다.")
        }else{
        <!-- 요청 추가 -->
        let httpRequest = new XMLHttpRequest();
        httpRequest.open('POST', "/mystore/name/edit");
        httpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        httpRequest.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                alert('상점명이 변경 되었습니다.')
                removeChildNode('storeName');
                let span = document.createElement("span");
                span.innerText = storeName;
                let a = document.createElement("a");
                a.innerText = '상점명 변경';
                a.setAttribute("href", "javascript:clickStoreName(0)")
                span.append(a);
                parent.append(span);
            } else if(this.readyState === 4 && this.status === 400) {
                alert('상점명이 중복입니다.')
            }

        }
        httpRequest.send('name=' + storeName);
        }
    }
}

function clickStoreContent(kind) {
    let div = document.getElementById("storeContentBox");

    let a = document.getElementById("storeContentButton");

    if (kind == 0) {
        let storeContent = div.innerText;

        removeChildNode('storeContentBox');

        let textarea = document.createElement("textarea");
        textarea.innerText = storeContent;
        div.append(textarea);

        a.setAttribute("href", "javascript:clickStoreContent(1)");

    } else if (kind == 1) {
        let storeContent = div.firstChild.value;

        if(storeContent.length <= 200) {
            <!-- 요청 추가 -->
            let httpRequest = new XMLHttpRequest();
            httpRequest.open('POST', "/mystore/content/edit");
            httpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            httpRequest.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    alert('상점 소개글이 변경 되었습니다.')
                    removeChildNode('storeContentBox');

                    let p = document.createElement("p");
                    p.innerText = storeContent;
                    div.append(p);
                    a.setAttribute("href", "javascript:clickStoreContent(0)");
                }
            }
            httpRequest.send('content=' + storeContent);
        } else {
            alert("글자수가 200자를 초과했습니다.");
        }


    }
}

function previewImage(f) {
    let file = f.files;
    // 확장자 체크
    if (!/\.(gif|jpg|jpeg|png)$/i.test(file[0].name)) {
        alert('gif, jpg, png 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);
        // 선택한 파일 초기화
        f.outerHTML = f.outerHTML;
        document.getElementById('preview').innerHTML = '';
    } else {
        // FileReader 객체 사용
        let reader = new FileReader();

        // 파일 읽기가 완료되었을때 실행
        reader.onload = function (rst) {
            document.getElementById('preview').innerHTML = '<img src="' + rst.target.result + '">';
            document.getElementById('inputImgLabel').setAttribute("style", "display: none")
            document.getElementById('submitImgLabel').setAttribute("style", "display: block")
        }
        // 파일을 읽는다
        reader.readAsDataURL(file[0]);
    }
}

function storeImgUpload() {
    let form = new FormData(document.getElementById("storeImgForm"));

    const httpRequest = new XMLHttpRequest();

    httpRequest.open("post", "/mystore/img/edit");
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            alert('상점 이미지가 변경 되었습니다.')
            document.getElementById('inputImgLabel').setAttribute("style", "display: block")
            document.getElementById('submitImgLabel').setAttribute("style", "display: none")
        }
    };
    httpRequest.send(form);
}