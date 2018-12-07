package graphql.java.tutorial.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.java.tutorial.domain.Link;
import graphql.java.tutorial.domain.LinkRepository;
import graphql.java.tutorial.domain.Page;

import java.util.List;

public class PageResolver implements GraphQLResolver<Page> {

    private final LinkRepository linkRepository;

    public PageResolver(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> links(Page page) {
        return linkRepository.getLinks(page.getLinksIds());
    }
}
