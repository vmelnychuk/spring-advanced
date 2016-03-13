package training.spring.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import training.spring.entity.User;
import training.spring.service.UserService;
import training.spring.webservice.beans.*;

@Endpoint
public class UserServiceEndpoint {
    public final static String NAMESPACE = "http://localhost:8080/cinema";
    public final static String CREATE_USER_REQUEST = "CreateUserRequest";
    private final static String GET_USER_INFO_REQUEST = "GetUserInfoRequest";
    private final static String DELETE_USER_REQUEST = "DeleteUserRequest";
    private final static int DEFAULT_AMOUNT = 100;

    @Autowired
    private UserService userService;

    @PayloadRoot(localPart = CREATE_USER_REQUEST, namespace = NAMESPACE)
    public @ResponsePayload
    CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user = userService.register(user);
        CreateUserResponse response = new CreateUserResponse();
        response.setId(user.getId());
        return response;
    }

    @PayloadRoot(localPart = GET_USER_INFO_REQUEST, namespace = NAMESPACE)
    public @ResponsePayload
    GetUserInfoResponse getUserInfo(@RequestPayload GetUserInfoRequest request) {
        long id = request.getId();
        User user = userService.getById(id);

        GetUserInfoResponse response = new GetUserInfoResponse();
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setPassword(user.getPassword());
        response.setAmount(DEFAULT_AMOUNT);
        return response;
    }

    @PayloadRoot(localPart = DELETE_USER_REQUEST, namespace = NAMESPACE)
    public @ResponsePayload
    DeleteUserResponse deleteUser(@RequestPayload GetUserInfoRequest request) {
        long id = request.getId();
        userService.delete(id);
        DeleteUserResponse response = new DeleteUserResponse();
        response.setStatus("ok");
        return response;
    }
}
