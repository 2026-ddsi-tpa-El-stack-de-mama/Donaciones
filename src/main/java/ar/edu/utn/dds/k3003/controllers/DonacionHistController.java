package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//
import ar.edu.utn.dds.k3003.model.DonacionHist;
//import ar.edu.utn.dds.k3003.services.DonacionService;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.MDC;

@RestController
@RequestMapping("/donacioneshist")
public class DonacionHistController {

    private final Fachada fachada;

    @Autowired
    public DonacionHistController(Fachada fachada) {
        this.fachada = fachada;
    }

    @GetMapping
    public ResponseEntity<List <DonacionHist>> getDonacionesHist(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarDonacionesHist());
    
    }
}
