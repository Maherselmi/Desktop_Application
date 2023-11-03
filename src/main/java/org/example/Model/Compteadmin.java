package org.example.Model;


public class Compteadmin{
    private   String Login;

    private String password;

    public Compteadmin(String login, String password) {
        Login = login;
        this.password = password;
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
