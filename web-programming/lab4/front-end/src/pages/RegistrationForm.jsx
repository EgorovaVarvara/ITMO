import {useContext, useState} from "react";
import {UserContext} from "../UserContext";
import {useNavigate} from "react-router-dom";
import "./styles/LoginRegisterPage.css"


export default function RegistrationForm() {
    const [login, setLogin] = useState('')
    const [password, setPassword] = useState('')
    const {login: loginUser} = useContext(UserContext)
    const navigate = useNavigate()

    function handleClickNext(){
        if(password.length < 3){
            document.getElementById("passwordStatus").innerText = "пароль не подходит под формат"
        } else{
            sendRegistration(login, password)
            document.getElementById("loginStatus").innerText = ""
            document.getElementById("passwordStatus").innerText = ""
        }
    }

    function handleClickBack(){
        navigate("/");
    }

    function sendRegistration(login, password){
        const isLogin = false
        return fetch('/api/main/registration', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({login, password, isLogin}),
        })
            .then(
                response => response.json()
            )
            .then(json => {
                console.log(json.registered)
                if (json.registered) {
                    loginUser(json)
                    navigate("/main")
                } else {
                    document.getElementById("loginStatus").innerText = "данный аккаунт уже зарегистрирован"
                }
            }).catch(error => {
                console.error("Ошибка сети:", error);
                document.getElementById("loginStatus").innerText = "Ошибка подключения, попробуйте позже";
            });
    }

    return(<div className="register-form">
        <button className="BackButton" onClick={handleClickBack}>Вернуться</button>
        <h2>Регистрация</h2>
        <input id="login"
               type="text"
               placeholder="Login"
               value={login}
               onChange={(e) => setLogin(e.target.value)}/>
        <input id="password"
               type="password"
               placeholder="Password"
               value={password}
               onChange={(e) => setPassword(e.target.value)}/>
        <button className="RegisterButton" onClick={handleClickNext}>Зарегистрироваться</button>
        <div>
            <h4 id="loginStatus"> </h4>
            <h4 id="passwordStatus"> </h4>
        </div>
    </div>)
}