function previewPic(event) {

    let reader = new FileReader();
    let previewDiv = document.getElementById("preview");

    while (previewDiv.hasChildNodes())
        previewDiv.removeChild(previewDiv.firstChild);

    reader.onload = function (event) {
        let img = document.createElement("img");
        img.setAttribute("src", event.target.result);
        previewDiv.appendChild(img);
    };
    reader.readAsDataURL(event.target.files[0]);

}

function submitAdvert() {
    //form안의 element들을 name으로 value구하기
    let title = document.forms["register"]["title"].value;
    let url = document.forms["register"]["url"].value;
    let startDate = document.forms["register"]["startDate"].value;
    let endDate = document.forms["register"]["endDate"].value;


    if (title.trim() == "" || url.trim() == "" || startDate.trim() == "" || endDate.trim() == "") {
        alert("빈칸을 채워주세요");
        return;
    } else if ((startDate < endDate) == false) {
        alert("시간 설정이 잘못되었습니다 확인해 주세요");
        return;
    } else {

        (confirm("등록하시겠습니까?")) ? finalSend() : false;
    }
}

function finalSend() {

    const submitAd = document.getElementById('register');
    alert("등록완료!");
    submitAd.submit();
}
