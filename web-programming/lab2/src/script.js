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
        send(x, yElements, r);
    }

});

document.querySelectorAll("input[name='y']").forEach(checkbox => {
    checkbox.addEventListener('change', function () {
        document.querySelectorAll("input[name='y']").forEach(cb => {
            if (cb !== this) cb.checked = false;
        });
    });
});


function send(x, yElements, r) {
    const data = JSON.stringify({x: x.value, y: yElements[0], r: r.value});
    console.log(data);
    fetch('/fcgi-bin/server.jar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })
        .then(response => {
            response.json().then(result => {
                console.log('response accepted');
                saveResponseToLocalStorage(result);
                showResponse(result);
            }).catch(error => console.error('Error:', error));
        })
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
    const resultBody = document.getElementById('result_table');
    const newRow = document.createElement('tr');

    newRow.innerHTML = `
            <td>${response.x}</td>
            <td>${response.y}</td>
            <td>${response.r}</td>
            <td>${response.result !== undefined ? (response.result ? 'есть пробитие' : 'осечка') : 'undefined'}</td>
            <td>${response.currentTime !== undefined ? response.currentTime : 'undefined'}</td>
            <td>${response.executionTime !== undefined ? response.executionTime : 'undefined'}</td>
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

    // if (isNaN(r)) {
    //     showError(r, "Нет, так не надо. Надо вот так: R - число");
    //     console.warn("Invalid R value:", r.value);
    //     return false;
    // }

    return true;
}

function resetForm() {
    document.getElementById("valForm").reset();
}

window.onload = () => {
    let data = getResponsesFromLocalStorage();

    for (let i = 0; i < data.length; i++) {
        console.log(data[i])
        showResponse(data[i]);
    }
}