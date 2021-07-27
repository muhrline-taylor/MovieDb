import React, { useEffect, useState } from 'react'
import MainService from '../services/MainService';

function AddActorsToMovie() {
    const [actors, setActors] = useState([]);
    const [movie, setMovie] = useState({});
    const [actorsForm, setActorsForm] = useState([]);
    const [actorIds, setActorIds] = useState([]);

    const changeHandler = e => {
        console.log(actorsForm);
        for(let i=0; i<actorsForm.length; i++){
            if(e.target.value === actorIds[i]){
                try{
                    var newActorIds = [];
                    for(let j=0; j<actorIds.length;j++){
                        if(!actorIds[j] === e.target.value){
                            newActorIds.push(actorIds[j]);
                        }
                    setActorIds(newActorIds);
                    }
                } catch(err){
                    console.log("did not find idx");
                }
            } else {
                setActorIds([
                    ...actorIds,
                    e.target.value
                ])
            }
        }
    }

    const submitHandler = e => {
        e.preventDefault();
        console.log(actorIds);
    }

    useEffect(() => {
        MainService.getAllActors()
            .then(res => {
                console.log(res.data.content);
                setActors(res.data.content);
                for(let i=0; i<res.data.content.length; i++){
                    setActorsForm([
                        ...actorsForm,
                        res.data.content[i]
                    ])
                }
            })
            .catch(err => {
                console.log(err);
            })
        console.log(actorsForm);
    },[])



    return (
        <div>
            
            <p
                style={{ color: "silver" }}
            >
                *Unavailable actors must be created beforehand*
                <br/>
                *Please add actor with 'Actors' tab*
            </p>

            <h2>Which actors are in this movie?</h2>

            {
                actors ?
                actors.map((actor, key) => (

                    <>
                    <input 
                        type="checkbox" 
                        name="checkbox"
                        onChange={changeHandler}
                    />
                    <label for={`${actor.lname}`}>{actor.lname}</label>
                    </>


                )):""
            }

            <br/>
            <input 
                type="submit"
                value="Add"
                onClick={submitHandler}
            />

            

        </div>
    )
}

export default AddActorsToMovie
