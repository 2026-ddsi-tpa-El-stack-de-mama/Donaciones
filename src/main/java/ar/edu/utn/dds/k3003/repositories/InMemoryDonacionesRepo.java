package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.EstadoAsginacionEnum;
import ar.edu.utn.dds.k3003.model.Donacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; //Agregada para poder filtrar y volver a lista
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import lombok.val;

public class InMemoryDonacionesRepo implements DonacionesRepository {

  private List<Donacion> donaciones;
  private AtomicLong idSecuencial = new AtomicLong(1);

  public InMemoryDonacionesRepo() {
    this.donaciones = new ArrayList<>();
  }

  @Override
  public Optional<Donacion> findById(String id) {
    return this.donaciones.stream().filter(d -> d.getId().equals(id)).findFirst();
  }

  @Override
  public Donacion save(Donacion donacion) {
    Donacion donacionConID = donacion;
    donacionConID.setId(String.valueOf(idSecuencial.getAndIncrement()));
    donacionConID.setEstado(EstadoDonacionEnum.INGRESADA);

    this.donaciones.add(donacionConID);
    return this.findById(donacionConID.getId()).get();
  }

  @Override
  public Donacion deleteById(String id) {
    val donacion = this.findById(id);
    this.donaciones.remove(donacion.get());
    return donacion.get();
  }

  @Override  
  public List<Donacion> buscarPorDonadorYFechaInicio(String donadorID, LocalDate fecha){
    List<Donacion> donacionesPosibles = this.donaciones.stream().filter(
         d -> d.getDonadorID().equals(donadorID) && d.getFechaInicio().isAfter(fecha)
    ).toList();
    return donacionesPosibles;
  }
/*
  // Método desesperado, probablemente debería sacarlo después
  public Donacion cambiarEstadoDonacion(String id, EstadoAsginacionEnum estado){
    Integer posicion = this.donaciones.indexOf(findById(id));
    this.donaciones.set(posicion, findById(id).setEstado(estado));


  }
*/
  public Donacion saveSinCambioID(Donacion donacion) {
    this.donaciones.add(donacion);
    return this.findById(donacion.getId()).get();
  }

  public List<Donacion> buscarDonaciones(){
    return donaciones;
  }
}
