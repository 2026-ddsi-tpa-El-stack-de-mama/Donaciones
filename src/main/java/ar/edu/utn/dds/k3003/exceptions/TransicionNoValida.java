package ar.edu.utn.dds.k3003.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransicionNoValida extends ResponseStatusException {
  public TransicionNoValida(String mensaje) {
    super(HttpStatus.BAD_REQUEST, mensaje);
  }
}