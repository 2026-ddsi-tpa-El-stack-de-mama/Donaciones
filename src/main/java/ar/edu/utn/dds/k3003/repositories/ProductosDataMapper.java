package ar.edu.utn.dds.k3003.repositories;
import java.util.List;
import java.util.ArrayList;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.ProductoDTO;
import ar.edu.utn.dds.k3003.model.Producto;
import ar.edu.utn.dds.k3003.model.Categoria;
import ar.edu.utn.dds.k3003.model.Identificador;

public class ProductosDataMapper {
      public ProductoDTO toProductoDTO(Producto producto) {
    return new ProductoDTO(
        producto.getId(),
        //producto.getDonadorID(),
        producto.getNombre(),
        producto.getDescripcion(),
        producto.getCategoria().getId(),
        producto.getIdentificador().getId());
  }
/*
// REFERENCIA
    String id;
    String nombre;
    String descripcion;
    String categoriaID;
    String identificadorID;
*/
  public Producto toProducto(ProductoDTO productoDTO, Categoria categoria, Identificador identificador) {
    return new Producto(
      productoDTO.id(),
        productoDTO.nombre(),
        productoDTO.descripcion(),
        categoria,
        identificador);
  }

  public List <ProductoDTO> listToProductoDTO(List<Producto> lista){
    List<ProductoDTO> nuevaLista = new ArrayList<>();
    for(Producto producto: lista){
      nuevaLista.add(toProductoDTO(producto));
    }
    return nuevaLista;
  }
}
