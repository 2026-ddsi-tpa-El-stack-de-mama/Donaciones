package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.IdentificadorDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.utn.dds.k3003.model.Identificador;
import java.util.List;

@RestController
@RequestMapping("/identificadores")
public class IdentificadorController {

    private Fachada fachada;

    public IdentificadorController(Fachada fachada) {
        this.fachada = fachada;
    }
    // Opcion 1 utilizando @RequestMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<IdentificadorDTO> postIdentificador(@RequestBody IdentificadorDTO identificadorDTO) {
        IdentificadorDTO identificadorAgregado = fachada.agregarIdentificador(identificadorDTO);
        return ResponseEntity.ok(identificadorAgregado);
    }

    @GetMapping
    public ResponseEntity<List <IdentificadorDTO>> getIdentificadores(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarIdentificadores());
    
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<IdentificadorDTO> deleteIdentificador(@PathVariable("id") String identificadorID) {
        IdentificadorDTO identificadorEliminada = fachada.borrarIdentificador(identificadorID);
        return ResponseEntity.ok(identificadorEliminada);
    }

}