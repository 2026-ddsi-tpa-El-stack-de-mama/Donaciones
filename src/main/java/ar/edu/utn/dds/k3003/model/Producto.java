package ar.edu.utn.dds.k3003.model;
import jakarta.persistence.*;
import ar.edu.utn.dds.k3003.model.Identificador;
import ar.edu.utn.dds.k3003.model.Categoria;
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    String id;
    String nombre;
    String descripcion;
    @ManyToOne
    @JoinColumn(name="categoria_id")
    Categoria categoria;
    @OneToOne
    @JoinColumn(name="identificador_id")
    Identificador identificador;

    public Producto(
        String id,
        String nombre,
        String descripcion,
        Categoria categoria,
        Identificador identificador) {
    this.id=id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.identificador = identificador;
  }

  public Producto(){
    
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public Identificador getIdentificador(){
    return identificador;
  }
}
