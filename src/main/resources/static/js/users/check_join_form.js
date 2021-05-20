//전역 check hidden변수
const passwordCheck = document.getElementById("password_check");
const duplicateCheck = document.getElementById("duplicate_check_result");
const agreementCheck = document.getElementById("agreement_check");

//아이디 증복 체크
const checkDuplicate = () => {
    const accountId = document.getElementById("accountId");
    const httpRequest = new XMLHttpRequest();

    if(accountId.value == '') {
        alert('아이디를 입력해주세요.');
        return;
    }

    if(accountId.hasAttribute("readonly")) {
        return;
    }

    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            if(this.responseText === null || this.responseText === '') {
                if(confirm('사용가능한 아이디 입니다' + '\n' +'사용하시겠습니까?') == true) {
                    accountId.setAttribute('readonly', "true");
                }
                duplicateCheck.value = true;
            }else{
                alert('사용불가능한 아이디입니다.')
                duplicateCheck.value = false;
            }
        }
    }

    httpRequest.open("GET", "/user/" + accountId.value + "/check");
    httpRequest.setRequestHeader("Content-Type", "text/plain");
    httpRequest.send();
}



//주소검색
const executePostCode = () => {
    new daum.Postcode({
        oncomplete: function(data) {
            let address = '';

            //사용자가 선택한 주소 타입에 따라 해당 주소 값
            if(data.userSelectedType === 'R') {
                address = data.roadAddress;
            }else {
                address = data.jibunAddress;
            }

            document.getElementById("zipNo").value = data.zonecode;
            document.getElementById("address").value = address;

        }
    }).open();
}

//동의서 파일 reader
const readInfoAgreement = () => {
    const filePath = "/file/agreement.txt";
    const httpRequest = new XMLHttpRequest();

    httpRequest.open("GET", filePath, true);
    httpRequest.onreadystatechange = function () {
        if(httpRequest.readyState === 4 ||httpRequest.status === 200) {
            document.getElementById("join_agreement").innerHTML = httpRequest.responseText;
        }
    };
    httpRequest.send();

}

window.addEventListener('load', readInfoAgreement);
//체크박스 확인
const checkAgreement = () => {
    if(agreementCheck.value === "true"){
        agreementCheck.value = "false";
    }else {
        agreementCheck.value = "true";
    }
}

//핸드폰번호 자동 - 삽입
function inputBarToPhoneNumber(obj) {
    const pattern =  /[ㄱ-ㅎ|ㅏ-ㅓ|가-힣|a-z|A-Z]/g;

    if(pattern.test(obj.value)){
        obj.value="";
    }else {
        let number = obj.value.replace(/[^0-9]/g, "");
        let phone = "";
        if (number.length < 4) {
            return number;
        } else if (number.length < 7) {
            phone += number.substr(0, 3);
            phone += "-";
            phone += number.substr(3);
        } else if (number.length < 11) {
            phone += number.substr(0, 3);
            phone += "-";
            phone += number.substr(3, 3);
            phone += "-";
            phone += number.substr(6);
        } else {
            phone += number.substr(0, 3);
            phone += "-";
            phone += number.substr(3, 4);
            phone += "-";
            phone += number.substr(7);
        }
        obj.value = phone;
    }
}

//비밀번호 체크
const checkPassWord = () => {
    const pw = document.getElementById('accountPw');
    const pwConfirm = document.getElementById('password_confirm');
    const check = document.getElementById('password_confirm_message');

    if(pw.value !=='' && pwConfirm.value !=='') {
        if(pw.value === pwConfirm.value) {
            check.innerText = '비밀번호가 일치합니다.';
            check.style.color = '#7429ff';
            passwordCheck.value = "true";
            return;
        }
        check.innerText = '비밀번호가 일치하지 않습니다.'
        passwordCheck.value = "false";
        check.style.color = 'RED';
    }
}

//form 제출
const submitForm = () => {
    const joinForm = document.querySelector(".join_form");
    if(duplicateCheck.value !== "true") {
        alert('아이디 증복체크를 해야합니다.');
        return;
    }
    if(passwordCheck.value !== "true") {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }
    if(authorNumberCheck.value !== "true") {
        alert('이메일 인증을 해야합니다.');
        return;
    }
    if(agreementCheck.value !== "true") {
        alert("이용약관에 동의해야합니다.");
        return;
    }
    joinForm.submit();

}