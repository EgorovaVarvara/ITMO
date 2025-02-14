import {useNavigate} from "react-router-dom";


export default function RegistrationButton() {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate("/register");
    }
    return (
        <button className="registration-button" onClick={handleClick}>Регистрация</button>
    )
}