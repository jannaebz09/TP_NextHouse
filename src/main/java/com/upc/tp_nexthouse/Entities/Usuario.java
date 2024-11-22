package com.upc.tp_nexthouse.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String contrasena;
    private boolean enabled;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_roles_id")
    private Role rol;
}
