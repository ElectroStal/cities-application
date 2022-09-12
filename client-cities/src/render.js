import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

export let root = (props) => {
    ReactDOM.render(<App props={props}/>, document.getElementById('root'));
}