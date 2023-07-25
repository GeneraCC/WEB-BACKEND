package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generacc.backend.calidad.backendcalidad.model.entity.MotivoRechazo;
import com.generacc.backend.calidad.backendcalidad.repositories.MotivoRechazoRepository;

@Service
public class MotivoRechazoImpl implements MotivoRechazoService {

    @Autowired
    private MotivoRechazoRepository repository;

    @Transactional(readOnly = true)
    public List<MotivoRechazo> findAll() { 
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<MotivoRechazo> findByActivo(){
        return repository.findByActivo(true);
    }
}
