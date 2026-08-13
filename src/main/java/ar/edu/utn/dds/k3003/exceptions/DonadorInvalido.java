package ar.edu.utn.dds.k3003.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DonadorInvalido extends ResponseStatusException {
  public DonadorInvalido(String mensaje) {
    super(HttpStatus.BAD_REQUEST,mensaje);
  }
}
