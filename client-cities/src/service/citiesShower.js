import './citiesShower.css';
import React from "react";
import state from "../model/state";

const ShowCities = () => {
    window.onkeyup = state.keyup;

    if (state.pageState.needLoad) {
        state.showNextCitiesList();
        state.pageState.needLoad = false;
    }

    return (
        <div className="container">
            <h1 className="h_name">Cities shower</h1>
            <div id="cities">
                <div className="div_find">
                    Find city
                    <label form="searchTxt" title="FindCity" className="label_search">
                        <input type="text" maxLength="512" className="input_search"></input>
                    </label>
                    <button type="button" className="button_authorise" onClick={state.logout}>Logout</button>
                </div>
                <br/>
                <div className="div_table_of_cities">
                    <div className="table_of_cities" id="table_of_cities">{state.pageState.itemsList}</div>
                </div>
                <div className="div_buttons">
                    <button type="button" id="previous" className="btn_change_page" onClick={state.showPreviousCitiesList} disabled={!state.pageState.hasPreviousPage}>Previous</button>
                    <button type="button" id="next"  className="btn_change_page" onClick={state.showNextCitiesList} disabled={!state.pageState.hasNextPage}>Next</button>
                </div>
            </div>
        </div>
    )
}

export default ShowCities;