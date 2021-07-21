import React, { useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function CreateDirector() {
    const history = useHistory();
    const [directorForm, setDirectorForm] = useState({
        fname: "",
        lname: ""
    });

    const changeHandler = e => {
        setDirectorForm({
            ...directorForm,
            [e.target.name]: e.target.value
        });
    }

    const submitHandler = e => {
        e.preventDefault();
        MainService.createDirector(directorForm)
            .then(res => {
                console.log(res)
                history.push("/directors")
            })
            .catch(err => {
                console.log(err)
            })
    }



    return (
        <div>
            <form>
                <p>First Name: </p>
                <input 
                    name="fname"
                    placeholder="First Name..."
                    value={directorForm.fname}
                    onChange={changeHandler}
                />

                <p>Last Name: </p>
                <input 
                    name="lname"
                    placeholder="Last Name..."
                    value={directorForm.lname}
                    onChange={changeHandler}
                />
                <br/>
                <input type="submit" value="Create" onClick={submitHandler}/>



            </form>
        </div>
    )
}

export default CreateDirector
