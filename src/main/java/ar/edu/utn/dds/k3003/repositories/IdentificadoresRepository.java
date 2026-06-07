package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Identificador;

import java.util.Optional;
//import java.util.ArrayList;
import java.util.List;

public interface IdentificadoresRepository {
  Optional<Identificador> findById(String id);

  Identificador save(Identificador identificador);

  Identificador deleteById(String id);

  Identificador saveSinCambioID(Identificador Identificador);

  List<Identificador> buscarIdentificadores();
}
