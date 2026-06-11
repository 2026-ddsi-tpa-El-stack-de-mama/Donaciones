package ar.edu.utn.dds.k3003.services;
import ar.edu.utn.dds.k3003.model.Producto;
import ar.edu.utn.dds.k3003.repositories.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductosService {
    private final ProductosRepository productoR;
    public ProductosService(ProductosRepository productoR){
        this.productoR = productoR;
    }
    public Optional<Producto> getProducto(String id){
        return productoR.findById(id);
    }
}
