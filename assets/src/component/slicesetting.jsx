import React from 'react';
import { Grid, Row, Col, Table, ButtonToolbar, Alert, Button, Panel, Tabs, Tab, FormGroup, ControlLabel, HelpBlock, FormControl, Checkbox, Glyphicon } from 'react-bootstrap';
import { observer, inject } from "mobx-react";
import { observable, action, useStrict, runInAction, toJS } from "mobx";
import API from '../setting';
import * as _ from 'lodash';
import utils from '../utils';



@inject('user')
@observer
class SliceSetting extends React.Component {
    constructor(props) {
        super(props)
        this.setting = this.props.setting[this.props.type];

        this.slice_setting = {};
        //
        this.slice_setting.table = (
            <div>
                <Row>
                    <Col md={12}>
                        <FormGroup>
                            <ControlLabel>PageSize:</ControlLabel>
                            <FormControl
                                id="pagesize"
                                type="text"
                                placeholder="Enter PageSize"
                            />
                        </FormGroup>
                    </Col>
                </Row>
            </div >
        );
        this.slice_setting.chart = (
            <div>
                <Row>
                    <Col md={4}>
                        <FormGroup>
                            <ControlLabel>Column Name:</ControlLabel>
                            <FormControl
                                id="cname"
                                type="text"
                                placeholder="Enter Column Name"
                            />
                        </FormGroup>
                        <FormGroup>
                            <ControlLabel>Column Chart Type:</ControlLabel>
                            <FormControl componentClass="select" placeholder="select" id="ctype"
                                defaultValue="line"
                            >
                                <option value="line">line</option>
                                <option value="spline">spline</option>
                                <option value="step">step</option>
                                <option value="area">area</option>
                                <option value="area-spline">area-spline</option>
                                <option value="area-step">area-step</option>
                                <option value="bar">bar</option>
                            </FormControl>
                        </FormGroup>
                        <Button onClick={this.handleColumnADD}>ADD</Button>
                    </Col>
                    <Col md={6}>
                        <ul id="cl">
                        </ul>
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <FormGroup>
                            <ControlLabel>Groups:</ControlLabel>
                            <FormControl
                                id="cgroup"
                                type="text"
                                placeholder="Enter Groups Name"
                            />
                            <HelpBlock> [["Column1", "Column2"],["Column3", "Column4", "Column5"]]</HelpBlock>
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col md={4}>
                        <FormGroup>
                            <ControlLabel>X axis type:</ControlLabel>
                            <FormControl componentClass="select" placeholder="select" id="xaxistype">
                                <option value="indexed">Indexed</option>
                                <option value="timeseries">Timeseries</option>
                                <option value="category">Category</option>
                            </FormControl>
                        </FormGroup>
                    </Col>
                    <Col md={4}>
                        <FormGroup>
                            <ControlLabel>X axis format:</ControlLabel>
                            <FormControl
                                id="xaxisformat"
                                type="text"
                                placeholder="Enter X axis format"
                            />
                        </FormGroup>
                    </Col>
                    <Col md={4}>
                        <FormGroup>
                            <ControlLabel>X colums name:</ControlLabel>
                            <FormControl
                                id="xcategories"
                                type="text"
                                placeholder="Enter colums name"
                            />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col md={3}>
                        <FormGroup>
                            <ControlLabel>Y2 axis:</ControlLabel>
                            <Checkbox id="y2">
                                Show Y2 axis
                            </Checkbox>
                        </FormGroup>
                    </Col>
                    <Col md={3}>
                        <FormGroup>
                            <ControlLabel>Rotated:</ControlLabel>
                            <Checkbox id="rotated">
                                Rotated Charts
                            </Checkbox>
                        </FormGroup>
                    </Col>
                    <Col md={3}>
                        <FormGroup>
                            <ControlLabel>Grid-X:</ControlLabel>
                            <Checkbox id="xgrid">
                                Show X Grid Line
                            </Checkbox>
                        </FormGroup>
                    </Col>
                    <Col md={3}>
                        <FormGroup>
                            <ControlLabel>Grid-Y:</ControlLabel>
                            <Checkbox id="ygrid">
                                Show Y Grid Line
                            </Checkbox>
                        </FormGroup>
                    </Col>
                </Row>
            </div>
        );
        this.handleClick = this.handleClick.bind(this);
    }
    handleColumnADD = (event) => {
        const name = $("#cname");
        const type = $("#ctype");
        $("#cl").append(`
        <li className="list-group-item" data-name="${name.val()}" data-type="${type.val()}" style="cursor:pointer" onclick="this.parentNode.removeChild(this)">
        ${name.val()},${type.val()}
        </li>
        `);
        name.val("");
    }
    handleClick = (event) => {
        const obj = {};
        const type = this.props.type;
        obj[type] = {};
        switch (type) {
            case "chart":
                //
                const data = obj[type].data = {};
                data.types = {};
                $("#cl li").each(function (i, e) {
                    const elm = $(this);
                    data.types[elm.data("name")] = elm.data("type")
                });
                //
                const g = $("#cgroup").val();
                if (g) {
                    data.groups = JSON.parse(g);
                }
                //
                const axis = obj[type].axis = {};
                axis.x = {
                    type: $("#xaxistype").val(),
                };
                //
                const format = $("#xaxisformat").val();
                if (format) {
                    axis.x.tick = { format: format }

                }
                const x = $("#xcategories").val();
                if (x) {
                    data.x = x;
                }
                //
                axis.y2 = { show: $("#y2")[0].checked, };
                //
                axis.rotated = $("#rotated")[0].checked;
                const grid = obj[type].grid = {};
                grid.x = { show: $("#xgrid")[0].checked };
                //
                grid.y = { show: $("#ygrid")[0].checked };
                //
                break;
            default:
                $("#slicesetting input").each(function (index, element) {
                    const elm = $(element);
                    obj[type][elm.attr("id")] = elm.val();
                    //console.log(elm);
                });

        }
        //console.log(obj[type]);

        this.props.updateSetting(obj);
    }

    componentDidUpdate() {
        const setting = this.props.setting[this.props.type];
        //console.log(setting);
        if (!setting) return;
        switch (this.props.type) {
            case "chart":
                $("#cl li").remove();
                if (setting.data && setting.data.types) {
                    for (const key in setting.data.types) {
                        $("#cl").append(`
        <li className="list-group-item" data-name="${key}" data-type="${setting.data.types[key]}" style="cursor:pointer" onclick="this.parentNode.removeChild(this)">
        ${key},${setting.data.types[key]}
        </li>
        `);
                    }
                }
                setting.data && setting.data.groups ?
                    $("#cgroup").val(setting.data.groups)
                    : "";
                setting.data && setting.data.x ?
                    $("#xcategories").val(setting.data.x)
                    : "";
                setting.axis && setting.axis.x && setting.axis.x.type ?
                    $("#xaxistype").val(setting.axis.x.type)
                    : "";
                setting.axis && setting.axis.x && setting.axis.x.tick ?
                    $("#xaxisformat").val(setting.axis.x.tick.format)
                    : "";
                setting.axis && setting.axis.y2 != undefined ?
                    $("#y2")[0].checked = setting.axis.y2.show
                    : "";
                setting.axis && setting.axis.rotated != undefined ?
                    $("#rotated")[0].checked = setting.axis.rotated
                    : "";
                setting.grid && setting.grid.x != undefined ?
                    $("#xgrid")[0].checked = setting.grid.x.show
                    : "";
                setting.grid && setting.grid.y != undefined ?
                    $("#ygrid")[0].checked = setting.grid.y.show
                    : "";
                break;
            case "table":
                $("#pagesize").val(setting && setting.pagesize ? setting.pagesize : 100);
                break;
            default:
        }
    }
    render() {
        const type = this.props.type;
        if (!type) return null;
        if (!this.slice_setting[type]) {
            return <Alert bsStyle="warning">
                {type}
            </Alert>
        }
        const html = this.slice_setting[type];
        return <Panel>
            <Panel.Heading>{type} Setting</Panel.Heading>
            <Panel.Body>
                <Row>
                    <Col md={12} id="slicesetting">
                        {html}
                        <Button onClick={this.handleClick}>Priview</Button>
                    </Col>
                </Row>
            </Panel.Body>
        </Panel>
    }
}

export default SliceSetting;