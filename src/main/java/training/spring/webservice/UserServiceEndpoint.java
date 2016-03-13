package training.spring.webservice;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import training.spring.webservice.beans.CreateUserRequest;
import training.spring.webservice.beans.CreateUserResponse;

@Endpoint
public class UserServiceEndpoint {
    public final static String NAMESPACE = "http://localhost:8080/cinema";
    public final static String CREATE_USER_REQUEST = "CreateUserRequest";


    @PayloadRoot(localPart = CREATE_USER_REQUEST, namespace = NAMESPACE)
    public @ResponsePayload
    CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        response.setId(100);
        return response;
    }
}
