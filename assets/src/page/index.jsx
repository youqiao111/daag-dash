import React from 'react';
import ReactDOM from 'react-dom';
import "./index.less";
import { Button } from 'react-bootstrap';
import API from '../setting';
import bootstrap from "bootstrap/dist/css/bootstrap.css"

ReactDOM.render(
  <Button href="/login.html">Login</Button>
  , $('#main')[0]);
