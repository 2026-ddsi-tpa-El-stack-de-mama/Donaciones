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
import ar.edu.utn.dds.k3003.model.Donacion;
//import ar.edu.utn.dds.k3003.services.DonacionService;

import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("/donaciones")
public class DonacionController {

    private final Fachada fachada;

    @Autowired
    public DonacionController(Fachada fachada) {
        this.fachada = fachada;
    }

    
    //Versión vieja de Post
    // Opcion 1 utilizando @RequestMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DonacionDTO> postDonacion(@RequestBody DonacionDTO donacionDTO) {
        DonacionDTO donacionAgregada = fachada.registrarDonacion(donacionDTO);
        return ResponseEntity.ok(donacionAgregada);
    }
    
    
    /*
    // Versión vieja de getDonacionByID
    // Opcion 2 utilizando @GetMapping
    @GetMapping
    public ResponseEntity<DonacionDTO> getDonacionByID(@RequestParam String donacionID){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarDonacionPorID(donacionID));
    }
    */

    // Versión nueva de getDonacionByID
    @GetMapping("/{id}")
	public ResponseEntity<DonacionDTO> getDonacionById(@PathVariable("id") String donacionID) {
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
    
    @DeleteMapping("/{id}")
    public ResponseEntity<DonacionDTO> deleteDonacion(@PathVariable("id") String donacionID) {
        DonacionDTO donacionEliminada = fachada.borrarDonacion(donacionID);
        return ResponseEntity.ok(donacionEliminada);
    }

    @GetMapping("/search")
    public ResponseEntity<List <DonacionDTO>> buscarPorDonadorYFechaInicio(@RequestParam String donadorID, @RequestParam LocalDate fecha){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarPorDonadorYFechaInicio(donadorID, fecha)) ;
    }
    
    /*
    // Versión vieja cambiarEstadoDeDonacion
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<DonacionDTO> cambiarEstadoDeDonacion(@RequestParam String donacionID, @RequestParam EstadoDonacionEnum estado){
        DonacionDTO nuevaVersionDonacion = fachada.cambiarEstadoDeDonacion(donacionID, estado);
        return ResponseEntity.ok(nuevaVersionDonacion);
    }
    */

    // Versión nueva cambiarEstadoDeDonacion
    @PatchMapping("/{id}/estado")
    public ResponseEntity<DonacionDTO> cambiarEstadoDeDonacion(
            @PathVariable("id") String donacionID, 
            @RequestParam EstadoDonacionEnum estado){

        DonacionDTO nuevaVersionDonacion = fachada.cambiarEstadoDeDonacion(donacionID, estado);
        return ResponseEntity.ok(nuevaVersionDonacion);
    }

    /*
    //Versión vieja de registrarQuejaEnDonacion 
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DonacionDTO> registrarQuejaEnDonacion(String donacionID, String descripcion){
        DonacionDTO donacionConQueja = fachada.registrarQuejaEnDonacion(donacionID,descripcion);
        return ResponseEntity.ok(donacionConQueja);
    }
    */

    //Versión nueva de registrarQuejaEnDonacion
    @PostMapping("{id}/queja")
    public ResponseEntity<DonacionDTO> registrarQuejaEnDonacion(
            @PathVariable("id") String donacionID, 
            @RequestParam String descripcion){
        DonacionDTO donacionConQueja = fachada.registrarQuejaEnDonacion(donacionID,descripcion);
        return ResponseEntity.ok(donacionConQueja);
    }
}