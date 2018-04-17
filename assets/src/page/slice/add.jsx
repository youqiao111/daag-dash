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
class AddSlice extends React.Component {
    
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleSubmit = async (event) => {
        event.preventDefault();
        const act = event.target.action;
        const method = event.target.method;
        const name = $("#name").val();
        const desc = $("#desc").val();
        if (name.length < 1 || desc.length < 1) {
            alert("name and description must not blank");
            return;
        }
        const req = {
            url: act,
            method: method,
            dataType: "json",
            data: {
                name: name,
                description: desc,
            },
        };
        try {
            const result = await $.ajax(req);
            if (result && result.status == 0) {
                alert("add successful！");
                window.location.href="/slice/";
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
                    Add Slice
                </PageHeader>
                <Row>
                    <Col md={12}>
                        <form action={API.slice.add} method='post' onSubmit={this.handleSubmit}>
                            <FormGroup controlId="name">
                                <ControlLabel>Name:</ControlLabel>
                                <FormControl
                                    id="name"
                                    type="text"
                                    placeholder="Enter Name"
                                    name="name"
                                />
                            </FormGroup>
                            <FormGroup controlId="desc">
                                <ControlLabel>Description:</ControlLabel>
                                <FormControl componentClass="textarea"
                                    id="desc"
                                    name="desc"
                                />
                            </FormGroup>
                            <ButtonToolbar>
                                <Button bsStyle="success" type="submit">Save</Button>
                                <Button bsStyle="danger" href="/slice/">Back</Button>
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
        <AddSlice />
    </LoginContainer>

    , $('#main')[0]);