import React from 'react';
import ReactDOM from 'react-dom';
import bootstrap from "bootstrap/dist/css/bootstrap.css"
import { Grid, Row, Col, PageHeader, ButtonToolbar, Button, Panel, FormGroup, ControlLabel, FormControl, Glyphicon, Checkbox } from 'react-bootstrap';
import LoginContainer from '../../LoginContainer'
import P_Navbar from '../../partial/nav-bar';
import { observer, inject } from "mobx-react";
import { observable, action, useStrict, runInAction } from "mobx";
import API from '../../setting';
import * as _ from 'lodash';



class EditSource extends React.Component {

    render() {
        return (
            <Grid>
                <PageHeader>
                    Srouce1 <small>Detail</small>
                </PageHeader>
                <Row>
                    <Col md={12}>
                        <form>
                            <FormGroup controlId="name">
                                <ControlLabel>Name:</ControlLabel>
                                <FormControl
                                    id="name"
                                    type="text"
                                    placeholder="Enter Name"
                                    name="name"
                                />
                            </FormGroup>
                            <FormGroup controlId="connstr">
                                <ControlLabel>Connection String:</ControlLabel>
                                <FormControl
                                    id="connstr"
                                    type="text"
                                    placeholder="Enter Connection String"
                                    name="connstr"
                                />
                                <Button bsStyle="info" >Test</Button>
                            </FormGroup>
                            <FormGroup controlId="type">
                                <ControlLabel>Type:</ControlLabel>
                                <FormControl componentClass="select" name="type" id="type" placeholder="select">
                                    <option value="mysql">Mysql/Mariadb</option>
                                    <option value="postgresql">Postgresql</option>
                                    <option value="mongodb">Mongodb</option>
                                </FormControl>
                            </FormGroup>

                            <ButtonToolbar>
                                <Button bsStyle="success" type="submit">Save</Button>
                                <Button bsStyle="danger" href="/source/">Back</Button>
                            </ButtonToolbar>
                        </form>
                    </Col>
                </Row>

            </Grid>
        );
    }
}
ReactDOM.render(
    <LoginContainer>
        <P_Navbar />
        <EditSource />
    </LoginContainer>
    , $('#main')[0]);