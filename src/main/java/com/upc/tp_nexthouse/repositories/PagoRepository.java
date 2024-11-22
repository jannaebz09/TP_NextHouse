package com.upc.tp_nexthouse.repositories;

import com.upc.tp_nexthouse.DTO.Query5DTO;
import com.upc.tp_nexthouse.Entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago,Long> {

    @Query("select new com.upc.tp_nexthouse.DTO.Query5DTO(SUM(p.monto))"+
            " from Pago p " +
            " where p.fechaTransaccion between :startDate and :endDate")
    List<Query5DTO> query5(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
