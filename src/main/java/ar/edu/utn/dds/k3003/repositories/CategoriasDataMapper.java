package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.CategoriaDTO;
import ar.edu.utn.dds.k3003.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriasDataMapper {
      public CategoriaDTO toCategoriaDTO(Categoria categoria) {
    return new CategoriaDTO(
        categoria.getId(),
        categoria.getNombre(),
        categoria.getDescripcion(),
        categoria.getSubcategoriaID());
  }

  public Categoria toCategoria(CategoriaDTO categoriaDTO) {
    return new Categoria(
      categoriaDTO.id(),
        categoriaDTO.nombre(),
        categoriaDTO.descripcion(),
    categoriaDTO.subcategoriaID());
  }

  public List <CategoriaDTO> listToCategoriaDTO(List<Categoria> lista){
    List<CategoriaDTO> nuevaLista = new ArrayList<>();
    for(Categoria categoria: lista){
      nuevaLista.add(toCategoriaDTO(categoria));
    }
    return nuevaLista;
  }
 
}
