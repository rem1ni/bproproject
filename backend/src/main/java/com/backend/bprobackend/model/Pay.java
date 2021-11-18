package com.backend.bprobackend.model;

import javax.persistence.*;

@Entity
@Table(name="pays")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long iduser;
    private Double pay;
    private String time;

    public Pay(Long iduser, Double pay, String time) {
        this.iduser = iduser;
        this.pay = pay;
        this.time = time;
    }

    public Pay() {

    }

    public Long getId() {
        return id;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
