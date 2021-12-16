package com.example.desktop;

public class User {
    private Long id;
    private String username;
    private String password;
    private Double account;
    private Double minutes;
    private String nametariff;
    private Double sum;

    public User(Long id,String username,Double minutes,Double account,String nametariff,Double sum){

        this.id=id;
        this.account=account;
        this.username=username;
        this.minutes=minutes;
        this.nametariff=nametariff;
        this.sum=sum;
    }


    public String getNametariff() {
        return nametariff;
    }

    public void setNametariff(String nametariff) {
        this.nametariff = nametariff;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public Double getMinutes() {
        return minutes;
    }

    public void setMinutes(Double minutes) {
        this.minutes = minutes;
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
