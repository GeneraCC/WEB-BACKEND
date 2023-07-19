package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generacc.backend.calidad.backendcalidad.model.dto.LocalidadDto;
import com.generacc.backend.calidad.backendcalidad.repositories.LocalidadRepository;

@Service
public class LocalidadImpl implements LocalidadService{
    
    @Autowired
    public LocalidadRepository repo;

    @Override
    public List<LocalidadDto> findJoinedData() {
        var s = repo.findJoinedData();
        return s;
    }
}
