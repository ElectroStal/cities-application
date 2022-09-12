import axios from "axios";
import './citiesData.css';
import './cityForEdit.css';


const CityForEdit = (uuid, currentPage, name, photo) => {

    axios.get(`http://localhost:8083/api/city/${this.props.pageState.currentPage}`, {withCredentials: true})
        .then(response => {props.citiesResponse.citiesList = response.data.citiesList})

    return (
        <table style="border:3px solid coral">
            <tr id="city-table">
                <td hidden>${uuid}</td>
                <td className="number_in_list">${id}</td>
                <td className="cur_name">${name}</td>
                <td className="cur_photo"><img id="curPhoto" src={photo} alt="Wrong reference to photo"
                                               height="300" width="500"/></td>
                <button className="button_edit" type="button" onClick="findCityForEdit('${name}')"
                        id="edit-${id}">Edit</button>
            </tr>
            <label htmlFor="newName">
                &nbsp;&nbsp;New name of city:&nbsp;&nbsp;<input name="newName" type="text" maxLength="512" id="newName"/>
            </label>
            <label htmlFor="newPhoto">
                &nbsp;&nbsp;New photo of city:&nbsp;&nbsp;&nbsp;<input name="newPhoto" type="text" maxLength="512"
                                                                       id="newPhoto"/>
            </label>
            <button type="button" className="submit_button" onClick="goUpdateCity()">Submit</button>
        </table>
    )
}