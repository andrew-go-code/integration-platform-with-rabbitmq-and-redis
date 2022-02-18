# Integration platform with Rabbit MQ and Redis
This demo project shows how systems integration can be implemented with queue middleware and cache.

### Structure

#### mq-request-app
It sends requests to the "request" queue with the specified correlationId.
Each replica of this app listens to the "response" queue and stores messages in the cache. Message id is correlationId.
After a request is sent, the app tries to find the response message in the cache by the correlationId.

#### mq-response-app
It listens to the "request" queue and sends the result to the "response" queue with relational correlationId.

### gutling-test
It emulates requests for mq-request-app