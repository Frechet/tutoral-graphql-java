package graphql.java.tutorial.domain;

import java.util.List;

public class Page {
    private final String name;
    private final List<Long> linksIds;

    public Page(String name, List<Long> linksIds) {
        this.name = name;
        this.linksIds = linksIds;
    }

    public String getName() {
        return name;
    }

    public List<Long> getLinksIds() {
        return linksIds;
    }
}
