package graphql.java.tutorial.controller;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;

import graphql.java.tutorial.domain.LinkRepository;
import graphql.java.tutorial.graphql.LinkMutation;
import graphql.java.tutorial.graphql.PageGraph;
import graphql.java.tutorial.graphql.PageResolver;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;


@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        LinkRepository linkRepository = new LinkRepository();
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new PageGraph(),
                        new PageResolver(linkRepository),
                        new LinkMutation(linkRepository))
                .build()
                .makeExecutableSchema();
    }
}

