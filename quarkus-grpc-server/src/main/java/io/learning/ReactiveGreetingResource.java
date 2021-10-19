package io.learning;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import io.learning.HelloReply;
import io.learning.HelloGrpc;
import io.learning.HelloRequest;

import java.util.Random;

@Path("/hello")
public class ReactiveGreetingResource {

    @GrpcClient("helloService")
    HelloGrpc helloGrpc;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy Reactive";
    }

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() { return "some random number: " + new Random().nextLong(); }

    @GET
    @Path("greetings/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<HelloReply> sayHello(@PathParam("name") String name) {
        return helloGrpc.sayHello(HelloRequest.newBuilder().setName(name).build());
    }
}