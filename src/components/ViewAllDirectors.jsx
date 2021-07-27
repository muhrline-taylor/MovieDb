import React, { useEffect, useState } from 'react';
import MainService from '../services/MainService';
import "../static/css/ViewAll.css";

function ViewAllDirectors() {
    const [directors, setDirectors] = useState([]);

    useEffect(() => {
        MainService.getAllDirectors()
            .then(res => {
                console.log(res.data.content);
                setDirectors(res.data.content);
            })
    },[])



    return (
        <div className="viewAll">
            
            <div className="viewAll__top">
                <div className="viewAll__topButtonContainer">
                    <a 
                        className="viewAll__topButton"
                        href="/directors/new"
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
                        <th>Movies</th>
                        <th>Actions</th>

                    </tr>

                    {
                        directors.map((director, k) => (
                            <tr>

                                <td className="center"><a href={`/directors/${director.id}`}>{director.fname ? `${director.fname} ${director.lname}`:`${director.lname}`}</a></td>
                                <td className="center">{
                                        director.movies.map((movie, key) => (
                                            <a href={`/movies/${movie.id}`}>{movie.name}</a>
                                        ))
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

export default ViewAllDirectors
