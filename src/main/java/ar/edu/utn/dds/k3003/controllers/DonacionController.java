package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
//
import ar.edu.utn.dds.k3003.model.Donacion;
//import ar.edu.utn.dds.k3003.services.DonacionService;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/donaciones")
public class DonacionController {

    private Fachada fachada;

    //Pongo el Autowired siguiendo el video
    @Autowired
    public DonacionController(Fachada fachada) {
        this.fachada = fachada;
    }

    // Opcion 1 utilizando @RequestMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DonacionDTO> postDonacion(@RequestBody DonacionDTO donacionDTO) {
        DonacionDTO donacionAgregada = fachada.registrarDonacion(donacionDTO);
        return ResponseEntity.ok(donacionAgregada);
    }

    // Opcion 2 utilizando @GetMapping
    @GetMapping
    public ResponseEntity<DonacionDTO> getDonacionByID(@RequestParam String donacionID){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarDonacionPorID(donacionID));
    }

    //Agregado
    @GetMapping
    public ResponseEntity<List <DonacionDTO>> getDonaciones(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarDonaciones());
    
    }
    
   @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<DonacionDTO> deleteDonacion(@RequestBody String donacionID) {
        DonacionDTO donacionEliminada = fachada.borrarDonacion(donacionID);
        return ResponseEntity.ok(donacionEliminada);
    }

    @GetMapping("/search")
    public ResponseEntity<List <DonacionDTO>> buscarPorDonadorYFechaInicio(@RequestParam String donadorID, @RequestParam LocalDate fecha){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarPorDonadorYFechaInicio(donadorID, fecha)) ;
    }
    
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<DonacionDTO> cambiarEstadoDeDonacion(@RequestParam String donacionID, @RequestParam EstadoDonacionEnum estado){
        DonacionDTO nuevaVersionDonacion = fachada.cambiarEstadoDeDonacion(donacionID, estado);
        return ResponseEntity.ok(nuevaVersionDonacion);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DonacionDTO> registrarQuejaEnDonacion(String donacionID, String descripcion){
        DonacionDTO donacionConQueja = fachada.registrarQuejaEnDonacion(donacionID,descripcion);
        return ResponseEntity.ok(donacionConQueja);
    }
}