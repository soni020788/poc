import React from "react";
import {Route, Switch, BrowserRouter as Router} from 'react-router-dom'
import CurrencyConverterPage from "../CurrencyConverterPage/CurrencyConverterPage";
import NetworkErrorPage from "../ErrorPage/NetworkErrorPage";
import NotFoundPage from "../ErrorPage/NotFoundPage";
import'./App.css';
import './CurrencyUI.css'

class App extends React.Component {
    render() {
        return (
            <div>
                <Router>
                    <Switch>
                        <Route default path="/currency" component={CurrencyConverterPage}/>
                        <Route path="/networkError" component={NetworkErrorPage}/>
                        <Route path="/*" component={NotFoundPage}/>
                    </Switch>
                </Router>
            </div>
        )
    }
}

export {App}