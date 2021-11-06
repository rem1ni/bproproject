package com.backend.bprobackend.response;

import com.backend.bprobackend.model.Contract;

import java.util.List;


public class JwtResponse {
    private String token;
    private String type ="Bearer";
    private Long id;
    private Long account;
    private String username;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username,Long account, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.account=account;
        this.username = username;
        this.roles = roles;
    }



    public String getAccessToken(){
        return token;
    }
    public void setAccessToken(String accessToken) {
        this.token=accessToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}