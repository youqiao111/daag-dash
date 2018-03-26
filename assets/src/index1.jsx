import React from 'react';
import ReactDOM from 'react-dom';
import "./index1.less";
import { Alert } from 'react-bootstrap';
//import bootstrap from "bootstrap/dist/css/bootstrap.css"
ReactDOM.render(
  <Alert bsStyle="warning">
    hello world1
   </Alert>
  , $('#main')[0]);
