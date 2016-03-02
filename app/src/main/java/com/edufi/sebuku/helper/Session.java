package com.edufi.sebuku.helper;

/**
 * Created by habibfikri on 3/3/2016.
 */
public class Session {

    private int user_id;
    private String username;
    private String token;
    private boolean loggedIn;

    public Session(int user_id, String username, String token, boolean isLoggedIn) {
        this.user_id = user_id;
        this.username = username;
        this.token = token;
        this.loggedIn = isLoggedIn;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.loggedIn = isLoggedIn;
    }
}
