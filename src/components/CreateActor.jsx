import React, { useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function CreateActor() {
    const history = useHistory();
    const [actorForm, setActorForm] = useState({
        fname: "",
        lname: ""
    });

    const changeHandler = e => {
        setActorForm({
            ...actorForm,
            [e.target.name]: e.target.value
        })
    }

    const submitHandler = e => {
        e.preventDefault();
        MainService.createActor(actorForm)
            .then(res => {
                console.log(res);
                history.push("/actors");
            })
            .catch(err => {
                console.log(err);
            })
    }



    return (
        <div>
            <form>

                <p>First Name: </p>
                <input 
                    name="fname"
                    placeholder="First Name..."
                    value={actorForm.fname}
                    onChange={changeHandler}
                />

                <p>Last Name: </p>
                <input 
                    name="lname"
                    placeholder="Last Name..."
                    value={actorForm.lname}
                    onChange={changeHandler}
                />

                <br/>
                <input 
                    type="submit"
                    value="Create"
                    onClick={submitHandler}
                />



            </form>
        </div>
    )
}

export default CreateActor
