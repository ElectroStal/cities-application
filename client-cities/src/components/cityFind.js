import citiesHtml from "./citiesData";
import axios from "axios";

function findCity(name) {
    if(document.getElementById("table_of_cities") != null) {
        document.getElementById("table_of_cities").textContent = '';
    }
    const res = axios.get(`http://localhost:8083/api/city?city=${name}`, {withCredentials: true})
        .then(t => {citiesHtml(t.data.uuid,1, t.data.name, t.data.photo);
        })
}

export default findCity;