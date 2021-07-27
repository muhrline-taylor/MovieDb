import React, { useEffect, useState } from 'react'
import MainService from '../services/MainService';

function ViewOneMovie({ ...props }) {
    const [movie, setMovie] = useState({});

    useEffect(() => {
        MainService.getMovieById(props.match.params.id)
            .then(res => {
                console.log(res.data);
                setMovie(res.data);
            })
            .catch(err => {
                console.log(err);
                setMovie({error: "Movie not found"})
            })
    },[])


    return (
        <>
            {
                movie.error ?
                <h1>{movie.error}</h1>
                :
                <div>
            
                    <h1>{movie.name}</h1>
                    <h2>{movie.year}</h2>
                    {
                        
                        movie.director ?
                        <p><a href={`/directors/${movie.director.id}`}>{movie.director.fname ? movie.director.fname:""} {movie.director.lname}</a></p>:""
                    }
                    <img 
                        src={movie.poster} 
                        alt={`${movie.name}_poster`}
                        style={{ width: "20vw", height: "auto" }}
                    />

                    <p>Set Director: </p>





                </div>
            }
        </>
        
    )
}

export default ViewOneMovie
