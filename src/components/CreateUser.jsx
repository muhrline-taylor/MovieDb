import React, { useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';


function CreateUser() {
    const history = useHistory();
    const [userForm, setUserForm] = useState({
        email: "",
        username: "",
        password: "",
        confirm_pw: ""
    });



    const changeHandler = e => {
        setUserForm({
            ...userForm,
            [e.target.name]: e.target.value
        })
    }

    const submitHandler = e => {
        e.preventDefault();
        console.log(userForm);
        MainService.createUser(userForm)
            .then(res => {
                console.log(res)
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

            <p>Email: </p>
            <input 
                name="email"
                placeholder="Email..."
                onChange={changeHandler}
                value={userForm.email}
            />

            <p>Username: </p>
            <input 
                name="username"
                placeholder="Username..."
                onChange={changeHandler}
                value={userForm.username}
            />

            <p>Password: </p>
            <input 
                name="password"
                placeholder="Password..."
                onChange={changeHandler}
                value={userForm.password}
            />

            <p>Confirm Password: </p>
            <input 
                name="confirm_pw"
                placeholder="Confirm Password..."
                onChange={changeHandler}
                value={userForm.confirm_pw}
            />

            <br/>
            <input 
                type="submit"
                value="Register"
                onClick={submitHandler}
            />


        </div>
    )
}

export default CreateUser
