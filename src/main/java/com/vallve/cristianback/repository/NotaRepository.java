package com.vallve.cristianback.repository;

import entities.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface NotaRepository extends JpaRepository<Nota,Long> {

    @Query("SELECT nota FROM Nota nota")
    List<Nota> findAll();
}
