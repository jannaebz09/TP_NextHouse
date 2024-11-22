package com.upc.tp_nexthouse.Controller;

import com.upc.tp_nexthouse.DTO.OpcionesPagoDTO;
import com.upc.tp_nexthouse.DTO.Query1DTO;
import com.upc.tp_nexthouse.Entities.OpcionesPago;
import com.upc.tp_nexthouse.serviceInterface.OpcionesPagoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/NextHouse")
public class OpcionesPagoController {
    @Autowired
    private OpcionesPagoService opcionesPagoService;
    @PostMapping("/OpcionesPago")
    public OpcionesPagoDTO insertar(@RequestBody OpcionesPagoDTO opcionesPagoDTO){
        ModelMapper modelMapper= new ModelMapper();
        OpcionesPago opcionesPago= modelMapper.map(opcionesPagoDTO, OpcionesPago.class);
        opcionesPago= opcionesPagoService.savedOpcionesPago(opcionesPago);
        return modelMapper.map(opcionesPago, OpcionesPagoDTO.class);
    }
    @GetMapping("/Listar_OpcionesPago")
    public List<OpcionesPagoDTO> list(){
        return opcionesPagoService.list().stream().map(y -> {
            ModelMapper modelMapper= new ModelMapper();
            return  modelMapper.map(y, OpcionesPagoDTO.class);
        }).collect(Collectors.toList());
    }
    @PutMapping("/Modificar_OpcionesPago")
    public OpcionesPagoDTO modificarOpcionesPago(@RequestBody OpcionesPagoDTO opcionesPagoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        OpcionesPago opcionesPago = modelMapper.map(opcionesPagoDTO, OpcionesPago.class);
        opcionesPago = opcionesPagoService.modificarOpcionesPago(opcionesPago);
        return modelMapper.map(opcionesPago, OpcionesPagoDTO.class);
    }

    @DeleteMapping("/Eliminar_OpcionesPago/{id}")
    public void eliminarOpcionesPago(@PathVariable Long id) {
        opcionesPagoService.eliminarOpcionesPago(id);
    }

    @PreAuthorize("hasRole('ARRENDATARIO')")
    @GetMapping("/Query1_OpcionesPago")
    public List<Query1DTO> query1 (){
        return  opcionesPagoService.query1DTO();
    }

    @GetMapping("/opcionesPadoFind/{id}")
    public ResponseEntity<OpcionesPagoDTO> buscaProveedor(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        OpcionesPago opcionesPago = opcionesPagoService.buscarPorId(id);
        OpcionesPagoDTO opcionesPagoDTO = modelMapper.map(opcionesPago, OpcionesPagoDTO.class);
        return ResponseEntity.ok(opcionesPagoDTO);
    }

}
