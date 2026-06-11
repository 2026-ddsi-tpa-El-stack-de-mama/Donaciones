package ar.edu.utn.dds.k3003.services;
import ar.edu.utn.dds.k3003.model.Identificador;
import ar.edu.utn.dds.k3003.repositories.IdentificadoresRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class IdentificadoresService {
    private final IdentificadoresRepository identificadorR;
    public IdentificadoresService(IdentificadoresRepository identificadorR){
        this.identificadorR = identificadorR;
    }
    public Optional<Identificador> getIdentificador(String id){
        return identificadorR.findById(id);
    }
}