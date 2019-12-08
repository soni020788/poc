import React from "react";

export default class CurrencyConverterDetailsPage extends React.Component {
    render() {
        return (
            <div className="App">
                <table>
                    <thead className="text-left">
                    <tr>
                        <th>
                            Source
                        </th>
                        <th>
                            Target
                        </th>
                        <th>
                            Amount
                        </th>
                        <th>
                            Converted Amount in ({this.props.currencyDetails.target})
                        </th>
                        <th>
                            Converted Amount (As Target Locale Currency)
                        </th>
                        <th>
                            Currency Calculation Time
                        </th>
                        <th>
                            Date
                        </th>
                    </tr>
                    </thead>
                    <thead>
                    <tr>
                        <td>
                            {this.props.currencyDetails.source}
                        </td>
                        <td>
                            {this.props.currencyDetails.target}
                        </td>
                        <td>
                            {this.props.currencyDetails.amount}
                        </td>
                        <td>
                            {this.props.currencyDetails.convertedAmount}
                        </td>
                        <td>
                            {this.props.currencyDetails.convertedLocaleAmount}
                        </td>
                        <td>
                            {this.props.currencyDetails.calculationTime}
                        </td>
                        <td>
                            {this.props.currencyDetails.date}
                        </td>
                    </tr>
                    </thead>
                </table>
            </div>
        )
    }
}