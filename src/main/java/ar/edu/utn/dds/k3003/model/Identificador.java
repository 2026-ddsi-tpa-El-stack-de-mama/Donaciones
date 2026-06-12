package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.TipoIdentificadorEnum;

import jakarta.persistence.*;

@Entity
@Table(name = "identificadores")
public class Identificador {
    @Id
    String id;
    @Enumerated(EnumType.STRING)
    TipoIdentificadorEnum tipo;
    String descripcion;

    public Identificador(
        String id,
        TipoIdentificadorEnum tipo,
        String descripcion) {
    this.id=id;
    this.tipo = tipo;
    this.descripcion = descripcion;
    }
    public Identificador(){
        
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