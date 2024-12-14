package com.TuAuto.Concesionaria.services;

import com.TuAuto.Concesionaria.entity.CarModel;
import com.TuAuto.Concesionaria.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.smartcardio.CardNotPresentException;
import java.util.List;

@Service
public class CarService {

        @Autowired
        private  CarRepository carRepository;

        public List<CarModel> getCars() {
                return carRepository.findAll();
        }

        public CarModel getCar(Long id) {
                return carRepository.findById(id).orElse(null);
        }

}
