package com.upc.tp_nexthouse.repositories;

import com.upc.tp_nexthouse.DTO.*;
import com.upc.tp_nexthouse.Entities.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PropiedadRepository extends JpaRepository<Propiedad,Long> {
    @Query(" select new com.upc.tp_nexthouse.DTO.Query2DTO(u.nombreCompleto, p.titulo) " +
            " from Propiedad p join p.usuario u " +
            " where p.tipo = 'Casa' and p.estado = 'Disponible'" +
            " group by u.nombreCompleto, p.titulo")
    List<Query2DTO> query2();


    @Query("SELECT new com.upc.tp_nexthouse.DTO.Query4DTO(u.ciudad, p.precio) " +
            "FROM Propiedad p JOIN p.ubicacion u " +
            "WHERE p.estado = 'Alquiler'" +
            "GROUP BY u.ciudad, p.precio")
    List<Query4DTO> query4();

    @Query("SELECT new com.upc.tp_nexthouse.DTO.Query6DTO(count(p))"+
            "FROM Propiedad p " +
            "WHERE p.fechaPublicacion BETWEEN :startDate AND :endDate")
    List<Query6DTO> query6(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.upc.tp_nexthouse.DTO.Query7DTO(count(p))"+
            "FROM Propiedad p " +
            "WHERE p.ubicacion.ciudad = :ciudad")
    List<Query7DTO> query7(@Param("ciudad") String ciudad);

    List<Propiedad> findByTipo(@Param("tipo") String tipo);
}
