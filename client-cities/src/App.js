import './App.css';
import './service/citiesShower'
import React from 'react';
import ShowCities from "./service/citiesShower";

const App = (props) => {
    return (
        <div>
            <ShowCities props={props}/>
        </div>
    )
}

export default App;
