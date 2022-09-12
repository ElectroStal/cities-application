import './citiesData.css';
import findCityForEdit from "./editCity";

const citiesHtml = (uuid, id, name, photo) => {
        const citiesList = document.getElementById('table_of_cities');
        citiesList.insertAdjacentHTML('beforeend', `
            <table>
                <tr id="city-table">
                    <td hidden>${uuid}</td>
                    <td class="number_in_list">${id}</td>
                    <td id="curName" class="cur_name">${name}</td>
                    <td><img id="curPhoto" class="cur_photo" src="${photo}" alt="Wrong reference to photo" height="300" width="500"></td>
                    <button type="button" id="edit-${id} onclick="findCityForEdit('${name}')">Edit</button>
                </tr>
            </table>`);
}

export default citiesHtml;