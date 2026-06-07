package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.model.Donacion;

import java.util.List;
import java.util.ArrayList;
public class DonacionesDataMapper {

  public DonacionDTO toDonacionDTO(Donacion donacion) {
    return new DonacionDTO(
        donacion.getId(),
        donacion.getDonadorID(),
        donacion.getDepositoID(),
        donacion.getDescripcion(),
        donacion.getProductoID(),
        donacion.getCantidad(),
        donacion.getEstado());
  }
/*
// REFERENCIA
id;
donadorID;
depositoID;
descripcion;
productoID;
cantidad;
estado;
*/
  public Donacion toDonacion(DonacionDTO donacionDTO) {
    return new Donacion(
        donacionDTO.donadorID(),
        donacionDTO.depositoID(),
        donacionDTO.descripcion(),
        donacionDTO.productoID(),
        donacionDTO.cantidad(),
        donacionDTO.estado());
  }

  public List <DonacionDTO> listToDonacionDTO(List<Donacion> lista){
    List<DonacionDTO> nuevaLista = new ArrayList<>();
    for(Donacion donacion: lista){
      nuevaLista.add(toDonacionDTO(donacion));
    }
    return nuevaLista;
  }
}
