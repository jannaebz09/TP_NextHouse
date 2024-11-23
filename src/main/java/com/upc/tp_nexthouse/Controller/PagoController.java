package com.upc.tp_nexthouse.Controller;

import com.upc.tp_nexthouse.DTO.PagoDTO;
import com.upc.tp_nexthouse.DTO.Query5DTO;
import com.upc.tp_nexthouse.Entities.Pago;
import com.upc.tp_nexthouse.serviceInterface.PagoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:4200","http://http://3.144.130.45/","https://tp-nexthouse.onrender.com","https://tf-front-next-house.vercel.app/"})
@RestController
@RequestMapping("/NextHouse")
public class PagoController {
    @Autowired
    private PagoService pagoService;
    @PostMapping("/Pago")
    public PagoDTO insertar(@RequestBody PagoDTO pagoDTO){
        ModelMapper modelMapper= new ModelMapper();
        Pago pago= modelMapper.map(pagoDTO, Pago.class);
        pago= pagoService.savedPago(pago);
        return modelMapper.map(pago, PagoDTO.class);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/Listar_Pago")
    public List<PagoDTO> list(){
        return pagoService.list().stream().map(y -> {
            ModelMapper modelMapper= new ModelMapper();
            return  modelMapper.map(y, PagoDTO.class);
        }).collect(Collectors.toList());
    }
    @PutMapping("/Modificar_Pago")
    public PagoDTO modificarPago(@RequestBody PagoDTO pagoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Pago pago = modelMapper.map(pagoDTO, Pago.class);
        pago = pagoService.modificarPago(pago);
        return modelMapper.map(pago, PagoDTO.class);
    }

    @DeleteMapping("/Eliminar_Pago/{id}")
    public void eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
    }

    @GetMapping("/PagoFind/{id}")
    public ResponseEntity<PagoDTO> buscaPago(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Pago pago = pagoService.buscarPorId(id);
        PagoDTO pagoDTO = modelMapper.map(pago, PagoDTO.class);
        return ResponseEntity.ok(pagoDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/PagoQuery5/{startDate}/{endDate}")
    public List<Query5DTO> query5(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return pagoService.query5(startDate,endDate);
    }
}
