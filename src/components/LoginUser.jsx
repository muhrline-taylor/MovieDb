import React, { useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function LoginUser() {
    const history = useHistory();
    const [loginForm, setLoginForm] = useState({
        email: "",
        password: ""
    })

    const changeHandler = e => {
        setLoginForm({
            ...loginForm,
            [e.target.name]: e.target.value
        });
    }

    const submitHandler = e => {
        e.preventDefault();
        MainService.loginUser(loginForm)
            .then(res => {
                console.log(res.data.id);
                if(localStorage.getItem("loggedInUser") !== null){
                    localStorage.removeItem("loggedInUser")
                }
                localStorage.setItem("loggedInUser", res.data.id);
                history.push("/");
            })
            .catch(err => console.log(err))
    }



    return (
        <div>
            <form>

                <p>Email: </p>
                <input 
                    name="email"
                    onChange={changeHandler}
                />

                <p>Password: </p>
                <input 
                    name="password"
                    type="password"
                    onChange={changeHandler}
                />
                
                <br/>
                <input 
                    type="submit"
                    value="Log In"
                    onClick={submitHandler}
                />


            </form>
        </div>
    )
}

export default LoginUser
