package training.spring.webservice;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import training.spring.webservice.vo.UserCreateRequest;
import training.spring.webservice.vo.UserCreateResponse;

@Endpoint
public class UserWebServiceEndpoint {
    private static final String TARGET_NAMESPACE = "http://localhost:8080/cinema";

//    @Autowired
//    UserWebService userWebService;

    @PayloadRoot(localPart = "userCreate", namespace = TARGET_NAMESPACE)
    @ResponsePayload
    public UserCreateResponse userCreate(@RequestPayload UserCreateRequest request) {
        int i = 222;
        return new UserCreateResponse(200);
    }

//    public void setUserWebService(UserWebService userWebService) {
//        this.userWebService = userWebService;
//    }
}
