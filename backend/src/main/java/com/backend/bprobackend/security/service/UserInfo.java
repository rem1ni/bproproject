package com.backend.bprobackend.security.service;

import com.backend.bprobackend.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserInfo {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private Double account;
    private Double minutes;



    private  String contracts;
    private Integer contracts_id;
    private Double contracts_sum;
    public static double function1(Double contract_sum,Double minutes){ //идентификатор доступа, функция является статичной, тип возвращаемого значения, имя функции без параметров
        double a = contract_sum;  //создаём переменную со значением
        double b = minutes;
        return a*b;  //возвращаем значение при вызове данной функции
    }
    private Double fac;
    public UserInfo(Long id, String username, Double account, Double minutes,Integer contracts_id, String contracts, Double contracts_sum,
                           Double fac) {
        this.id = id;
        this.username = username;
        this.account=account;
        this.minutes=minutes;
        this.contracts_id=contracts_id;
        this.contracts=contracts;
        this.contracts_sum=contracts_sum;
        this.fac=fac;
    }

    public static UserInfo build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());


        return new UserInfo(
                user.getId(),
                user.getUsername(),
                user.getAccount(),
                user.getMinutes(),
                user.getContract().getId(),
                user.getContract().getName(),
                user.getContract().getSum(),
                function1(user.getContract().getSum(),user.getMinutes()));
    }


    public Double getFac() {
        return fac;
    }

    public Long getId() {
        return id;
    }



    public String getUsername() {
        return username;
    }




    public boolean isEnabled() {
        return true;
    }

    public Double getAccount() {
        return account;
    }

    public Double getMinutes() {
        return minutes;
    }

    public String getContracts() {
        return contracts;
    }

    public Double getContracts_sum() {
        return contracts_sum;
    }

    public Integer getContracts_id() {
        return contracts_id;
    }

    public void setContracts_id(Integer contracts_id) {
        this.contracts_id = contracts_id;
    }

}
