package com.upc.tp_nexthouse.ServiceImplement;

import com.upc.tp_nexthouse.DTO.Query5DTO;
import com.upc.tp_nexthouse.Entities.Pago;
import com.upc.tp_nexthouse.repositories.PagoRepository;
import com.upc.tp_nexthouse.serviceInterface.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagoServiceImplement implements PagoService {
    @Autowired
    private PagoRepository pagoRepository;
    @Transactional
    @Override
    public Pago savedPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void eliminarPago(Long id) {
        if(pagoRepository.existsById(id)) {
            pagoRepository.deleteById(id);
        }
    }

    @Override
    public Pago modificarPago(Pago pago) {
        if(pagoRepository.existsById(pago.getIdPago())){
            return pagoRepository.save(pago);
        }
        return null;
    }

    @Override
    public List<Pago> list() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago buscarPorId(long id) {
        if (pagoRepository.findById(id).isPresent()){
            return pagoRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Query5DTO> query5(LocalDate startDate, LocalDate endDate) {
        return pagoRepository.query5(startDate,endDate);
    }
}
