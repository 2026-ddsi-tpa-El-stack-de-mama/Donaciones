package ar.edu.utn.dds.k3003.repositories;
import java.util.List;
import java.util.ArrayList;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.ProductoDTO;
import ar.edu.utn.dds.k3003.model.Producto;

public class ProductosDataMapper {
      public ProductoDTO toProductoDTO(Producto producto) {
    return new ProductoDTO(
        producto.getId(),
        //producto.getDonadorID(),
        producto.getNombre(),
        producto.getDescripcion(),
        producto.getCategoria(),
        producto.getIdentificador());
  }
/*
// REFERENCIA
    String id;
    String nombre;
    String descripcion;
    String categoriaID;
    String identificadorID;
*/
  public Producto toProducto(ProductoDTO productoDTO) {
    return new Producto(
        productoDTO.nombre(),
        productoDTO.descripcion(),
        productoDTO.categoriaID(),
        productoDTO.identificadorID());
  }

  public List <ProductoDTO> listToProductoDTO(List<Producto> lista){
    List<ProductoDTO> nuevaLista = new ArrayList<>();
    for(Producto producto: lista){
      nuevaLista.add(toProductoDTO(producto));
    }
    return nuevaLista;
  }
}
