package billetera.app;

import com.google.gson.GsonBuilder;
import dto.app.response.ResponseOUTDto;
import dto.app.superastro.*;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.comunclass.DatosCliente;
import dto.comunclass.DatosTransaccion;
import dto.comunclass.Geolocalizacion;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
import dto.loginbtw.response.LoginBtwOUTDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import process.LoginServiceBtw;
import process.clientecaracteristicas.ClienteCarateristicasServcie;
import process.consultcontenidodinamico.ConsultContenidoDinamicoService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class SuperAstroAutomaticApp {

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
    String tipoVenta = "manual";
    String tipoVenta2 = "automatica";

    String version;

    public SuperAstroAutomaticApp(){

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

        ClienteCaracteristicasOUTDto clientService = ClienteCarateristicasServcie.clienteCaracteristicasData(tokenId, idSession);
    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }
    /*
    public static String numeroAleatorio(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(9000) + 1000;

        return Integer.toString(numeroAleatorio);
    }
    public static String numeroAleatorio1(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(5000) + 4000;

        return Integer.toString(numeroAleatorio1);
    }
    public static String numeroAleatorio2(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio2 = random.nextInt(4000) + 3000;

        return Integer.toString(numeroAleatorio2);
    }

    public static String numeroAleatorio3(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio3 = random.nextInt(3000) + 2000;

        return Integer.toString(numeroAleatorio3);
    }
    public static String numeroAleatorio4(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio4 = random.nextInt(2000) + 1000;

        return Integer.toString(numeroAleatorio4);
    }

     */

    public SuperAstroINDto initValue(){

        SuperAstroINDto transaccion = new SuperAstroINDto();

        DatosEntrada datosEntrada =  new DatosEntrada();

        VentaSuperAstro ventaSuperAstro = new VentaSuperAstro();

        ventaSuperAstro.setTipoIdentificacion("1");
        ventaSuperAstro.setNumeroIdentificacion("1020655447");
        ventaSuperAstro.setRegistroDANE("25758");
        ventaSuperAstro.setIdTipoTarjeta(idTipoTarjeta);
        ventaSuperAstro.setCantidadSorteos(null);
        ventaSuperAstro.setSorteos(null);
        ventaSuperAstro.setCantidadApuestas(null);
        ventaSuperAstro.setDetalleApuestas(null);
        ventaSuperAstro.setTipoVenta(tipoVenta2);
        ventaSuperAstro.setCodigoSorteo("2");
        ventaSuperAstro.setMonto("500");


        datosEntrada.setVentaSuperAstro(ventaSuperAstro);



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


    /*Prueba el astro automatico*/
    @Test
    public void SuperAstroAutomaticSuccessful() {


        SuperAstroINDto transaccion = initValue();


        LocalTime horaActual = LocalTime.now();
        LocalTime horaCondicional = LocalTime.of(14,05);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);


        Response response =given()
                .body(jsonBody)
                .when()
                .relaxedHTTPSValidation()
                .post(baseURI);

        ResponseOUTDto respuestaBody =  response.getBody().as(ResponseOUTDto.class);

        if (horaActual.isBefore(horaCondicional)){
            Assert.assertTrue(respuestaBody.getDatosSalida().getListProductoRespuestaWs().get(0).getMensaje().equals("Error en la ejecucion de la regla (SORTEO CERRADO)."));
        }else {
            Assert.assertTrue(respuestaBody.getDatosRespuesta().getMensaje().equals("OPERACION EXITOSA"));
        }

    }

}


