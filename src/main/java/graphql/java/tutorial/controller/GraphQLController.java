package graphql.java.tutorial.controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.analysis.MaxQueryComplexityInstrumentation;
import graphql.analysis.MaxQueryDepthInstrumentation;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.java.tutorial.graphql.LinkMutation;
import graphql.java.tutorial.graphql.PageGraph;
import graphql.java.tutorial.graphql.PageResolver;
import graphql.java.tutorial.graphql.ProductGraph;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaPrinter;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
public class GraphQLController {

    private final GraphQL graphQL;

    public GraphQLController(PageGraph pageGraph, PageResolver pageResolver,
                             LinkMutation linkMutation, ProductGraph productGraph) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages("graphql.java.tutorial")
                .withResolverBuilders(
                        new AnnotatedResolverBuilder(),
                        new PublicResolverBuilder("graphql.java.tutorial.graphql"))
                .withOperationsFromSingleton(pageGraph)
                .withOperationsFromSingleton(pageResolver) // wtf
                .withOperationsFromSingleton(linkMutation)
                .withOperationsFromSingleton(productGraph)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();

        // if we very want
        System.out.println(new SchemaPrinter(
                SchemaPrinter.Options.defaultOptions()
                        .includeScalarTypes(true)
                        .includeExtendedScalarTypes(true)
                        .includeIntrospectionTypes(true)
        ).print(schema));

        graphQL = GraphQL.newGraphQL(schema)
                .queryExecutionStrategy(new AsyncExecutionStrategy())
                .instrumentation(new ChainedInstrumentation(Arrays.asList(
                        new MaxQueryComplexityInstrumentation(200),
                        new MaxQueryDepthInstrumentation(20)
                )))
                .build();
    }

    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ExecutionResult execute(@RequestBody Map<String, Object> request) {
        return graphQL.execute(ExecutionInput.newExecutionInput()
                .query((String) request.get("query"))
                .operationName((String) request.get("operationName"))
                .build());
    }

}
