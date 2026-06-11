package ar.edu.utn.dds.k3003.repositories;
/*
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.EstadoAsginacionEnum;
import ar.edu.utn.dds.k3003.model.Donacion;
import ar.edu.utn.dds.k3003.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; //Agregada para poder filtrar y volver a lista
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import lombok.val;

public class InMemoryProductosRepo implements ProductosRepository {

  private List<Producto> productos;
  private AtomicLong idSecuencial = new AtomicLong(1);

  public InMemoryProductosRepo() {
    this.productos = new ArrayList<>();
  }

  @Override
  public Optional<Producto> findById(String id) {
    return this.productos.stream().filter(d -> d.getId().equals(id)).findFirst();
  }

  @Override
  public Producto save(Producto producto) {
    Producto productoConID = producto;
    productoConID.setId(String.valueOf(idSecuencial.getAndIncrement()));

    this.productos.add(productoConID);
    return this.findById(productoConID.getId()).get();
  }

  @Override
  public Producto deleteById(String id) {
    val producto = this.findById(id);
    this.productos.remove(producto.get());
    return producto.get();
  }

  public Producto saveSinCambioID(Producto producto) {
    this.productos.add(producto);
    return this.findById(producto.getId()).get();
  }

  public List<Producto> buscarProductos(){
    return productos;
  }

  // Tal vez sería mejor usar un sistema inspirado en este para el cambio de estados de las donaciones :1
  // (Usando set en lugar de los malavares que hace ahora)
  public Producto putProducto(Producto nuevoProducto, String id){
    nuevoProducto.setId(id);
    Integer index = this.productos.indexOf(findById(id).get());
    this.productos.set(index,nuevoProducto);
    return this.findById(nuevoProducto.getId()).get();

  }
}
*/