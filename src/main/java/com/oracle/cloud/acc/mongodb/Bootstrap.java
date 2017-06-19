package com.oracle.cloud.acc.mongodb;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Bootstrap {

    static void bootstrapREST() throws IOException {

        String hostname = "0.0.0.0";
        String port = Optional.ofNullable(System.getenv("PORT")).orElse("8080");

        URI baseUri = UriBuilder.fromUri("http://" + hostname + "/").port(Integer.parseInt(port)).build();

        ResourceConfig config = new ResourceConfig(EmployeesResource.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        Logger.getLogger(Bootstrap.class.getName()).log(Level.INFO, "Application accessible at {0}", baseUri.toString());

        //gracefully exit Grizzly and Eclipselink services when app is shut down
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Logger.getLogger(Bootstrap.class.getName()).info("Exiting......");
                server.shutdownNow();
                JPAFacade.closeEMF();
                Logger.getLogger(Bootstrap.class.getName()).info("REST and Persistence services stopped");
            }
        }));
        server.start();

    }

    private static final String PERSISTENCE_UNIT_NAME = "mongo-ogm";

    static void bootstrapJPA(String puName, Map<String, String> props) {

        JPAFacade.bootstrapEMF(puName, props);
        Logger.getLogger(Bootstrap.class.getName()).info("EMF bootstrapped");

    }

    public static void main(String[] args) throws IOException {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.ogm.datastore.host", System.getenv().getOrDefault("MONGO_HOST", "192.168.99.100"));
        props.put("hibernate.ogm.datastore.port", System.getenv().getOrDefault("MONGO_PORT", "27017"));
        bootstrapREST();
        bootstrapJPA(PERSISTENCE_UNIT_NAME, props);

    }
}
