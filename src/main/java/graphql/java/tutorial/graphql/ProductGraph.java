package graphql.java.tutorial.graphql;

import graphql.execution.batched.Batched;
import graphql.java.tutorial.domain.Item;
import graphql.java.tutorial.domain.Product;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class ProductGraph {

    // касательно плохого проектирование в REST vs GraphQL
    // Cart { items: [Item {product: [Product]}]} N + 1 request to products by items of cart
    @GraphQLQuery(name = "product")
    @Batched // <--
    public List<Product> products(
            @GraphQLContext List<Item> items,
            // we have environment
            @GraphQLEnvironment Set<String> subFields
    ) {
        return Arrays.asList(new Product(1L, "Teacher"), new Product(2L, "Student"));
    }
}
