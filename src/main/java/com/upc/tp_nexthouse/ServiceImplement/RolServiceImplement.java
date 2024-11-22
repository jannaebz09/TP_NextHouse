package com.upc.tp_nexthouse.ServiceImplement;

import com.upc.tp_nexthouse.Entities.Role;
import com.upc.tp_nexthouse.repositories.RolRepository;
import com.upc.tp_nexthouse.serviceInterface.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImplement implements RolService {
    @Autowired
    private RolRepository rolRepository;
    @Transactional
    @Override
    public Role savedRol(Role rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void eliminarRol(Long id) {
        if(rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        }
    }

    @Override
    public Role modificarRol(Role rol) {
        if(rolRepository.existsById(rol.getIdRol())){
            return rolRepository.save(rol);
        }
        return null;
    }

    @Override
    public List<Role> list() {
        return rolRepository.findAll();
    }

    @Override
    public Role buscarPorId(long id) {
        if (rolRepository.findById(id).isPresent()){
            return rolRepository.findById(id).get();
        }
        return null;
    }
}
