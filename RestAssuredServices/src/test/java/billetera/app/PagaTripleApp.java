package billetera.app;
import com.google.gson.GsonBuilder;
import dto.app.pagatriple.*;
import dto.app.pagatriple.DatosTransaccion;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
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

public class PagaTripleApp {

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

    public PagaTripleApp(){

        RestAssured.baseURI = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/producto/pagar";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

        //Se ejecuta el servicio de contenido dinamico
        //ConsultContenidoDinamicoOUTDto consult = ConsultContenidoDinamicoService.contenidoDinamicoData();
        //System.out.println(consult);

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

        ClienteCaracteristicasOUTDto clientService = ClienteCarateristicasServcie.clienteCaracteristicasData(tokenId, idSession );
        System.out.println(clientService);
    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }
    public static String numeroAleatorio(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(20) + 10;

        return Integer.toString(numeroAleatorio1);
    }

    public static String numeroAleatorio2(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio3 = random.nextInt(30) + 20;

        return Integer.toString(numeroAleatorio3);
    }
    public static String numeroAleatorio3(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio3 = random.nextInt(40) + 30;

        return Integer.toString(numeroAleatorio3);
    }

    public PagaTripleINDto initValue(){

        PagaTripleINDto transaccion = new PagaTripleINDto();

        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("31");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        return transaccion;
    }



    /*pagaTripleSuccessful prueba la respuesta del servicio al enviar la data */
    @Test
    public void pagaTripleSuccessful() {


        PagaTripleINDto  transaccion = initValue();



        String jsonBody = new GsonBuilder().create().toJson(transaccion);

                given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(true));


    }

    /*pagaTripleSuccessful prueba la respuesta del servicio al enviar la data */
    @Test
    public void pagaTripleSinIncentivo() {


        PagaTripleINDto  transaccion = initValue();

        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);


        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);


        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");


        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(true));


    }


    /*pagaTripleEmptyDatosCliente prueba la respuesta del servicio al enviar la data sin Datos del cliente */
    @Test
    public void pagaTripleEmptyDatosCliente() {


        PagaTripleINDto  transaccion =  new PagaTripleINDto();


        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setCanal(canalId);

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession("");
        datosCliente.setLogin("");
        datosCliente.setRol("");
        datosCliente.setTerminal("");

        datosTransaccion.setDatosCliente(datosCliente);


        datosTransaccion.setDispositivo(dispositivo);
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.mensaje", Matchers.equalTo("EL CAMPO [transaccion/datosCliente/login] ES OBLIGATORIO."));


    }


    /*pagaTripleEmptyDatosCliente prueba la respuesta del servicio al enviar un tokenId incorrecto */
    @Test
    public void pagaTripleIncorrectTokenId() {


        PagaTripleINDto  transaccion =  new PagaTripleINDto();


        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId("SELECT * FROM PROYECTO.LOTERIAS");
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }


    /*pagaTripleValueNegative prueba la respuesta del servicio al enviar el valor de la apuesta
     negativo */
    @Test
    public void pagaTripleValueNegative() {


        PagaTripleINDto  transaccion =  new PagaTripleINDto();


        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(-3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }

    /*pagaTripleValueNegative prueba la respuesta del servicio al enviar el valor de la apuesta
    supera el valor maximo */
    @Test
    public void pagaTripleValueMax() {


        PagaTripleINDto  transaccion =  new PagaTripleINDto();


        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(986565815);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }

    /*pagaTripleNumUnaCifra prueba la respuesta del servicio al enviar el valor de los numeros
    apostados de una cifra en dos apuestas */
    @Test
    public void pagaTripleNumUnaCifra() {


        PagaTripleINDto  transaccion =  new PagaTripleINDto();

        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado("0");
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado("1");
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }

    /*pagaTripleNumTresCifra prueba la respuesta del servicio al enviar el valor de los numeros
apostados de con tres cifra en tres apuestas */
    @Test
    public void pagaTripleNumTresCifra() {


        PagaTripleINDto  transaccion =  new PagaTripleINDto();

        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado("745");
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado("985");
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado("698");
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }

    /*pagaTripleUnaApuesta prueba la respuesta del servicio al enviar una sola apuesta */
    @Test
    public void pagaTripleUnaApuesta() {


        PagaTripleINDto  transaccion =  new PagaTripleINDto();

        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }

    /*pagaTripleDosApuesta prueba la respuesta del servicio al enviar dos apuestas */
    @Test
    public void pagaTripleDosApuesta() {


        PagaTripleINDto  transaccion =  new PagaTripleINDto();

        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("37");
        apuesta2.setNombreLoteria("Paisita2");
        apuesta2.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);


        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta4);


        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }

    /*pagaTripleValueIdApuestaCero prueba la respuesta del servicio al enviar la data
    * con el valor de IdApuesta en 0 */
    @Test
    public void pagaTripleValueIdApuestaCero() {


        PagaTripleINDto  transaccion = initValue();

        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();


        apuesta4.setIdApuesta(0);
        apuesta4.setValorPagado(0);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");


        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(true));


    }

    /*pagaTripleApuestasVacias prueba la respuesta del servicio al enviar la data
    * de las apuestas vacias */
    @Test
    public void pagaTripleApuestasVacias() {


        PagaTripleINDto  transaccion = initValue();


        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("");
        apuesta1.setNombreLoteria("");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("");
        apuesta2.setNombreLoteria("");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("");
        apuesta3.setNombreLoteria("");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }


    /*pagaTripleApuestasIncorrect prueba la respuesta del servicio al enviar la data
     * de las apuestas incorrectas */
    @Test
    public void pagaTripleApuestasIncorrect() {


        PagaTripleINDto  transaccion = initValue();


        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("0");
        apuesta1.setNombreLoteria("Data");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("1");
        apuesta2.setNombreLoteria("Select * from");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("2");
        apuesta3.setNombreLoteria("Hola mundo");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");




        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }

    /*pagaTripleIdApuestaCero prueba la respuesta del servicio al enviar la data
     * con el valor de IdApuesta en 0 */
    @Test
    public void pagaTripleIdApuestaCero() {


        PagaTripleINDto  transaccion = initValue();

        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(3500);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(0);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(0);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(0);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();


        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");


        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }

    /*pagaTripleMaxValorPago prueba la respuesta del servicio al enviar la data
     * con el valor de IdApuesta en 0 */
    @Test
    public void pagaTripleMaxValorPago() {


        PagaTripleINDto  transaccion = initValue();

        DatosEntrada datosEntrada  = new DatosEntrada();

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);

        VentaICC ventaIcc = new VentaICC();

        ventaIcc.setIdPromocion(128);
        ventaIcc.setValorTotal(11000);

        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(300);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(312);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("33");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(300);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(300);

        Apuesta apuesta4 = new Apuesta();


        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(9100);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        ventaIcc.setApuestas(listApuestas);

        datosEntrada.setVentaICC(ventaIcc);


        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");


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
        geolocalizacion.setCodigoDane("11001");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);
        transaccion.setDireccionIp("192.168.252.28");


        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(false));


    }



}
