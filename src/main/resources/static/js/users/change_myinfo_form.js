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

            document.querySelector(".join_add").value = data.zonecode;
            document.querySelector(".join_add_detail").value = address;

        }
    }).open();
}

const changePw = (birthdate) => {
    if(birthdate === "OAUTH") {
        alert('소셜로그인 이용자는 해당 서비스를 이용해주세요.');
        return;
    }
    location.href = "/user/modify/myinfo/password";
}

//form 제출
const submitForm = () => {
    if(!(document.getElementById('gender1').checked || document.getElementById('gender2').checked)) {
        alert('성별을 선택해주세요');
        return;
    }

    const joinForm = document.querySelector(".join_form");
    alert('변경되었습니다.');
    joinForm.submit();

}

const submitUserDrop = () => {
    if(confirm('회원탈퇴를 하실경우 저희 서비스를 이용하실 수 없습니다.' + '\n' + '회원탈퇴 하시겠습니까?') == true) {

        const formElement = document.createElement('form');
        formElement.setAttribute('method', 'post');
        formElement.setAttribute('action',
            "/user/modify/myinfo/drop" + "?accountId=" + document.getElementById('accountId').value);

        document.body.appendChild(formElement);
        alert('회원탈퇴 되었습니다.');
        formElement.submit();
    }
}