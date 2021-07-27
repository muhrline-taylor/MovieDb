import React, { useEffect, useState } from 'react'
import MainService from '../services/MainService';

function CreateProduct() {
    const [users, setUsers] = useState([]);
    const [productForm, setProductForm] = useState({
        name: "",
        price: 0.01,
        seller_id: -1
    });

    const changeHandler = e => {
        if(e.target.name === "name"){
            // set productForm.name
            setProductForm({
                ...productForm,
                [e.target.name]: e.target.value
            })

        } else if(e.target.name === "price"){
            // set productForm.price
            setProductForm({
                ...productForm,
                [e.target.name]: parseFloat(e.target.value)
            });

        } else {
            // set productForm.seller_id
            setProductForm({
                ...productForm,
                [e.target.name]: parseInt(e.target.value)
            });

        }
    }

    const submitHandler = e => {
        e.preventDefault();
        console.log(productForm);
        MainService.createProduct(productForm)
            .then(res => {
                console.log(res)
            })
            .catch(err => console.log(err));
    }

    useEffect(() => {
        MainService.getAllUsers()
            .then(res => {
                setUsers(res.data)
            })
            .catch(err => console.log(err))
    },[])


    return (
        <div>
            
            <form>

                <input 
                    name="name"
                    value={productForm.name}
                    onChange={changeHandler}
                />

                <input 
                    name="price"
                    type="number"
                    value={productForm.price}
                    onChange={changeHandler}
                    min="0.01"
                    step="0.01"
                />

                <select name="seller_id" onChange={changeHandler}>
                    <option value="none" selected disabled hidden>SELECT_USER</option>
                    {
                        users ?
                        users.map((user, k) => (
                            <option value={user.id}>{user.email}</option>
                        )):""
                    }
                </select>

                <input 
                    type="submit"
                    value="Create"
                    onClick={submitHandler}
                />
                




            </form>



        </div>
    )
}

export default CreateProduct
