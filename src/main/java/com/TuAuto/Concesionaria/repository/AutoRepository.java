package com.TuAuto.Concesionaria.repository;

import com.TuAuto.Concesionaria.entity.AutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<AutoModel, Long> {
}
