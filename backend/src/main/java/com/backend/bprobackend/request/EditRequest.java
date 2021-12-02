package com.backend.bprobackend.request;

public class EditRequest {
    private Integer idcontract;
    private String title;
    private Double body;

    public Integer getIdcontract() {
        return idcontract;
    }

    public void setIdcontract(Integer idcontract) {
        this.idcontract = idcontract;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getBody() {
        return body;
    }

    public void setBody(Double body) {
        this.body = body;
    }
}
