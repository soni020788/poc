package currency.converter.api.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ZoomableSunburstDto {
    public final ZoomableSunburstData children;

    public ZoomableSunburstDto(CurrencyRates currencyRates) {
        this.children = new ZoomableSunburstData("<b>Base Currency:</b> " + currencyRates.base + ", <b>Date:</b> " + currencyRates.date + ", <b>Total Currencies:</b> ", getWrapperChildren(currencyRates.rates, currencyRates.base));
    }

    private List<ZoomableSunburstChildrenWrapper> getWrapperChildren(Map<String, BigDecimal> currencyDtls, String base) {
        return currencyDtls.entrySet().stream().map(stringBigDecimalEntry -> new ZoomableSunburstChildrenWrapper(stringBigDecimalEntry.getKey() + ", <b>Base Currency:</b> " + base, getChildren(stringBigDecimalEntry.getKey(), stringBigDecimalEntry.getValue(), base))).collect(Collectors.toList());
    }

    private List<ZoomableSunburstChildren> getChildren(String currency, BigDecimal rate, String base) {
        return Collections.singletonList(new ZoomableSunburstChildren("<b>Currency:</b> " + currency + ", <b>Rate:</b> " + rate + ", <b>Base Currency:</b> " + base, 1));
    }
}