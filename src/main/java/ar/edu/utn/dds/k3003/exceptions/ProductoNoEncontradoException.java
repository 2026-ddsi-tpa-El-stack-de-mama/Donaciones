package ar.edu.utn.dds.k3003.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductoNoEncontradoException extends ResponseStatusException {
  public ProductoNoEncontradoException(String mensaje) {
    super(HttpStatus.NOT_FOUND, mensaje);
  }
}