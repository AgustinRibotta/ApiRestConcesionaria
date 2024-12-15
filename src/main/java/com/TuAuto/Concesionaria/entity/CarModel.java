package com.TuAuto.Concesionaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Not null")
    private String marca;
    @NotNull(message = "Not null")
    private String model;
    @NotNull(message = "Not null")
    private String motor;
    @NotNull(message = "Not null")
    private String color;
    @NotNull(message = "Not null")
    @Min(value = 3, message = "The number of doors must be 3 or 5")
    @Max(value = 5, message = "The number of doors must be 3 or 5")
    private Integer cantPuertas;
    @NotNull(message = "Not null")
    private Integer anio;

    public CarModel() {
    }

    public CarModel(Long id,String marca, String model, String motor, String color, Integer cantPuertas, Integer anio) {
        this.id = id;
        this.marca = marca;
        this.model = model;
        this.motor = motor;
        this.color = color;
        this.cantPuertas = cantPuertas;
        this.anio = anio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCantPuertas() {
        return cantPuertas;
    }

    public void setCantPuertas(Integer cantPuertas) {
        this.cantPuertas = cantPuertas;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
}
