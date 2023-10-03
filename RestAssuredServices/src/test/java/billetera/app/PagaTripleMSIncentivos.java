package billetera.app;

import com.google.gson.GsonBuilder;
import dto.comunclass.DatosCliente;
import dto.comunclass.Geolocalizacion;
import dto.msincentivos.Apuesta;
import dto.msincentivos.DatosEntrada;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.loginbtw.response.LoginBtwOUTDto;
import dto.msincentivos.DatosTransaccion;
import dto.msincentivos.PagaTripleMsIncentivosINDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import process.LoginServiceBtw;
import process.clientecaracteristicas.ClienteCarateristicasServcie;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PagaTripleMSIncentivos {

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

    public PagaTripleMSIncentivos(){

        RestAssured.baseURI = "http://10.101.0.12:8098/api/realizarVenta";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();


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

    public PagaTripleMsIncentivosINDto initValue(){

        PagaTripleMsIncentivosINDto transaccion = new PagaTripleMsIncentivosINDto();

        DatosEntrada datosEntrada = new DatosEntrada();

        datosEntrada.setIdPromocion(128);
        datosEntrada.setIdModalidad(null);
        datosEntrada.setValorTotal(3500);


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

        datosEntrada.setApuestas(listApuestas);


        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setId(1);
        datosTransaccion.setFecha(fechaActual());

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setLogin(login1);
        datosCliente.setTerminal(terminal);
        datosCliente.setIdSession(idSession);

        datosTransaccion.setDatosCliente(datosCliente);

        datosTransaccion.setCanal("WEB");
        datosTransaccion.setTokenId(tokenId);


        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setDispositivo("WEB");
        datosTransaccion.setIdAplicacion(0);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);



        return transaccion;
    }



    /*pagaTripleSuccessful prueba la respuesta del servicio al enviar la data */
    @Test
    public void pagaTripleSuccessful() {


        PagaTripleMsIncentivosINDto  transaccion = initValue();



        String jsonBody = new GsonBuilder().create().toJson(transaccion);

                given()
                .body(jsonBody)
                .header("idToken", tokenId)
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosSalida.exito", Matchers.equalTo(true));


    }


    /*pagaTripleLoteriesEmpty prueba la respuesta del servicio al enviar la data de las
    * loterias vacias*/
    @Test
    public void pagaTripleLoteriesEmpty() {


        PagaTripleMsIncentivosINDto  transaccion = initValue();

        DatosEntrada datosEntrada = new DatosEntrada();

        datosEntrada.setIdPromocion(128);
        datosEntrada.setIdModalidad(null);
        datosEntrada.setValorTotal(3500);


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

        datosEntrada.setApuestas(listApuestas);


        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setId(1);
        datosTransaccion.setFecha(fechaActual());

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setLogin(login1);
        datosCliente.setTerminal(terminal);
        datosCliente.setIdSession(idSession);

        datosTransaccion.setDatosCliente(datosCliente);

        datosTransaccion.setCanal("WEB");
        datosTransaccion.setTokenId(tokenId);


        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setDispositivo("WEB");
        datosTransaccion.setIdAplicacion(0);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);



        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .header("idToken", tokenId)
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosSalida.exito", Matchers.equalTo(false));


    }

    /*pagaTripleIdApuestaIncorrect prueba la respuesta del servicio al enviar la data
    * del tributo idApuesta incorrecto */
    @Test
    public void pagaTripleIdApuestaIncorrect() {


        PagaTripleMsIncentivosINDto  transaccion = initValue();

        DatosEntrada datosEntrada = new DatosEntrada();

        datosEntrada.setIdPromocion(128);
        datosEntrada.setIdModalidad(null);
        datosEntrada.setValorTotal(3500);


        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(01);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);

        Apuesta apuesta2 = new Apuesta();

        apuesta2.setIdApuesta(314);
        apuesta2.setNumeroApostado(numeroAleatorio2());
        apuesta2.setCodLoteria("31");
        apuesta2.setNombreLoteria("Dorado tarde");
        apuesta2.setValorPagado(105);

        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(315);
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

        datosEntrada.setApuestas(listApuestas);


        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setId(1);
        datosTransaccion.setFecha(fechaActual());

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setLogin(login1);
        datosCliente.setTerminal(terminal);
        datosCliente.setIdSession(idSession);

        datosTransaccion.setDatosCliente(datosCliente);

        datosTransaccion.setCanal("WEB");
        datosTransaccion.setTokenId(tokenId);


        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setDispositivo("WEB");
        datosTransaccion.setIdAplicacion(0);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);



        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .header("idToken", tokenId)
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosSalida.exito", Matchers.equalTo(false));


    }

    /*pagaTripleUnaApuesta prueba la respuesta del servicio al enviar la data
    * con una sola apuesta */
    @Test
    public void pagaTripleUnaApuesta() {


        PagaTripleMsIncentivosINDto transaccion = initValue();

        DatosEntrada datosEntrada = new DatosEntrada();

        datosEntrada.setIdPromocion(128);
        datosEntrada.setIdModalidad(null);
        datosEntrada.setValorTotal(3500);


        ArrayList<Apuesta> listApuestas = new ArrayList<>();

        Apuesta apuesta1 = new Apuesta();

        apuesta1.setIdApuesta(311);
        apuesta1.setNumeroApostado(numeroAleatorio());
        apuesta1.setCodLoteria("52");
        apuesta1.setNombreLoteria("Chontico Noche");
        apuesta1.setValorPagado(105);
    /*
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

     */

        listApuestas.add(apuesta1);
       /*
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        */

        datosEntrada.setApuestas(listApuestas);


        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setId(1);
        datosTransaccion.setFecha(fechaActual());

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setLogin(login1);
        datosCliente.setTerminal(terminal);
        datosCliente.setIdSession(idSession);

        datosTransaccion.setDatosCliente(datosCliente);

        datosTransaccion.setCanal(canalId);
        datosTransaccion.setTokenId(tokenId);


        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setDispositivo("WEB");
        datosTransaccion.setIdAplicacion(0);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);


        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .header("idToken", tokenId)
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosSalida.exito", Matchers.equalTo(true));

    }



    /*pagaTripleDosApuesta prueba la respuesta del servicio al enviar la data
     * con una sola apuesta */
    @Test
    public void pagaTripleDosApuesta() {


        PagaTripleMsIncentivosINDto transaccion = initValue();

        DatosEntrada datosEntrada = new DatosEntrada();

        datosEntrada.setIdPromocion(128);
        datosEntrada.setIdModalidad(null);
        datosEntrada.setValorTotal(3500);


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
/*
        Apuesta apuesta3 = new Apuesta();

        apuesta3.setIdApuesta(313);
        apuesta3.setNumeroApostado(numeroAleatorio3());
        apuesta3.setCodLoteria("29");
        apuesta3.setNombreLoteria("Caf2 noche");
        apuesta3.setValorPagado(105);

        Apuesta apuesta4 = new Apuesta();

        apuesta4.setIdApuesta(128);
        apuesta4.setValorPagado(3185);

     */

        listApuestas.add(apuesta1);

        listApuestas.add(apuesta2);
        /*
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);

        */

        datosEntrada.setApuestas(listApuestas);


        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setId(1);
        datosTransaccion.setFecha(fechaActual());

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setLogin(login1);
        datosCliente.setTerminal(terminal);
        datosCliente.setIdSession(idSession);

        datosTransaccion.setDatosCliente(datosCliente);

        datosTransaccion.setCanal("WEB");
        datosTransaccion.setTokenId(tokenId);


        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setDispositivo("WEB");
        datosTransaccion.setIdAplicacion(0);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);


        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .header("idToken", tokenId)
                .when()
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("datosSalida.exito", Matchers.equalTo(true));

    }
}
