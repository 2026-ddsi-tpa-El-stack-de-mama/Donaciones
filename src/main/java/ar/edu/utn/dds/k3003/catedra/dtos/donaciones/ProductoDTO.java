package ar.edu.utn.dds.k3003.catedra.dtos.donaciones;

import ar.edu.utn.dds.k3003.model.Categoria;
import ar.edu.utn.dds.k3003.model.Identificador;
public record ProductoDTO(
    String id, String nombre, String descripcion, Categoria categoriaID, Identificador identificadorID) {}
