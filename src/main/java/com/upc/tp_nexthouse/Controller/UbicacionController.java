package com.upc.tp_nexthouse.Controller;

import com.upc.tp_nexthouse.DTO.UbicacionDTO;
import com.upc.tp_nexthouse.Entities.Ubicacion;
import com.upc.tp_nexthouse.serviceInterface.UbicacionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/NextHouse")
public class UbicacionController {
    @Autowired
    private UbicacionService ubicacionService;
    @PostMapping("/Ubicacion")
    public UbicacionDTO insertar(@RequestBody UbicacionDTO ubicacionDTO){
        ModelMapper modelMapper= new ModelMapper();
        Ubicacion ubicacion= modelMapper.map(ubicacionDTO, Ubicacion.class);
        ubicacion= ubicacionService.savedUbicacion(ubicacion);
        return modelMapper.map(ubicacion, UbicacionDTO.class);
    }
    @GetMapping("/Listar_Ubicacion")
    public List<UbicacionDTO> list(){
        return ubicacionService.list().stream().map(y -> {
            ModelMapper modelMapper= new ModelMapper();
            return  modelMapper.map(y, UbicacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PutMapping("/Modificar_Ubicacion")
    public UbicacionDTO modificarUbicacion(@RequestBody UbicacionDTO ubicacionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Ubicacion ubicacion = modelMapper.map(ubicacionDTO, Ubicacion.class);
        ubicacion = ubicacionService.modificarUbicacion(ubicacion);
        return modelMapper.map(ubicacion, UbicacionDTO.class);
    }

    @DeleteMapping("/Eliminar_Ubicacion/{id}")
    public void eliminarUbicacion(@PathVariable Long id) {
        ubicacionService.eliminarUbicacion(id);
    }

    @GetMapping("/UbicacionFind/{id}")
    public ResponseEntity<UbicacionDTO> buscaUbicacion(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Ubicacion ubicacion = ubicacionService.buscarPorId(id);
        UbicacionDTO ubicacionDTO = modelMapper.map(ubicacion, UbicacionDTO.class);
        return ResponseEntity.ok(ubicacionDTO);
    }


}
