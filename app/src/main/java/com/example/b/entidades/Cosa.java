package com.example.b.entidades;

import java.io.Serializable;

public class Cosa implements Serializable {
    private Integer id;
    private String cosa;
    private String cantidad;

    public Cosa(Integer id, String cosa, String cantidad) {
        this.id = id;
        this.cosa = cosa;
        this.cantidad = cantidad;
    }

    public Cosa(){

    }

    public Integer getId() {
        return id;
    }

    public Integer setId(Integer id) {
        this.id = id;
        return id;
    }

    public String getCosa() {
        return cosa;
    }

    public void setCosa(String cosa) {
        this.cosa = cosa;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
