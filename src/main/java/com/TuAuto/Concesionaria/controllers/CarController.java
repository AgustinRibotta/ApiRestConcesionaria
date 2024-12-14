package com.TuAuto.Concesionaria.controllers;


import com.TuAuto.Concesionaria.entity.CarModel;
import com.TuAuto.Concesionaria.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<CarModel>> getCars () {
        return ResponseEntity.ok(carService.getCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCar(@PathVariable Long id) {
        CarModel car = carService.getCar(id);
        // Si el coche no es encontrado, devolvemos un 404
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found with id " + id);
        }
        return ResponseEntity.ok(car);
    }
}