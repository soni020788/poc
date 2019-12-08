export const apiUrl = {
    getBaseApiUrl
};

function getBaseApiUrl() {
    return process.env.NODE_ENV === 'development' ? 'http://localhost:9090' : 'https://currency-converter-rest-api.herokuapp.com';
}