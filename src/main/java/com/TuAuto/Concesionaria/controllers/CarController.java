package com.TuAuto.Concesionaria.controllers;


import com.TuAuto.Concesionaria.entity.CarModel;
import com.TuAuto.Concesionaria.services.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarServiceImpl carServiceImpl;

    @GetMapping
    public ResponseEntity<List<CarModel>> getCars () {
        return ResponseEntity.ok(carServiceImpl.getCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCar(@PathVariable Long id) {
        CarModel car = carServiceImpl.getCar(id);
        // Si el coche no es encontrado, devolvemos un 404
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found with id " + id);
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<?> postCar(@RequestBody CarModel carModel) {
        CarModel savedCar = carServiceImpl.postCar(carModel);

        // Construimos la URI con el ID generado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedCar);
    }
}