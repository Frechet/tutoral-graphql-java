package graphql.java.tutorial.graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import graphql.java.tutorial.domain.Page;

import java.util.Arrays;

public class PageGraph implements GraphQLRootResolver {

    public Page page(Long id) {
        return new Page("page", Arrays.asList(1L, 2L));
    }
}
