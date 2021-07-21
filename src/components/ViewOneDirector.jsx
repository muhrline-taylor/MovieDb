import React, { useEffect, useState } from 'react'
import MainService from '../services/MainService';

function ViewOneDirector({ ...props }) {
    const [director, setDirector] = useState({});

    useEffect(() => {
        MainService.getDirectorById(props.match.params.id)
            .then(res => {
                console.log(res.data);
                setDirector(res.data);
            })
            .catch(err => {
                console.log(err);
                setDirector({error: "Director not found"})
            })
    },[])



    return (
        <div>
            
            {
                director.error ?
                <h1>{director.error}</h1>
                :<>
                    <h1>
                    {
                        director.fname ?
                        `${director.fname} ${director.lname}`
                        :`${director.lname}`
                    }
                    </h1>
                    <p>
                        {
                            director.movies ?
                                director.movies[0] ?
                                director.movies.map((movie, k) => (
                                    movie.name
                                )):""
                            :""
                        }
                    </p>
                
                
                
                
                </>
            }



        </div>
    )
}

export default ViewOneDirector
