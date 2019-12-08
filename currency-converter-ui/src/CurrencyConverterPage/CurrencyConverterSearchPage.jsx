import React from "react";
import {currencyConverterService} from "../_services/currency.converter.service";
import CurrencyConverterDetailsPage from "../CurrencyConverterPage/CurrencyConverterDetailsPage"

export default class CurrencyConverterSearchPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            source: '',
            target: '',
            amount: 1,
            submit: false,
            loading: false,
            convertedCurrencyDetails: '',
            error: ''
        };
        this.handleChange = this.handleChange.bind(this);
        this.handelSubmit = this.handelSubmit.bind(this);
    }

    componentDidMount() {
        this.getCurrencyDetails();
    }

    getCurrencyDetails() {
        currencyConverterService.getCurrencyDetails().then(response => {
            this.setState({
                convertedCurrencyDetails: response.data
            })
        }).catch(function (error) {
            console.log('Error' + error)
        })
    }

    handleChange(e) {
        const {name, value} = e.target;
        this.setState({
            [name]: value,
            loading: false,
            error: ''
        });
    }

    handelSubmit(event) {
        event.preventDefault();
        this.setState({submit: true});
        const {source, target, amount} = this.state;
        if (!(source && target && amount)) {
            return;
        }
        this.setState({loading: true});
        currencyConverterService.getConvertedCurrencyDetails(source, target, amount).then(response => {
            this.setState({
                convertedCurrencyDetails: response.data
            })
        }, error => this.setState({error: error, loading: false})).catch(function (error) {
            console.log('Error in getConvertedCurrencyDetails: ' + error);
        })
    }

    render() {
        const {source, target, amount, submit, loading, error} = this.state;
        return (
            <div className="App">
                <h2>
                    Real Time Currency Converter Page
                </h2>
                <div className={'form-group' + (submit && !source ? 'has-error' : '')}>
                    <label className="label info" htmlFor="source">Source Currency</label>
                    <input placeholder="Currency Source" type="text" name="source" value={source} required={true}
                           minLength={3} maxLength={3}
                           onChange={this.handleChange}/>
                    {submit && !source && <div className="text-danger">Source is required!</div>}
                </div>
                <div className={'form-group' + (submit && !target ? 'has-error' : '')}>
                    <label className="label info" htmlFor="target">Target Currency</label>
                    <input placeholder="Currency Target" type="text" name="target" value={target} required={true}
                           minLength={3} maxLength={3}
                           onChange={this.handleChange}/>
                    {submit && !target && <div className="text-danger">Target is required!</div>}
                </div>
                <div className={'form-group' + (submit && !amount ? 'has-error' : '')}>
                    <label className="label-amount" htmlFor="amount">Amount </label>
                    <input placeholder="Currency convert Amount" type="number" name="amount" value={amount}
                           required={true}
                           minLength={1}
                           onChange={this.handleChange}/>
                    {submit && !amount &&
                    <div className="text-danger">Amount is required, Please enter valid amount!</div>}
                </div>
                <div>
                    <button className="btn btn-primary" onClick={this.handelSubmit} disabled={loading}>Convert
                        Currency
                    </button>
                </div>
                {error && <div className={'bg-danger'}>{error}</div>}
                <br/>
                {this.state.convertedCurrencyDetails &&
                <CurrencyConverterDetailsPage currencyDetails={this.state.convertedCurrencyDetails}/>}
            </div>
        )
    }
}