package training.spring.vo;

import training.spring.entity.User;

public class UserResponse {
    private String name;
    private String email;
    private Long id;

    public UserResponse() {
    }

    public UserResponse(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public UserResponse(User user) {
        name = user.getName();
        email = user.getEmail();
        id = user.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
