package training.spring.webservice.vo;

public class UserCreateRequest {
    private String name;
    private String password;
    private String email;
    private int money;

    public UserCreateRequest() {
    }

    public UserCreateRequest(String name, String password, String email, int money) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
