package ar.edu.utn.dds.k3003.exceptions;

public class TransicionNoValida extends RuntimeException {
  public TransicionNoValida(String mensaje) {
    super(mensaje);
  }
}