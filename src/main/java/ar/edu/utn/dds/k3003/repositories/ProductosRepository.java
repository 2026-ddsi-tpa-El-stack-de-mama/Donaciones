package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Producto;

import java.util.List;
import java.util.Optional;
//import java.util.ArrayList;
//import java.util.List;

public interface ProductosRepository {
  Optional<Producto> findById(String id);

  Producto save(Producto producto);

  Producto deleteById(String id);

  Producto saveSinCambioID(Producto Producto);

  List<Producto> buscarProductos();

  Producto putProducto(Producto producto, String id);
}
