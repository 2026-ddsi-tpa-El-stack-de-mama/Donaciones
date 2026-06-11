package ar.edu.utn.dds.k3003.model;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    String id;
    String nombre;
    String descripcion;
    String subcategoriaID;
}
