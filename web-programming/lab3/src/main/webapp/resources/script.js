window.onload = init;
window.redrawCanvas = redrawCanvas;

function drawCanvas1() {
    const canvas = document.getElementById('canvas1');
    canvas.width = 300;
    canvas.height = 300;
    if (canvas.getContext) {
        const ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        // Квдрат
        ctx.beginPath();
        ctx.rect(150, 150, 150, 150);
        ctx.fillStyle = 'rgba(91,136,255,0.82)';
        ctx.fill();
    }
}

function drawCanvas2() {
    const canvas = document.getElementById('canvas2');
    canvas.width = 300;
    canvas.height = 300;
    if (canvas.getContext) {
        const ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        // Треугольник
        ctx.beginPath();
        ctx.moveTo(150, 150 + 75); // Вершина (0, -R/2)
        ctx.lineTo(75, 150); // Вершина (-R/2, 0)
        ctx.lineTo(150, 150); // Вершина (0, 0)
        ctx.closePath();
        ctx.fillStyle = 'rgba(91,136,255,0.82)';
        ctx.fill();
    }
}

function drawCanvas3() {
    const canvas = document.getElementById('canvas3');
    canvas.width = 300;
    canvas.height = 300;
    if (canvas.getContext) {
        const ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        // Четверть круга
        ctx.beginPath();
        ctx.arc(150, 150, 75, 1.5 * Math.PI, 2 * Math.PI); // Радиус R/2
        ctx.lineTo(150, 150);
        ctx.closePath();
        ctx.fillStyle = 'rgba(91,136,255,0.82)';
        ctx.fill();
    }
}

function redrawCanvas() {
    clearDots();
    drawCanvas1();
    drawCanvas2();
    drawCanvas3();

    let r = document.getElementById("mainForm:rSlider").value;
    drawDotsFromBeanTableData(r);
    updateR(r);
}

function init() {
    redrawCanvas();
    document.querySelector(".axis-box").addEventListener('click', handleAxisBoxClick);
}

function handleAxisBoxClick(event) {
    const axisBox = document.querySelector(".axis-box");
    const rect = axisBox.getBoundingClientRect();
    let r = document.getElementById("mainForm:rSlider").value;


    let x = event.clientX - rect.left - 150;
    let y = -(event.clientY - rect.top - 150); // Инвертируем Y, т.к. ось Y направлена вверх

    x = (x / 100) * r;
    y = (y / 100) * r;

    sendRequest(x, y, r);
}


function drawDot(clientX, clientY, hit) {
    const dot = document.createElement('div');
    dot.className = 'dot';
    dot.style.left = clientX + `px`;
    dot.style.top = clientY + `px`;
    if (hit) dot.classList.add("hit");
    document.body.appendChild(dot);
}

function sendRequest(x, y, r) {
    updateR(r);
    updateX(x);
    updateY(y);
    document.getElementById("mainForm:submit_button").click();
}

function showResponse(response, clientX, clientY) {
    let tbody = document.querySelector("tbody") // table

    const tr = document.createElement("tr")
    tr.setAttribute("class", "move-in")

    const th1 = document.createElement("th")
    th1.setAttribute("class", "row")
    th1.appendChild(document.createTextNode(Number(response.x).toFixed(4)))

    const th2 = document.createElement("th")
    th2.setAttribute("class", "row")
    th2.appendChild(document.createTextNode(Number(response.y).toFixed(4)))

    const th3 = document.createElement("th")
    th3.setAttribute("class", "row")
    th3.appendChild(document.createTextNode(Number(response.r).toFixed(0)))


    const th4 = document.createElement("th")
    th4.setAttribute("class", "row")
    if (response.isHit) th4.setAttribute("class", "row text-gradient");
    th4.appendChild(document.createTextNode(response.isHit ? "пробитие" : "осечка"))

    tr.appendChild(th1)
    tr.appendChild(th2)
    tr.appendChild(th3)
    tr.appendChild(th4)

    tbody.insertBefore(tr, tbody.firstChild)
    drawDot(clientX, clientY, response.isHit)
}

function drawDotsFromBeanTableData(r) {
    const tbody = document.querySelector("table#results-table > tbody")
    let output = []
    tbody.childNodes.forEach(function (tr) {
        let data = []
        tr.childNodes.forEach(function (th) {
            if (th.textContent.trim() !== "") data.push(th.textContent.trim());
        })
        if (data.length !== 0) output.push(data);
    });
    console.log(output)

    const rect = document.querySelector(".axis-box").getBoundingClientRect();
    for (var i = 0; i < output.length; i++) {
        let x = Number(output[i][0]);
        let y = Number(output[i][1]);
        let isHit = String(output[i][3]) === 'пробитие';

        x /= r
        y /= r
        x *= 100
        y *= 100

        y = 150 - y
        x += 150

        x += rect.left;
        y += rect.top;

        drawDot(x, y, isHit)

    }
}

function clearDots() {
    const dots = document.querySelectorAll(".dot");
    dots.forEach(function (dot) {
        dot.remove();
    });
}

function myXSliderFunction() {
    var sliderValue = document.getElementById('mainForm:xSlider').value
    updateX(sliderValue)
}

function myRSliderFunction() {
    var sliderValue = document.getElementById('mainForm:rSlider').value
    updateR(sliderValue)
    redrawCanvas();
}

function updateX(inputX) {
    document.getElementById("mainForm:xSlider").value = inputX;
    document.getElementById("mainForm:xHidden").value = inputX;
}

function updateY(inputY) {
    document.getElementById("mainForm:yInput").value = inputY;
    document.getElementById("mainForm:yHidden").value = inputY;
}

function updateR(inputR) {
    document.getElementById("mainForm:rSlider").value = inputR;
    document.getElementById("mainForm:rHidden").value = inputR;
}
