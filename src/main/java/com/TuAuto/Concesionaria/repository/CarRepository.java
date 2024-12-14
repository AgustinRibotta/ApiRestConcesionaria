package com.TuAuto.Concesionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TuAuto.Concesionaria.entity.CarModel;

public interface CarRepository extends JpaRepository<CarModel, Long> {
}
