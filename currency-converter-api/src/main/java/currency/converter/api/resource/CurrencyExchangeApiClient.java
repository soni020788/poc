package currency.converter.api.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import currency.converter.api.domain.CurrencyRates;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyExchangeApiClient {

    @Inject
    @Named("currency.exchange.api.url")
    private String apiUrl;

    public CurrencyRates getCurrencyRates(String source) throws IOException {
        Gson gson = new Gson();
        final String completeUrl = null != source ? apiUrl + "?base=" + source : apiUrl;
        URL url = new URL(completeUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        if (httpURLConnection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
        }
        try (InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream())) {
            return gson.fromJson(inputStreamReader, new TypeToken<CurrencyRates>() {
            }.getType());
        }
    }
}
