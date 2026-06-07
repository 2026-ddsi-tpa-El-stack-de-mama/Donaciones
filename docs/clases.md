classDiagram
    FachadaDonaciones <|.. Fachada
    Fachada : +DonacionesRepository donacionesRepository
    Fachada : +DonacionesDataMapper donacionesDataMapper
    Fachada : +FachadaDonadoresYEntidades donadoresYEntidades
    Fachada : +FachadaLogistica logistica
    
    Fachada : + Fachada()
    Fachada : + buscarDonacionPorIDInterna(donacionID .. String) DonacionDTO


    FachadaDonaciones : +registrarDonacion(donacionDTO .. DonacionDTO) DonacionDTO
    FachadaDonaciones : +buscarDonacionPorID(donacionID .. String) DonacionDTO
    FachadaDonaciones : +registrarQuejaEnDonacion(donacionID .. String, descripcion .. String) DonacionDTO
    FachadaDonaciones : +cambiarEstadoDeDonacion(donacionID .. String, estado .. EstadoDonacionEnum) DonacionDTO
    FachadaDonaciones : +buscarPorDonadorYFechaInicio(donacionID .. String, fecha .. LocalDate) List<DonacionDTO>
    FachadaDonaciones : +setFachadaDonadoresYEntidades(fachadaDonadoresYEntidades .. FachadaDonadoresYEntidades) void
    FachadaDonaciones : +setFachadaLogistica(fachadaLogistica .. FachadaLogistica) void
    
    FachadaLogistica <-- Fachada
    FachadaDonadoresYEntidades <-- Fachada
    
    FachadaLogistica : +gestionarDonacion(depositoID .. String, donacionID .. String, productoID .. String, cantidad .. Integer) DepositoDTO
    
    FachadaDonadoresYEntidades: +buscarDonadorPorID(donadorID .. String) DonadorDTO
    FachadaDonadoresYEntidades: +agregarQueja(queja .. QuejaDTO) QuejaDTO
    FachadaDonadoresYEntidades: +puedeDonar(donadorID .. String) Boolean
    
    DonacionesRepository <-- Fachada
    DonacionesRepository: +findById(id .. String) Optional<Donacion>
    DonacionesRepository: +save(donador .. Donacion) Donacion
    DonacionesRepository: + deleteById(id .. String) Donacion
    DonacionesRepository: +buscarPorDonadorYFechaDeInicio(donadorID .. String, fecha .. LocalDate) List<Donacion>
    DonacionesRepository: saveSinCambioID(donador .. Donacion) Donacion

    DonacionesDataMapper <-- Fachada
    DonacionesDataMapper: +toDonacionDTO(donacion .. Donacion) DonacionDTO
    DonacionesDataMapper: +toDonacion(donacionDTO .. DonacionDTO)Donacion

    DonacionesRepository <|.. InMemoryDonacionesRepo
    InMemoryDonacionesRepo: +donaciones List<Donacion>
    InMemoryDonacionesRepo o-- Donacion

    Donacion: +id String
    Donacion: donadorID String
    Donacion: depositoID String
    Donacion: descripcion String
    Donacion: productoID String
    Donacion: cantidad Integer
    Donacion: estado EstadoDonacionEnum
    Donacion: fechaInicio LocalDate
