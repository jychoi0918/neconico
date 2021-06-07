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

