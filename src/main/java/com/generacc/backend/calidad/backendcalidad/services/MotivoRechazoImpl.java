package com.generacc.backend.calidad.backendcalidad.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generacc.backend.calidad.backendcalidad.model.entity.MotivoRechazo;
import com.generacc.backend.calidad.backendcalidad.repositories.MotivoRechazoRepository;

@Service
public class MotivoRechazoImpl implements MotivoRechazoService {

    private MotivoRechazoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<MotivoRechazo> findAll() { 
        return repository.findAll();
    }    
}
