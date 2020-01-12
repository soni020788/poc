package currency.converter.api.domain;

import java.util.List;

public class ZoomableSunburstChildrenWrapper {
    public String name;
    public List<ZoomableSunburstChildren> children;

    public ZoomableSunburstChildrenWrapper(String name, List<ZoomableSunburstChildren> children) {
        this.name = name;
        this.children = children;
    }
}
