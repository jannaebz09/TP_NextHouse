package com.upc.tp_nexthouse.repositories;

import com.upc.tp_nexthouse.DTO.Query8DTO;
import com.upc.tp_nexthouse.Entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @Query("select new com.upc.tp_nexthouse.DTO.Query8DTO(count(c), c.usuario.nombreCompleto) " +
           "from Comentario c " +
           "where c.calificacion > '3'"+
            "group by c.usuario.nombreCompleto")
    List<Query8DTO> query8();
}
