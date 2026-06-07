package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.TipoIdentificadorEnum;

public class Identificador {
    String id;
    TipoIdentificadorEnum tipo;
    String descripcion;

    public Identificador(
        TipoIdentificadorEnum tipo,
        String descripcion) {
    this.tipo = tipo;
    this.descripcion = descripcion;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoIdentificadorEnum getTipo() {
        return tipo;
    }

  public String getDescripcion() {
    return descripcion;
  }
}