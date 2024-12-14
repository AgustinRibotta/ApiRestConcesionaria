package com.TuAuto.Concesionaria.repository;

import com.TuAuto.Concesionaria.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarModel, Long> {
}
