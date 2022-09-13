import CitiesData from "./citiesData";
import "./editCity.css";
import React from "react";
import state from "../model/state";

const CityForEdit = (data) => {
    let newNameReference = React.createRef();
    let newPhotoReference = React.createRef();

    let updateCity = () => state.updateCity(newNameReference, newPhotoReference);

    return (
        <div>
            <CitiesData data={data.data}/>
            <label form="newName" className="label_editing">
                New name of city:<input name="newName" type="text" maxLength="512" ref={newNameReference}
                                        id="newName" className="input_editing"/><br/>
            </label>
            <label form="newPhoto" className="label_editing">
                New photo of city:<input name="newPhoto" type="text" maxLength="512" ref={newPhotoReference}
                                         id="newPhoto" className="input_editing"/><br/>
            </label>
            <div className="div_button">
                <button type="button" className="submit_button"
                        onClick={updateCity}>Submit</button>
            </div>
        </div>);
}

export default CityForEdit;