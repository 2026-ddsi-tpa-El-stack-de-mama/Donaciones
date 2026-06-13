package ar.edu.utn.dds.k3003.clients;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.QuejaDTO;


//Después poner bien el url
@FeignClient(name="donadoresYEntidades", url="https://donadores-y-entidades-ngp3.onrender.com")
public interface DonadoresYEntidadesClient {
    
    @GetMapping("/donadores/{id}/puede-donar")
    Boolean puedeDonar(@PathVariable String id);

    @GetMapping("/donadores/{id}")
    DonadorDTO buscarDonador(@PathVariable String id);

    @PostMapping("/donadores/{id}/quejas")
    QuejaDTO agregarQueja(@PathVariable String id, @RequestBody QuejaDTO quejaDTO);

}
