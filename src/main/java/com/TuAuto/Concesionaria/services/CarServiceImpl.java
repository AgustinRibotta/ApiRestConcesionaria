package com.TuAuto.Concesionaria.services;

import com.TuAuto.Concesionaria.entity.CarModel;
import com.TuAuto.Concesionaria.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarServiceImpl implements CarService {

        @Autowired
        private CarRepository carRepository;

        @Override
        public List<CarModel> getCars() {
                return carRepository.findAll();
        }

        @Override
        public CarModel getCar(Long id) {
                return carRepository.findById(id).orElse(null);
        }

        @Override
        public CarModel postCar(CarModel carModel) {
                return carRepository.save(carModel);
        }

        @Override
        public CarModel putCar(CarModel carModel) {
                return null;
        }
}
