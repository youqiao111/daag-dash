import React from 'react';
import ReactDOM from 'react-dom';
import bootstrap from "bootstrap/dist/css/bootstrap.css"
import { Grid, Row, Col, PageHeader, ButtonToolbar, Button, Panel, FormGroup, HelpBlock, ControlLabel, FormControl, Glyphicon, Checkbox, Modal } from 'react-bootstrap';
import LoginContainer from '../../LoginContainer'
import P_Navbar from '../../partial/nav-bar';
import { observer, inject } from "mobx-react";
import { observable, action, useStrict, runInAction } from "mobx";
import API from '../../setting';
import * as _ from 'lodash';

//useStrict(true);

@inject("user")
@observer
class AddUser extends React.Component {
    form = observable({
        name: "",
        username: "",
        email: "",
        password1: "",
        password2: "",
        role: 3,
        isactive: true,
    })
    modal = observable({
        msg: "",
        url: "",
    })
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.validateForm = this.validateForm.bind(this);
    }
    validateForm = () => {
        return (
            this.form.name.length > 0 &&
            this.form.username.length > 0 &&
            this.form.email.length > 0 &&
            this.form.password1.length > 0 &&
            this.form.password1 === this.form.password2
        );
    }
    handleClose = (event) => {
        if (this.modal.url.length > 0) {
            window.location.href = this.modal.url;
            return;
        }
        this.modal.url = "";
        this.modal.msg = "";

    }
    handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        this.form[name] = value;

    }
    handleSubmit = async (event) => {
        event.preventDefault();
        const act = event.target.action;
        const method = event.target.method;
        //
        if (!this.validateForm()) {
            this.modal.msg = `please fill all form`;
            this.modal.url = "";
            return;
        }

        try {
            const req = {
                url: act,
                method: method,
                dataType: "json",
                data: {
                    name: this.form.name,
                    username: this.form.username,
                    email: this.form.email,
                    available: this.form.isactive,
                    plainpassword: this.form.password1,
                    replainpassword:this.form.password2,
                    roles:this.form.role,
                },
            };
            const result = await $.ajax(req);
            if (result) {
                if (result.status == 0) {
                    this.modal.msg = "User Added Successful";
                    this.modal.url = "/user/";
                }
                else {
                    console.log(result.status);
                    this.modal.msg = `Error Code: ${result.status} ${result.msg}`;
                    this.modal.url = "";


                }
            }
            else {
                this.modal.msg = "Can not access Server!";
                this.modal.url = "";
            }
        }
        catch (exp) {
            this.modal.msg = "Fate Error!";
            this.modal.url = "";
            console.log(exp);
        }
    }
    render() {
        console.log(this.modal);
        let mbox = null;
        if (this.modal.msg.length > 0) {
            mbox = (
                <div className="static-modal">
                    <Modal.Dialog>
                        <Modal.Body>{this.modal.msg}</Modal.Body>
                        <Modal.Footer>
                            <Button onClick={this.handleClose} >Close</Button>
                        </Modal.Footer>
                    </Modal.Dialog>
                </div>
            );
        }
        return (
            <Grid>
                {mbox}
                <PageHeader>
                    Add User
                </PageHeader>
                <Row>
                    <Col md={12}>
                        <form action={API.user.add} method='post' onSubmit={this.handleSubmit}>
                            <FormGroup controlId="name">
                                <ControlLabel>Name:</ControlLabel>
                                <FormControl
                                    id="name"
                                    type="text"
                                    placeholder="Enter Name"
                                    name="name"
                                    value={this.form.name}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <FormGroup controlId="username">
                                <ControlLabel>Username:</ControlLabel>
                                <FormControl
                                    id="username"
                                    type="text"
                                    placeholder="Enter Username"
                                    name="username"
                                    value={this.form.username}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <FormGroup controlId="email">
                                <ControlLabel>Email:</ControlLabel>
                                <FormControl
                                    id="email"
                                    type="email"
                                    placeholder="Enter Email"
                                    name="email"
                                    value={this.form.email}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <FormGroup controlId="password1">
                                <ControlLabel>Password:</ControlLabel>
                                <FormControl
                                    id="password1"
                                    type="password"
                                    placeholder="Enter Password"
                                    name="password1"
                                    value={this.form.password1}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <FormGroup controlId="password2">
                                <ControlLabel>Confirm Password:</ControlLabel>
                                <FormControl
                                    id="password2"
                                    type="password"
                                    placeholder="Confirm Password"
                                    name="password2"
                                    value={this.form.password2}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <FormGroup controlId="role">
                                <ControlLabel>Role</ControlLabel>
                                <FormControl
                                    componentClass="select"
                                    name="role"
                                    id="role"
                                    placeholder="select"
                                    value={this.form.role}
                                    onChange={this.handleChange}
                                >
                                    <option value="3">User</option>
                                    <option value="2">Analyst</option>
                                    <option value="1">Admin</option>
                                </FormControl>
                            </FormGroup>
                            <FormGroup controlId="isactive">
                                <ControlLabel>Is Active</ControlLabel>
                                <Checkbox
                                    name="isactive"
                                    id="isactive"
                                    defaultChecked={this.form.isactive}
                                    onChange={this.handleChange}
                                >
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
        <AddUser />
    </LoginContainer>

    , $('#main')[0]);