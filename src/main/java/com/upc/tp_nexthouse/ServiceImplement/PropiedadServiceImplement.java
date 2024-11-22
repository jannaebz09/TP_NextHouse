package com.upc.tp_nexthouse.ServiceImplement;

import com.upc.tp_nexthouse.DTO.Query2DTO;
import com.upc.tp_nexthouse.DTO.Query4DTO;
import com.upc.tp_nexthouse.DTO.Query6DTO;
import com.upc.tp_nexthouse.DTO.Query7DTO;
import com.upc.tp_nexthouse.Entities.Propiedad;
import com.upc.tp_nexthouse.repositories.PropiedadRepository;
import com.upc.tp_nexthouse.serviceInterface.PropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PropiedadServiceImplement implements PropiedadService {
    @Autowired
    private PropiedadRepository propiedadRepository;
    @Transactional
    @Override
    public Propiedad savedPropiedad(Propiedad propiedad) {
        return propiedadRepository.save(propiedad);
    }

    @Override
    public void eliminarPropiedad(Long id) {
        if(propiedadRepository.existsById(id)) {
            propiedadRepository.deleteById(id);
        }
    }

    @Override
    public Propiedad modificarPropiedad(Propiedad propiedad) {
        if(propiedadRepository.existsById(propiedad.getIdPropiedad())){
            return propiedadRepository.save(propiedad);
        }
        return null;
    }

    @Override
    public List<Propiedad> list() {
        return propiedadRepository.findAll();
    }

    @Override
    public List<Query2DTO> query2() {
        return propiedadRepository.query2();
    }

    @Override
    public List<Query4DTO> query4() {
        return propiedadRepository.query4();
    }

    @Override
    public Propiedad buscarPorId(long id) {
        if (propiedadRepository.findById(id).isPresent()){
            return propiedadRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Propiedad> buscarPropiedadesPorTipo(String tipo) {
        return propiedadRepository.findByTipo(tipo);
    }

    @Override
    public List<Query6DTO> query6(LocalDate startDate, LocalDate endDate) {
        return propiedadRepository.query6(startDate, endDate);
    }

    @Override
    public List<Query7DTO> query7(String ciudad) {
        return propiedadRepository.query7(ciudad);
    }
}
