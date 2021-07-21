import React, { useEffect, useState } from 'react';
import MainService from '../services/MainService';
import "../static/css/ViewAll.css";

function ViewAllMovies() {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        MainService.getAllMovies()
            .then(res => {
                console.log(res.data.content)
                setMovies(res.data.content)
            })
    },[])



    return (
        <div className="viewAll">
            
            <div className="viewAll__top">
                <div className="viewAll__topButtonContainer">
                    <a 
                        className="viewAll__topButton"
                        href="/movies/new"
                        style={{ backgroundColor: "green" }}
                    >
                        Create
                    </a>

                    
                </div>
            </div>

            <div className="viewAll__table">

                <table style={{width: "100%", paddingTop: "1%"}}>

                    <tr>

                        <th>Name</th>
                        <th>Director</th>
                        <th>Release</th>
                        <th>Actors</th>
                        <th>Actions</th>

                    </tr>

                    {
                        movies.map((movie, k) => (
                            <tr>
                                <td className="center">{movie.name}</td>
                                <td className="center">{movie.director ? movie.director.fname:""}</td>
                                <td className="center">{movie.year}</td>
                                <td className="center">{
                                    movie.actors ?
                                    movie.actors.map((actor, key) => (
                                        `${actor}`
                                    )):""
                                }</td>
                                <td className="center">_ACTIONS_</td>


                            </tr>
                        ))
                    }

                </table>



            </div>


        </div>
    )
}

export default ViewAllMovies
