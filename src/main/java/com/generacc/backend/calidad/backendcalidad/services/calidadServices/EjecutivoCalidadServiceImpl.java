package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.generacc.backend.calidad.backendcalidad.model.entity.Usuario;
import com.generacc.backend.calidad.backendcalidad.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class EjecutivoCalidadServiceImpl implements EjecutivoCalidadService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<Map<String, Object>> getVentasPorAuditar(Long id,int numeroPagina, int tamano){
         try {
            Query query = entityManager.createNativeQuery("EXEC CALIDAD_WEB.dbo.pa_ListaVentasAEvaluarAsignadas :idusuario");
            query.setParameter("idusuario", id);
            var nativeQuery = (NativeQueryImpl) query;
            nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> resultList = nativeQuery.getResultList();
            int totalElements = resultList.size();
            int comienzo = Math.max(0, (numeroPagina - 1) * tamano);
            int finalLista = Math.min(comienzo + tamano, totalElements);
            List<Map<String, Object>> subList = resultList.subList(comienzo, finalLista);
            return new PageImpl<>(subList, PageRequest.of(numeroPagina - 1, tamano), totalElements);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public Long findUserIdByUsername(String username){
        Optional<Usuario> user = userRepository.findBynombreusuario(username);
        if(user != null){
            return user.get().getIdusuario();
        }
        return null;

    }
    
    
       
}
