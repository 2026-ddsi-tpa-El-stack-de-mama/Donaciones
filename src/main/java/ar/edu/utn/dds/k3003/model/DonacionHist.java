package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;
import java.time.LocalDate;
import ar.edu.utn.dds.k3003.model.Producto;
//Para DB
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="donacionesHist")
public class DonacionHist {

  @Id
  //TODO: el id deberia ser autogenerado
  //@GeneratedValue(strategy = GenerationType.)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private LocalDateTime fechaHist;
  private String donacionID;
  private String donadorID;
  private String depositoID;
  private String descripcion;
  private String productoID;
  private Integer cantidad;
  @Enumerated(EnumType.STRING)
  private EstadoDonacionEnum estado;
  private LocalDate fechaInicio; //Creo que se necesita para buscarPorDonadorYFechaInicio

  public DonacionHist(
      String id,
      String donacionID,
      String donadorID,
      String depositoID,
      String descripcion,
      String productoID,
      Integer cantidad,
      EstadoDonacionEnum estado,
      LocalDate fechaInicio) {
    this.id=id;
    this.fechaHist =LocalDateTime.now();
    this.donacionID=donacionID;
    this.donadorID = donadorID;
    this.depositoID = depositoID;
    this.descripcion = descripcion;
    this.productoID = productoID;
    this.cantidad = cantidad;
    this.estado = estado;
    this.fechaInicio = fechaInicio;
  }

  //Pruebo para ver si esto es lo que necesita Hibernate
  public DonacionHist(){
    
  }

  public String getId() {
    return id;
  }

  public LocalDateTime getFechaHist() {
    return fechaHist;
  }

  public String getDonacionID(){
    return donacionID;
  }

  public String getDonadorID() {
    return donadorID;
  }

  public String getDepositoID() {
    return depositoID;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getProductoID() {
    return productoID;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public EstadoDonacionEnum getEstado() {
    return estado;
  }

  public LocalDate getFechaInicio(){
    return fechaInicio;
  }

  //Creo que no debería tener un setFechaInicio por trazabilidad
}
