package com.generacc.backend.calidad.backendcalidad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generacc.backend.calidad.backendcalidad.model.entity.Comuna;
import com.generacc.backend.calidad.backendcalidad.model.dto.LocalidadDto;

public interface LocalidadRepository extends JpaRepository<Comuna, Long> {

    @Query("SELECT new com.generacc.backend.calidad.backendcalidad.model.dto.LocalidadDto(c.idComuna, c.nombreComuna, ci.idCiudad, ci.nombreCiudad, r.idRegion, r.nombreRegion) " +
           "FROM Comuna c " +
           "JOIN c.ciudad ci " +
           "JOIN ci.region r " +
           "WHERE c.activo = true")
    List<LocalidadDto> findJoinedData();
}
