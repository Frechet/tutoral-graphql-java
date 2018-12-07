package graphql.java.tutorial.controller;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;

import graphql.java.tutorial.domain.LinkRepository;
import graphql.java.tutorial.graphql.PageGraph;
import graphql.java.tutorial.graphql.PageResolver;
import graphql.servlet.SimpleGraphQLServlet;


@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super(SchemaParser.newParser()
                .file("schema.graphqls") //parse the schema file created earlier
                .resolvers(new PageGraph(), new PageResolver(new LinkRepository()))
                .build()
                .makeExecutableSchema());
    }
}

