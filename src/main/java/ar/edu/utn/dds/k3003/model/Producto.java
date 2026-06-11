package ar.edu.utn.dds.k3003.model;
import jakarta.persistence.*;
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    String id;
    String nombre;
    String descripcion;
    @ManyToOne
    @JoinColumn(name="categoria_id")
    String categoriaID;
    @OneToOne
    @JoinColumn(name="identificador_id")
    String identificadorID;

    public Producto(
        String nombre,
        String descripcion,
        String categoriaID,
        String identificadorID) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.categoriaID = categoriaID;
    this.identificadorID = identificadorID;
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

  public String getCategoriaID() {
    return categoriaID;
  }

  public String getIdentificadorID(){
    return identificadorID;
  }
}
