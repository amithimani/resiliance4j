# Resilience4j Microservices Example

This project is an example of two Spring Boot microservices that demonstrate the use of Resilience4j for implementing the Circuit Breaker pattern in a distributed system. The first microservice, called "Caller", has a REST endpoint that invokes the second microservice, called "Supplier", through a Resilience4j Circuit Breaker.

## Getting Started

To run this project, you will need to have Java 11 or higher installed on your machine. You can clone this repository and build the project using the following commands:

```
git clone https://github.com/your-username/resilience4j-microservices-example.git
cd resilience4j-microservices-example
mvn clean package
```

This will build the project and generate the executable JAR files for both the Caller and Supplier microservices.

## Running the Microservices

To run the Caller microservice, use the following command, or run using mvn or your favourite IDE:

```
java -jar caller/target/caller-1.0.0.jar
```

To run the Supplier microservice, use the following command, or run using mvn or your favourite IDE:

```
java -jar supplier/target/supplier-1.0.0.jar
```

Both microservices will start up on their respective ports and will be ready to receive HTTP requests.

## Endpoints

The Caller microservice has a single GET endpoint at `/caller` that invokes the Supplier microservice through a Resilience4j Circuit Breaker. The Supplier microservice has a single GET endpoint at `/supplier` that returns a simple message.

## Resilience4j Circuit Breaker

The Caller microservice has a Resilience4j Circuit Breaker configured for the `/caller` endpoint. If the Supplier microservice fails to respond, the Circuit Breaker will trip and return a fallback response instead of propagating the error back to the client.

The Circuit Breaker configuration can be found in the `application.yml` file for the Caller microservice:

```yaml
resilience4j:
  circuitbreaker:
    instances:
      callerService:
        registerHealthIndicator: true
        eventConsumerBufferSize: 10
        failureRateThreshold: 10
        minimumNumberOfCalls: 5
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowSize: 10
        slidingWindowType: COUNT_BASED
```

## Conclusion

This project demonstrates the use of Resilience4j for implementing the Circuit Breaker pattern in a distributed system. The Caller microservice is protected by a Circuit Breaker that prevents cascading failures when invoking the Supplier microservice. With this pattern, our system is more resilient and able to handle failures gracefully.
