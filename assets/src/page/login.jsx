import React from 'react';
import ReactDOM from 'react-dom';
import bootstrap from "bootstrap/dist/css/bootstrap.css"
import { Grid, Row, Col, Panel, FormGroup, FormControl, ControlLabel, Button, Alert } from 'react-bootstrap';
import P_Navbar from '../partial/nav-bar';
import API from '../setting';
// col-md-offset-3 col-sm-8 col-sm-offset-2
class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            form: {
                username: "",
                password: "",

            },
            box: {
                msg: "",
                type: "",
            }

        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        this.setState(prevState => ({
            form: {
                ...prevState.form,
                [name]: value,
            },
            box: { type: "" }
        }));

    }
    showinfo = (type, msg) => {
        this.setState(prevState => ({
            box: {
                type: type,
                msg: msg
            }
        }));
    }
    handleSubmit = async (event) => {
        event.preventDefault();
        //const data = new FormData(event.target);
        const action = event.target.action;
        const method = event.target.method;

        try {
            const req = {
                url: action,
                method: method,
                dataType: "json",
                data: this.state.form,
            };
            //console.log(req);
            const result = await $.ajax(req);
            if (result) {
                if (result.status==0){
                    this.showinfo("info", "Success, Please wait for redirect.");
                    setTimeout(() => {
                        window.location.href='/dashboard/';
                    }, 500);
                }
                else{
                    this.setState(prevState=>({
                        form: {
                            ...prevState.form,
                            password: "",
                        },
                    }));
                    this.showinfo("danger", result.msg);
                }
            }
            else {
                this.showinfo("danger", "Can not access Server!");
            }
        }
        catch (exp) {
            this.showinfo("danger", "Fate Error!!");
            console.log(exp);
        }

    }

    render() {
        //console.log(this.state);
        let alert = "";
        if (this.state.box.type) {
            alert = (
                <Alert bsStyle={this.state.box.type}>
                    <p>{this.state.box.msg}</p>
                </Alert>

            )
        }
        return (
            <Grid>
                <Row className="show-grid">
                    <Col md={6} mdOffset={3} sm={8} smOffset={2}>
                        <Panel>
                            <Panel.Heading>Sign In</Panel.Heading>
                            <Panel.Body>
                                <form action={API.login} method='post' onSubmit={this.handleSubmit}>
                                    {alert}
                                    <FormGroup
                                        controlId="username"
                                    >
                                        <ControlLabel>Username:</ControlLabel>
                                        <FormControl
                                            type="text"
                                            name="username"
                                            value={this.state.form.username}
                                            onChange={this.handleChange}

                                        />
                                        <FormControl.Feedback />
                                    </FormGroup>
                                    <FormGroup
                                        controlId="password"
                                    >
                                        <ControlLabel>Password:</ControlLabel>
                                        <FormControl
                                            type="password"
                                            name="password"
                                            value={this.state.form.password}
                                            onChange={this.handleChange}
                                        />
                                        <FormControl.Feedback />
                                    </FormGroup>
                                    <FormGroup
                                        controlId="submit"
                                    >
                                        <Button type="submit" >Submit</Button>
                                    </FormGroup>
                                </form>
                            </Panel.Body>
                        </Panel>
                    </Col>
                </Row>
            </Grid>
        );
    }
}
ReactDOM.render(
    <Login />
    , $('#main')[0]);