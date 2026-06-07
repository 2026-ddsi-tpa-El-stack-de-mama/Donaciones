package ar.edu.utn.dds.k3003.exceptions;

public class DonacionYaExistenteException extends RuntimeException {
  public DonacionYaExistenteException(String mensaje) {
    super(mensaje);
  }
}
