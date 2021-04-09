import {html, render} from 'https://unpkg.com/lit-html?module';
import {convertTime, sendData} from "./common.js";
import {getOwns} from"./owns.js"

let response = await fetch("http://localhost:8080/api/ships");
let army = await response.json();
let allCards = () => Object.entries(army).map(([k, v]) => {
    let button;
    let time = convertTime(v.time);
    if (sessionStorage.getItem('ship-clicked-on')) {
        if (Number(sessionStorage.getItem('ship-id')) === v.id) {
            timeCounter();
            button = html`<p>Remaining time <span id="count-down-element">${time}</span></p>`;
        } else {
            button = html`</br>`;
        }
    } else {
        button = html`<input type="number"><button type="submit" id="${v.id}"> Build</button>`;
    }
    return makeShipCard(v, time, button);
});
let container = document.getElementById('card-ship');


let makeShipCard = (object, time, button) => html`<div class="ships">
                <img alt="Image of ship" src="${object.imgUrl}" />
                <h1>${object.name}</h1>
                <p>You have: <span>${object.count}</span></p>
                <p>To create 1 you need to wait <span id="time">${time}</span> and spend</p>
                <p>${object.metal} metal</p>
                <p>${object.gas} gas</p>
                <p>${object.diamond} diamond</p>
                <p>${object.energy} energy</p>
                <p>${object.description}</p>
                ${button}
            </div>`;


render(allCards(), container);
let owns = [...document.getElementsByTagName('footer')[0].querySelectorAll('p>span')];
container.addEventListener('click', async (e) => {
    if (e.target.innerText == 'Build' && e.target.tagName == 'BUTTON') {
        let count = e.target.parentNode.querySelector('input').value;

        let needToBuild = [...e.target.parentNode.children];
        let metal = Number(needToBuild[0].textContent.split(' ')[0]) * count;
        let gas = Number(needToBuild[1].textContent.split(' ')[0]) * count;
        let diamond = Number(needToBuild[2].textContent.split(' ')[0]) * count;
        let energy = Number(needToBuild[3].textContent.split(' ')[0]) * count;
        let h3;
        let h2;
        if (Number(owns[0].textContent) < metal || Number(owns[1].textContent) < gas ||
            Number(owns[2].textContent) < diamond || Number(owns[3].textContent) < energy) {
            if (e.target.parentNode.children[e.target.parentNode.children.length - 1].tagName !== 'H2') {
                h2 = document.createElement('h2')
                h2.innerText = 'Sorry not enough resources!';
                e.target.parentNode.appendChild(h2)
            }
            return;
        }
        if (count <= 0) {
            if (e.target.parentNode.children[e.target.parentNode.children.length - 1].tagName !== 'H3') {
                h3 = document.createElement('h3')
                h3.innerText = 'Sorry the count of the ships must be grater than zero!';
                e.target.parentNode.appendChild(h3)
            }
            return;
        }
        let current = e.target.parentNode.children[3].children[0];
        let time = current.textContent.split(' : ');
        sendData("http://localhost:8080/api/ships/" + e.target.id + "/" + count)
        let seconds = Number(time[0]) * 3600 + Number(time[1]) * 60 + Number(time[2]);
        sessionStorage.setItem('ship-clicked-on', (new Date).toString());
        sessionStorage.setItem('ship-seconds-waiting', (seconds * count) + '');
        sessionStorage.setItem('ship-id', e.target.id);
        render(allCards(), container);


    }
})

function timeCounter() {
    let currentTime = sessionStorage.getItem('ship-seconds-waiting')
    let seconds = Number(currentTime);
    let clicked = new Date(sessionStorage.getItem('ship-clicked-on'));
    let timer = setInterval(countDown, 1000);

    async function countDown() {
        let timeContainer = document.getElementById('count-down-element');
        let v = clicked;
        let n = new Date();
        let s = seconds - Math.round((n.getTime() - v.getTime()) / 1000.);
        let m = 0;
        let h = 0;
        if (s > 59) {
            m = Math.floor(s / 60);
            s = s - m * 60
        }
        if (m > 59) {
            h = Math.floor(m / 60);
            m = m - h * 60
        }
        if (s > 0 || m > 0 || h > 0) {
            timeContainer.textContent = `${(h + '').padStart(2, '0')} : ${(m + '').padStart(2, '0')} : ${(s + '').padStart(2, '0')}`
        } else {
            clearInterval(timer);
            sessionStorage.removeItem('ship-clicked-on');
            sessionStorage.removeItem('ship-seconds-waiting');
            sessionStorage.removeItem('ship-id');
            response = await fetch("http://localhost:8080/api/ships");
            army = await response.json();
            await getOwns();
            render(allCards(), container);
        }
    }
}