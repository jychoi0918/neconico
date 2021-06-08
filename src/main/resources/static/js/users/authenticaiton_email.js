//이메일 전송
const emailId = document.getElementById("email_id");
const emailConfirm = document.getElementById("email_confirm");
const authorNumberCheck = document.getElementById("author_number_check");

let executeTimer = null;
let executeTimeOut = null;

//이메일 증복 체크
const checkEmailDuplication = () => {
    const inputEmail = document.getElementById("email");
    const inputEmailValue = inputEmail.value;

    //이메일 양식 체크
    if(inputEmailValue === '' ||
        (inputEmailValue.indexOf('@') === -1 || inputEmailValue.indexOf('.') === -1)) {
        alert('잘못된 이메일 양식입니다.');
        return;
    }

    const httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            if(this.responseText === 'true') { //등록되지 않은 이메일
                //증복 확인 후 이메일 전송
                sendAuthorNum(inputEmail);
            }else {
                alert('등록된 이메일 입니다.');
            }
        }else if(this.readyState === 4 && this.status === 400) {
            alert('잘못된 이메일 양식입니다.');
        }
    }

    httpRequest.open("GET", "/user/check/email/" + inputEmail.value);
    httpRequest.setRequestHeader("Content-Type", "text/plain");
    httpRequest.send();

}

const sendAuthorNum = (emailDom) => {
    const inputEmail = emailDom;

    const httpRequest = new XMLHttpRequest();
    //타이머 count
    let timerCount = 180;

    //타이머
    const timer = () => {
        timerCount--;
        const authorTimer = document.getElementById("author_timer");
        let min;
        let sec;

        min = "0" + String(Math.floor(timerCount/60));
        sec = String(timerCount%60);

        if(sec.length === 1) {
            sec = "0" + sec;
        }
        authorTimer.innerHTML = "인증확인 남은시간 " + min + ":" + sec;
    }

    const timeOut = executeTimer => {
        clearInterval(executeTimer); //타이머 정지
        deleteAuthorNumber(); // 인증번호 제거
        emailConfirm.style.display = "none";
        alert('인증확인 시간을 초과하였습니다.');
    }

    const timeOutForErrorEmail = executeTimer => {
        clearInterval(executeTimer); //타이머 정지
        deleteAuthorNumber(); // 인증번호 제거
        emailConfirm.style.display = "none";
    }

    /*
     * 인증버튼을 누른후 여러번 누를시 기존에 있는 인증번호, 타이머 제거
     * 기존 인증확인되었을 시 확인 제거
     */
    if(emailId.value != '') {
        deleteAuthorNumber();
        authorNumberCheck.value = false;
        clearInterval(executeTimer);
        clearTimeout(executeTimeOut);
    }

    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            emailId.value = parseInt(this.responseText);
        }else if(this.readyState === 4 && this.status === 400) {
            timeOutForErrorEmail(executeTimer);
            alert('잘못된 이메일 양식입니다.');
        }
    }

    httpRequest.open("POST", "/user/email/send?emailAddress=" + inputEmail.value);
    httpRequest.setRequestHeader("Content-Type", "test/plain");
    httpRequest.send();

    // 잘못된 전송일 경우
    emailConfirm.style.display = "block";
    executeTimer = setInterval(timer, 1000);
    executeTimeOut = setTimeout(timeOut, 180050, executeTimer);
    alert('인증번호를 해당 메일로 발송하였습니다.');


}

// 비밀번호 찾기 시 이메일 전송
const sendAuthorNumForFindPw = () => {
    const inputEmail = document.getElementById("email");

    const httpRequest = new XMLHttpRequest();
    //타이머 count
    let timerCount = 180;

    //타이머
    const timer = () => {
        timerCount--;
        const authorTimer = document.getElementById("author_timer");
        let min;
        let sec;

        min = "0" + String(Math.floor(timerCount/60));
        sec = String(timerCount%60);

        if(sec.length === 1) {
            sec = "0" + sec;
        }
        authorTimer.innerHTML = "인증확인 남은시간 " + min + ":" + sec;
    }

    const timeOut = executeTimer => {
        clearInterval(executeTimer); //타이머 정지
        deleteAuthorNumber(); // 인증번호 제거
        emailConfirm.style.display = "none";
        alert('인증확인 시간을 초과하였습니다.');
    }

    const timeOutForErrorEmail = executeTimer => {
        clearInterval(executeTimer); //타이머 정지
        deleteAuthorNumber(); // 인증번호 제거
        emailConfirm.style.display = "none";
    }

    /*
     * 인증버튼을 누른후 여러번 누를시 기존에 있는 인증번호, 타이머 제거
     * 기존 인증확인되었을 시 확인 제거
     */
    if(emailId.value != '') {
        deleteAuthorNumber();
        authorNumberCheck.value = false;
        clearInterval(executeTimer);
        clearTimeout(executeTimeOut);
    }

    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            emailId.value = parseInt(this.responseText);
        }else if(this.readyState === 4 && this.status === 400) {
            timeOutForErrorEmail(executeTimer);
            alert('잘못된 이메일 양식입니다.');
        }
    }

    httpRequest.open("POST", "/user/email/send?emailAddress=" + inputEmail.value);
    httpRequest.setRequestHeader("Content-Type", "test/plain");
    httpRequest.send();

    // 잘못된 전송일 경우
    emailConfirm.style.display = "block";
    executeTimer = setInterval(timer, 1000);
    executeTimeOut = setTimeout(timeOut, 180050, executeTimer);
    alert('인증번호를 해당 메일로 발송하였습니다.');


}

//이메일 인증확인
const checkAuthorNum = () => {
    const authorNumber = document.getElementById("author_number").value;
    const httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status ===200) {
            if(this.responseText === null || this.responseText === '') {
                alert('알맞지 않은 인증번호입니다.');
            }else{
                authorNumberCheck.value = true;
                clearInterval(executeTimer);
                clearTimeout(executeTimeOut);
                alert('인증되었습니다.');

                //인증한 후 삭제
                deleteAuthorNumber();
                emailConfirm.style.display = "none";
            }
        }
    }

    httpRequest.open("POST", "/user/email/" + authorNumber + "/check");
    httpRequest.setRequestHeader("Content-Type", "text/plain");
    httpRequest.send();
}


//이메일 인증번호 삭제
const deleteAuthorNumber = () => {
    const httpRequest = new XMLHttpRequest();
    httpRequest.open("DELETE", "/user/email/" + emailId.value + "/delete");
    httpRequest.setRequestHeader("Content-Type", "text/plain");
    httpRequest.send();
}

//해당 페이지를 벗어났을경우 인증번호가 있을경우 삭제
window.onbeforeunload = e => {
    e.preventDefault();

    if(emailId.value != '') {
        deleteAuthorNumber();
    }
}


