import React, { useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';

function CreateMovie() {
    const history = useHistory();
    const [movieForm, setMovieForm] = useState({
        name: "",
        year: 2000,
        poster: ""
    });
    const [directors, setDirectors] = useState([]);
    const [directorId, setDirectorId] = useState();


    const changeHandler = e => {
        if(e.target.name === "year"){
            // set movieForm.year
            setMovieForm({
                ...movieForm,
                [e.target.name]: parseInt(e.target.value)
            });
        } else {
            // set all other movieForm fields
            setMovieForm({
                ...movieForm,
                [e.target.name]: e.target.value
            });

        }
    }

    const directorHandler = e => {
        setDirectorId(e.target.value)
    }

    const submitHandler = e => {
        e.preventDefault();
        MainService.createMovie(movieForm, directorId)
            .then(res => {
                console.log(res);
                history.push("/movies");
            })
            .catch(err => {
                console.log(err);
            })
    }

    useEffect(() => {
        MainService.getAllDirectors()
            .then(res => {
                console.log(res.data.content);
                setDirectors(res.data.content);
            })
    },[])




    return (
        <div>
            <form>

                <p>Movie Title: </p>
                <input 
                    name="name"
                    placeholder="Movie Title..."
                    value={movieForm.name}
                    onChange={changeHandler}
                />

                <p>Year: </p>
                <input 
                    type="number"
                    name="year"
                    min="1895"
                    max="2030"
                    step="1"
                    value={movieForm.year}
                    onChange={changeHandler}
                />

                <p>Link to Poster (image-only links please)</p>
                <input 
                    name="poster"
                    placeholder="Poster Url..."
                    value={movieForm.poster}
                    onChange={changeHandler}
                />

                <p>Director: </p>
                <select
                    name="director_id"
                    onChange={directorHandler}
                >
                    <option>
                        SELECT
                    </option>
                    {
                        directors ?
                        directors.map((director, k) => (
                            <option 
                                value={director.id}
                            >
                                {director.lname}
                            </option>
                        )):""
                    }
                </select>

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

export default CreateMovie
