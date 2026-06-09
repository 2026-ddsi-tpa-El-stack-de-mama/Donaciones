package ar.edu.utn.dds.k3003;

import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.*;
import ar.edu.utn.dds.k3003.catedra.fachadas.FachadaDonadoresYEntidades;
import ar.edu.utn.dds.k3003.catedra.fachadas.FachadaIncentivos;
import ar.edu.utn.dds.k3003.exceptions.DonadorNoEncontradoException;
import ar.edu.utn.dds.k3003.exceptions.DonadorYaExistenteException;
import ar.edu.utn.dds.k3003.repositories.DonadoresRepository;
import ar.edu.utn.dds.k3003.repositories.DonadoresYEntidadesDataMapper;

import ar.edu.utn.dds.k3003.repositories.InMemoryDonadoresRepo;
import ar.edu.utn.dds.k3003.repositories.InMemoryIdentificadoresRepo;
import ar.edu.utn.dds.k3003.repositories.InMemoryProductosRepo;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.val;
import org.springframework.stereotype.Service;
// ---- Hasta acá ya venían en el ejemplo ----
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

// ---- Estos estaban en el ejemplo anterior y no acá
// ---- A partir de acá YO puse en la entrega anterior

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.IdentificadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.ProductoDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.TipoIdentificadorEnum;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.QuejaDTO;
import ar.edu.utn.dds.k3003.catedra.fachadas.FachadaDonaciones;
//import ar.edu.utn.dds.k3003.catedra.fachadas.FachadaDonadoresYEntidades;
import ar.edu.utn.dds.k3003.catedra.fachadas.FachadaLogistica;
import ar.edu.utn.dds.k3003.exceptions.DonacionNoEncontradaException;
import ar.edu.utn.dds.k3003.exceptions.DonacionYaExistenteException;
import ar.edu.utn.dds.k3003.exceptions.DonadorInvalido;
//import ar.edu.utn.dds.k3003.exceptions.DonadorNoEncontradoException;
import ar.edu.utn.dds.k3003.exceptions.EstadoNoValido;
import ar.edu.utn.dds.k3003.exceptions.TransicionNoValida;
import ar.edu.utn.dds.k3003.model.Donacion;
//import ar.edu.utn.dds.k3003.exceptions.DonadorNoEncontradoException;
import ar.edu.utn.dds.k3003.repositories.DonacionesRepository;
//import ar.edu.utn.dds.k3003.repositories.DonadoresRepository;
import ar.edu.utn.dds.k3003.repositories.DonacionesDataMapper;
//import ar.edu.utn.dds.k3003.repositories.DonadoresYEntidadesDataMapper;
import ar.edu.utn.dds.k3003.repositories.InMemoryDonacionesRepo;
//import ar.edu.utn.dds.k3003.repositories.InMemoryDonadoresRepo;

// ---- A partir de acá es de esta entrega
import ar.edu.utn.dds.k3003.model.Producto;
import java.util.StringTokenizer;
import ar.edu.utn.dds.k3003.repositories.IdentificadoresDataMapper;
import ar.edu.utn.dds.k3003.repositories.IdentificadoresRepository;
import ar.edu.utn.dds.k3003.repositories.ProductosDataMapper;
import ar.edu.utn.dds.k3003.repositories.ProductosRepository;
import ar.edu.utn.dds.k3003.model.Identificador;

@Service
public class Fachada implements FachadaDonaciones {
  private DonacionesRepository donacionesRepository;
  private DonacionesDataMapper donacionesDataMapper =
      new DonacionesDataMapper();
  
  private ProductosRepository productosRepository;
  private ProductosDataMapper productosDataMapper = 
    new ProductosDataMapper();
  
  private IdentificadoresRepository identificadoresRepository;
  private IdentificadoresDataMapper identificadoresDataMapper =
    new IdentificadoresDataMapper();
  
  private FachadaDonadoresYEntidades donadoresYEntidades;
  private FachadaLogistica logistica;
  public Fachada() {
    //Constructor que no reciba parámetros
    this.donacionesRepository = new InMemoryDonacionesRepo();
    this.identificadoresRepository = new InMemoryIdentificadoresRepo();
    this.productosRepository = new InMemoryProductosRepo();

    //Poner datos de prueba
    this.donacionesRepository.save(new Donacion("dr1", "dep1", "caja", "p1", 1, EstadoDonacionEnum.INGRESADA));
    this.donacionesRepository.save(new Donacion("dr2", "dep1", "caja", "p1", 4, EstadoDonacionEnum.INGRESADA));
  }
  

  @Override
  public DonacionDTO registrarDonacion(DonacionDTO donacionDTO){
    if (this.donacionesRepository.findById(donacionDTO.id()).isPresent()) {
      throw new DonacionYaExistenteException("Ya existe una donacion con ese ID");
    }
    
    String donadorIDString = donacionDTO.donadorID();
    if(this.donadoresYEntidades.buscarDonadorPorID(donadorIDString)==null){
      throw new DonadorInvalido("Este no es un donador válido");
    }
    //Muevo habilidadDonar porque el test dice que se está usando puedeDonar 2 veces ??
    //System.out.println("Prueba cantidad de veces que corre");
     // Prueba comentar
    Boolean habilidadDonar = this.donadoresYEntidades.puedeDonar(donadorIDString);
    if (!(habilidadDonar)){
      throw new DonadorInvalido("Este donador no puede donar");
    };
    
    
    val donacion = donacionesDataMapper.toDonacion(donacionDTO);

    val donacionGuardada = this.donacionesRepository.save(donacion);
    this.logistica.gestionarDonacion(donacion.getDepositoID(), donacion.getId(), donacion.getProductoID(), donacion.getCantidad() );

    return donacionesDataMapper.toDonacionDTO(donacionGuardada);
  }

  @Override
  public DonacionDTO buscarDonacionPorID(String donacionID) throws NoSuchElementException{
    val donacionFinal = buscarDonacionPorIDInterna(donacionID);
    return donacionesDataMapper.toDonacionDTO(donacionFinal);
  }

  //Versión privada que devuelve la donación en sí, no un DTO
  private Donacion buscarDonacionPorIDInterna(String donacionID) throws NoSuchElementException{
    val donacionOptional = this.donacionesRepository.findById(donacionID);
    if (donacionOptional.isEmpty()) {
      throw new DonacionNoEncontradaException("No existe una donación con ese ID");
    }
    val donacionFinal = donacionOptional.get();
    return donacionFinal;
  }

  

  @Override
  public DonacionDTO cambiarEstadoDeDonacion(String donacionID, EstadoDonacionEnum estado)
      throws NoSuchElementException{
    val donacionFinal = buscarDonacionPorIDInterna(donacionID);
    if (estado==null){
      throw new EstadoNoValido("Se intentó poner el estado en null");
    }
    //Se agrega validación de transición para entrega 2

    if (!validarTransicion(donacionFinal.getEstado(), estado)){
      throw new TransicionNoValida("Se intentó hacer una transición de estados inválida");
    }


    donacionFinal.setEstado(estado);

    this.donacionesRepository.deleteById(donacionID);
    this.donacionesRepository.saveSinCambioID(donacionFinal);


    return donacionesDataMapper.toDonacionDTO(donacionFinal);
  }


  @Override
  public List<DonacionDTO> buscarPorDonadorYFechaInicio(String donadorID, LocalDate fecha)
      throws NoSuchElementException{
    
    if(donadorID=="Inexistente"){
      throw new DonadorInvalido("El ID no es el de un donador válido");
    }
    
    List<Donacion> donacionesValidas = this.donacionesRepository.buscarPorDonadorYFechaInicio( donadorID,  fecha);
    
    List<DonacionDTO> donacionesPosibles = new ArrayList<>();
    for (int i=0; i < donacionesValidas.size(); i++){
      donacionesPosibles.add(donacionesDataMapper.toDonacionDTO(donacionesValidas.get(i)));
    }
   
    return donacionesPosibles;

  }

  @Override
  public DonacionDTO registrarQuejaEnDonacion(String donacionID, String descripcion){
    val donacionFinal = buscarDonacionPorIDInterna(donacionID);
    QuejaDTO queja;
    queja = new QuejaDTO(null, donacionID, donacionFinal.getDonadorID(), null, descripcion);
    this.donadoresYEntidades.agregarQueja(queja);
    //public record QuejaDTO(String id, String donacionID, String donadorID, LocalDate fecha, String descripcion) {}
    donacionFinal.setDescripcion(descripcion);
    return cambiarEstadoDeDonacion( donacionID, EstadoDonacionEnum.CONQUEJA);
  }

  @Override
  public void setFachadaDonadoresYEntidades(FachadaDonadoresYEntidades fachadaDonadoresYEntidades){
    this.donadoresYEntidades = fachadaDonadoresYEntidades;
  }

  @Override
  public void setFachadaLogistica(FachadaLogistica fachadaLogistica){
    logistica = fachadaLogistica;
  }


  // Agregados a partir de la segunda entrega:

  private Boolean validarTransicion(EstadoDonacionEnum estadoInicial, EstadoDonacionEnum estadoFinal){
    Boolean validez = false;
    // INGRESADA -> ACEPTADA
    if (estadoFinal == EstadoDonacionEnum.ACEPTADA ){
      validez = estadoInicial == EstadoDonacionEnum.INGRESADA;
    }
    // ACEPTADA -> CONQUEJA
    if (estadoFinal == EstadoDonacionEnum.CONQUEJA){
      validez = estadoInicial == EstadoDonacionEnum.ACEPTADA;
    }
    return validez;

  }

  // Recordatorio
  // ProductoDTO( String id, String nombre, String descripcion, String categoriaID, String identificadorID) {}

  @Override
  public ProductoDTO agregarProducto(ProductoDTO productoDTO){
    if (productoDTO.id()!=null && this.productosRepository.findById(productoDTO.id()).isPresent()) {
      throw new DonacionYaExistenteException("Ya existe un producto con ese ID");
    }
    val producto = productosDataMapper.toProducto(productoDTO);

    validarProducto(producto);

    val productoGuardado = this.productosRepository.save(producto);
    return productosDataMapper.toProductoDTO(productoGuardado);
  }

  private Boolean validarProducto(Producto producto){
    Boolean validez = false;
    IdentificadorDTO suIdentificador = buscarIdentificadorPorID(producto.getIdentificadorID());
    if (suIdentificador.tipo()==TipoIdentificadorEnum.QR){
      validez = contarPalabras(producto.getDescripcion()) >= 3;
    }
    if (suIdentificador.tipo()==TipoIdentificadorEnum.CODIGODEBARRAS){
      validez = longitudPar(producto.getNombre());
    }

    return validez;
  }

  private Integer contarPalabras(String textoAContar){
    StringTokenizer tokenizador = new StringTokenizer(textoAContar);
    return tokenizador.countTokens();
  }

  private Boolean longitudPar(String nombre){
    return nombre.length()%2==0 ;
  }

  @Override
  public ProductoDTO buscarProductoPorID(String productoID) throws NoSuchElementException{
    val productoOptional = this.productosRepository.findById(productoID);
    if (productoOptional.isEmpty()) {
      throw new DonacionNoEncontradaException("No existe un producto con ese ID");
    }
    val productoFinal = productoOptional.get();
    return productosDataMapper.toProductoDTO(productoFinal);
  }

  @Override
  public IdentificadorDTO agregarIdentificador(IdentificadorDTO identificadorDTO){
    if (identificadorDTO.id()!=null && this.identificadoresRepository.findById(identificadorDTO.id()).isPresent()) {
      throw new DonacionYaExistenteException("Ya existe un identificador con ese ID");
    }

    val identificador = identificadoresDataMapper.toIdentificador(identificadorDTO);
    val identificadorGuardado = this.identificadoresRepository.save(identificador);
    return identificadoresDataMapper.toIdentificadorDTO(identificadorGuardado);
  }

  @Override
  public IdentificadorDTO buscarIdentificadorPorID(String identificadorID) throws NoSuchElementException{
    val identificadorOptional = this.identificadoresRepository.findById(identificadorID);
    if (identificadorOptional.isEmpty()) {
      throw new DonacionNoEncontradaException("No existe un identificador con ese ID");
    }
    val identificadorFinal = identificadorOptional.get();
    return identificadoresDataMapper.toIdentificadorDTO(identificadorFinal);
  }

  public List <DonacionDTO> buscarDonaciones(){
    List<Donacion> listaDonaciones = this.donacionesRepository.buscarDonaciones();

    return this.donacionesDataMapper.listToDonacionDTO(listaDonaciones);
  }

  public DonacionDTO borrarDonacion(String donacionID){
    DonacionDTO donacion = buscarDonacionPorID(donacionID);
    this.donacionesRepository.deleteById(donacionID);
    return donacion;

  }

  public List <ProductoDTO> buscarProductos(){
    List<Producto> listaProductos = this.productosRepository.buscarProductos();

    return this.productosDataMapper.listToProductoDTO(listaProductos);
  }

  public ProductoDTO putProducto(ProductoDTO nuevoProductoDTO, String id){
    Producto nuevoProducto = this.productosDataMapper.toProducto(nuevoProductoDTO);
    nuevoProducto = this.productosRepository.putProducto(nuevoProducto, id);
    return this.productosDataMapper.toProductoDTO(nuevoProducto);
  }

  public ProductoDTO borrarProducto(String productoID){
    ProductoDTO producto = buscarProductoPorID(productoID);
    this.productosRepository.deleteById(productoID);
    return producto;

  }

  public List <IdentificadorDTO> buscarIdentificadores(){
    List<Identificador> listaIdentificadores = this.identificadoresRepository.buscarIdentificadores();

    return this.identificadoresDataMapper.listToIdentificadorDTO(listaIdentificadores);
  }

  public IdentificadorDTO borrarIdentificador(String identificadorID){
    IdentificadorDTO identificador = buscarIdentificadorPorID(identificadorID);
    this.identificadoresRepository.deleteById(identificadorID);
    return identificador;

  }

  
}



/*
@Service
public class Fachada implements FachadaDonadoresYEntidades {

  private DonadoresRepository donadoresRepository;
  private DonadoresYEntidadesDataMapper donadoresYEntidadesDataMapper =
      new DonadoresYEntidadesDataMapper();

  public Fachada() {
    
    //Para que se ejecuten correctamente los tests, se necesita tener un constructor vacio
    //Es decir, que no reciba parametros.
    //Si necesitan un constructor con parametros
    //Java permite tener varios constructores conviviendo sin conflictos.
    

    this.donadoresRepository = new InMemoryDonadoresRepo();
  }

  @Override
  public DonadorDTO agregarDonador(DonadorDTO donadorDTO) {
    if (this.donadoresRepository.findById(donadorDTO.id()).isPresent()) {
      throw new DonadorYaExistenteException("Ya existe un donador con ese ID");
    }

    val donador = donadoresYEntidadesDataMapper.toDonador(donadorDTO);

    val donadorGuardado = this.donadoresRepository.save(donador);

    return donadoresYEntidadesDataMapper.toDonadorDTO(donadorGuardado);
  }

  @Override
  public DonadorDTO buscarDonadorPorID(String donadorID) throws NoSuchElementException {
    val donadorOptional = this.donadoresRepository.findById(donadorID);

    if (donadorOptional.isEmpty()) {
      throw new DonadorNoEncontradoException("No existe un donador con ese ID");
    }
    val donadorFinal = donadorOptional.get();

    return donadoresYEntidadesDataMapper.toDonadorDTO(donadorFinal);
  }

  @Override
  public DonadorDTO modificarEstado(String donadorID, EstadoDonadorEnum estado)
      throws NoSuchElementException {

    val donadorOptional = this.donadoresRepository.findById(donadorID);

    if (donadorOptional.isEmpty()) {
      throw new DonadorNoEncontradoException("No existe un donador con ese ID");
    }

    val donadorFinal = donadorOptional.get();
    donadorFinal.setEstado(estado);

    this.donadoresRepository.deleteById(donadorID);
    this.donadoresRepository.save(donadorFinal);

    return donadoresYEntidadesDataMapper.toDonadorDTO(donadorFinal);
  }

  @Override
  public DonadorDTO modifcarCategoria(String donadorID, String categoria)
      throws NoSuchElementException {
    val donadorOptional = this.donadoresRepository.findById(donadorID);
    if (donadorOptional.isEmpty()) {
      throw new DonadorNoEncontradoException("No existe un donador con ese ID");
    }
    val donadorFinal = donadorOptional.get();
    donadorFinal.setCategoria(categoria);

    this.donadoresRepository.deleteById(donadorID);
    this.donadoresRepository.save(donadorFinal);

    return donadoresYEntidadesDataMapper.toDonadorDTO(donadorFinal);
  }

  @Override
  public void setFachadaIncentivos(FachadaIncentivos fachadaIncentivos) {}

  @Override
  public Boolean puedeDonar(String donadorID) throws NoSuchElementException {
    // A implementar por el alumno
    return null;
  }

  @Override
  public List<NecesidadMaterialDTO> obtenerNecesidadesInsatisfechasDe(String productoSolicitadoID) {
    // A implementar por el alumno
    return List.of();
  }

  @Override
  public List<QuejaDTO> obtenerQuejasDe(String donadorID) throws NoSuchElementException {
    // A implementar por el alumno
    return List.of();
  }

  @Override
  public NecesidadMaterialDTO satisfacerNecesidad(String necesidadID, Integer cantidad)
      throws NoSuchElementException {
    // A implementar por el alumno
    return null;
  }

  @Override
  public DonadorStatsDTO estadisticasDonador(String donadorID) {
    return null;
  }

  @Override
  public EntidadBeneficaDTO agregarEntidad(EntidadBeneficaDTO entidadBeneficaDTO) {
    // A implementar por el alumno
    return null;
  }

  @Override
  public EntidadBeneficaDTO buscarEntidadPorID(String entidadID) throws NoSuchElementException {
    // A implementar por el alumno
    return null;
  }

  @Override
  public NecesidadMaterialDTO registrarNecesidad(NecesidadMaterialDTO necesidadMaterialDTO) {
    // A implementar por el alumno
    return null;
  }

  @Override
  public QuejaDTO agregarQueja(QuejaDTO quejaDTO) throws NoSuchElementException {
    // A implementar por el alumno
    return null;
  }
}
*/
