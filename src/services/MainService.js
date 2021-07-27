import axios from 'axios';

const MOVIE_API_BASE_URL = "http://localhost:8080/api/v1/movies";
const DIRECTOR_API_BASE_URL = "http://localhost:8080/api/v1/directors";
const ACTOR_API_BASE_URL = "http://localhost:8080/api/v1/actors";
const USER_API_BASE_URL = "http://localhost:8080/api/v1/users";
const PRODUCT_API_BASE_URL = "http://localhost:8080/api/v1/store/products";


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


    // USERS -------------------------------- //

        // get all
        getAllUsers(){
            return axios.get(`${USER_API_BASE_URL}/`);
        }

        // get by id
        getUserById(id){
            return axios.get(`${USER_API_BASE_URL}/${id}`);
        }

        // register
        createUser(reqBody){
            return axios.post(`${USER_API_BASE_URL}/new`, reqBody)
        }

        // login
        loginUser(reqBody){
            console.log(reqBody)
            return axios.get(`${USER_API_BASE_URL}/login`, {
                params: {
                    email: reqBody.email,
                    password: reqBody.password
                }
            })
        }


    // PRODUCTS ---------------------------------------- //

        // get all
        getAllProducts(){
            return axios.get(`${PRODUCT_API_BASE_URL}/`);
        }

        // get by id
        getProductById(id){
            return axios.get(`${PRODUCT_API_BASE_URL}/${id}`)
        }

        // create
        createProduct(reqBody){
            return axios.post(`${PRODUCT_API_BASE_URL}/new`, reqBody);
        }

        // buy
        buyProduct(product_id, user_id){
            const reqBody = {
                product_id: product_id,
                user_id: user_id
            }
            return axios.put(`${PRODUCT_API_BASE_URL}/buy`, reqBody);
        }







}


export default new MainService();