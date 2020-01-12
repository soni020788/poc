import axios from 'axios'
import {apiUrl} from "../_helpers/api.url";
import {Redirect} from 'react-router-dom';
import React from "react";

export const currencyConverterService = {
    getCurrencyDetails,
    getConvertedCurrencyDetails,
    getZoomableSunburstCurrencyData
};

const request = axios.create({
    withCredentials: true,
    baseURL: apiUrl.getBaseApiUrl()
});

function getCurrencyDetails() {
    return request.get('/rest/currency/convert/details?source=USD&target=GBP&amount=100').then().catch(function (error) {
        console.log('getCurrencyDetails error: ' + error);
    })
}

function getConvertedCurrencyDetails(source, target, amount) {
    return request.get('/rest/currency/convert/details?source=' + source + '&target=' + target + '&amount=' + amount).then().catch(function (error) {
        if (error.response.status === 400) {
            return Promise.reject(error.response.data);
        } else {
            return Promise.reject(<Redirect to='/networkError'/>);
        }
    })
}

function getZoomableSunburstCurrencyData() {
    return request.get('/rest/currency/zoomable/sunburst/details').then().catch(function (error) {
        console.log('getZoomableSunburstCurrencyData error: ' + error);
    })
}