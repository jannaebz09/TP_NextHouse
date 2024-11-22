package com.upc.tp_nexthouse.serviceInterface;


import com.upc.tp_nexthouse.DTO.Query5DTO;
import com.upc.tp_nexthouse.Entities.Mensaje;
import com.upc.tp_nexthouse.Entities.Pago;
import com.upc.tp_nexthouse.Entities.Propiedad;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PagoService {
    public Pago savedPago(Pago pago);
    public void eliminarPago(Long  id);
    public Pago modificarPago(Pago pago);
    public List<Pago> list();

    public Pago buscarPorId(long id);
    List<Query5DTO> query5(LocalDate startDate, LocalDate endDate);

}
