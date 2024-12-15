package com.TuAuto.Concesionaria.controllers;


import com.TuAuto.Concesionaria.entity.CarModel;
import com.TuAuto.Concesionaria.exceptions.ResourceNotFoundException;
import com.TuAuto.Concesionaria.services.CarServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")

public class CarController {

    private final CarServiceImpl carServiceImpl;

    // Constructor para inyectar el servicio
    public CarController(CarServiceImpl carServiceImpl) {
        this.carServiceImpl = carServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<CarModel>> getCars() {
        List<CarModel> cars = carServiceImpl.getCars();

        if (cars == null || cars.isEmpty()) {
            // Si no hay coches, lanzar la excepción
            throw new ResourceNotFoundException("No cars found");
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCar(@PathVariable Long id) {
        CarModel car = carServiceImpl.getCar(id);
        if (car == null) {
            throw new ResourceNotFoundException("Car not found with id " + id);
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<?> postCar( @RequestBody @Valid  CarModel carModel) {
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