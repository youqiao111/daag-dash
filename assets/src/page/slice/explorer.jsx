import React from 'react';
import ReactDOM from 'react-dom';
import bootstrap from "bootstrap/dist/css/bootstrap.css"
import { Grid, Row, Col, Table, PageHeader, ButtonToolbar, Button, Panel, Tabs, Tab, FormGroup, ControlLabel, FormControl, Glyphicon, Checkbox } from 'react-bootstrap';
import LoginContainer from '../../LoginContainer'
import P_Navbar from '../../partial/nav-bar';
import { observer, inject } from "mobx-react";
import { observable, action, useStrict, runInAction, toJS } from "mobx";
import API from '../../setting';
import * as _ from 'lodash';
import ReactTable from "react-table";
import utils from '../../utils';
import "react-table/react-table.css";
import SliceSetting from "../../component/slicesetting";
import Slice from '../../component/slice';
@inject("user")
@observer
class Explorer extends React.Component {
    @observable sliceinfo = null
    @observable datasourcelist = null
    @observable data = null
    @observable type = ""
    @observable setting = {};
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleQuery = this.handleQuery.bind(this);
        this.updateSetting = this.updateSetting.bind(this);
        this.handleSave = this.handleSave.bind(this);
    }
    handleChange = (enent) => {
        this.type = enent.target.value;
    }
    updateSetting = (ns) => {
        this.setting = Object.assign({}, ns);
    }
    handleSave = async () => {
        const type = this.type;
        const setting = JSON.stringify(this.setting);
        const sql = $("#query").val();
        const source = $("#source").val();
        const req = {
            url: API.slice.update,
            method: "POST",
            dataType: "json",
            data: {
                id: this.sliceinfo.id,
                slicesql: sql,
                type: type,
                setting: setting,
                datasource_id: source,
            }
        };
        //console.log(req);
        try {
            const result = await $.ajax(req);
            if (result && result.status == 0) {
                alert("Save Successful!");
            } else {
                alert(result.msg ? result.msg : " Server Error");
            }
        }
        catch (exp) {
            console.log(exp);
            alert("Fate Error!!");
        }
    }
    async componentWillMount() {
        try {
            const detail_req = {
                url: API.slice.detail + `/${utils.getParameter("id")}/`,
                method: "GET",
                dataType: "json",
            };
            const detail_result = await $.ajax(detail_req);
            if (detail_result && detail_result.status == 0) {
                this.sliceinfo = detail_result.data;
            } else {
                alert(detail_result.msg ? detail_result.msg : " Server Error");
            }
            this.type = this.sliceinfo.type;
            this.setting = this.sliceinfo.setting ? JSON.parse(this.sliceinfo.setting):{};
            
            //console.log(this.sliceinfo.setting);
            const source_req = {
                url: API.datasource.list,
                method: "GET",
                dataType: "json",
            };
            const source_result = await $.ajax(source_req);
            if (source_result && source_result.status == 0) {
                this.datasourcelist = source_result.data;
            }
            else {
                alert(source_result.msg);
            }


        }
        catch (exp) {
            console.log(exp);
            alert("Fate Error!!");
        }

    }
    handleQuery = async (event) => {
        const source = $("#source").val()
        const sql = $("#query").val();
        const req = {
            url: API.slice.query,
            method: "POST",
            dataType: "json",
            data: {
                datasource_id: source,
                sql: sql,
            }
        };
        try {
            const result = await $.ajax(req);
            if (result && result.status == 0) {
                //console.log(result.data);
                this.data = result.data;
            }
            else {
                alert(result.msg);
            }
        }
        catch (exp) {
            alert(exp);
        }
    }
    render() {
        if (!this.sliceinfo) return null;
        let source_opt = [];
        source_opt.push(<option value="0" key={0}>select</option>);
        //console.log(this.datasourcelist);
        if (this.datasourcelist) {

            this.datasourcelist.forEach(item => {
                source_opt.push(<option value={item.id} key={item.id}>{item.name} </option>);
            });
        }
        const columns = [];
        const js_data = toJS(this.data);
        const js_setting = toJS(this.setting);
        if (js_data && js_data.length > 0) {
            const row = js_data[0];
            for (const key in row) {
                columns.push({
                    Header: key,
                    accessor: key
                })
            }
        }
        //console.log("defaultValue");
        //console.log(this.sliceinfo);

        return (//
            <Grid>
                <PageHeader>
                    {this.sliceinfo.name} Data Explorer
                </PageHeader>
                <Row>
                    <Col md={12}>
                        <FormControl.Static>{this.sliceinfo.description}</FormControl.Static>
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <FormGroup controlId="source">
                            <ControlLabel>Source:</ControlLabel>
                            <FormControl
                                componentClass="select"
                                name="source"
                                id="source"
                                placeholder="select source"
                                value={1}
                                onChange={() => { }}
                            >
                                {source_opt}
                            </FormControl>
                        </FormGroup>
                        <FormGroup controlId="query">
                            <ControlLabel>Query:</ControlLabel>
                            <FormControl componentClass="textarea"
                                id="query"
                                name="query"
                                rows={10}
                                defaultValue={this.sliceinfo.slicesql}
                            />
                        </FormGroup>

                        <ButtonToolbar>
                            <Button bsStyle="info" onClick={this.handleQuery}>Run Query</Button>
                            <Button bsStyle="success" onClick={this.handleSave}>Save</Button>
                        </ButtonToolbar>

                    </Col>

                </Row>

                <Row>
                    <Col md={12}>
                        <h3>Result</h3>
                    </Col>
                    <Col md={12}>
                        <Tabs defaultActiveKey={1} id="result-tabs">
                            <Tab eventKey={1} title="Data">

                                <ReactTable
                                    data={js_data ? js_data : []}
                                    columns={columns}
                                    pageSize={10}
                                    showPaginationTop={true}
                                    showPaginationBottom={false}
                                    showPageJump={false}
                                    showPageSizeOptions={false}
                                />

                            </Tab>
                            <Tab eventKey={2} title="Chart">
                                <FormGroup controlId="type">
                                    <ControlLabel>Type:</ControlLabel>
                                    <FormControl
                                        componentClass="select"
                                        name="type"
                                        id="type"
                                        placeholder="select type"
                                        value={this.type}
                                        onChange={this.handleChange}
                                    >
                                        <option value="">select</option>
                                        <option value="chart">Combination Chart</option>
                                        <option value="table">Table</option>
                                        <option value="scatter">Scatter</option>
                                        <option value="pie">Pie</option>
                                        <option value="donut">Donut</option>
                                        <option value="gauge">Gauge</option>
                                    </FormControl>
                                </FormGroup>
                                <SliceSetting
                                    data={js_data}
                                    setting={js_setting}
                                    type={this.type}
                                    updateSetting={this.updateSetting}
                                />
                                <Slice
                                    data={js_data}
                                    setting={js_setting}
                                    type={this.type}
                                />

                            </Tab>
                        </Tabs>


                    </Col>
                </Row>
            </Grid>
        );
    }
}
ReactDOM.render(
    <LoginContainer>
        <P_Navbar />
        <Explorer />
    </LoginContainer>

    , $('#main')[0]);