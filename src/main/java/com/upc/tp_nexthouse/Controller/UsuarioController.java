package com.upc.tp_nexthouse.Controller;


import com.upc.tp_nexthouse.DTO.UbicacionDTO;
import com.upc.tp_nexthouse.DTO.UsuarioDTO;
import com.upc.tp_nexthouse.Entities.Ubicacion;
import com.upc.tp_nexthouse.Entities.Usuario;
import com.upc.tp_nexthouse.serviceInterface.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:4200","http://http://3.144.130.45/","https://tp-nexthouse.onrender.com","https://tf-front-next-house.vercel.app/"})
@RestController
@RequestMapping("/NextHouse")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/Usuario")
    public UsuarioDTO insertar(@RequestBody UsuarioDTO usuarioDTO){
        ModelMapper modelMapper= new ModelMapper();
        Usuario usuario= modelMapper.map(usuarioDTO, Usuario.class);
        usuario= usuarioService.savedUsuario(usuario);
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
    @GetMapping("/Listar_Usuario")
    public List<UsuarioDTO> list(){
        return usuarioService.list().stream().map(y -> {
            ModelMapper modelMapper= new ModelMapper();
            return  modelMapper.map(y, UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PutMapping("/Modificar_Usuario")
    public UsuarioDTO modificarUsuraio(@RequestBody UsuarioDTO usuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario = usuarioService.modificarUsuario(usuario);
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @DeleteMapping("/Eliminar_Usuraio/{id}")
    public void eliminarUsuraio(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }

    @GetMapping("/UsuarioFind/{id}")
    public ResponseEntity<UsuarioDTO> buscaUbicacion(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = usuarioService.buscarPorId(id);
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return ResponseEntity.ok(usuarioDTO);
    }
}
