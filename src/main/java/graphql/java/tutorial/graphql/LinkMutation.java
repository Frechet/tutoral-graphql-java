package graphql.java.tutorial.graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import graphql.java.tutorial.domain.Link;
import graphql.java.tutorial.domain.LinkRepository;

public class LinkMutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public LinkMutation(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link createLink(String url, String description) {
        Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
}
