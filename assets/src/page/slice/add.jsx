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

    render() {
        return (
            <Grid>
                <PageHeader>
                    Add Slice
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