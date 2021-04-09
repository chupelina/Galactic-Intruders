import {html, render} from 'https://unpkg.com/lit-html?module';
import {convertTime, sendData} from "./common.js";
import {getOwns} from"./owns.js"

let response = await fetch("http://localhost:8080/api/science")
let scienceProjects = await response.json();

let allCards = () => Object.entries(scienceProjects).map(([k, v]) => {
    console.log(v);
    let button;
    let time = convertTime(v.time);
    if (sessionStorage.getItem('science-clicked-on')) {
        if (Number(sessionStorage.getItem('science-id')) === v.id) {
            timeCounter();
            button = html`<p>Remaining time <span id="count-down-element">${time}</span></p>`;
        } else {
            button = html`</br>`;
        }
    } else {
        button = html`<button type="submit" id="${v.id}"> Build</button>`;
    }
    return  makeScienceCard(v, time, button);
});
let container = document.getElementById('card-science');



let makeScienceCard = (object, time, button) => html`<div class="science">
                <h1>${object.name}</h1>
                <p>Current level: <span>${object.currentLevel}</span></p>
                <p>${object.description}</p>
                <p>For the next level you need to wait <span id="time">${time}</span> and spend</p>
                <ul>
                    <li>${object.metal} metal</li>
                    <li>${object.gas} gas</li>
                    <li>${object.diamond} diamond</li> 
                    <li>${object.energy} energy</li>  
                </ul>
                ${button}
                 </div>`;

render(allCards(), container);
let owns = [...document.getElementsByTagName('footer')[0].querySelectorAll('p>span')];

container.addEventListener('click', async (e) => {
        if (e.target.innerText === 'Build' && e.target.tagName === 'BUTTON') {
            let needToBuild = [...e.target.parentNode.querySelector('ul').children];
            let metal =Number( needToBuild[0].textContent.split(' ')[0]);
            let gas = Number(needToBuild[1].textContent.split(' ')[0]);
            let diamond = Number(needToBuild[2].textContent.split(' ')[0]);
            let energy = Number(needToBuild[3].textContent.split(' ')[0]);
            if (Number(owns[0].textContent) < metal || Number(owns[1].textContent) < gas ||
                Number(owns[2].textContent) < diamond || Number(owns[3].textContent) < energy) {
                if (e.target.parentNode.children[e.target.parentNode.children.length - 1].tagName !== 'H2') {
                    let h2 = document.createElement('h2')
                    h2.innerText = 'Sorry not enough resources!';
                    e.target.parentNode.appendChild(h2)
                }
                return;
            }
            sendData("http://localhost:8080/api/science/" + e.target.id);
            let time = e.target.parentNode.children[3].children[0].textContent;
            sessionStorage.setItem('science-clicked-on', (new Date).toString());
            sessionStorage.setItem('science-seconds-waiting', time);
            sessionStorage.setItem('science-id', e.target.id);
            render(allCards(), container);

        }
    }
)

function timeCounter() {
    let currentTime = sessionStorage.getItem('science-seconds-waiting').split(" : ")
    let seconds = Number(currentTime[0]) * 3600 + Number(currentTime[1]) * 60 + Number(currentTime[2]);
    let clicked = new Date(sessionStorage.getItem('science-clicked-on'));

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
            sessionStorage.removeItem('science-clicked-on');
            sessionStorage.removeItem('science-seconds-waiting');
            sessionStorage.removeItem('science-id');
            response = await fetch("http://localhost:8080/api/science")
            scienceProjects = await response.json();
            await getOwns();
            render(allCards(), container);
        }
    }
}


