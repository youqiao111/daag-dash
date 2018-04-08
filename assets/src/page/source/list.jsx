import React from 'react';
import ReactDOM from 'react-dom';
import bootstrap from "bootstrap/dist/css/bootstrap.css"
import { Grid, Row, Col, Panel, Table, Pager, PageHeader, Well, ButtonToolbar, Button, Glyphicon } from 'react-bootstrap';
import LoginContainer from '../../LoginContainer'
import P_Navbar from '../../partial/nav-bar';
import { observer, inject } from "mobx-react";
import { observable, action, useStrict, runInAction } from "mobx";
import API from '../../setting';
import * as _ from 'lodash';
import moment from 'moment';

@inject("user")
@observer
class SourceList extends React.Component {
    @observable list = null;
    async componentWillMount() {
        try {
            const req = {
                url: API.datasource.list,
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
    handleClick = async (id) => {
        const con = confirm("Confirm?")
        if (con) {
            const req = {
                url: API.datasource.delete,
                method: "post",
                dataType: "json",
                data: {
                    id: id,
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
            tr.push(
                <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.name}</td>
                    <td>{item.type}</td>
                    <td>{item.username}</td>
                    <td>{moment(item.createtime).format("YYYY-MM-DD hh:mm:ss")}</td>
                    <td>

                            <ButtonToolbar><Button bsSize="xsmall" href={`/source/edit.html?id=${item.id}`}><Glyphicon glyph="edit" /></Button>
                                <Button bsSize="xsmall" onClick={() => this.handleClick(item.id)}><Glyphicon glyph="remove"/></Button>
                            </ButtonToolbar>

                    </td>
                </tr>
            );

        });
        return (
            <Grid>
                <PageHeader>
                    Sources <small>Totals {this.list.length}</small>
                </PageHeader>
                <Well><Button bsSize="small" href="/source/add.html"><Glyphicon glyph="plus" /> ADD</Button></Well>
                <Row>
                    <Col md={12}>
                        <Table striped bordered condensed hover>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Type</th>
                                    <th>Creator</th>
                                    <th>Last Changed</th>
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
        <SourceList />
    </LoginContainer>
    , $('#main')[0]);