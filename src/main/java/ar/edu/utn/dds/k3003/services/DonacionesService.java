package ar.edu.utn.dds.k3003.services;
import ar.edu.utn.dds.k3003.model.Donacion;
import ar.edu.utn.dds.k3003.repositories.DonacionesRepository;
import ar.edu.utn.dds.k3003.repositories.IdentificadoresRepository;
import ar.edu.utn.dds.k3003.repositories.ProductosRepository;
import ar.edu.utn.dds.k3003.repositories.CategoriasRepository;

import org.springframework.stereotype.Service;
import ar.edu.utn.dds.k3003.Fachada;
import java.util.List;
import java.util.Optional;
@Service
public class DonacionesService {
    private final Fachada fachada;
    private final DonacionesRepository donacionR;
    private final ProductosRepository productoR;
    private final IdentificadoresRepository identificadorR;
    private final CategoriasRepository categoriaR;


    public DonacionesService(Fachada fachada, DonacionesRepository donacionR, ProductosRepository productoR, IdentificadoresRepository identificadorR, CategoriasRepository categoriaR){
        this.fachada = fachada;
        this.donacionR = donacionR;
        this.productoR = productoR;
        this.identificadorR = identificadorR;
        this.categoriaR = categoriaR;
    }
    public Optional<Donacion> getDonacion(String id){
        return donacionR.findById(id);
    }

    public List<Donacion> getDonaciones(){
        return donacionR.findAll();
    }

    public Donacion postDonacion(Donacion donacion){
        donacionR.save(donacion);
        return donacion;
    }

    public Optional<Donacion> deleteDonacion(String id) {
        Optional<Donacion> donacion = donacionR.findById(id);
        donacionR.deleteById(id);
        return donacion;
    }
}
