package graphql.java.tutorial.graphql;

import graphql.java.tutorial.domain.Link;
import graphql.java.tutorial.domain.LinkRepository;
import graphql.java.tutorial.domain.Page;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageResolver {

    private final LinkRepository linkRepository;

    public PageResolver(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @GraphQLQuery
    public List<Link> links(@GraphQLContext Page page) {
        return linkRepository.getLinks(page.getLinkIds());
    }
}
