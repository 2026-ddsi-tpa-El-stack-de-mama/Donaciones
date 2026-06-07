package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Donacion;

import java.time.LocalDate;
import java.util.Optional;
//import java.util.ArrayList;
import java.util.List;

public interface DonacionesRepository {
  Optional<Donacion> findById(String id);

  Donacion save(Donacion donador);

  Donacion deleteById(String id);

  List<Donacion> buscarPorDonadorYFechaInicio(String donadorID, LocalDate fecha);

  Donacion saveSinCambioID(Donacion donacion);

  List<Donacion> buscarDonaciones();
}
