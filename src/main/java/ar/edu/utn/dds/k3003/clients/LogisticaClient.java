package ar.edu.utn.dds.k3003.clients;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.utn.dds.k3003.catedra.dtos.logistica.PaqueteDTO;


//Después poner bien el url
@FeignClient(name="logistica", url="https://logistica-jc94.onrender.com")
public interface LogisticaClient {
    @PostMapping("/depositos/{id}/donacion")
    String postDonacion(@PathVariable String id, @RequestBody PaqueteDTO paquete);

}
