package com.backend.bprobackend.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class PayDetailService {

    private static final long serialVersionUID = 1L;

    private Double minutes;
    private Double contracts_sum;
    private Double summary;

    public PayDetailService(Double summary) {

    }

    public PayDetailService PayDetailService (Long id) {
        summary=3D;



        return new PayDetailService(
                summary);
    }

    public Long getId() {
        return id;
    }




    public Double getAccount() {
        return account;
    }

    public Double getMinutes() {
        return minutes;
    }


    public Double getContracts_sum() {
        return contracts_sum;
    }


}
