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

@inject("user")
@observer
class AddSource extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleSubmit = async (event) => {
        event.preventDefault();
        const act = event.target.action;
        const method = event.target.method;
        const name = $("#name").val();
        const connstr = $("#connstr").val();
        const type = $("#type").val();
        if (name.length < 1 || connstr.length < 1) {
            alert("name or connectionstring must not blank");
            return;
        }
        const req = {
            url: act,
            method: method,
            dataType: "json",
            data: {
                name: name,
                url: connstr,
                type: type,
                other:"{}",
            },
        };
        try {
            const result = await $.ajax(req);
            if (result && result.status == 0) {
                alert("add successful！");
                window.location.href="/source/";
            }
            else {
                alert(result.msg ? result.msg : " Server Error");
            }
        }
        catch (exp) {
            alert("FATE ERROR!");
            console.log(exp);

        }


    }
    render() {
        return (
            <Grid>
                <PageHeader>
                    Add Source
                </PageHeader>
                <Row>
                    <Col md={12}>
                        <form action={API.datasource.add} method='post' onSubmit={this.handleSubmit}>
                            <FormGroup controlId="name">
                                <ControlLabel>Name:</ControlLabel>
                                <FormControl
                                    id="name"
                                    type="text"
                                    placeholder="Enter Name"
                                    name="name"
                                    defaultValue=""
                                />
                            </FormGroup>
                            <FormGroup controlId="connstr">
                                <ControlLabel>Connection String:</ControlLabel>
                                <FormControl
                                    id="connstr"
                                    type="text"
                                    placeholder="Enter Connection String"
                                    name="connstr"
                                    defaultValue=""
                                />
                                <Button bsStyle="info" >Test</Button>
                            </FormGroup>
                            <FormGroup controlId="type">
                                <ControlLabel>Type:</ControlLabel>
                                <FormControl componentClass="select"
                                    name="type"
                                    id="type"
                                    placeholder="select">
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
        <AddSource />
    </LoginContainer>

    , $('#main')[0]);