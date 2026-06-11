package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Donacion;
import ar.edu.utn.dds.k3003.model.Identificador;

import java.util.Optional;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentificadoresRepository extends JpaRepository<Identificador, String>{
  /*
  Optional<Identificador> findById(String id);

  Identificador save(Identificador identificador);

  void deleteById(String id);

  Identificador saveSinCambioID(Identificador Identificador);

  List<Identificador> buscarIdentificadores();
  */
}
