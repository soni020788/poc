package currency.converter.api.domain;

import java.util.List;

public class ZoomableSunburstData {
    public String name;
    public List<ZoomableSunburstChildrenWrapper> children;

    public ZoomableSunburstData(String name, List<ZoomableSunburstChildrenWrapper> children) {
        this.name = name;
        this.children = children;
    }
}
