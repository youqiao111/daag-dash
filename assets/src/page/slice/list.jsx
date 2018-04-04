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


@inject("user")
@observer
class SliceList extends React.Component {
    render() {
        return (

            <Grid>
                <PageHeader>
                    Slices <small>Totals 100</small>
                </PageHeader>
                <Well><Button bsSize="small" href="/slice/add.html"><Glyphicon glyph="plus" /> ADD</Button></Well>
                <Row>
                    <Col md={12}>
                        <Table striped bordered condensed hover>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Type</th>
                                    <th>Datasource</th>
                                    <th>Creator</th>
                                    <th>Last Modified</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Mark</td>
                                    <td>Otto</td>
                                    <td>Mark</td>
                                    <td>Otto</td>
                                    <td>Mark</td>
                                    <td>
                                        <ButtonToolbar>
                                            <Button bsSize="xsmall" href="/slice/edit.html"><Glyphicon glyph="edit" /></Button>
                                            <Button bsSize="xsmall"><Glyphicon glyph="remove" /></Button>
                                        </ButtonToolbar>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jacob</td>
                                    <td>Thornton</td>
                                    <td>Mark</td>
                                    <td>Otto</td>
                                    <td>Mark</td>
                                    <td>
                                        <ButtonToolbar>
                                            <Button bsSize="xsmall" href="/slice/edit.html"><Glyphicon glyph="edit" /></Button>
                                            <Button bsSize="xsmall"><Glyphicon glyph="remove" /></Button>
                                        </ButtonToolbar>
                                    </td>

                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Larry the Bird</td>
                                    <td>@twitter</td>
                                    <td>Mark</td>
                                    <td>Otto</td>
                                    <td>Mark</td>
                                    <td>
                                        <ButtonToolbar>
                                            <Button bsSize="xsmall" href="/slice/edit.html"><Glyphicon glyph="edit" /></Button>
                                            <Button bsSize="xsmall"><Glyphicon glyph="remove" /></Button>
                                        </ButtonToolbar>
                                    </td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Larry the Bird</td>
                                    <td>@twitter</td>
                                    <td>Mark</td>
                                    <td>Otto</td>
                                    <td>Mark</td>
                                    <td>
                                        <ButtonToolbar>
                                            <Button bsSize="xsmall" href="/slice/edit.html"><Glyphicon glyph="edit" /></Button>
                                            <Button bsSize="xsmall"><Glyphicon glyph="remove" /></Button>
                                        </ButtonToolbar>
                                    </td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>Larry the Bird</td>
                                    <td>@twitter</td>
                                    <td>Mark</td>
                                    <td>Otto</td>
                                    <td>Mark</td>
                                    <td>
                                        <ButtonToolbar>
                                            <Button bsSize="xsmall" href="/slice/edit.html"><Glyphicon glyph="edit" /></Button>
                                            <Button bsSize="xsmall"><Glyphicon glyph="remove" /></Button>
                                        </ButtonToolbar>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Larry the Bird</td>
                                    <td>@twitter</td>
                                    <td>Mark</td>
                                    <td>Otto</td>
                                    <td>Mark</td>
                                    <td>
                                        <ButtonToolbar>
                                            <Button title='Edit' bsSize="xsmall" href="/slice/edit.html"><Glyphicon glyph="edit" /></Button>
                                            <Button bsSize="xsmall" href="/slice/explorer.html"><Glyphicon glyph="search" /></Button>
                                            <Button bsSize="xsmall"><Glyphicon glyph="remove" /></Button>
                                        </ButtonToolbar>
                                    </td>
                                </tr>
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
        <SliceList />
    </LoginContainer>

    , $('#main')[0]);