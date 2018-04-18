import React from 'react';
import ReactDOM from 'react-dom';
import { Grid, Row, Col, Table, ButtonToolbar, Button, Panel, Tabs, Tab, FormGroup, ControlLabel, FormControl, Glyphicon, Checkbox } from 'react-bootstrap';
import { observer, inject } from "mobx-react";
import { observable, action, useStrict, runInAction, toJS } from "mobx";
import API from '../setting';
import * as _ from 'lodash';
import ReactTable from "react-table";
import utils from '../utils';
import "react-table/react-table.css";
import c3 from 'c3';
import 'c3/c3.css';

@inject('user')
@observer
class Slice extends React.Component {

    constructor(props) {
        super(props)

    }
    componentDidMount() {
        //console.log(this.props);
        if (this.props.type != "table")
            this.updateChart(this.props.type, this.props.data, this.props.setting);
    }
    componentDidUpdate() {
        //console.log(newProps);
        if (this.props.type != "table")
            this.updateChart(this.props.type, this.props.data, this.props.setting);
    }

    render() {
        if (!this.props.data || !this.props.type) return null;
        const setting = this.props.setting[this.props.type];
        if (!setting) return null;
        if (this.props.type == "table") {
            const columns = [];
            const row = this.props.data[0];
            for (const key in row) {
                columns.push({
                    Header: key,
                    accessor: key
                })
            }

            return <ReactTable
                data={this.props.data}
                columns={columns}
                pageSize={setting.pagesize}
                showPaginationTop={true}
                showPaginationBottom={false}
                showPageJump={false}
                showPageSizeOptions={false}
            />
        }
        return <div>

            <p>type: {JSON.stringify(this.props.type)}</p>
            <p>setting: {JSON.stringify(this.props.setting)}</p>
            <p>data: {JSON.stringify(this.props.data)}</p>
        </div>
    }
    updateChart(type, data, setting) {
        if (!data) return;
        const config = Object.assign({}, setting[type]);
        console.log(data);
        if (!config.data) return;
        config.data.columns = [];
        if (type == "chart") {
            for (const key in config.data.types) {
                if (config.data.types.hasOwnProperty(key)) {
                    const element = config.data.types[key];
                    console.log(element);
                    const line = _.map(data, key);
                    line.unshift(key);
                    config.data.columns.push(line);

                }
            }
            if (config.data.x) {
                const line = _.map(data, config.data.x);
                line.unshift(config.data.x);
                config.data.columns.push(line);
            }
            //console.log(config.data.columns);
        }
        
        const newConfig = Object.assign({ bindto: ReactDOM.findDOMNode(this) }, config);
        const chart = c3.generate(newConfig);
        console.log(newConfig);
    }
}

export default Slice;