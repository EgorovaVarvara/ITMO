import {useNavigate} from "react-router-dom";



export default function LoginButton() {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate("/login");
    }
    return (
        <button className="login-button" onClick={handleClick}>Вход</button>
    )
}