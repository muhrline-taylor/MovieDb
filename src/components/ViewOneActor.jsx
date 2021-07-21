import React, { useEffect, useState } from 'react'
import MainService from '../services/MainService';
import ViewAllMovies from './ViewAllMovies';

function ViewOneActor({ ...props }) {
    const [actor, setActor] = useState({});

    useEffect(() => {
        MainService.getActorById(props.match.params.id)
            .then(res => {
                console.log(res.data);
                setActor(res.data);
            })
            .catch(err => {
                console.log(err);
                setActor({error: "Actor not found"});
            })
    },[])




    return (
        <div>
            
            {
                actor.error ?
                <h1>{actor.error}</h1>
                :
                <>
                
                {
                    actor.fname ?
                    <h1>{actor.fname} {actor.lname}</h1>
                    :<h1>{actor.lname}</h1>
                }
                {
                    actor.movies ?
                        actor.movies[0] ?
                        <>
                        <h3>Movies: </h3>
                        {
                            actor.movies.map((movie, k) => (
                                    <p>{movie.name}</p>
                                ))
                        }
                        </>
                        :""
                    :""
                }
                
                
                
                
                
                
                </>
            }



        </div>
    )
}

export default ViewOneActor
