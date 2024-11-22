package com.upc.tp_nexthouse.Controller;

import com.upc.tp_nexthouse.DTO.*;
import com.upc.tp_nexthouse.Entities.Role;
import com.upc.tp_nexthouse.serviceInterface.RolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/NextHouse")
public class RolController {
    @Autowired
    private RolService rolService;
    @PostMapping("/Rol")
    public RolDTO insertar(@RequestBody RolDTO rolDTO){
        ModelMapper modelMapper= new ModelMapper();
        Role rol= modelMapper.map(rolDTO, Role.class);
        rol= rolService.savedRol(rol);
        return modelMapper.map(rol, RolDTO.class);
    }
    @GetMapping("/Listar_Rol")
    public List<RolDTO> list(){
        return rolService.list().stream().map(y -> {
            ModelMapper modelMapper= new ModelMapper();
            return  modelMapper.map(y, RolDTO.class);
        }).collect(Collectors.toList());
    }
    @PutMapping("/Modificar_Rol")
    public RolDTO modificarRol(@RequestBody RolDTO rolDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Role rol = modelMapper.map(rolDTO, Role.class);
        rol = rolService.modificarRol(rol);
        return modelMapper.map(rol, RolDTO.class);
    }

    @DeleteMapping("/Eliminar_Rol/{id}")
    public void eliminarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
    }

    @GetMapping("/RolFind/{id}")
    public ResponseEntity<RolDTO> buscaRol(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Role rol = rolService.buscarPorId(id);
        RolDTO rolDTO = modelMapper.map(rol, RolDTO.class);
        return ResponseEntity.ok(rolDTO);
    }


}
