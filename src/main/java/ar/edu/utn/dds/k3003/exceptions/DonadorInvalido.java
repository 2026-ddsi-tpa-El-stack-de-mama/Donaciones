package ar.edu.utn.dds.k3003.exceptions;

public class DonadorInvalido extends RuntimeException {
  public DonadorInvalido(String mensaje) {
    super(mensaje);
  }
}
