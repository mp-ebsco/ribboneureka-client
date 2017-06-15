# Overview
The `platform.training.ribboneureka-client` project demonstrates a service that discovers and sends requests to a back end service identified by the Eureka VIP (virtual IP) of `platform.training.ribboneureka.searchservice`

It is a Spring Boot microservice that uses Spring Cloud Netflix for Netflix OSS Ribbon client side load balancing. 

The Ribbon client interjects a layer on top of Spring's RestTemplate. This layer is responsible for interaction with a Eureka server to fetch and update a server list corresponding to the VIP. You can think of the VIP as very similar to a DNS name  with Eureka providing IP address information of registered services.  
			  

## How to Build

	$ gradle clean build
	
## How to run

See instructions in `platform.training.ribboneureka-server` for starting the Eureka `platform.shared.discoveryservice` and the service we're going to access through Eureka.

Ensure you've started both the Eureka `platform.shared.discoveryservice` and `platform.training.ribboneureka-server` services you've verified a successful registration to Eureka.

### Start 'ribboneureka-client' Service

    $ gradle bootRun
    
## How to access

The service is available on port 8091.

http://localhost:8091/search/mysearch

The response simply echos back the last token of the URL in a JSON body (the last token can be anything).

    {
        response: "mysearch-result"
    }

## Simulating Hystrix Circuit Breaker Failure

The project also uses a Hystrix Command to ensure fault isolation in the call to `platform.training.ribboneureka-server`. This approach should be used for any network call a microservice makes such as to databases or other microservices.

You can simulate a failure in the Hystrix command by accessing: http://localhost:8091/search/boom. Notice how the fallback response is received.

To bring up the Hystrix Dashboard:
* Go to: http://localhost:8091/hystrix
* In the top field, enter: http://localhost:8091/hystrix.stream
* Click the ‘Monitor Stream’ button. 

You will see summary statistics of how the Hystrix command is operating, showing successful calls, failures and an indication of the state of the circuit (e.g. normal/closed, failed/open).
 
As you invoke the service with failures at http://localhost:8091/search/boom you will eventually see the circuit “Open” in the Hystrix Dashboard. This means Hystrix will no longer try to execute the Hystrix Command (make the remote call). Instead it will go directly to the fallback method. The reason to do this is to isolate failures and prevent them from cascading and affecting other endpoints in the same or other microservices.
