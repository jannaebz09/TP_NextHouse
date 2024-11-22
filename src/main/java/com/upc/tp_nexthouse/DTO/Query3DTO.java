package com.upc.tp_nexthouse.DTO;

import com.upc.tp_nexthouse.Entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query3DTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double precioMensual;
    private String estado;
    private Usuario usuario;

}
