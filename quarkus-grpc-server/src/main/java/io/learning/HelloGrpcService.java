package io.learning;

import io.quarkus.grpc.GrpcService;

import io.learning.HelloGrpc;
import io.learning.HelloReply;
import io.learning.HelloRequest;

import io.smallrye.mutiny.Uni;

@GrpcService
public class HelloGrpcService implements HelloGrpc {

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().item("Hello " + request.getName() + "!")
                .map(msg -> HelloReply.newBuilder().setMessage(msg).build());
    }

}
