//내용 글자수 세기
const content = document.getElementById("content");
const countText = () => {
    let textLength = document.getElementById("text_length");
    let inputTextLength = content.value.length;

    textLength.style.color = "BLACK";

    if (inputTextLength >= 500) {
        textLength.style.color = "RED";
        if (inputTextLength > 500) {
            inputTextLength -= 1;
        }
    }

    const maxLength = "500";
    textLength.innerHTML = inputTextLength + "/" + maxLength;
}

/*
 * 주소 찾기
 */
const area = document.getElementById("area");

//내주소 찾기
const findMyAddress = () => {
    const httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            const sessionUserInfo = JSON.parse(this.responseText);
            const userAddress = sessionUserInfo.address;
            if (userAddress === "OAUTH") {
                alert('사용자 주소가 등록되어있지 않습니다.' + '\n' + '사용자 정보변경에서 변경해주세요.')
                return;
            }
            area.value = userAddress;
        }
    }
    httpRequest.open("GET", "/user/find/address");
    httpRequest.setRequestHeader("Content-type", "text/plain");
    httpRequest.send();
}

//주소 검색
const executePostCode = () => {
    new daum.Postcode({
        oncomplete: function (data) {
            let address = '';

            //사용자가 선택한 주소 타입에 따라 해당 주소 값
            if (data.userSelectedType === 'R') {
                address = data.roadAddress;
            } else {
                address = data.jibunAddress;
            }
            area.value = address;
        }
    }).open();
}


/**
 * 가격 ',' 삽입
 * @param obj
 */
function inputNumberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

/**
 * 파일 저장
 */
let storedFiles = [];

document.getElementById("imageUpload1").addEventListener('change', function () {
    const fileList = this.files;

    if (!/(\.(gif|jpg|jpeg|png))$/i.test(fileList[0].name)) {
        alert('gif, jpg, png 만 등록 가능합니다.');
        return;
    }

    for (let i = 0; i < fileList.length; i++) {
        storedFiles.push(fileList[i]);
    }

    if(storedFiles.length > 3) {
        alert('이미지는 최대 3개까지 등록가능합니다.');
        storedFiles.pop();
        return;
    }

    readImage(storedFiles.length - 1);
});

/**
 * 이미지 미리보기
 * @param index
 */
const readImage = (index) => {
    const images = document.querySelector(".new04_right_img");
    //자식 노드
    const figure = document.createElement("figure");

    const figureInput = document.createElement('input');
    figureInput.setAttribute('type', 'hidden');
    figureInput.setAttribute('value', index);

    const figureAnchor = document.createElement('a');
    figureAnchor.setAttribute('onclick', 'removeImage(this)');
    figureAnchor.setAttribute('class', 'new_img_close_btn');
    figureAnchor.style.cursor = 'pointer';

    const figureImg = document.createElement("img");
    figureImg.setAttribute('src', '');
    figureImg.style.height = "100%";
    figureImg.style.width = "100%";

    figure.append(figureInput, figureImg, figureAnchor);
    images.appendChild(figure);

    const fileReader = new FileReader();

    fileReader.onload = function (e) {
        figureImg.src = e.target.result;
    }

    fileReader.readAsDataURL(storedFiles[index]);
}

/**
 * 이미지 제거
 * @param figureAnchor
 */
function removeImage(figureAnchor) {
    const figure = figureAnchor.parentNode;
    const figureInput = figure.firstChild;

    if (figureInput.value === '1' && storedFiles.length === 3) {
        storedFiles[parseInt(figureInput.value)] = storedFiles[storedFiles.length - 1];
        storedFiles.pop();
    } else if (figureInput.value === '0' && storedFiles.length === 3) {
        for (let i = 0; i <= 1; i++) {
            storedFiles[i] = storedFiles[i + 1];
        }
        storedFiles.pop();
    } else if (figureInput.value === '0' && storedFiles.length === 2) {
        storedFiles[0] = storedFiles.pop();
    } else if (figureInput.value === '2') {
        storedFiles.pop();
    } else {
        storedFiles = [];
    }

    figure.remove();
}

/**
 * ItemStatus, ShippingPrice 체크등록
 */
let checkItemStatus;
let checkShippingPrice;

function setItemStatus(itemStatus) {
    checkItemStatus = itemStatus.value;

}

function setShippingPrice(shippingPrice) {
    checkShippingPrice = shippingPrice.value;
}

/**
 *  form 제출
 */
const submitForm = () => {

    if (checkFormData()) {
        let formData = new FormData();

        for (let i = 0; i < storedFiles.length; i++) {
            formData.append("files", storedFiles[i]);
        }

        //formData에 삽입
        insertDataToFromData(formData);

        submitFormDataToAjax(formData);
    }

}

const checkFormData = () => {
    if (document.getElementById("title").value.length < 2) {
        alert('상품명을 2자 이상 적어주세요.');
        return false;
    }
    if (storedFiles.length <= 0) {
        alert('이미지 개수는 1개 이상이어야 합니다.');
        return false;
    }
    if (document.getElementById("price").value === '') {
        alert('가격을 입력해주세요.');
        return false;
    }
    if (document.getElementById("area").value === '') {
        alert('주소를 등록해주세요.');
        return false;
    }
    if (checkItemStatus == null) {
        alert('상품 상태를 선택해주세요.');
        return false;
    }
    if (checkShippingPrice == null) {
        alert('배송 가격을 선택해주세요.');
        return false;
    }
    return true;
}

const insertDataToFromData = (formData) => {
    formData.append("subId", document.getElementById("sub_id").value);
    formData.append("title", document.getElementById("title").value);
    formData.append("content", document.getElementById("content").value);
    formData.append("price", document.getElementById("price").value);
    formData.append("area", document.getElementById("area").value);
    formData.append("itemStatus", checkItemStatus);
    formData.append("shippingPrice", checkShippingPrice);
}

const submitFormDataToAjax = (formData) => {
    const httpRequest = new XMLHttpRequest();

    formData.enctype = 'multipart/form-data';
    formData.method = 'post';
    formData.action = '/item/new';

    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            alert('등록되었습니다.');
            location.href = "/";
        }
    }

    httpRequest.open('post', '/item/new');
    httpRequest.send(formData);

}