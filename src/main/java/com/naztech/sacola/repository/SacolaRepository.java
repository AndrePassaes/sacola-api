package com.naztech.sacola.repository;

import com.naztech.sacola.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> {
}
