package currency.converter.api.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.mycila.guice.ext.closeable.CloseableInjector;
import currency.converter.api.di.CurrencyConverterContext;

public class CurrencyConverterServletConfig extends GuiceServletContextListener {
    private final CloseableInjector injector;
    private static CurrencyConverterServletConfig instance;

    public CurrencyConverterServletConfig() {
        this.injector = createInjector();
        instance = this;
    }

    private CloseableInjector createInjector() {
        return Guice.createInjector(CurrencyConverterContext.DEVELOPMENT.createModules()).getInstance(CloseableInjector.class);
    }

    private static synchronized CurrencyConverterServletConfig getInstance() {
        return null == instance ? new CurrencyConverterServletConfig() : instance;
    }

    public static Injector injectorInstance() {
        return getInstance().getInjector();
    }

    protected Injector getInjector() {
        return injector;
    }
}
