package com.TuAuto.Concesionaria.services;

import com.TuAuto.Concesionaria.entity.CarModel;

import java.util.List;
import java.util.Map;

public interface CarService {
    List<CarModel> getCars();
    CarModel getCar(Long id);
    CarModel postCar(CarModel carModel);
    CarModel putCar (CarModel carModel);
    CarModel patchCar(Long id, Map<String, Object> updates);
    void deleteCar(Long id);
}
