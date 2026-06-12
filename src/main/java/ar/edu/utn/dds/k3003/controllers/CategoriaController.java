package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.CategoriaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.utn.dds.k3003.model.Categoria;
import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private Fachada fachada;

    public CategoriaController(Fachada fachada) {
        this.fachada = fachada;
    }
    // Opcion 1 utilizando @RequestMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoriaDTO> postCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaAgregado = fachada.agregarCategoria(categoriaDTO);
        return ResponseEntity.ok(categoriaAgregado);
    }

    @GetMapping
    public ResponseEntity<List <CategoriaDTO>> getCategorias(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarCategorias());
    
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> deleteCategoria(@PathVariable("id") String categoriaID) {
        CategoriaDTO categoriaEliminada = fachada.borrarCategoria(categoriaID);
        return ResponseEntity.ok(categoriaEliminada);
    }

}