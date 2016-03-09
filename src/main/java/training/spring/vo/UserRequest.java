package training.spring.vo;

public class UserRequest {
    private String name;
    private String email;
    private String password;
    private int money;

    public UserRequest() {
    }

    public UserRequest(String name, String email, String password, int money) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.money = money;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
