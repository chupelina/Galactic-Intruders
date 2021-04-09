import {html, render} from 'https://unpkg.com/lit-html?module';

export async  function  getOwns(){
    let response = await fetch("http://localhost:8080/owns");
    let data = await response.json();
    let container = document.querySelector('footer');
    render(card(data), container);

}
let card = (obj)=> html`<p>Total owns:</p>
    <p>Metal: <span>${obj.metalOwn}</span></p>
    <p>Gas: <span>${obj.gasOwn}</span></p>
    <p>Diamond: <span>${obj.diamondOwn}</span></p>
    <p>Energy: <span >${obj.energyOwn}</span></p>`;

getOwns();