package currency.converter.api.resource;

import currency.converter.api.domain.CurrencyConverterDto;
import currency.converter.api.service.CurrencyConverterService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Pattern;

@Path("/currency")
@Singleton
public class CurrencyConverterResource {

    @Inject
    CurrencyConverterService currencyConverterService;

    @GET
    @Path("/convert/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConvertedCurrencyDetails(@Context HttpServletRequest request, @QueryParam("source") String source, @QueryParam("target") String target, @QueryParam("amount") String amount) {
        return getResponse(source, target, amount, false);
    }

    @GET
    @Path("/convert/amounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConvertedCurrency(@Context HttpServletRequest request, @QueryParam("source") String source, @QueryParam("target") String target, @QueryParam("amount") String amount) {
        return getResponse(source, target, amount, true);
    }

    private Response getResponse(@QueryParam("source") String source, @QueryParam("target") String target, @QueryParam("amount") String amount, boolean onlyConvertedAmount) {
        CurrencyConverterDto currencyConverterDto;
        if (source == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid source input field. Please enter valid source").build();
        }
        if (target == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid target input field. Please enter valid target").build();
        }
        if (amount == null || isStringInvalidNumber(amount)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid amount input field. Please enter valid amount").build();
        }
        try {
            Map<String, BigDecimal> currencyList = currencyConverterService.getCurrencyList();
            if (!source.equals("EUR") && !currencyList.containsKey(source)) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Source '" + source + "' is not supported.").build();
            }
            if (!target.equals("EUR") && !currencyList.containsKey(target)) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Target '" + target + "' is not supported.").build();
            }
            currencyConverterDto = currencyConverterService.getCurrencyRates(source, target, amount);
        } catch (IOException | RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return onlyConvertedAmount ? Response.ok(currencyConverterDto.convertedAmount).build() : Response.ok(currencyConverterDto).build();
    }

    private boolean isStringInvalidNumber(String number) {
        String decimalPattern = "^[1-9]\\d*(\\.\\d+)?$";
        return !Pattern.matches(decimalPattern, number);
    }
}
