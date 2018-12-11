package graphql.java.tutorial.graphql;

import graphql.java.tutorial.domain.Link;
import graphql.java.tutorial.domain.LinkRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import org.springframework.stereotype.Component;

@Component
public class LinkMutation {

    private final LinkRepository linkRepository;

    public LinkMutation(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @GraphQLMutation(name = "createLink")
    public Link createLink(@GraphQLArgument(name = "url") String url,
                           @GraphQLArgument(name = "description", defaultValue = "default") String description) {
        Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
}