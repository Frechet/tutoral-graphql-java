package graphql.java.tutorial.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinkRepository {

    private final List<Link> links;

    public LinkRepository() {
        links = new ArrayList<>();
        //add some linkIds to start off with
        links.add(new Link("http://howtographql.com", "Your favorite GraphQL page"));
        links.add(new Link("http://graphql.org/learn/", "The official docks"));
    }

    public List<Link> getLinks(List<Long> ids) {
        return links;
    }

    public void saveLink(Link link) {
        links.add(link);
    }
}
