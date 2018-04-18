import React from 'react';
import ReactDOM from 'react-dom';
import bootstrap from "bootstrap/dist/css/bootstrap.css"
import { Grid, Row, Col, Panel, Table, Pager } from 'react-bootstrap';
import { observer, inject } from "mobx-react";
import { observable } from "mobx";
import LoginContainer from '../../LoginContainer'
import P_Navbar from '../../partial/nav-bar';
import API from '../../setting';

@inject("user")
@observer class Dashboard extends React.Component {
    constructor(props) {
        super(props)
        ///console.log(this.props)
        
    }
    render() {
        if (!this.props.user) return null;
        return (
            <Grid>
                <Row>
                    <Col md={12}>
                        <Panel>
                            <Panel.Heading>
                                <Row>
                                    <Col md={6}>
                                        <h2>Dashboard</h2>
                                    </Col>
                                    <Col md={6}>

                                    </Col>
                                </Row>
                            </Panel.Heading>
                            <Panel.Body>
                                <Table striped bordered condensed hover>
                                    <thead>
                                        <tr>
                                            <th>Dashboard</th>
                                            <th>Creator</th>
                                            <th>Modified</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Mark</td>
                                            <td>Otto</td>

                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Jacob</td>
                                            <td>Thornton</td>

                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>Larry the Bird</td>
                                            <td>@twitter</td>
                                        </tr>
                                    </tbody>
                                </Table>
                            </Panel.Body>
                        </Panel>
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <Pager>
                            <Pager.Item href="#">Previous</Pager.Item>{' '}
                            <Pager.Item href="#">Next</Pager.Item>
                        </Pager>
                    </Col>
                </Row>
            </Grid >
        )
    }
}
ReactDOM.render(
    <LoginContainer>
        <P_Navbar />
        <Dashboard />
    </LoginContainer>
    , $('#main')[0]);