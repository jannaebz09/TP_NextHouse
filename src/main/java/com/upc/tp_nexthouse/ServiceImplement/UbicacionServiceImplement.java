package com.upc.tp_nexthouse.ServiceImplement;

import com.upc.tp_nexthouse.Entities.Ubicacion;

import com.upc.tp_nexthouse.repositories.UbicacionRepository;
import com.upc.tp_nexthouse.serviceInterface.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UbicacionServiceImplement implements UbicacionService {
    @Autowired
    private UbicacionRepository ubicacionRepository;
    @Transactional
    @Override
    public Ubicacion savedUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    @Override
    public void eliminarUbicacion(Long id) {
        if(ubicacionRepository.existsById(id)) {
            ubicacionRepository.deleteById(id);
        }
    }
    @Override
    public Ubicacion modificarUbicacion(Ubicacion ubicacion) {
        if(ubicacionRepository.existsById(ubicacion.getIdUbicacion())){
            return ubicacionRepository.save(ubicacion);
        }
        return null;
    }
    @Override
    public List<Ubicacion> list() {
        return ubicacionRepository.findAll();
    }

    @Override
    public Ubicacion buscarPorId(long id) {
        if (ubicacionRepository.findById(id).isPresent()){
            return ubicacionRepository.findById(id).get();
        }
        return null;
    }
}
