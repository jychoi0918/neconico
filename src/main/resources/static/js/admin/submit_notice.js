function submit_notice() {
    let title = document.forms["adm_notice_write"]["title"].value;
    let content = document.forms["adm_notice_write"]["content"].value;

    if (title.trim() == "" || content.trim() == "") {
        alert("빈칸을 채워 주세요");
        return false;
    } else {
        let submit_btn = document.getElementsByName('adm_notice_write');
        alert("등록완료!");

        submit_btn[0].submit();

    }
}