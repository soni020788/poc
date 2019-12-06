package currency.converter.api.service;

import currency.converter.api.domain.CurrencyConverterDto;
import currency.converter.api.domain.CurrencyRates;
import currency.converter.api.resource.CurrencyExchangeApiClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

@Singleton
public class CurrencyConverterService {
    @Inject
    CurrencyExchangeApiClient currencyExchangeApiClient;

    public CurrencyConverterDto getCurrencyRates(final String source, final String target, final String amount) throws IOException {
        long time = System.currentTimeMillis();
        Locale locale = Arrays.stream(NumberFormat.getAvailableLocales()).filter(locale1 -> NumberFormat.getCurrencyInstance(locale1).getCurrency().getCurrencyCode().equals(target)).findFirst().orElse(Locale.getDefault());
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
        CurrencyRates currencyRates = currencyExchangeApiClient.getCurrencyRates(source);
        BigDecimal calculatedAmount = currencyRates.rates.get(target).multiply(new BigDecimal(amount));
        return new CurrencyConverterDto(source, target, amount, calculatedAmount, currencyInstance.format(calculatedAmount), (System.currentTimeMillis() - time) + "ms", currencyRates.date);
    }

    public Map<String, BigDecimal> getCurrencyList() throws IOException {
        CurrencyRates currencyRates = currencyExchangeApiClient.getCurrencyRates(null);
        return currencyRates.rates;
    }
}
