package com.upc.tp_nexthouse.Controller;

import com.upc.tp_nexthouse.DTO.Query3DTO;
import com.upc.tp_nexthouse.DTO.ReservaAlquilerDTO;
import com.upc.tp_nexthouse.Entities.ReservaAlquiler;
import com.upc.tp_nexthouse.serviceInterface.ReservaAlquilerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/NextHouse")
public class ReservaAlquilerController {
    @Autowired
    private ReservaAlquilerService reservaAlquilerService;
    @PostMapping("/ReservaAlquiler")
    public ReservaAlquilerDTO insertar(@RequestBody ReservaAlquilerDTO reservaAlquilerDTO){
        ModelMapper modelMapper= new ModelMapper();
        ReservaAlquiler reservaAlquiler= modelMapper.map(reservaAlquilerDTO, ReservaAlquiler.class);
        reservaAlquiler= reservaAlquilerService.savedReservaAlquiler(reservaAlquiler);
        return modelMapper.map(reservaAlquiler, ReservaAlquilerDTO.class);
    }
    @GetMapping("/Listar_ReservaAlquiler")
    public List<ReservaAlquilerDTO> list(){
        return reservaAlquilerService.list().stream().map(y -> {
            ModelMapper modelMapper= new ModelMapper();
            return  modelMapper.map(y, ReservaAlquilerDTO.class);
        }).collect(Collectors.toList());
    }

    @PutMapping("/Modificar_ReservaAlquiler")
    public ReservaAlquilerDTO modificarReservaAlquiler(@RequestBody ReservaAlquilerDTO reservaAlquilerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ReservaAlquiler reservaAlquiler = modelMapper.map(reservaAlquilerDTO, ReservaAlquiler.class);
        reservaAlquiler = reservaAlquilerService.modificarReservaAlquiler(reservaAlquiler);
        return modelMapper.map(reservaAlquiler, ReservaAlquilerDTO.class);
    }

    @DeleteMapping("/Eliminar_ReservaAlquiler/{id}")
    public void eliminarReservaAlquiler(@PathVariable Long id) {
        reservaAlquilerService.eliminarReservaAlquiler(id);
    }

    @PreAuthorize("hasRole('ARRENDADOR')")
    @GetMapping("/ReservaQuery3/{startDate}/{endDate}")
    public List<Query3DTO> query3(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return  reservaAlquilerService.query3(startDate, endDate);
    }

    @GetMapping("/ReservaAlquilerFind/{id}")
    public ResponseEntity<ReservaAlquilerDTO> buscaReservaAlquiler(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        ReservaAlquiler reservaAlquiler = reservaAlquilerService.buscarPorId(id);
        ReservaAlquilerDTO reservaAlquilerDTO = modelMapper.map(reservaAlquiler, ReservaAlquilerDTO.class);
        return ResponseEntity.ok(reservaAlquilerDTO);
    }
}
