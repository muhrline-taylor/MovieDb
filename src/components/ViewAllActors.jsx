import React, { useEffect, useState } from 'react'
import MainService from '../services/MainService';

function ViewAllActors() {
    const [actors, setActors] = useState([]);

    useEffect(() => {
        MainService.getAllActors()
            .then(res => {
                console.log(res.data.content);
                setActors(res.data.content);
            })
    },[])



    return (
        <div className="viewAll">
            
            <div className="viewAll__top">
                <div className="viewAll__topButtonContainer">
                    <a 
                        className="viewAll__topButton"
                        href="/actors/new"
                        style={{ backgroundColor: "green" }}
                    >
                        Create
                    </a>

                    
                </div>
            </div>

            <div className="viewAll__table">

                <table style={{width: "100%", paddingTop: "1%"}}>

                    <tr>

                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Movies</th>
                        <th>Actions</th>

                    </tr>

                    {
                        actors.map((actor, k) => (
                            <tr>

                            <td className="center">{actor.fname ? actor.fname:""}</td>
                            <td className="center">{actor.lname}</td>
                            <td className="center">
                                {
                                    actor.movies.map((movie, key) => (
                                        movie.name
                                    ))
                                }
                            </td>
                            <td className="center">_ACTIONS_</td>


                            </tr>
                        ))
                    }

                </table>



            </div>


        </div>
    )
}

export default ViewAllActors
