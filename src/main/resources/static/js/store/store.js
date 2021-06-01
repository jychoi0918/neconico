/*<![CDATA[*/
let userId = /*[[ ${userId} ]]*/ +"";
/*]]*/

let currentMenu = 'myItem';
let currentPage = 1;
let sortingColumn = 'CREATED';
let requestOrder = 'DESC';

window.onload = function () {
    ajax();
}

function logVar() {
    console.log(userId + "," + currentMenu + "," + currentPage + "," + sortingColumn + "," + requestOrder);
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

    httpRequest.open("POST", "/store/" + userId + "/list/" + currentMenu);
    httpRequest.setRequestHeader("Content-Type", "application/json");
    httpRequest.send(reqData);

    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let resp = JSON.parse(this.responseText);
            removeChildNode();
            if ((resp.pagination.totalContent + 0) !== 0) {
                createItemCard(resp.itemList);
                createPageButton(resp.pagination);
            }
        }
    };

}

function removeChildNode() {
    let parent = document.getElementById('itemListDiv');
    while (parent.hasChildNodes()) {
        parent.removeChild(parent.firstChild);
    }
}

function createItemCard(itemList) {
    let parent = document.getElementById('itemListDiv');

    switch (currentMenu) {
        case "storeReview":
            createStoreReviewList(parent, itemList);
            break;
        default :
            createItemCardList(parent, itemList);
            break;
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
            let img = document.createElement("img");
            img.setAttribute("src", item.itemImg);
            img.setAttribute("alt", "");
            fig.append(img);

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
            img.setAttribute("src", item.itemImg);
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
function calculateDay(input) {
    let dateArr = (input + "").substring(0, 10).split('-');
    let createTime = new Date(dateArr[0], dateArr[1] - 1, dateArr[2]);
    let nowTime = new Date();
    let cDay = Math.floor((nowTime.getTime() - createTime.getTime()) / 1000 / 60 / 60 / 24);
    if (cDay == 0) {
        return '오늘';
    } else {
        return cDay + '일 전';
    }
}