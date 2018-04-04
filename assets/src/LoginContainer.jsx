import React from 'react';
import { observer, Provider } from "mobx-react";
import { observable } from "mobx";
import API from './setting';

@observer
class LoginContainer extends React.Component {
    @observable user = null;
    constructor(props) {
        super(props);
    }
    async componentWillMount() {
        try {
            const req = {
                url: API.user.info,//"?_="+(new Date()).valueOf(),
                method: "GET",
                dataType: "json",
            };
            const result = await $.ajax(req);
            if (result && result.status == 0) {
                const data = Object.assign({}, result.data)
                this.user = data;
            }
            else {
                alert("Please Login!");
                window.location.href = "/login.html";
            }
        }
        catch (exp) {
            console.log(exp);
            alert("Fate Error!!");
        }

    }
    render() {
        if (this.props.children && this.user) {
            return (
                <Provider user={this.user}>
                    <div>
                        {React.Children.map(this.props.children, (child, i) => {
                            return child;
                        })}
                    </div>
                </Provider>
            );
        } else {
            return null;
        }

    }

}
export default LoginContainer