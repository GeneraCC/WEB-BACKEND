package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generacc.backend.calidad.backendcalidad.model.entity.ParentescoCampana;
import com.generacc.backend.calidad.backendcalidad.repositories.ParentescoRepository;

@Service
public class ParentescoImpl implements ParentescoService{
    @Autowired
    private ParentescoRepository repo;

    @Override
    public List<ParentescoCampana> findByCentroCostoAndActivo(Long centroCosto, Boolean activo) {
        return repo.findByCentroCostoAndActivo(centroCosto, activo);
    }

}
