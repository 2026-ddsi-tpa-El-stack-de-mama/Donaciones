package ar.edu.utn.dds.k3003.services;
import ar.edu.utn.dds.k3003.model.Categoria;
import ar.edu.utn.dds.k3003.repositories.CategoriasRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriasService {
    private final CategoriasRepository categoriaR;
    public CategoriasService(CategoriasRepository categoriaR){
        this.categoriaR = categoriaR;
    }
    public Optional<Categoria> getCategoria(String id){
        return categoriaR.findById(id);
    }
}