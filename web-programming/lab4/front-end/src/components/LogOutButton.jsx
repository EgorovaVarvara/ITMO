import {useNavigate} from "react-router-dom";
import {useContext} from "react";
import {UserContext} from "../UserContext";
import "../components/LogOutButton.css"


export default function LogOutButton() {
    const navigate = useNavigate();
    const {logout} = useContext(UserContext);

    const handleClick = () => {
        logout();
        navigate("/");
    }
    return (
        <button className="logOut-button" onClick={handleClick}>Выход</button>
    )
}