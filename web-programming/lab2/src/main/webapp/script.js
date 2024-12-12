document.getElementById('valForm').addEventListener('submit', function (e) {
    e.preventDefault();
    let x = document.getElementById('x');
    let yCheckBoxes = document.querySelectorAll('input[name="y"]:checked');
    let r = document.getElementById('r');

    if (validate(x, yCheckBoxes, r)) {
        let yElements = [];
        for (let index = 0; index < yCheckBoxes.length; index++) {
            if (yCheckBoxes[index].checked) {
                yElements.push(yCheckBoxes[index].value);
            }
        }
        send(x.value, yElements[0], r.value, "fom");
    }

});

document.getElementById('r').addEventListener('change', function (e) {
    document.querySelectorAll("circle").forEach(point => point.remove());
    drawPoints(getResponsesFromLocalStorage());
});

document.getElementById('clear_table').addEventListener('click', function (e) {
    console.log('очищено')
    document.querySelectorAll("circle").forEach(point => point.remove());
    document.getElementById("resultBody").querySelectorAll("tr").forEach(row => row.remove());
    localStorage.clear();
});

document.getElementById('area').addEventListener('click', function (e) {
    const point = document.getElementById('graph').createSVGPoint();
    point.x = e.clientX;
    point.y = e.clientY;

    const svgPoint = point.matrixTransform(document.getElementById('graph').getScreenCTM().inverse());
    let r = document.getElementById("r");
    if (r.value !== "") {
        let userPointX = ((svgPoint.x - 150) / 100 * r.value).toFixed(2);
        let userPointY = ((150 - svgPoint.y) / 100 * r.value).toFixed(2);
        console.log(`Координаты на плоскости: x=${userPointX}, y=${userPointY}, Координаты в svg: (${svgPoint.x.toFixed(2)}, ${svgPoint.y.toFixed(2)})`);
        send(userPointX, userPointY, r.value, "click");
    } else {
        showError(document.getElementById('graph'), "Необходимо выбрать значение радиуса");
    }
})

document.querySelectorAll("input[name='y']").forEach(checkbox => {
    checkbox.addEventListener('change', function () {
        document.querySelectorAll("input[name='y']").forEach(cb => {
            if (cb !== this) cb.checked = false;
        });
    });
});


function send(x, y, r, flag) {

    const data = JSON.stringify({x: x, y: y, r: r, flag: flag});
    console.log(data);
    fetch("/lab2.1/controller", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: data
    })
        .then(response => response.json())
        .then(data => {
            saveResponseToLocalStorage(data);
            showResponse(data);
            drawPoint(data.x, data.y, data.r, data.value);
        }).catch(error => console.error('Error:', error));
}

function getResponsesFromLocalStorage() {
    let data = localStorage.getItem("data")
    if (data == null) {
        data = '[]'
    }
    const obj = JSON.parse(data)
    return Object.keys(obj).map((key) => obj[key]);
}

function saveResponseToLocalStorage(response) {
    let responses = getResponsesFromLocalStorage()
    responses.push(response)
    localStorage.setItem("data", JSON.stringify(responses))
}

function showResponse(response) {
    const resultBody = document.getElementById('resultBody');
    const newRow = document.createElement('tr');

    newRow.innerHTML = `
            <td>${response.x}</td>
            <td>${response.y}</td>
            <td>${response.r}</td>
            <td>${response.value !== undefined ? (response.value === "true" ? 'есть пробитие' : 'осечка') : 'undefined'}</td>
            <td>${response.time !== undefined ? response.time : 'undefined'}</td>
            <td>${response.execTime !== undefined ? response.execTime : 'undefined'}</td>
            `;

    resultBody.appendChild(newRow);
}

function showError(element, message) {
    const errorElement = document.createElement('div');
    errorElement.classList.add('error-message');
    errorElement.textContent = message;
    errorElement.style.color = 'red';
    errorElement.style.fontSize = '13px';
    errorElement.style.textAlign = 'center';
    element.parentNode.insertBefore(errorElement, element.nextSibling);
    setTimeout(function () {
        errorElement.remove();
    }, 3000);
}

function validate(x, yCheckBoxes, r) {
    let yElements = [];
    for (let index = 0; index < yCheckBoxes.length; index++) {
        if (yCheckBoxes[index].checked) {
            yElements.push(yCheckBoxes[index].value);
        }
    }
    if (x === null) {
        showError(x, "Необходимо выбрать значение координаты X :(");
        // console.warn("Invalid X value:", x.value);
        return false;
    }
    if (!/^-?\d+(\.\d+)?$/.test(x.value) || x.value < -3 || x.value > 5) {
        showError(x, "Необходимо ввести валидное значение Х от -3 до 5 :(");
        console.warn("Invalid X value:", x.value);
        return false;
    }

    if (yElements.length === 0) {
        showError(document.getElementById("y_label"), "Необходимо указать значение координаты Y :(");
        console.warn("Invalid Y value:", yElements);
        return false;
    }

    if (yElements.length !== 1) {
        showError(yCheckBoxes, "Нужно выбрать только 1 значение Y :(");
        console.warn("Invalid Y value:", yElements);
        return false;
    }


    if (r.value === "") {
        showError(r, "Необходимо указать значение радиуса:(");
        console.warn("Invalid R value:", r.value);
        return false;
    }
    return true;
}

function resetForm() {
    document.getElementById("valForm").reset();
}

function drawPoint(x, y, r, hit) {
    let svgPointX = 100 * parseFloat(x) / r + 150;
    let svgPointY = 150 - parseFloat(y) * 100 / r;
    let svg = document.getElementById("graph");
    let dot = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    dot.setAttribute("cx", svgPointX.toString());
    dot.setAttribute("cy", svgPointY.toString());
    dot.setAttribute("r", "4");
    dot.setAttribute("fill", hit === "true" ? "green" : "red");
    svg.appendChild(dot);
}

function drawPoints(points) {
    console.log('drawing')
    let r = document.getElementById('r').value;
    if (r === "") return;

    for (let i = 0; i < points.length; i++) {
        let point = points[i];
        if (Math.abs(point.x) < r * 1.5 && Math.abs(point.y) < r * 1.5) {
            drawPoint(point.x, point.y, r, point.value);
        }
    }
}

window.onload = () => {
    let data = getResponsesFromLocalStorage();

    for (let i = 0; i < data.length; i++) {
        console.log(data[i])
        showResponse(data[i]);
    }
    drawPoints(data);
}
