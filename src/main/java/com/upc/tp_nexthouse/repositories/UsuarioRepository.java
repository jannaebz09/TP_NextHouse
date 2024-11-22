package com.upc.tp_nexthouse.repositories;

import com.upc.tp_nexthouse.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
