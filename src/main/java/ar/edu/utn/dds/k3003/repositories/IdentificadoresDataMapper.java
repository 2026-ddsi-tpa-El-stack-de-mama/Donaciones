package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.IdentificadorDTO;
import ar.edu.utn.dds.k3003.model.Identificador;

import java.util.ArrayList;
import java.util.List;

public class IdentificadoresDataMapper {
      public IdentificadorDTO toIdentificadorDTO(Identificador identificador) {
    return new IdentificadorDTO(
        identificador.getId(),
        identificador.getTipo(),
        identificador.getDescripcion());
  }
/*
// REFERENCIA
    String id;
    TipoIdentificadorEnum tipo;
    String descripcion;
*/
  public Identificador toIdentificador(IdentificadorDTO identificadorDTO) {
    return new Identificador(
      identificadorDTO.id(),
        identificadorDTO.tipo(),
        identificadorDTO.descripcion());
  }

  public List <IdentificadorDTO> listToIdentificadorDTO(List<Identificador> lista){
    List<IdentificadorDTO> nuevaLista = new ArrayList<>();
    for(Identificador identificador: lista){
      nuevaLista.add(toIdentificadorDTO(identificador));
    }
    return nuevaLista;
  }
}
