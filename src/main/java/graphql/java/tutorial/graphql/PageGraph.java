package graphql.java.tutorial.graphql;

import graphql.java.tutorial.domain.Page;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;


@Component
public class PageGraph {

    @GraphQLQuery(name = "page")
    public Page page(@GraphQLArgument(name = "id") Long id,
            // we have environment
            @GraphQLEnvironment Set<String> subFields) {
        return new Page("page", Arrays.asList(1L, 2L));
    }

}
