package com.TuAuto.Concesionaria.controllers;

import com.TuAuto.Concesionaria.entity.CarModel;
import com.TuAuto.Concesionaria.exceptions.ResourceNotFoundException;
import com.TuAuto.Concesionaria.services.CarService;
import com.TuAuto.Concesionaria.services.CarServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carServiceImpl;

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
    public ResponseEntity<?> postCar( @RequestBody @Valid CarModel carModel) {
        CarModel savedCar = carServiceImpl.postCar(carModel);

        // Construimos la URI con el ID generado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCar(@PathVariable Long id, @RequestBody @Valid CarModel carModel) {
        CarModel carToUpdate = carServiceImpl.getCar(id);

        if (carToUpdate == null) {
            throw new ResourceNotFoundException("Car not found with id " + id);
        }

        // Si el coche existe, actualizamos sus campos
        carToUpdate.setMarca(carModel.getMarca());
        carToUpdate.setMotor(carModel.getMotor());
        carToUpdate.setModel(carModel.getModel());
        carToUpdate.setColor(carModel.getColor());
        carToUpdate.setCantPuertas(carModel.getCantPuertas());
        carToUpdate.setAnio(carModel.getAnio());

        CarModel updatedCar = carServiceImpl.putCar(carToUpdate);

        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        CarModel car = carServiceImpl.getCar(id);
        if (car == null) {
             throw new ResourceNotFoundException("Car not found with id " + id);
        }
        carServiceImpl.deleteCar(id);
        Map<String, Object> response = Map.of(
                "message", "Car has been deleted successfully",
                "id", id
        );

        return ResponseEntity.ok(response);
    }


}