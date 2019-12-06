package currency.converter.api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyConverterDto {
    public final String source;
    public final String target;
    public final String amount;
    public final BigDecimal convertedAmount;
    public final String convertedLocaleAmount;
    public final String calculationTime;
    public final String date;

    @JsonCreator
    public CurrencyConverterDto(@JsonProperty("source") final String source, @JsonProperty("target") final String target, @JsonProperty("amount") final String amount,
                                @JsonProperty("convertedAmount") final BigDecimal convertedAmount, @JsonProperty("convertedLocaleAmount") final String convertedLocaleAmount,
                                @JsonProperty("timestamp") final String timestamp, @JsonProperty("date") final String date) {
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
        this.convertedLocaleAmount = convertedLocaleAmount;
        this.calculationTime = timestamp;
        this.date = date;
    }
}
