import React from 'react';
import ReactDOM from 'react-dom';
import bootstrap from "bootstrap/dist/css/bootstrap.css"
import { Grid, Row, Col, PageHeader, ButtonToolbar, Button, Panel, FormGroup, ControlLabel, FormControl,HelpBlock, Glyphicon, Checkbox } from 'react-bootstrap';
import LoginContainer from '../../LoginContainer'
import P_Navbar from '../../partial/nav-bar';
import API from '../../setting';
import { observer, inject } from "mobx-react";
import { observable } from "mobx";
import * as _ from 'lodash';
import utils from '../../utils';

@inject("user")
@observer
class EditUser extends React.Component {
    @observable userinfo = null;
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    async componentWillMount() {
        try {
            const req = {
                url: API.user.detail + `/${utils.getParameter("id")}/`,
                method: "GET",
                dataType: "json",
            };
            console.log(req);
            const result = await $.ajax(req);
            if (result && result.status == 0) {
                this.userinfo = result.data;
            } else {
                alert(result.msg ? result.msg : " Server Error");
            }
        }
        catch (exp) {
            console.log(exp);
            alert("Fate Error!!");
        }

    }
    handleSubmit = async (event) => {
        event.preventDefault();

        const act = event.target.action;
        const method = event.target.method;
        const username = $("#username").val();
        const name = $("#name").val();
        const email = $("#email").val();
        const password1 = $("#password1").val();
        const password2 = $("#password2").val();
        const role = $("#role").val();
        const isactive = $("#isactive")[0].checked;
        if (name.length < 1 || email.length < 1|| username.length < 1) {
            alert("name,username,email must not blank");
            return;
        }
        if (password1.length >0 && password1!=password2) {
            alert("please input same password");
            return;
        }
        const req = {
            url: act,
            method: method,
            dataType: "json",
            data: {
                id:this.userinfo.id,
                name: name,
                username:username,
                email: email,
                newpassword: password1,
                renewpassword:password1,
                roles: role,
                available: isactive,
            },
        };
        try {
            const result = await $.ajax(req);
            if (result && result.status == 0) {
                alert("update successful！");
                window.location.href="/user/";
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
        if (!this.userinfo) return null;
        return (
            <Grid>
                <PageHeader>
                    {this.userinfo.username} <small>Detail</small>
                </PageHeader>
                <Row>
                    <Col md={12}>
                        <form  action={API.user.edit} method='post' onSubmit={this.handleSubmit}>
                            <FormGroup controlId="name">
                                <ControlLabel>Name:</ControlLabel>
                                <FormControl
                                    id="name"
                                    type="text"
                                    placeholder="Enter Name"
                                    name="name"
                                    defaultValue={this.userinfo.name}
                                />
                            </FormGroup>
                            <FormGroup controlId="username">
                                <ControlLabel>Username:</ControlLabel>
                                <FormControl
                                    id="username"
                                    type="text"
                                    placeholder="Enter Username"
                                    name="username"
                                    defaultValue={this.userinfo.username}
                                />
                            </FormGroup>
                            <FormGroup controlId="email">
                                <ControlLabel>Email:</ControlLabel>
                                <FormControl
                                    id="email"
                                    type="email"
                                    placeholder="Enter Email"
                                    name="email"
                                    defaultValue={this.userinfo.email}
                                />
                            </FormGroup>
                            <FormGroup controlId="password1">
                                <ControlLabel>Password:</ControlLabel>
                                <FormControl
                                    id="password1"
                                    type="password"
                                    placeholder="Enter Password"
                                    name="password1"
                                />
                                <HelpBlock>Level blank if not change</HelpBlock>
                            </FormGroup>
                            <FormGroup controlId="password2">
                                <ControlLabel>Confirm Password:</ControlLabel>
                                <FormControl
                                    id="password2"
                                    type="password"
                                    placeholder="Confirm Password"
                                    name="password2"
                                />
                            </FormGroup>
                            <FormGroup controlId="role">
                                <ControlLabel>Role</ControlLabel>
                                <FormControl
                                    componentClass="select"
                                    name="role"
                                    id="role"
                                    placeholder="select"
                                    defaultValue={this.userinfo.roleList.length > 0 ? this.userinfo.roleList[0].id : 3}
                                >
                                    <option value="3">User</option>
                                    <option value="2">Analyst</option>
                                    <option value="1">Admin</option>
                                </FormControl>
                            </FormGroup>
                            <FormGroup controlId="role">
                                <ControlLabel>Is Active</ControlLabel>
                                <Checkbox name="isactive" id="isactive" defaultChecked={this.userinfo.available} >
                                    Active
                            </Checkbox>
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
        <EditUser />
    </LoginContainer>
    , $('#main')[0]);