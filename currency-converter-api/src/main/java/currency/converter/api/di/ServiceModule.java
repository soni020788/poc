package currency.converter.api.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import currency.converter.api.service.CurrencyConverterService;

public class ServiceModule extends AbstractModule {
    protected void configure() {
        bind(CurrencyConverterService.class).in(Scopes.SINGLETON);
    }
}
