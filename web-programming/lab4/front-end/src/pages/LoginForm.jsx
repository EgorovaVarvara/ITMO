import {useContext, useState} from "react";
import {useNavigate} from "react-router-dom";
import {UserContext} from "../UserContext";
import "./styles/LoginRegisterPage.css"

export default function LoginForm() {
    const [userLogin, setUserLogin] = useState('')
    const [password, setPassword] = useState('')
    const {login: lgn} = useContext(UserContext);
    const navigate = useNavigate();

    function handleClickNext(){
        sendLogin(userLogin, password);
    }
    function handleClickBack(){
        navigate("/");
    }

    function sendLogin(login, password){
        const isLogin = false
        console.log(login)
        return fetch('/api/main/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({login, password, isLogin}),
        })
            .then(
                response => response.json()
            )
            .then(json => {
                console.log(json)
                if (json.registered) {
                    lgn(json)
                    navigate("/main")
                } else {
                    document.getElementById("loginStatus").innerText = "неверный логин или пароль"
                }
            }).catch(error => {
                console.error("Ошибка сети:", error);
                document.getElementById("loginStatus").innerText = "Ошибка подключения, попробуйте позже";
            });
    }
    return (<div className="login-form">
            <button className="BackButton" onClick={handleClickBack}>Вернуться</button>
            <h2>Вход</h2>
            <input id="login"
                   type="text"
                   placeholder="Login"
                   value={userLogin}
                   onChange={(e) => setUserLogin(e.target.value)}
                   required/>
            <input id="password"
                   type="password"
                   placeholder="Password"
                   value={password}
                   onChange={(e) => setPassword(e.target.value)}
                   required/>
            <button className="LoginButton" onClick={handleClickNext}>Вход</button>
            <div>
                <h4 id="loginStatus"> </h4>
            </div>

        </div>
    )
}