package com.upc.tp_nexthouse.Controller;

import com.upc.tp_nexthouse.DTO.*;
import com.upc.tp_nexthouse.Entities.Propiedad;
import com.upc.tp_nexthouse.serviceInterface.PropiedadService;
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
public class PropiedadController {
    @Autowired
    private PropiedadService propiedadService;
    @PostMapping("/Propiedad")
    public PropiedadDTO insertar(@RequestBody PropiedadDTO propiedadDTO){
        ModelMapper modelMapper= new ModelMapper();
        Propiedad propiedad= modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad= propiedadService.savedPropiedad(propiedad);
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }
    @GetMapping("/Listar_Propiedad")
    public List<PropiedadDTO> list(){
        return propiedadService.list().stream().map(y -> {
            ModelMapper modelMapper= new ModelMapper();
            return  modelMapper.map(y, PropiedadDTO.class);
        }).collect(Collectors.toList());
    }
    @PutMapping("/Modificar_Propiedad")
    public PropiedadDTO modificarPropiedad(@RequestBody PropiedadDTO propiedadDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad = propiedadService.modificarPropiedad(propiedad);
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }

    @DeleteMapping("/Eliminar_Propiedad")
    public void eliminarPropiedad(@PathVariable Long id) {
        propiedadService.eliminarPropiedad(id);
    }

    @PreAuthorize("hasRole('ARRENDATARIO')")
    @GetMapping("/Query2_Propiedad")
    public List<Query2DTO> query2(){
        return  propiedadService.query2();
    }

    @PreAuthorize("hasRole('ARRENDADOR')")
    @GetMapping("/Query4_Propiedad")
    public List<Query4DTO> query4(){
        return  propiedadService.query4();
    }

    @GetMapping("/PropiedadFind/{id}")
    public ResponseEntity<PropiedadDTO> buscaPropiedad(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Propiedad propiedad = propiedadService.buscarPorId(id);
        PropiedadDTO propiedadDTO = modelMapper.map(propiedad, PropiedadDTO.class);
        return ResponseEntity.ok(propiedadDTO);
    }
    @GetMapping("/PropiedadBuscarPorTipo/{tipo}")
    public List<Propiedad> buscarPorTipo(@PathVariable String tipo) {
        return propiedadService.buscarPropiedadesPorTipo(tipo);
    }

    @PreAuthorize("hasRole('ARRENDATARIO')")
    @GetMapping("/PropiedadQuery6/{startDate}/{endDate}")
    public List<Query6DTO> query6(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return  propiedadService.query6(startDate, endDate);
    }

    @PreAuthorize("hasRole('ARRENDATARIO')")
    @GetMapping("/PropiedadQuery7/{ciudad}")
    public List<Query7DTO> query7(@PathVariable String ciudad){
        return  propiedadService.query7(ciudad);
    }
}
