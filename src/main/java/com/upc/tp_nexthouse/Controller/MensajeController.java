package com.upc.tp_nexthouse.Controller;

import com.upc.tp_nexthouse.DTO.ComentarioDTO;
import com.upc.tp_nexthouse.DTO.MensajeDTO;
import com.upc.tp_nexthouse.DTO.UsuarioDTO;
import com.upc.tp_nexthouse.Entities.Comentario;
import com.upc.tp_nexthouse.Entities.Mensaje;
import com.upc.tp_nexthouse.Entities.Usuario;
import com.upc.tp_nexthouse.serviceInterface.MensajeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:4200","http://http://3.144.130.45/","https://tp-nexthouse.onrender.com","https://tf-front-next-house.vercel.app/"})
@RestController
@RequestMapping("/NextHouse")
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;
    @PostMapping("/Mensaje")
    public MensajeDTO insertar(@RequestBody MensajeDTO mensajeDTO){
        ModelMapper modelMapper= new ModelMapper();
        Mensaje mensaje= modelMapper.map(mensajeDTO, Mensaje.class);
        mensaje= mensajeService.savedMensaje(mensaje);
        return modelMapper.map(mensaje, MensajeDTO.class);
    }
    @GetMapping("/Listar_Mensajes")
    public List<MensajeDTO> list(){
        return mensajeService.list().stream().map(y -> {
            ModelMapper modelMapper= new ModelMapper();
            return  modelMapper.map(y, MensajeDTO.class);
        }).collect(Collectors.toList());
    }
    @PutMapping("/Modificar_Mensaje")
    public MensajeDTO modificarMensaje(@RequestBody MensajeDTO mensajeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Mensaje mensaje = modelMapper.map(mensajeDTO, Mensaje.class);
        mensaje = mensajeService.modificarMensaje(mensaje);
        return modelMapper.map(mensaje, MensajeDTO.class);
    }

    @DeleteMapping("/Eliminar_Mensaje/{id}")
    public void eliminarMensaje(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
    }


    @GetMapping("/MensajeFind/{id}")
    public ResponseEntity<MensajeDTO> buscaMensaje(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Mensaje mensaje = mensajeService.buscarPorId(id);
        MensajeDTO mensajeDTO = modelMapper.map(mensaje, MensajeDTO.class);
        return ResponseEntity.ok(mensajeDTO);
    }

}
