package ar.edu.utn.dds.k3003.model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.ClassFinder;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.QuejaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.DepositoDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.TipoAlgoritmoEnum;
import ar.edu.utn.dds.k3003.catedra.fachadas.FachadaDonaciones;
import ar.edu.utn.dds.k3003.catedra.fachadas.FachadaDonadoresYEntidades;
import ar.edu.utn.dds.k3003.catedra.fachadas.FachadaLogistica;
import java.time.LocalDate;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@EnabledIf("ar.edu.utn.dds.k3003.catedra.donaciones.DonacionesTest#condicion")
public class DonacionesTestOriginales{
    
  FachadaDonaciones instancia;
  @Mock FachadaDonadoresYEntidades fachadaDonadoresYEntidades;
  @Mock FachadaLogistica fachadaLogistica;

  DonadorDTO donadorEjemplo;
  DonacionDTO donacionEjemplo;
  DonacionDTO donacionAceptadaEjemplo;
  QuejaDTO quejaEjemplo;

  @SneakyThrows
  @BeforeEach
  void setUp() {

    var clazz = ClassFinder.findClass();
    instancia = (FachadaDonaciones) clazz.getDeclaredConstructor().newInstance();

    instancia.setFachadaDonadoresYEntidades(fachadaDonadoresYEntidades);
    instancia.setFachadaLogistica(fachadaLogistica);

    donacionEjemplo =
        new DonacionDTO(
            null,
            "donador1",
            "deposito1",
            "descripcion1",
            "producto1",
            5,
            EstadoDonacionEnum.INGRESADA);
    donacionAceptadaEjemplo =
        new DonacionDTO(
            null,
            "donador1",
            "deposito1",
            "descripcion1",
            "producto1",
            5,
            EstadoDonacionEnum.ACEPTADA);
    donadorEjemplo =
        new DonadorDTO(
            "donador1",
            "donador1",
            "donador1",
            5,
            "donador1",
            "donador1",
            "donador1",
            null,
            "donador1");
    quejaEjemplo = new QuejaDTO(null, "donacion1", "donador1", null, "descripcion1");
  }

  static boolean condicion() {

    return FachadaDonaciones.class.isAssignableFrom(Fachada.class);
  }
  
@Test
  void testRegistrarDonacionBuscarDevolvioNull() {

    when(fachadaDonadoresYEntidades.buscarDonadorPorID(donadorEjemplo.id()))
        .thenReturn(null);
        Assertions.assertThrows(
        RuntimeException.class,
        () -> {
          instancia.registrarDonacion(donacionEjemplo);
        });

  }


@Test
  void testCambiarEstadoDeDonacionConQueja() {

    when(fachadaDonadoresYEntidades.buscarDonadorPorID(donadorEjemplo.id()))
        .thenReturn(donadorEjemplo);
    when(fachadaDonadoresYEntidades.puedeDonar(donadorEjemplo.id())).thenReturn(Boolean.TRUE);
    when(fachadaLogistica.gestionarDonacion(any(), any(), any(), anyInt()))
        .thenReturn(new DepositoDTO("deposito1", TipoAlgoritmoEnum.PRIORIDAD_POR_SCORE, "nombre1", "unaDireccion",1000, null));

    DonacionDTO retorno = instancia.registrarDonacion(donacionEjemplo);
    retorno = instancia.cambiarEstadoDeDonacion(retorno.id(), EstadoDonacionEnum.ACEPTADA);

    DonacionDTO actualizada =
        instancia.cambiarEstadoDeDonacion(retorno.id(), EstadoDonacionEnum.CONQUEJA);

    Assertions.assertNotNull(actualizada);
    Assertions.assertEquals(EstadoDonacionEnum.CONQUEJA, actualizada.estado());

    Assertions.assertEquals(actualizada.id(), retorno.id());

    Assertions.assertEquals(
        EstadoDonacionEnum.CONQUEJA, instancia.buscarDonacionPorID(retorno.id()).estado());
  }

  
  @Test
  void testBuscarPorDonadorYFechaInicioVacio() {

    when(fachadaDonadoresYEntidades.buscarDonadorPorID(donadorEjemplo.id()))
        .thenReturn(donadorEjemplo);
    when(fachadaDonadoresYEntidades.puedeDonar(donadorEjemplo.id())).thenReturn(Boolean.TRUE);
    when(fachadaLogistica.gestionarDonacion(any(), any(), any(), anyInt()))
        .thenReturn(new DepositoDTO("deposito1", TipoAlgoritmoEnum.PRIORIDAD_POR_SCORE, "nombre1", "unaDireccion",1000, null));

    DonacionDTO retorno = instancia.registrarDonacion(donacionEjemplo);

    List<DonacionDTO> resultado =
        instancia.buscarPorDonadorYFechaInicio(retorno.donadorID(), LocalDate.now());

    Assertions.assertNotNull(resultado);
    Assertions.assertFalse(resultado.stream().anyMatch(d -> d.id().equals(retorno.id())));
    Assertions.assertEquals(0, resultado.size());

    verify(fachadaDonadoresYEntidades, times(1)).puedeDonar(donadorEjemplo.id());
    verify(fachadaDonadoresYEntidades, times(1)).buscarDonadorPorID(donadorEjemplo.id());
    verify(fachadaLogistica, times(1)).gestionarDonacion(any(), any(), any(), anyInt());
  }


  
  @Test
  void testRegistrarQuejaNecesitaPasarPorACEPTADA() {

    when(fachadaDonadoresYEntidades.buscarDonadorPorID(donadorEjemplo.id()))
        .thenReturn(donadorEjemplo);
    when(fachadaDonadoresYEntidades.puedeDonar(donadorEjemplo.id())).thenReturn(Boolean.TRUE);
    when(fachadaLogistica.gestionarDonacion(any(), any(), any(), anyInt()))
        .thenReturn(new DepositoDTO("deposito1", TipoAlgoritmoEnum.PRIORIDAD_POR_SCORE, "nombre1", "unaDireccion",1000, null));

    DonacionDTO retornoD = instancia.registrarDonacion(donacionEjemplo);

    when(fachadaDonadoresYEntidades.agregarQueja(any()))
        .thenReturn(
            new QuejaDTO("queja1", retornoD.id(), retornoD.donadorID(), null, "descripcion1"));

        

    Assertions.assertThrows(
        RuntimeException.class,
        () -> {
          instancia.registrarQuejaEnDonacion(retornoD.id(), quejaEjemplo.descripcion());
        });

    verify(fachadaDonadoresYEntidades, times(1)).puedeDonar(donadorEjemplo.id());
    verify(fachadaDonadoresYEntidades, times(1)).buscarDonadorPorID(donadorEjemplo.id());
    verify(fachadaLogistica, times(1)).gestionarDonacion(any(), any(), any(), anyInt());
    verify(fachadaDonadoresYEntidades, times(1)).agregarQueja(any());
  }






}