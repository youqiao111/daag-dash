import React from 'react';
import ReactDOM from 'react-dom';
import bootstrap from "bootstrap/dist/css/bootstrap.css"
import { Grid, Row, Col, PageHeader, ButtonToolbar, Button, Panel, FormGroup, ControlLabel, FormControl, HelpBlock, Glyphicon, Checkbox } from 'react-bootstrap';
import LoginContainer from '../../LoginContainer'
import P_Navbar from '../../partial/nav-bar';
import API from '../../setting';
import { observer, inject } from "mobx-react";
import { observable } from "mobx";
import * as _ from 'lodash';

@inject("user")
@observer
class UsetInfo extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleSubmit = async (event) => {
        event.preventDefault();
        const act = event.target.action;
        const method = event.target.method;
        const name = $("#name").val();
        const email = $("#email").val();
        const password = $("#password").val();
        const password1 = $("#password1").val();
        const password2 = $("#password2").val();
        if (name.length < 1 || email.length < 1) {
            alert("name or email must not blank");
            return;
        }
        if (password.length < 1) {
            alert("please input password");
            return;
        }
        const req = {
            url: act,
            method: method,
            dataType: "json",
            data: {
                name: name,
                email: email,
                plainpassword: password,
                newpassword: (password1.length > 0 && password1 === password2) ? password1 : '',
                renewpassword: password2,
            },
        };
        try {
            const result = await $.ajax(req);
            if (result && result.status == 0) {
                alert("update successful！");
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
        if (!this.props.user) return null;
        const role = _.map(this.props.user.roleList, "name");
        return (
            <Grid>
                <PageHeader>
                    {this.props.user.username} <small>Information</small>
                </PageHeader>
                <Row>
                    <Col md={12}>
                        <form action={API.user.update} method='post' onSubmit={this.handleSubmit}>
                            <FormGroup controlId="name">
                                <ControlLabel>Name:</ControlLabel>
                                <FormControl
                                    id="name"
                                    type="text"
                                    placeholder="Enter Name"
                                    name="name"
                                    defaultValue={this.props.user.name}
                                />
                            </FormGroup>
                            <FormGroup controlId="email">
                                <ControlLabel>Email:</ControlLabel>
                                <FormControl
                                    id="email"
                                    type="email"
                                    placeholder="Enter Email"
                                    name="email"
                                    defaultValue={this.props.user.email}
                                />
                            </FormGroup>
                            <FormGroup controlId="password">
                                <ControlLabel>Password:</ControlLabel>
                                <FormControl
                                    id="password"
                                    type="password"
                                    placeholder="Enter Password"
                                    name="password"
                                />
                                <HelpBlock>Input CURRENT password to confirm change</HelpBlock>
                            </FormGroup>
                            <FormGroup controlId="password1">
                                <ControlLabel>New Password:</ControlLabel>
                                <FormControl
                                    id="password1"
                                    type="password"
                                    placeholder="Enter Password"
                                    name="password1"
                                />
                                <HelpBlock>Leavel Blank if you do not change password</HelpBlock>
                            </FormGroup>
                            <FormGroup controlId="password2">
                                <ControlLabel>Confirm New Password:</ControlLabel>
                                <FormControl
                                    id="password2"
                                    type="password"
                                    placeholder="Confirm Password"
                                    name="password2"
                                />
                            </FormGroup>
                            <FormGroup controlId="role">
                                <ControlLabel>Role</ControlLabel>
                                <FormControl.Static>{role.join(",")}</FormControl.Static>
                            </FormGroup>
                            <ButtonToolbar>
                                <Button bsStyle="success" type="submit">Save</Button>
                                <Button bsStyle="danger" href="/user/">Back</Button>
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
        <UsetInfo />
    </LoginContainer>

    , $('#main')[0]);