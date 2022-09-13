import './citiesData.css';
import unknownCity from './../images/unknown_city.png';
import state from "../model/state";
import React from "react";

const CitiesData = (data) => {
    let curNameReference = React.createRef();

    let showEditingCity = () => state.findCityByName(curNameReference.current.textContent, true);

    return (
        <table>
            <tbody id="city-table">
                <td hidden>{data.uuid}</td>
                <td className="number_in_list">{data.data.id}</td>
                <td ref={curNameReference} className="cur_name">{data.data.name}</td>
                <td><img id="curPhoto" className="cur_photo" src={data.data.photo}
                         onError={(e) => {
                             e.target.src = unknownCity
                         }} alt={"Wrong reference to photo"} height="300" width="500"/></td>
            </tbody>
            <button type="button" onClick={showEditingCity} hidden={state.pageState.editing}>Edit</button>
        </table>);
}

export default CitiesData;