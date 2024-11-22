package com.upc.tp_nexthouse.serviceInterface;


import com.upc.tp_nexthouse.DTO.*;
import com.upc.tp_nexthouse.Entities.Mensaje;
import com.upc.tp_nexthouse.Entities.Propiedad;
import com.upc.tp_nexthouse.Entities.ReservaAlquiler;

import java.time.LocalDate;
import java.util.List;

public interface PropiedadService {
    public Propiedad savedPropiedad(Propiedad propiedad);
    public void eliminarPropiedad(Long  id);
    public Propiedad modificarPropiedad(Propiedad propiedad);
    public List<Propiedad> list();
    public  List<Query2DTO>query2();
    public List<Query4DTO>query4();

    public Propiedad buscarPorId(long id);

    public List<Propiedad> buscarPropiedadesPorTipo(String tipo);

    List<Query6DTO> query6(LocalDate startDate, LocalDate endDate);

    public List<Query7DTO> query7(String ciudad);
}
