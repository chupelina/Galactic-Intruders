import {sendData} from "./common.js";

let shipNames = document.getElementsByClassName("ship-name")[0].children;
let shipCounts = document.getElementsByClassName("ship-count")[0].children;
let shipSendToBattle = document.getElementsByClassName("ship-send-to-battle")[0].children;


document.getElementsByClassName("army")[0].addEventListener("submit", (e)=>{
    e.preventDefault();
    let armySend = [];
    for (let i=1; i < shipNames.length; i++) {
        let shipName = shipNames[i].innerText;
        let shipCount = Number(shipCounts[i].innerText);
        let shipSend = shipSendToBattle[i].value==""? 0 : Number(shipSendToBattle[i].value);
        if(shipSend<0 || shipCount<shipSend){
            alert("Please enter valid counts of ships")
            return;
        }
        let ship =`${shipName}:${shipCount}:${shipSend}`;
        armySend.push(ship);
    }
    sendData("http://localhost:8080/api/battle/"+armySend.join("-"))
    console.log(armySend);
});

