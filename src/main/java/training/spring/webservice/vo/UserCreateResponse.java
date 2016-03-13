package training.spring.webservice.vo;

public class UserCreateResponse {
    private long id;

    public UserCreateResponse() {
    }

    public UserCreateResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
