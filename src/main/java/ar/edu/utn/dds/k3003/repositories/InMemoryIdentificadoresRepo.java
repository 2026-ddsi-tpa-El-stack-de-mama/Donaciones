package ar.edu.utn.dds.k3003.repositories;
/*
import ar.edu.utn.dds.k3003.model.Identificador;
import ar.edu.utn.dds.k3003.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; //Agregada para poder filtrar y volver a lista
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import lombok.val;

public class InMemoryIdentificadoresRepo implements IdentificadoresRepository {

  private List<Identificador> identificadores;
  private AtomicLong idSecuencial = new AtomicLong(1);

  public InMemoryIdentificadoresRepo() {
    this.identificadores = new ArrayList<>();
  }

  @Override
  public Optional<Identificador> findById(String id) {
    return this.identificadores.stream().filter(d -> d.getId().equals(id)).findFirst();
  }

  @Override
  public Identificador save(Identificador identificador) {
    Identificador identificadorConID = identificador;
    identificadorConID.setId(String.valueOf(idSecuencial.getAndIncrement()));

    this.identificadores.add(identificadorConID);
    return this.findById(identificadorConID.getId()).get();
  }

  @Override
  public Identificador deleteById(String id) {
    val identificador = this.findById(id);
    this.identificadores.remove(identificador.get());
    return identificador.get();
  }

  public Identificador saveSinCambioID(Identificador identificador) {
    this.identificadores.add(identificador);
    return this.findById(identificador.getId()).get();
  }

  public List<Identificador> buscarIdentificadores(){
    return identificadores;
  }
}
*/