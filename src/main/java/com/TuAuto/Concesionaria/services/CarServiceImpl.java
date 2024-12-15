package com.TuAuto.Concesionaria.services;

import com.TuAuto.Concesionaria.entity.CarModel;
import com.TuAuto.Concesionaria.exceptions.ResourceNotFoundException;
import com.TuAuto.Concesionaria.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "service.products", havingValue = "car")
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
                return carRepository.save(carModel);
        }

        @Override
        public CarModel patchCar(Long id, Map<String, Object> updates) {
                // Buscar el coche por ID utilizando el repositorio
                Optional<CarModel> carOptional = carRepository.findById(id);

                if (carOptional.isEmpty()) {
                        throw new ResourceNotFoundException("Car not found with id " + id);
                }

                CarModel carToUpdate = carOptional.get();

                // Actualizar los campos proporcionados
                updates.forEach((key, value) -> {
                        switch (key) {
                                case "marca":
                                        carToUpdate.setMarca((String) value);
                                        break;
                                case "motor":
                                        carToUpdate.setMotor((String) value);
                                        break;
                                case "model":
                                        carToUpdate.setModel((String) value);
                                        break;
                                case "color":
                                        carToUpdate.setColor((String) value);
                                        break;
                                case "cantPuertas":
                                        carToUpdate.setCantPuertas((Integer) value);
                                        break;
                                case "anio":
                                        carToUpdate.setAnio((Integer) value);
                                        break;
                                default:
                                        throw new IllegalArgumentException("Field " + key + " is not updatable");
                        }
                });

                // Guardar los cambios en la base de datos
                return carRepository.save(carToUpdate);
        }

        @Override
        public void deleteCar(Long id) {
                 carRepository.deleteById(id);
        }
}
