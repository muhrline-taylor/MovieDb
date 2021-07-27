import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import MainService from '../services/MainService';
import "../static/css/ViewAll.css";

function ViewAllProducts() {
    const [products, setProducts] = useState([]);
    const history = useHistory();


    useEffect(() => {
        MainService.getAllProducts()
            .then(res => {
                setProducts(res.data)
            })
            .catch(err => console.log(err));
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
                        <th>Price</th>
                        <th>Seller</th>
                        <th>Buyer</th>
                        <th>Actions</th>

                    </tr>

                    {
                        products ?
                        products.map((product, k) => (
                            <tr>
                                <td className="center">{product.name}</td>
                                <td className="center">{product.price}</td>
                                <td className="center">{product.seller.username}</td>
                                <td className="center">{
                                    product.buyer ?
                                    "Sold"
                                    : "None"
                                }</td>
                                <td className="center">
                                    {
                                        product.buyer ?
                                        "":<form>
                                            <input type="submit" value="Buy" onClick={() => 
                                                localStorage.getItem("loggedInUser") ?
                                                history.push(`/store/products/${product.id}/buy`)
                                                :history.push("/users/login")
                                            }/>
                                        </form>
                                        
                                    }
                                </td>
                            </tr>
                        )):""



                    }

                </table>



            </div>


        </div>
    )
}

export default ViewAllProducts
