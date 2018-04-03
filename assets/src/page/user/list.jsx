import React from 'react';
import ReactDOM from 'react-dom';
import bootstrap from "bootstrap/dist/css/bootstrap.css"
import { Grid, Row, Col, Panel, Table, Pager, PageHeader, Well, ButtonToolbar, Button, Glyphicon } from 'react-bootstrap';
import LoginContainer from '../../LoginContainer'
import { observer, inject } from "mobx-react";
import { observable } from "mobx";
import P_Navbar from '../../partial/nav-bar';
import API from '../../setting';
import * as _ from 'lodash';
@inject("user")
@observer
class UserList extends React.Component {
    @observable list = null;
    async componentWillMount() {
        try {
            const req = {
                url: API.user.list,
                method: "GET",
                dataType: "json",
            };
            const result = await $.ajax(req);
            console.log(result);
            if (result && result.status == 0) {
                this.list = result.data;
            }
            else {
                alert(result.msg);
            }
        }
        catch (exp) {
            console.log(exp);
            alert("Fate Error!!");
        }

    }
    handleClick = async (id, b) => {
        const con = confirm("Confirm?")
        if (con) {
            const req = {
                url: API.user.available,
                method: "post",
                dataType: "json",
                data: {
                    id: id,
                    available: !b,
                }
            };
            try {
                const result = await $.ajax(req);
                if (result && result.status == 0) {
                    //alert('done');
                    this.componentWillMount()
                }
                else {
                    alert(result.msg ? result.msg : "Server Error!");
                }

            }
            catch (exp) {
                alert("Fate Error!");
                console.log(exp);
            }

        }
    }
    render() {
        if (!this.list) {
            return null;
        }
        const tr = [];
        this.list.forEach((item) => {
            const role = _.map(item.roleList, "name");
            //console.log(role);
            tr.push(
                <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.name}</td>
                    <td>{item.username}</td>
                    <td>{item.email}</td>
                    <td>{item.available.toString()}</td>
                    <td>{role.join(",")}</td>
                    <td>
                        {this.props.user.id != item.id ?
                            <ButtonToolbar><Button bsSize="xsmall" href={`/user/edit.html?id=${item.id}`}><Glyphicon glyph="edit" /></Button>
                                <Button bsStyle={item.available ? "danger" : "success"} bsSize="xsmall" onClick={() => this.handleClick(item.id, item.available)}><Glyphicon glyph={item.available ? "remove" : "ok"} /></Button>
                            </ButtonToolbar>
                            : ""

                        }
                    </td>
                </tr>
            );

        });
        return (
            <Grid>
                <PageHeader>
                    Users <small>Totals {this.list.length}</small>
                </PageHeader>
                <Well><Button bsSize="small" href="/user/add.html"><Glyphicon glyph="plus" /> ADD</Button></Well>
                <Row>
                    <Col md={12}>
                        <Table striped bordered condensed hover>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>User Name</th>
                                    <th>Email</th>
                                    <th>Is Active</th>
                                    <th>Role</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                {tr}
                            </tbody>
                        </Table>
                    </Col>
                </Row>

            </Grid>
        );
    }
}
ReactDOM.render(
    <LoginContainer>
        <P_Navbar />
        <UserList />
    </LoginContainer>

    , $('#main')[0]);