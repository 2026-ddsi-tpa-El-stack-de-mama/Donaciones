package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Categoria;

import java.util.Optional;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categoria, String>{
/*
  Optional<Categoria> findById(String id);

  Categoria save(Categoria donador);

  void deleteById(String id);

  Categoria saveSinCambioID(Categoria Categoria);

  List<Categoria> buscarCategorias();
*/
  }