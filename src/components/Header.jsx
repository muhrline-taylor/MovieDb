import React from 'react';
import "../static/css/Header.css";

function Header() {
    return (
        <div className="header">
            <div className="header__top">
                <h1>MovieDb API</h1>
            </div>
            <div className="header__bottom">
                <div className="header__bottomButtonContainer">
                    <a 
                        className="header__bottomButton"
                        href="/movies"
                    >
                        Movies
                    </a>
                    <a 
                        className="header__bottomButton"
                        href="/directors"
                    >
                        Directors
                    </a>
                    <a 
                        className="header__bottomButton"
                        href="/actors"
                    >
                        Actors
                    </a>
                    
                </div>
            </div>
        </div>
    )
}

export default Header
