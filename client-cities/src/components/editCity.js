import axios from "axios";
import citiesHtml from "./citiesData";
import state from "../model/state";


function findCityForEdit(name) {
    if(document.getElementById("table_of_cities") != null) {
        document.getElementById("table_of_cities").textContent = '';
    }
    const res = axios.get(`http://localhost:8083/api/city?city=${name}`, {withCredentials: true})
        .then(t => {citiesHtml(t["uuid"],1, t["name"], t["photo"]);
        })
    const citiesList = document.getElementById('table-of-cities');
    citiesList.insertAdjacentHTML('beforeend', `
        <label for="newName">
            &nbsp;&nbsp;New name of city:&nbsp;&nbsp;<input name="newName" type="text" maxlength="512" id="newName"/>
        </label>
        <label for="newPhoto">
            &nbsp;&nbsp;New photo of city:&nbsp;&nbsp;&nbsp;<input name="newPhoto" type="text" maxlength="512" id="newPhoto"/>
        </label>
        <button type="button" id="submitButton" style="background-color:red;color:bisque" onclick="goUpdateCity()">Submit</button>`);

    console.log(res);
}

function goUpdateCity() {
    updateCity(
        document.getElementById("u_uid").textContent,
        document.getElementById("newName").value == null || document.getElementById("newName").value.length === 0
            ? document.getElementById("curName").textContent : document.getElementById("newName").value,
        document.getElementById("newPhoto").value == null || document.getElementById("newPhoto").value.length === 0
            ? document.getElementById("curPhoto").getAttribute("src") : document.getElementById("newPhoto").value,
    )
}

function updateCity(uuid, name, photo) {
    if(document.getElementById("table_of_cities") != null) {
        document.getElementById("table_of_cities").textContent = '';
    }
    const res = (axios.put('http://localhost:8083/api/city', {
        uuid: uuid,
        name: name,
        photo: photo
    }))
    let status = res.data.status;
    if (status !== 200) {
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
            citiesHtml(x.data.uuid, ((state.pageState.currentPage + 1) * state.pageState.pageSize + i++), x.data.name, x.data.photo);
        }))
        alert("You don't have access rights to edit cities list!");
        return;
    }
    const result = res
        .then(t => {citiesHtml(t.data.uuid,1, t.data.name, t.data.photo);
        })
    console.log(res);
}

export default findCityForEdit;