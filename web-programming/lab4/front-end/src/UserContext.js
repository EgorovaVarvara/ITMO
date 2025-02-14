import React, {createContext, useState, useEffect } from 'react';

const UserContext = createContext({});

const getInitialState = () => {
    const currentUser = sessionStorage.getItem("currentUser");
    return currentUser ? JSON.parse(currentUser) : null
}

const UserProvider = ({children}) => {
    const [currentUser, setCurrentUser] = useState(getInitialState);

    useEffect(() => {
        sessionStorage.setItem("currentUser", JSON.stringify(currentUser))
    }, [currentUser])

    const login = (user) => {
        console.log(user)
        setCurrentUser(user)
    }

    const logout = () => {
        setCurrentUser(null)
    }

    return (
        <UserContext.Provider
            value={{currentUser, login, logout}}>
            {children}
        </UserContext.Provider>
    )
}

export { UserContext, UserProvider }