package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/prueba")
public class PruebaController {

    private final Fachada fachada;

    public PruebaController(Fachada fachada) {
        this.fachada = fachada;
    }
    // ----- ------------- -----
    // ----- De donaciones -----
    // ----- ------------- -----
    @GetMapping("/donaciones")
    public ResponseEntity<?> getDonaciones(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarDonaciones());
    
    }
    // ______ Para probar ______
    @GetMapping
    public ResponseEntity<String> getD(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Llegan bien los request");
    
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getNum(@PathVariable("id") String pruebaID){
        return ResponseEntity.status(HttpStatus.OK).body("Llega bien el request"+ " " + pruebaID);
    }

}