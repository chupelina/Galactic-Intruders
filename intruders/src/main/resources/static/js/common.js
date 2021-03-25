function convertTime(s) {
    const hour = Math.floor(s / 3600);
    let off = s - hour * 3600;
    const minutes = Math.floor(off / 60);
    off = off - minutes * 60;
    const sec = off;
    return `${(hour + '').padStart(2, '0')} : ${(minutes + '').padStart(2, '0')} : ${(sec + '').padStart(2, '0')}`;
}

function  sendData(url){
    let xhttp = new XMLHttpRequest();
    let elementToken = document.querySelector('meta[name="_csrf"]');
    let token = elementToken && elementToken.getAttribute("content");
    let elementHeader = document.querySelector('meta[name="_csrf_header"]');
    let header = elementHeader && elementHeader.getAttribute("content");
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader(header, token);
    xhttp.send();
}


export {convertTime, sendData}