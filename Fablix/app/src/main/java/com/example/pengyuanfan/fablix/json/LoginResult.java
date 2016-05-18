package com.example.pengyuanfan.fablix.json;

/**
 * Created by pengyuanfan on 5/18/2016.
 */
public class LoginResult {
    private boolean login_result;
    private String login_name;

    public boolean isLogin_result() {
        return login_result;
    }

    public void setLogin_result(boolean login_result) {
        this.login_result = login_result;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
}
