import {UserContext} from "../UserContext";
import {useContext, useEffect, useState} from "react";
import LogOutButton from "../components/LogOutButton";
import "../pages/styles/MainPage.css"

export default function MainPage() {
    const {currentUser} = useContext(UserContext);
    return (
        <div>
            {(currentUser != null) ? (
                <>
                    <div className="logout-button"><LogOutButton/></div>
                    <Page/>
                </>
            ) : (
                <div>
                    <p>вы не вошли в систему</p>
                </div>
            )}
        </div>)
}

function Page() {
    const {currentUser} = useContext(UserContext);
    const login = currentUser?.login;
    const password = currentUser?.password;
    const isLogin = true
    const [r, setR] = useState(1);
    const [x, setX] = useState(0);
    const [y, setY] = useState(0);
    const [error, setError] = useState('');
    const [points, setPoints] = useState([]);

    useEffect(() => {
        loginned()
    }, [login, password]);

    function loginned() {
        fetch('api/points/getpoint', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({login, password, isLogin}),
        }).then(response => {
            if (!response.ok) {
                throw new Error('ERROR response.');
            }
            return response.json();
        })
            .then(json => {
                sessionStorage.setItem("points", JSON.stringify(json));
                setPoints(json);
            })
    }

    useEffect(() => {
        const resultsTableBody = document.querySelector('#results-table tbody');
        while (resultsTableBody.firstChild) {
            resultsTableBody.removeChild(resultsTableBody.firstChild);
        }

        points.forEach((point) => {
            if (point.r.toString() === r.toString()) {
                draw(point.x, point.y, point.r, point.hit);
                insertTable(point.x, point.y, point.r, point.hit, point.executionTime, point.serverTime);
            }
        });
    }, [points, r]);

    function handleCheck() {
        const x = document.getElementById("sliderX").value
        const y = document.getElementById("inputY").value
        const r = document.getElementById("sliderR").value
        if (isNaN(x) || x > 3 || x < -5) {
            document.getElementsByName("Xstatus").innerText = "некорректное значение для X"
            return
        }
        if (isNaN(y) || y > 3 || y < -5) {
            document.getElementsByName("Ystatus").innerText = "некорректное значение для Y"
            return
        }
        if (isNaN(y) || y > 3 || y < -5) {
            document.getElementsByName("Rstatus").innerText = "некорректное значение для R"
            return
        }
        checkPoint(x, y, r)
    }

    const handleChangeX = (event) => {
        setX(event.target.value);
    }

    const handleChangeY = (event) => {
        const value = event.target.value;
        setY(value);

        const numberValue = Number(value);
        if (isNaN(numberValue)) {
            setError('Введите числовое значение.');
        } else if (numberValue < -5 || numberValue > 3) {
            setError('Значение должно быть в диапазоне от -5 до 3.');
        } else {
            setError('');
        }
    }

    const handleChangeR = (event) => {
        const svgElement = document.getElementById('graph');
        const circles = Array.from(svgElement.getElementsByTagName('circle'));
        const newR = event.target.value
        setR(newR);
        circles.forEach(circle => {
            svgElement.removeChild(circle);
        });

        const resultsTableBody = document.querySelector('#results-table tbody');
        while (resultsTableBody.firstChild) {
            resultsTableBody.removeChild(resultsTableBody.firstChild);
        }

        const pointsString = sessionStorage.getItem("points");
        if (pointsString) {
            try {
                const points = JSON.parse(pointsString);
                setPoints(points)
                points.forEach((point) => {
                    // const pointR = parseFloat(point.r);
                    if (point.r.toString() === newR) {
                        insertTable(point.x, point.y, point.r, point.hit, point.executionTime, point.serverTime);
                        draw(point.x, point.y, point.r, point.hit);
                    }
                });
            } catch (error) {
                console.error("Ошибка парсинга JSON из sessionStorage:", error)
                sessionStorage.removeItem("points")
            }
        } else {
            console.log("Точки не найдены в sessionStorage");
        }
        GraphWithControls(newR)
    };

    function draw(x, y, r, answer) {
        const svg = document.getElementById("graph");
        let scale = 163 / Math.abs(r)
        const centerX = 350;
        const centerY = 250;

        const xSVG = centerX + (x * scale * ((r >= 0) ? 1 : -1));
        const ySVG = centerY - (y * scale * ((r >= 0) ? 1 : -1));

        const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", xSVG.toFixed(2));
        circle.setAttribute("cy", ySVG.toFixed(2));
        circle.setAttribute("r", "3");
        circle.setAttribute("class", "circles");
        circle.setAttribute("fill", answer ? "green" : "red");
        svg.appendChild(circle);
    }

    function checkPoint(x, y, r) {
        fetch('api/points/check-point', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({x, y, r, login}),
        }).then(response => {
            return response.json();
        }).then(json => {
            let points = sessionStorage.getItem("points");
            if (points) {
                points = JSON.parse(points);
            } else {
                points = [];
            }
            points.push(json);
            sessionStorage.setItem("points", JSON.stringify(points));
            const r = json.r;
            const x = json.x;
            const y = json.y;

            draw(x, y, r, json.hit)
            document.getElementById("sliderX").value = Math.round(x).toString()
            document.getElementById("inputY").value = Math.round(y).toString()

            insertTable(json.x, json.y, json.r, json.hit, json.executionTime, json.serverTime);
        });
    }

    function insertTable(x, y, r, isHit, executionTime, serverTime) {
        const table = document.querySelector('#results-table tbody');
        const newRow = table.insertRow();
        const xCell = newRow.insertCell(0);
        const yCell = newRow.insertCell(1);
        const rCell = newRow.insertCell(2);
        const answerCell = newRow.insertCell(3);
        const executionTimeCell = newRow.insertCell(4);
        const serverTimeCell = newRow.insertCell(5);

        xCell.innerText = x;
        yCell.innerText = y;
        rCell.innerText = r;
        answerCell.innerText = isHit ? "есть пробитие" : "осечка";
        executionTimeCell.innerText = executionTime;
        serverTimeCell.innerText = serverTime;

        const container = document.getElementById("results-table-container")
        // setTimeout(() => {
        //     container.scrollTop = container.scrollHeight;
        // }, 0);
    }

    useEffect(() => {
        const svgElement = document.getElementById("graph");

        const click = (event) => {
            const r = parseFloat(document.getElementById("sliderR").value);
            const point = svgElement.createSVGPoint();
            point.x = event.clientX;
            point.y = event.clientY;
            const svgCoords = point.matrixTransform(svgElement.getScreenCTM().inverse());
            console.log(svgCoords);

            const centerX = 350
            const centerY = 250
            const scale = 166 / Math.abs(r)

            const x = ((svgCoords.x - centerX) / scale * ((r >= 0) ? 1 : -1)).toFixed(2)
            const y = (-((svgCoords.y - centerY) / scale * ((r >= 0) ? 1 : -1))).toFixed(2)
            console.log(x, y)

            checkPoint(x, y, r);
        };

        svgElement.addEventListener('click', click);

        return () => {
            svgElement.removeEventListener('click', click);
        };
    }, [checkPoint]);

    function GraphWithControls(r) {
        const defaultText = "?";
        const updateLabel = (selector, value) => {
            const element = document.querySelector(selector);
            if (element) {
                element.textContent = isNaN(value) ? defaultText : value.toString();
            }
        }
        updateLabel("text[x='427']", r / 2);
        updateLabel("text[x='510']", r);
        updateLabel("text[x='178']", -r);
        updateLabel("text[x='256']", -r / 2);
        updateLabel("text[y='161']", r / 2);
        updateLabel("text[y='78']", r);
        updateLabel("text[y='327']", -r / 2);
        updateLabel("text[y='410']", -r);
    }

    return (<div className="main-container">
        <section className="block plot-section">
            <div className="graphic">
                <svg id="graph" height="500" width="700" xmlns="http://www.w3.org/2000/svg">
                    <svg width="700" height="500">
                        <path d="M 350 250
             L 350 167
             A 83 83 0 0 1 433 250
             L 350 250"
                              fill="#5b88ff" fillOpacity="0.6"/>
                    </svg>

                    <rect id="rect3" x="350" y="250" width="166" height="166" fill="#5b88ff" fillOpacity="0.6"/>

                    <polygon id="triangle" points="350,250 262,250 350,332" fill="#5b88ff" fillOpacity="0.6"/>


                    <line stroke="#000000" x1="100" x2="600" y1="250" y2="250"></line>
                    <line stroke="#000000" x1="350" x2="350" y1="0" y2="500"></line>
                    <polygon fill="black" points="350,0 344,15 356,15" stroke="black"></polygon>
                    <polygon fill="black" points="600,250 585,256 585,244" stroke="black"></polygon>

                    <line stroke="black" x1="432" x2="432" y1="255" y2="245"></line>
                    <line stroke="black" x1="515" x2="515" y1="255" y2="245"></line>

                    <line stroke="black" x1="183" x2="183" y1="255" y2="245"></line>
                    <line stroke="black" x1="266" x2="266" y1="255" y2="245"></line>

                    <line stroke="black" x1="345" x2="355" y1="166" y2="166"></line>
                    <line stroke="black" x1="345" x2="355" y1="83" y2="83"></line>

                    <line stroke="black" x1="345" x2="355" y1="332" y2="332"></line>
                    <line stroke="black" x1="345" x2="355" y1="415" y2="415"></line>

                    <text fill="black" x="427" y="240">R/2</text>
                    <text fill="black" x="510" y="240">R</text>

                    <text fill="black" x="178" y="240">-R</text>
                    <text fill="black" x="256" y="240">-R/2</text>

                    <text fill="black" x="360" y="161">R/2</text>
                    <text fill="black" x="360" y="78">R</text>

                    <text fill="black" x="360" y="327">-R/2</text>
                    <text fill="black" x="360" y="410">-R</text>

                    <text fill="black" x="360" y="10">Y</text>
                    <text fill="black" x="590" y="240">X</text>
                </svg>
            </div>
        </section>
        <div className="selectors">
            <div>
                <label htmlFor="sliderX">Выберите значение X</label>
                <p>{x}</p>
                <input id="sliderX" type="range" min="-5" max="3" step="1" onChange={handleChangeX} value={x}/>
            </div>
            <br></br>
            <div>
                <label htmlFor="inputY">Выберите значение Y</label><br></br>
                <input id="inputY" type="text" value={y} onChange={handleChangeY}/>
                {error && <p style={{color: 'red'}}>{error}</p>}
            </div>
            <br/>
            <div>
                <label htmlFor="sliderR">Выберите значение R</label>
                <p>{r}</p>
                <input id="sliderR" type="range" min="-5" max="3" step="1" onChange={handleChangeR} value={r}/>
            </div>
            <h4 id="Xstatus"></h4>
            <h4 id="Ystatus"></h4>
            <h4 id="Rstatus"></h4>
        </div>
        <button id="value-button" onClick={handleCheck}>проверить</button>
        <div id="results-table-container">
            <table id="results-table">
                <thead>
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Попадание</th>
                    <th>Время<br></br> выполнения (нс)</th>
                    <th>Время на сервере</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>)
}