package io.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//@QuarkusTest
public class HelloGrpcServiceTest {

    @GrpcClient
    io.learning.HelloGrpc helloGrpc;

    @Test
    @Disabled
    public void testHello() {
        io.learning.HelloReply reply = helloGrpc
                .sayHello(io.learning.HelloRequest.newBuilder().setName("Neo").build()).await().atMost(Duration.ofSeconds(5));
        assertEquals("Hello Neo!", reply.getMessage());
    }

}
