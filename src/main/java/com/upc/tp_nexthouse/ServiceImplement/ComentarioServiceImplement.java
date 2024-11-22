package com.upc.tp_nexthouse.ServiceImplement;

import com.upc.tp_nexthouse.DTO.Query8DTO;
import com.upc.tp_nexthouse.Entities.Comentario;
import com.upc.tp_nexthouse.repositories.ComentarioRepository;
import com.upc.tp_nexthouse.serviceInterface.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComentarioServiceImplement implements ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Transactional
    @Override
    public Comentario savedComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public void eliminarComentario(Long id) {
        if(comentarioRepository.existsById(id)) {
            comentarioRepository.deleteById(id);
        }
    }

    @Override
    public Comentario modificarComentario(Comentario comentario) {
        if(comentarioRepository.existsById(comentario.getIdComentario())){
            return comentarioRepository.save(comentario);
        }
        return null;
    }

    @Override
    public List<Comentario> list() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario buscarPorId(long id) {
        if (comentarioRepository.findById(id).isPresent()){
            return comentarioRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Query8DTO> query8() {
        return comentarioRepository.query8();
    }
}
