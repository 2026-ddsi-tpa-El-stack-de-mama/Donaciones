package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Donacion;

import java.time.LocalDate;
import java.util.Optional;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionesRepository extends JpaRepository<Donacion, String>{
/*  
Optional<Donacion> findById(String id);

  Donacion save(Donacion donador);

  //Antes devolvía Donacion
  void deleteById(String id);

  List<Donacion> buscarPorDonadorYFechaInicio(String donadorID, LocalDate fecha);

  

  List<Donacion> buscarDonaciones();
  */
 Donacion saveSinCambioID(Donacion donacion);
 List<Donacion> buscarPorDonadorYFechaInicio(String donadorID, LocalDate fecha);
}
