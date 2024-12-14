package com.TuAuto.Concesionaria.services;

import com.TuAuto.Concesionaria.entity.CarModel;

import java.util.List;

public interface CarService {
    List<CarModel> getCars();
    CarModel getCar(Long id);
    CarModel postCar(CarModel carModel);
    CarModel putCar (CarModel carModel);

}
