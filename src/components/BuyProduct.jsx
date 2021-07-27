import React, { useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom'
import MainService from '../services/MainService';

function BuyProduct({ ...props }) {
    const [user, setUser] = useState({});
    const [product, setProduct] = useState({});
    const history = useHistory();

    useEffect(() => {
        if(localStorage.getItem("loggedInUser") === null) {
            history.push("/users/login")
        }
        MainService.getUserById(localStorage.getItem("loggedInUser"))
            .then(res => {
                setUser(res.data)
            })
            .catch(err => {
                console.log(err);
                history.push("/users/login")
            })
    },[])

    useEffect(() => {
        MainService.getProductById(props.match.params.id)
            .then(res => {
                setProduct(res.data)
            })
            .catch(err => console.log(err));
    },[])



    return (
        <div>
            <h1>Buy {product.name} for {product.price}?</h1>
            <input 
                type="submit"
                value="Buy"
                onClick={() => {
                    MainService.buyProduct(product.id, user.id)
                        .then(res => {
                            history.push("/")
                        })
                        .catch(err => console.log(err))
                }}
            />
            <input 
                type="submit"
                value="Cancel"
            />
        </div>
    )
}

export default BuyProduct
