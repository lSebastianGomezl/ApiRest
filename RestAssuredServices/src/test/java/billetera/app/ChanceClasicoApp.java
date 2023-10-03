package billetera.app;
import com.google.gson.GsonBuilder;
import dto.app.chance.ChanceClasicoINDto;
import dto.app.chance.DatosEntrada;
import dto.app.chance.LstProductoChance;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.comunclass.*;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
import dto.loginbtw.response.LoginBtwOUTDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import process.LoginServiceBtw;
import process.clientecaracteristicas.ClienteCarateristicasServcie;
import process.consultcontenidodinamico.ConsultContenidoDinamicoService;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ChanceClasicoApp {

    String tokenId ;
    String usuario;
    String canalId;
    String idSession;
    String terminal ;
    String login1;
    String idTipoTarjeta = "MI_SALDO";
    String idTipoTarifaTransaccion = "COBRA";
    String dispositivo;
    String ciudad;
    String longitud;
    String latitud;
    int id;

    String version;

    public ChanceClasicoApp (){

        RestAssured.baseURI = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/producto/pagar";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

        //Se ejecuta el servicio de contenido dinamico
        ConsultContenidoDinamicoOUTDto consult = ConsultContenidoDinamicoService.contenidoDinamicoData();

        LoginBtwOUTDto respuesta = LoginServiceBtw.getDataLogin();
        System.out.println("esta es la respuesta: " +respuesta);

        login1 = respuesta.getDatosTransaccion().getDatosCliente().getLogin();
        System.out.println("Este ese login " + login1);

        tokenId = respuesta.getDatosSalida().getTokenId();
        System.out.println("Este es el tokenId actual " + tokenId );

        idSession = respuesta.getDatosTransaccion().getDatosCliente().getIdSession();
        System.out.println("Este es el idSession actual" + idSession);

        terminal = respuesta.getDatosTransaccion().getDatosCliente().getTerminal();
        System.out.println("Esta es la terminal:" + terminal);

        canalId = respuesta.getDatosTransaccion().getCanal();
        System.out.println("Este es el canal " + canalId);

        usuario = respuesta.getDatosTransaccion().getDatosCliente().getRol();
        System.out.println("este es el usuario " + usuario);

        dispositivo = respuesta.getDatosTransaccion().getDispositivo();
        System.out.println("Este es el dispositivo " + dispositivo);

        ciudad = respuesta.getDatosTransaccion().getGeolocalizacion().getCiudad();
        System.out.println("ciudad " + ciudad);

        longitud = respuesta.getDatosTransaccion().getGeolocalizacion().getLongitud();
        System.out.println("longitud " + longitud);

        latitud = respuesta.getDatosTransaccion().getGeolocalizacion().getLatitud();
        System.out.println("latitud " + latitud);

        id = respuesta.getDatosTransaccion().getId();
        System.out.println("id " + id);

        version = respuesta.getDatosTransaccion().getVersion();
        System.out.println("version" + version);

        ClienteCaracteristicasOUTDto clientService = ClienteCarateristicasServcie.clienteCaracteristicasData(tokenId, null);
    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }
    public static String numeroAleatorio(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;

        return Integer.toString(numeroAleatorio1);
    }
    public static String numeroAleatorioCombinado(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(900) + 100;

        return Integer.toString(numeroAleatorio1);
    }

    public static String numeroAleatorioUna(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9) + 1;

        return Integer.toString(numeroAleatorio1);
    }
    public static String numeroAleatorioPata(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(99) + 10;

        return Integer.toString(numeroAleatorio1);
    }

    public ChanceClasicoINDto initValue(){

        ChanceClasicoINDto transaccion = new ChanceClasicoINDto();

        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoChance> listProductChance = new ArrayList<>();

        LstProductoChance chance = new LstProductoChance();

        chance.setCantidad(1);
        chance.setValorIva(319);
        chance.setValorPagar(2000);
        chance.setFechaSorteo(fechaActual());
        chance.setValorProducto(2000);
        chance.setValorUnitario(2000);
        chance.setCantidadPuntos(0);
        chance.setValorDescuento(0);
        chance.setIdProductoTrans(1);
        chance.setValorTransaccion(0);

        chance.setTipoTarifaTransaccion(idTipoTarifaTransaccion);

        ArrayList<Loteria> listLotieria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("52");
        loteria.setNombreLoteria("Chontico Noche");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);

        Loteria loteria2 = new Loteria();

        loteria2.setCodLoteria("29");
        loteria2.setNombreLoteria("Cafeterito Noche");
        loteria2.setFechaSorteo(fechaActual());
        loteria2.setCifrasNumero(0);
        loteria2.setCifrasSerie(0);

        listLotieria.add(loteria);
        listLotieria.add(loteria2);

        chance.setLoterias(listLotieria);

        ArrayList<Moldalidade> listModalidad = new ArrayList<>();

        Moldalidade modalidad = new Moldalidade();

        modalidad.setChanceModalidad("SUPER_PLENO_DIRECTO");
        modalidad.setValor(500);
        modalidad.setNumero(numeroAleatorio());

        Moldalidade modalidad1 = new Moldalidade();

        modalidad1.setChanceModalidad("SUPER_PLENO_COMBINADO");
        modalidad1.setValor(500);
        modalidad1.setNumero(numeroAleatorio());

        listModalidad.add(modalidad);
        listModalidad.add(modalidad1);

        chance.setMoldalidades(listModalidad);


        listProductChance.add(chance);

        datosEntrada.setLstProductoChance(listProductChance);
        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");

        datosEntrada.setLstProductoChance(listProductChance);

        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setCanal(canalId);

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession(idSession);
        datosCliente.setLogin(login1);
        datosCliente.setRol(usuario);
        datosCliente.setTerminal(terminal);

        datosTransaccion.setDatosCliente(datosCliente);


        datosTransaccion.setDispositivo(dispositivo);
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);

        return transaccion;
    }

    /*ChanceClasicoUnaSuccessful prueba la respuesta del servicio al enviar la data con super pleno directo y
    * super pleno combinado
    * */
    @Test
    public void ChanceClasicoSuccessful() {


        ChanceClasicoINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);


        given()
                .body(jsonBody)
                .when()
                .relaxedHTTPSValidation()
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(true));


    }

    /*ChanceClasicoUnaSuccessful prueba la respuesta del servicio al enviar la data con modalidad una*/
    @Test
    public void ChanceClasicoUnaSuccessful() {


        ChanceClasicoINDto transaccion = initValue();


        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoChance> listProductChance = new ArrayList<>();

        LstProductoChance chance = new LstProductoChance();

        chance.setCantidad(1);
        chance.setValorIva(160);
        chance.setValorPagar(1000);
        chance.setFechaSorteo(fechaActual());
        chance.setValorProducto(1000);
        chance.setValorUnitario(1000);
        chance.setCantidadPuntos(0);
        chance.setValorDescuento(0);
        chance.setIdProductoTrans(1);
        chance.setValorTransaccion(0);

        chance.setTipoTarifaTransaccion(idTipoTarifaTransaccion);

        ArrayList<Loteria> listLotieria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("52");
        loteria.setNombreLoteria("Chontico Noche");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);


        listLotieria.add(loteria);


        chance.setLoterias(listLotieria);

        ArrayList<Moldalidade> listModalidad = new ArrayList<>();

        Moldalidade modalidad = new Moldalidade();

        modalidad.setChanceModalidad("UNA");
        modalidad.setValor(1000);
        modalidad.setNumero(numeroAleatorioUna());


        listModalidad.add(modalidad);


        chance.setMoldalidades(listModalidad);


        listProductChance.add(chance);

        datosEntrada.setLstProductoChance(listProductChance);
        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");

        datosEntrada.setLstProductoChance(listProductChance);

        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setCanal(canalId);

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession(idSession);
        datosCliente.setLogin(login1);
        datosCliente.setRol(usuario);
        datosCliente.setTerminal(terminal);

        datosTransaccion.setDatosCliente(datosCliente);


        datosTransaccion.setDispositivo(dispositivo);
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);


        given()
                .body(jsonBody)
                .when()
                .relaxedHTTPSValidation()
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(true));


    }

    /*ChanceClasicoUnaPataSuccessful prueba la respuesta del servicio al enviar la data con modalidad una y pata*/
    @Test
    public void ChanceClasicoUnaPataSuccessful() {


        ChanceClasicoINDto transaccion = initValue();


        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoChance> listProductChance = new ArrayList<>();

        LstProductoChance chance = new LstProductoChance();

        chance.setCantidad(1);
        chance.setValorIva(160);
        chance.setValorPagar(1000);
        chance.setFechaSorteo(fechaActual());
        chance.setValorProducto(1000);
        chance.setValorUnitario(1000);
        chance.setCantidadPuntos(0);
        chance.setValorDescuento(0);
        chance.setIdProductoTrans(1);
        chance.setValorTransaccion(0);

        chance.setTipoTarifaTransaccion(idTipoTarifaTransaccion);

        ArrayList<Loteria> listLotieria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("52");
        loteria.setNombreLoteria("Chontico Noche");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);


        listLotieria.add(loteria);


        chance.setLoterias(listLotieria);

        ArrayList<Moldalidade> listModalidad = new ArrayList<>();

        Moldalidade modalidad = new Moldalidade();

        modalidad.setChanceModalidad("UNA");
        modalidad.setValor(500);
        modalidad.setNumero(numeroAleatorioUna());

        Moldalidade modalidad1 = new Moldalidade();

        modalidad1.setChanceModalidad("PATA");
        modalidad1.setValor(500);
        modalidad1.setNumero(numeroAleatorioPata());


        listModalidad.add(modalidad);
        listModalidad.add(modalidad1);


        chance.setMoldalidades(listModalidad);


        listProductChance.add(chance);

        datosEntrada.setLstProductoChance(listProductChance);
        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");

        datosEntrada.setLstProductoChance(listProductChance);

        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setCanal(canalId);

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession(idSession);
        datosCliente.setLogin(login1);
        datosCliente.setRol(usuario);
        datosCliente.setTerminal(terminal);

        datosTransaccion.setDatosCliente(datosCliente);


        datosTransaccion.setDispositivo(dispositivo);
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);


        given()
                .body(jsonBody)
                .when()
                .relaxedHTTPSValidation()
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(true));


    }

    /*PlenoDirectoCombinadoUnaPataSuccessful prueba la respuesta del servicio al enviar la data con modalidad pleno directo
    pleno combinado, una y pata*/
    @Test
    public void PlenoDirectoCombinadoUnaPataSuccessful() {


        ChanceClasicoINDto transaccion = initValue();


        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoChance> listProductChance = new ArrayList<>();

        LstProductoChance chance = new LstProductoChance();

        chance.setCantidad(1);
        chance.setValorIva(160);
        chance.setValorPagar(1000);
        chance.setFechaSorteo(fechaActual());
        chance.setValorProducto(1000);
        chance.setValorUnitario(1000);
        chance.setCantidadPuntos(0);
        chance.setValorDescuento(0);
        chance.setIdProductoTrans(1);
        chance.setValorTransaccion(0);

        chance.setTipoTarifaTransaccion(idTipoTarifaTransaccion);

        ArrayList<Loteria> listLotieria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("52");
        loteria.setNombreLoteria("Chontico Noche");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);


        listLotieria.add(loteria);


        chance.setLoterias(listLotieria);

        ArrayList<Moldalidade> listModalidad = new ArrayList<>();

        Moldalidade modalidad = new Moldalidade();

        modalidad.setChanceModalidad("UNA");
        modalidad.setValor(250);
        modalidad.setNumero(numeroAleatorioUna());

        Moldalidade modalidad1 = new Moldalidade();

        modalidad1.setChanceModalidad("PATA");
        modalidad1.setValor(250);
        modalidad1.setNumero(numeroAleatorioPata());

        Moldalidade modalidad2 = new Moldalidade();

        modalidad2.setChanceModalidad("PLENO_DIRECTO");
        modalidad2.setValor(250);
        modalidad2.setNumero(numeroAleatorio());

        Moldalidade modalidad3 = new Moldalidade();

        modalidad3.setChanceModalidad("PLENO_COMBINADO");
        modalidad3.setValor(250);
        modalidad3.setNumero(numeroAleatorioCombinado());

        listModalidad.add(modalidad);
        listModalidad.add(modalidad1);
        listModalidad.add(modalidad2);
        listModalidad.add(modalidad3);


        chance.setMoldalidades(listModalidad);


        listProductChance.add(chance);

        datosEntrada.setLstProductoChance(listProductChance);
        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");

        datosEntrada.setLstProductoChance(listProductChance);

        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setCanal(canalId);

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession(idSession);
        datosCliente.setLogin(login1);
        datosCliente.setRol(usuario);
        datosCliente.setTerminal(terminal);

        datosTransaccion.setDatosCliente(datosCliente);


        datosTransaccion.setDispositivo(dispositivo);
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);


        given()
                .body(jsonBody)
                .when()
                .relaxedHTTPSValidation()
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(true));


    }

}
