package ar.edu.utn.dds.k3003.controllers;

//import ar.edu.utn.dds.k3003.identificadoresService;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.IdentificadorDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.utn.dds.k3003.model.Identificador;
import java.util.List;

import ar.edu.utn.dds.k3003.services.IdentificadoresService;


@RestController
@RequestMapping("/identificadores")
public class IdentificadorController {

    private identificadoresService identificadoresService;

    public IdentificadorController(identificadoresService identificadoresService) {
        this.identificadoresService = identificadoresService;
    }
    // Opcion 1 utilizando @RequestMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<IdentificadorDTO> postIdentificador(@RequestBody IdentificadorDTO identificadorDTO) {
        IdentificadorDTO identificadorAgregado = identificadoresService.agregarIdentificador(identificadorDTO);
        return ResponseEntity.ok(identificadorAgregado);
    }

    @GetMapping
    public ResponseEntity<List <IdentificadorDTO>> getIdentificadores(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.identificadoresService.buscarIdentificadores());
    
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<IdentificadorDTO> deleteIdentificador(@PathVariable("id") String identificadorID) {
        IdentificadorDTO identificadorEliminada = identificadoresService.borrarIdentificador(identificadorID);
        return ResponseEntity.ok(identificadorEliminada);
    }

}