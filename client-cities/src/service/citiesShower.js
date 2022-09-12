import './citiesShower.css';
import React from "react";
import axios from "axios";
import {root} from "../render";
import state from "../model/state";
import citiesHtml from "../components/citiesData";
import findCity from "../components/cityFind";

const ShowCities = (props) => {
    window.onkeyup = keyup;
    let inputTextValue;

    function keyup(e) {
        inputTextValue = e.target.value;
        if (e.keyCode === 13) {
            findCity(inputTextValue).then(() => {
                state.pageState.currentPage = 0;
                state.pageState.hasPreviousPage = false;
                document.getElementById('previous').disabled = true;
            });
        }
    }

    let showNextCitiesList = () => {
        state.pageState.currentPage += 1;
        axios.get(`http://localhost:8083/api/city/${state.pageState.currentPage}`, {withCredentials: true})
            .then(response => {
                let respData = String(response.data);
                if (respData.match("Please sign in")) {
                    window.location.replace('http://localhost:8083/');
                }
                state.citiesResponse.citiesList = response.data.citiesList;
                state.pageState.hasNextPage = response.data.hasNextPage;
                state.pageState.hasPreviousPage = response.data.hasPreviousPage;
            });
        let i = 0;
        if(document.getElementById("table_of_cities") != null) {
            document.getElementById("table_of_cities").textContent = '';
        }
        state.citiesResponse.citiesList.forEach((x => {
            citiesHtml(x.uuid, ((state.pageState.currentPage + 1) * state.pageState.pageSize + i++), x.name, x.photo);
        }))
        root(props);
    }

    let showPreviousCitiesList = () => {
        state.pageState.currentPage -= 1;
        axios.get(`http://localhost:8083/api/city/${state.pageState.currentPage}`, {withCredentials: true})
            .then(response => {
                let respData = String(response.data);
                if (respData.match("Please sign in")) {
                    window.location.replace('http://localhost:8083/');
                }
                state.citiesResponse.citiesList = response.data.citiesList;
                state.pageState.hasNextPage = response.data.hasNextPage;
                state.pageState.hasPreviousPage = response.data.hasPreviousPage;
            });
        let i = 0;
        if(document.getElementById("table_of_cities") != null) {
            document.getElementById("table_of_cities").textContent = '';
        }
        state.citiesResponse.citiesList.forEach((x => {
            citiesHtml(x.uuid, ((state.pageState.currentPage + 1) * state.pageState.pageSize + i++), x.name, x.photo);
        }))
        root(props);
    }

    let logout = () => {
        window.location.replace('http://localhost:8083/logout');
    }

    return (
        <div className="container">
            <h1 className="h_name">Cities shower</h1>
            <div id="cities">
                <div className="div_find">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Find city&nbsp;&nbsp;&nbsp;
                    <label form="searchTxt" title="FindCity">
                        <input type="text" maxLength="512"></input>
                    </label>
                    <button type="button" className="btn_authorise" onClick={logout}>Logout</button>
                </div>
                <br/>
                <div className="div_table_of_cities">
                    <table className="table_of_cities" id="table_of_cities"></table>
                </div>
                <div className="div_buttons">
                    <button type="button" id="previous" className="btn_change_page" onClick={showPreviousCitiesList} disabled={!state.pageState.hasPreviousPage}>Previous</button>
                    <button type="button" id="next"  className="btn_change_page" onClick={showNextCitiesList} disabled={!state.pageState.hasNextPage}>Next</button>
                </div>
            </div>
        </div>
    )
}

export default ShowCities;