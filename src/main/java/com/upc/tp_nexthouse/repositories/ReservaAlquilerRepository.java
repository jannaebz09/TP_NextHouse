package com.upc.tp_nexthouse.repositories;

import com.upc.tp_nexthouse.DTO.Query3DTO;
import com.upc.tp_nexthouse.DTO.ReservaAlquilerDTO;
import com.upc.tp_nexthouse.Entities.ReservaAlquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaAlquilerRepository extends JpaRepository<ReservaAlquiler,Long> {
    @Query("select new com.upc.tp_nexthouse.DTO.Query3DTO(r.fechaInicio, r.fechaFin, r.precioMensual, r.estado, r.usuario) " +
            "from ReservaAlquiler r join r.usuario u " +
            "where (r.fechaInicio <= :startDate AND r.fechaFin >= :endDate) " +
            "and r.estado = 'Disponible'")
    List<Query3DTO> query3(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
