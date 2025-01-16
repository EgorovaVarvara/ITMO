function updateTime() {
    let time = document.querySelector(".time")
    const options = { day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false };
    time.textContent = new Date().toLocaleString('ru-RU', options).replace(',', '');
}

updateTime();
setInterval(updateTime, 1000)