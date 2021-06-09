//전역 check hidden변수
const passwordCheck = document.getElementById("password_check");

//비밀번호 input
const pw = document.getElementById('accountPw');

const checkPassWord = () => {
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

const submitForm = () => {
    const modifyPwForm = document.querySelector(".modify_pw_form");
    if(pw.value === '') {
        alert('변경될 비밀번호를 입력해주세요.');
        return;
    }
    if(passwordCheck.value !== "true"){
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }

    alert('비밀번호가 변경되었습니다.');
    modifyPwForm.submit();
}