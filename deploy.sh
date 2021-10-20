./quarkus-grpc-server/mvnw install -DskipTests=true -Dmaven.javadoc.skip=true -B -V

./quarkus-grpc-client/mvnw install -DskipTests=true -Dmaven.javadoc.skip=true -B -V

./quarkus-grpc-server/mvnw package
./quarkus-grpc-client/mvnw package

docker build -t raghukumarm/quarkus-grpc-server-jvm:latest -t raghukumarm/quarkus-grpc-server-jvm:$SHA -f ./quarkus-grpc-server/src/main/docker/Dockerfile.jvm ./quarkus-grpc-server 
docker build -t raghukumarm/quarkus-grpc-client-jvm:latest -t raghukumarm/quarkus-grpc-client-jvm:$SHA -f ./quarkus-grpc-client/src/main/docker/Dockerfile.jvm ./quarkus-grpc-client 

docker push  raghukumarm/quarkus-grpc-server-jvm:latest
docker push  raghukumarm/quarkus-grpc-client-jvm:latest

docker push  raghukumarm/quarkus-grpc-server-jvm:$SHA
docker push  raghukumarm/quarkus-grpc-client-jvm:$SHA

kubectl apply -f k8s

kubectl set image deployments/grpc-server-deployment quarkus-grpc-server=raghukumarm/quarkus-grpc-server-jvm:latest
kubectl set image deployments/grpc-client-deployment quarkus-grpc-client=raghukumarm/quarkus-grpc-client-jvm:latest
