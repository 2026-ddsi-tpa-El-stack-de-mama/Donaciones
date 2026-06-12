package ar.edu.utn.dds.k3003.model;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.CategoriaDTO;
import ar.edu.utn.dds.k3003.exceptions.DonacionYaExistenteException;
import jakarta.persistence.*;
import lombok.val;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    String id;
    String nombre;
    String descripcion;
    String subcategoriaID;


    public Categoria(
        String id,
        String nombre,
        String descripcion,
        String subcategoriaID
    ){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.subcategoriaID=subcategoriaID;
    }
    public Categoria(){

    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getDescripcion(){
        return this.descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }

    public String getSubcategoriaID(){
        return this.id;
    }
    public void setSubcategoriaID(String subcategoriaID){
        this.subcategoriaID=subcategoriaID;
    }

}
