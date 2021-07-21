import axios from 'axios';

const MOVIE_API_BASE_URL = "http://localhost:8080/api/v1/movies";
const DIRECTOR_API_BASE_URL = "http://localhost:8080/api/v1/directors";
const ACTOR_API_BASE_URL = "http://localhost:8080/api/v1/actors";


class MainService {

// API CALLS ======================= //

    // MOVIES ------------------- //

        // get all
        getAllMovies(){
            return axios.get(`${MOVIE_API_BASE_URL}/`);
        }

        // get by id
        getMovieById(id){
            return axios.get(`${MOVIE_API_BASE_URL}/${id}`);
        }

        // create
        createMovie(reqBody, director_id){
            return axios.post(`${MOVIE_API_BASE_URL}/new/${director_id}`, reqBody)
        }



    // DIRECTORS ----------------- //
        
        // get all
        getAllDirectors(){
            return axios.get(`${DIRECTOR_API_BASE_URL}/`);
        }

        // get by id
        getDirectorById(id){
            return axios.get(`${DIRECTOR_API_BASE_URL}/${id}`);
        }

        // create
        createDirector(reqBody){
            return axios.post(`${DIRECTOR_API_BASE_URL}/new`, reqBody)
        }



    // ACTORS ---------------------- //

        // get all
        getAllActors(){
            return axios.get(`${ACTOR_API_BASE_URL}/`);
        }

        // get by id
        getActorById(id){
            return axios.get(`${ACTOR_API_BASE_URL}/${id}`);
        }

        // create
        createActor(reqBody){
            return axios.post(`${ACTOR_API_BASE_URL}/new`, reqBody);
        }







}


export default new MainService();