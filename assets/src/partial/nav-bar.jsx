
import React from 'react';
import { Navbar, NavItem, NavDropdown, MenuItem, Nav } from 'react-bootstrap';
import { observer, inject } from "mobx-react";
import { observable } from "mobx";
import * as _ from 'lodash';
import API from '../setting';

@inject('user')
@observer
class P_Navbar extends React.Component {
    constructor(props) {
        super(props);
        this.handleLogout = this.handleLogout.bind(this);
    }
    handleLogout = async (event) => {
        console.log("logout");
        const req = {
            url: API.logout,
            method: "GET",
            dataType: "json",
        };
        const result = await $.ajax(req);
        window.location.href = "/";
    }
    render() {
        let menu = (
            <Nav>

            </Nav>
        );
        if (this.props.user && _.find(this.props.user.roleList, { name: 'admin' })) {
            menu = (
                <Nav>
                    <NavItem eventKey={2} href="/user/">
                        Users
            </NavItem>
                    <NavItem eventKey={3} href="/source/">
                        Sources
            </NavItem>
                    <NavItem eventKey={4} href="/slice/">
                        Slices
            </NavItem>
                    <NavItem eventKey={5} href="/dashboard/list.html">
                        Dashboards
            </NavItem>
                    <NavItem eventKey={6} href="/tools/">
                        Tools
            </NavItem>
                </Nav>
            );
        }
        else if (this.props.user && _.find(this.props.user.roleList, { name: 'analyst' })) {
            menu = (
                <Nav>
                    <NavItem eventKey={3} href="/source/">
                        Sources
            </NavItem>
                    <NavItem eventKey={4} href="/slice/">
                        Slices
            </NavItem>
                    <NavItem eventKey={5} href="/dashboard/list.html">
                        Dashboards
            </NavItem>
                    <NavItem eventKey={6} href="/tools/">
                        Tools
            </NavItem>
                </Nav>
            );
        }
        return (
            <Navbar collapseOnSelect>
                <Navbar.Header>
                    <Navbar.Brand>
                        <a href="/dashboard/">DAAG</a>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                    {menu}
                    <Nav pullRight>
                        <NavItem eventKey={7} href="/help.html">
                            Help
                        </NavItem>
                        <NavDropdown eventKey={1} title={this.props.user ? `Hi ${this.props.user.name}` : ""} id="user-nav-dropdown">
                            <MenuItem eventKey={1.1} href="/user/info.html">Profile</MenuItem>
                            <MenuItem eventKey={1.2} onClick={this.handleLogout} >Logout</MenuItem>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Navbar >
        );
    }
}


export default P_Navbar;