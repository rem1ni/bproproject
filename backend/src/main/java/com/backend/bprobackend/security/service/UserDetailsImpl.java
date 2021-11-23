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

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private Double account;
    private Double minutes;
    @JsonIgnore
    private String password;


    private Collection<? extends GrantedAuthority> authorities;
    private  String contracts;
    private Integer contracts_id;
    private Double contracts_sum;
    public static double function1(Double contract_sum,Double minutes){ //идентификатор доступа, функция является статичной, тип возвращаемого значения, имя функции без параметров
        double a = contract_sum;  //создаём переменную со значением
        double b = minutes;
        return a*b;  //возвращаем значение при вызове данной функции
    }
    private Double fac;
    public UserDetailsImpl(Long id, String username, String password, Double account, Double minutes,Integer contracts_id, String contracts, Double contracts_sum,
                           Collection<? extends GrantedAuthority> authorities,Double fac) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.account=account;
        this.minutes=minutes;
        this.contracts_id=contracts_id;
        this.contracts=contracts;
        this.contracts_sum=contracts_sum;
        this.authorities = authorities;
        this.fac=fac;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());


        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAccount(),
                user.getMinutes(),
                user.getContract().getId(),
                user.getContract().getName(),
                user.getContract().getSum(),
                authorities,
                function1(user.getContract().getSum(),user.getMinutes()));
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Double getFac() {
        return fac;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}

