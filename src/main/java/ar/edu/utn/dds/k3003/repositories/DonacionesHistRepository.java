package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.DonacionHist;

import java.time.LocalDate;
import java.util.Optional;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionesHistRepository extends JpaRepository<DonacionHist, String>{

}