package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.ProductoDTO;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.utn.dds.k3003.model.Producto;
import java.util.List;



@RestController
@RequestMapping("/productos")
public class ProductoController {

    private Fachada fachada;

    public ProductoController(Fachada fachada) {
        this.fachada = fachada;
    }
    // Opcion 1 utilizando @RequestMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductoDTO> postProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoAgregado = fachada.agregarProducto(productoDTO);
        return ResponseEntity.ok(productoAgregado);
    }

    /*
    // Opcion 2 utilizando @GetMapping
    @GetMapping
    public ResponseEntity<ProductoDTO> getProductoByID(@RequestParam String productoID){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarProductoPorID(productoID));
    }
    */

    @GetMapping("/{id}")
	public ResponseEntity<?> getProductoByID(@PathVariable("id") String productoID){
        String requestId = MDC.get("request_id");
        try{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarProductoPorID(productoID));
        }catch(RuntimeException ex){
            if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("no existe")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-Request-Id", requestId).body(ex.getMessage());
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("X-Request-Id", requestId).body(ex.getMessage());
        }
    }
	

    @GetMapping
    public ResponseEntity<List <ProductoDTO>> getProductos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarProductos());
    
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> putProducto(
            @PathVariable("id") String productoID,
            @RequestParam ProductoDTO nuevoProductoDTO) {
        ProductoDTO productoCambiado = fachada.putProducto(nuevoProductoDTO, productoID);
        return ResponseEntity.ok(productoCambiado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDTO> deleteProducto(@PathVariable("id") String productoID) {
        ProductoDTO productoEliminada = fachada.borrarProducto(productoID);
        return ResponseEntity.ok(productoEliminada);
    }

}