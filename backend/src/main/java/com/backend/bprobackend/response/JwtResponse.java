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
    private String contract;
    private Long contract_sum;

    public JwtResponse(String accessToken, Long id, String username,Long account,String contract,Long contract_sum, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.account=account;
        this.username = username;
        this.contract=contract;
        this.contract_sum=contract_sum;
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

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Long getContract_sum() {
        return contract_sum;
    }

    public void setContract_sum(Long contract_sum) {
        this.contract_sum = contract_sum;
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