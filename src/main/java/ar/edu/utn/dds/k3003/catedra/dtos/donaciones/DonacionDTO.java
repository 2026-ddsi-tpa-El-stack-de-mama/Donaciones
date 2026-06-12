package ar.edu.utn.dds.k3003.catedra.dtos.donaciones;
import  ar.edu.utn.dds.k3003.model.Producto;
public record DonacionDTO(
    String id,
    String donadorID,
    String depositoID,
    String descripcion,
    Producto producto,
    Integer cantidad,
    EstadoDonacionEnum estado) {}
