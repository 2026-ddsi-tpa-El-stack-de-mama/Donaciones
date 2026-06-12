package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;
import java.time.LocalDate;
import ar.edu.utn.dds.k3003.model.Producto;
//Para DB
import jakarta.persistence.*;
@Entity
@Table(name="donaciones")
public class Donacion {

  @Id
  private String id;
  private String donadorID;
  private String depositoID;
  private String descripcion;
  @ManyToOne
  @JoinColumn(name="producto_id")
  private Producto producto;
  private Integer cantidad;
  @Enumerated(EnumType.STRING)
  private EstadoDonacionEnum estado;
  private LocalDate fechaInicio; //Creo que se necesita para buscarPorDonadorYFechaInicio

  public Donacion(
      String donadorID,
      String depositoID,
      String descripcion,
      Producto producto,
      Integer cantidad,
      EstadoDonacionEnum estado) {
    this.donadorID = donadorID;
    this.depositoID = depositoID;
    this.descripcion = descripcion;
    this.producto = producto;
    this.cantidad = cantidad;
    this.estado = estado;
    this.fechaInicio = LocalDate.now();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDonadorID() {
    return donadorID;
  }

  public void setDonadorID(String donadorID) {
    this.donadorID = donadorID;
  }

  public String getDepositoID() {
    return depositoID;
  }

  public void setDepositoID(String depositoID) {
    this.depositoID = depositoID;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Producto getProducto() {
    return producto;
  }

  public void setProductoID(Producto producto) {
    this.producto = producto;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public EstadoDonacionEnum getEstado() {
    return estado;
  }

  public void setEstado(EstadoDonacionEnum estado) {
    this.estado = estado;
  }

  public LocalDate getFechaInicio(){
    return fechaInicio;
  }

  //Creo que no debería tener un setFechaInicio por trazabilidad
}
