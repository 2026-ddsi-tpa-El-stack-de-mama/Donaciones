package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;
import java.time.LocalDate;

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
  private String productoID;
  private Integer cantidad;
  @Enumerated(EnumType.STRING)
  private EstadoDonacionEnum estado;
  private LocalDate fechaInicio; //Creo que se necesita para buscarPorDonadorYFechaInicio

  public Donacion(
      String donadorID,
      String depositoID,
      String descripcion,
      String productoID,
      Integer cantidad,
      EstadoDonacionEnum estado) {
    this.donadorID = donadorID;
    this.depositoID = depositoID;
    this.descripcion = descripcion;
    this.productoID = productoID;
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

  public String getProductoID() {
    return productoID;
  }

  public void setProductoID(String productoID) {
    this.productoID = productoID;
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
