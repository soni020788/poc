package currency.converter.api.domain;

import java.math.BigDecimal;
import java.util.Map;

public class CurrencyRates {
    public final Map<String, BigDecimal> rates;
    public final String base;
    public final String date;

    public CurrencyRates(final Map<String, BigDecimal> rates, final String base, final String date) {
        this.rates = rates;
        this.base = base;
        this.date = date;
    }
}
