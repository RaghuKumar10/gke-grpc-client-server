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

@Path("greetings")
public class GreetingsResource {

    @GrpcClient("helloService")
    HelloGrpc helloService;

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<io.learning.HelloReply> sayHello(@PathParam("name") String name) {
        return helloService.sayHello(io.learning.HelloRequest.newBuilder().setName(name).build());
    }

}
