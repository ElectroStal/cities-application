import axios from "axios";
import CitiesData from "../components/citiesData";
import {root} from "../render";
import React from 'react';
import CityForEdit from "../components/editCity";

let state = {
    pageState: {
        currentPage: -1,
        pageSize: 10,
        hasNextPage: true,
        hasPreviousPage: false,
        inputTextValue: null,
        itemsList: [],
        editing: false,
        needLoad: true
    },
    citiesRequest: {
        uuid: null,
        name: null,
        photo: null
    },
    citiesResponse: {
        hasNextPage: true,
        hasPreviousPage: false,
        name: null,
        photo: null,
        uuid: null,
        citiesList: []
    },

    showPreviousCitiesList() {
        state.pageState.currentPage -= 1;
        state.pageState.editing = false;
        state.showCitiesPage();
    },

    showNextCitiesList() {
        state.pageState.currentPage += 1;
        state.pageState.editing = false;
        state.showCitiesPage();
    },

    showCitiesPage() {
        axios.get(`http://localhost:8083/api/city/${state.pageState.currentPage}`, {withCredentials: true})
            .then(response => {
                let respData = String(response.data);
                if (respData.match("Please sign in")) {
                    window.location.replace('http://localhost:8083/');
                }
                let i = 0;
                console.log(response)

                state.pageState.itemsList = response.data.citiesList.map(x => {
                    let id = (state.pageState.currentPage * state.pageState.pageSize + i++) + 1;
                    let data = {
                        uuid: x.uuid,
                        id: id,
                        name: x.name,
                        photo: x.photo
                    };
                    console.log(data);
                    return <CitiesData data={data}/>;
                })
                state.pageState.hasNextPage = response.data.hasNextPage;
                state.pageState.hasPreviousPage = response.data.hasPreviousPage;
                console.log(state.pageState.itemsList)
                root();
            });
    },

    keyup(e) {
        let inputTextValue = e.target.value;
        if (e.keyCode === 13) {
            state.findCityByName(inputTextValue, false);
        }
    },

    findCityByName(cityName, editing) {
        const res = axios.get(`http://localhost:8083/api/city?city=${cityName}`, {withCredentials: true})
            .then(response => {
                let respData = String(response.data);
                if (respData.match("Please sign in")) {
                    window.location.replace('http://localhost:8083/');
                }
                console.log(response)
                let data = {
                    uuid: response.data.uuid,
                    id: 1,
                    name: response.data.name,
                    photo: response.data.photo
                };

                state.pageState.itemsList = editing ? <CityForEdit data={data}/> : <CitiesData data={data}/>;
                state.pageState.hasNextPage = true;
                state.pageState.hasPreviousPage = false;
                state.pageState.editing = editing;
                state.pageState.currentPage = -1;
                state.citiesRequest.uuid = data.uuid;
                state.citiesRequest.name = data.name;
                state.citiesRequest.photo = data.photo;
                console.log(state.pageState.itemsList);
                root();
            }).catch(response => {
                alert(`City ${cityName} not found!`);
            });
    },

    updateCity(newNameReference, newPhotoReference) {
        let name = newNameReference.current.value == null || newNameReference.current.value.length === 0
            ? state.citiesRequest.name : newNameReference.current.value;
        let photo = newPhotoReference.current.value == null || newPhotoReference.current.value.length === 0
            ? state.citiesRequest.photo : newPhotoReference.current.value;
        console.log(state.citiesRequest.uuid, name, photo);
        axios.put("http://localhost:8083/api/city", {
            uuid: state.citiesRequest.uuid,
            name: name,
            photo: photo
        }, {withCredentials: true})
            .then(response => {
                let respData = String(response.data);
                if (respData.match("Please sign in")) {
                    window.location.replace('http://localhost:8083/');
                }
                state.findCityByName(response.data.name);
            }).catch(response => {
            alert("You don't have access rights to edit cities list");
        });
    },

    logout() {
        window.location.replace('http://localhost:8083/logout');
    }
}
export default state;