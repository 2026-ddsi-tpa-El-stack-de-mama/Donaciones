package ar.edu.utn.dds.k3003.controllers;

//import ar.edu.utn.dds.k3003.productosService;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.ProductoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.utn.dds.k3003.model.Producto;
import java.util.List;

import ar.edu.utn.dds.k3003.services.ProductosService;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    private ProductosService productosService;

    public ProductoController(productosService productosService) {
        this.productosService = productosService;
    }
    // Opcion 1 utilizando @RequestMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductoDTO> postProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoAgregado = productosService.agregarProducto(productoDTO);
        return ResponseEntity.ok(productoAgregado);
    }

    /*
    // Opcion 2 utilizando @GetMapping
    @GetMapping
    public ResponseEntity<ProductoDTO> getProductoByID(@RequestParam String productoID){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.productosService.buscarProductoPorID(productoID));
    }
    */

    @GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> getProductoByID(@PathVariable("id") String productoID){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.productosService.buscarProductoPorID(productoID));
    }
	

    @GetMapping
    public ResponseEntity<List <ProductoDTO>> getProductos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.productosService.buscarProductos());
    
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> putProducto(
            @PathVariable("id") String productoID,
            @RequestParam ProductoDTO nuevoProductoDTO) {
        ProductoDTO productoCambiado = productosService.putProducto(nuevoProductoDTO, productoID);
        return ResponseEntity.ok(productoCambiado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDTO> deleteProducto(@PathVariable("id") String productoID) {
        ProductoDTO productoEliminada = productosService.borrarProducto(productoID);
        return ResponseEntity.ok(productoEliminada);
    }

}