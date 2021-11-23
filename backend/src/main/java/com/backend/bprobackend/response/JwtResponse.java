package com.backend.bprobackend.response;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public class JwtResponse {
    private String token;
    private String type ="Bearer";
    private Long id;
    private Double account;
    private Double minutes;
    private String username;
    private List<String> roles;
    private String contract;
    private Integer contract_id;
    private Double contract_sum;
    private Double fac;

    public JwtResponse(String accessToken, Long id, String username, Double account, Double minutes, Integer contract_id, String contract, Double contract_sum, List<String> roles, Double fac) {
        this.token = accessToken;
        this.id = id;
        this.account=account;
        this.minutes=minutes;
        this.username = username;
        this.contract_id=contract_id;
        this.contract=contract;
        this.contract_sum=contract_sum;
        this.roles = roles;
        this.fac=fac;
    }


    public Double getFac() {
        return fac;
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

    public Double getContract_sum() {
        return contract_sum;
    }

    public void setContract_sum(Double contract_sum) {
        this.contract_sum = contract_sum;
    }

    public Double getMinutes() {
        return minutes;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }
}