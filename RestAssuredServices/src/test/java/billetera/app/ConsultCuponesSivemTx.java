package billetera.app;
import com.google.gson.GsonBuilder;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
import dto.consultcuponessivemtx.*;
import dto.consultcuponessivemtx.response.ConsultCuponesOUTDto;
import dto.loginbtw.response.LoginBtwOUTDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import process.LoginServiceBtw;
import process.clientecaracteristicas.ClienteCarateristicasServcie;
import process.consultcontenidodinamico.ConsultContenidoDinamicoService;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.ResponseAwareMatcherComposer.and;
import static org.hamcrest.Matchers.*;

public class ConsultCuponesSivemTx {


    String login1 = "leceitracreta-2473@yopmail.com";

    String tokenId;
    String idSession;
    String terminal = "FC3AE886-671B-4EB4-960E-859126D2C6D2";


    public ConsultCuponesSivemTx(){

        RestAssured.baseURI= "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/consultarcupones";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

        ConsultContenidoDinamicoOUTDto consult = ConsultContenidoDinamicoService.contenidoDinamicoData();
        LoginBtwOUTDto respuesta = LoginServiceBtw.getDataLogin();

        tokenId = respuesta.getDatosSalida().getTokenId();
        System.out.println("Este es el tokenId actual " + tokenId );

        idSession = respuesta.getDatosTransaccion().getDatosCliente().getIdSession();
        System.out.println("Este es el idSession actual" + idSession);

        ClienteCaracteristicasOUTDto clientService = ClienteCarateristicasServcie.clienteCaracteristicasData(tokenId,idSession);



    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }


    public ConsultCuponesINDto initValue(){

        ConsultCuponesINDto consultCupon = new ConsultCuponesINDto();

        DatosTransaccion datosTransaccion = new DatosTransaccion();

        DatosEntrada dato = new DatosEntrada();

        dato.setLogin("leceitracreta-2473@yopmail.com");
        dato.setTipoConsulta("CUPONES_ASIGNADOS");
        datosTransaccion.setCanal("APP");

        consultCupon.setDatosEntrada(dato);

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession(idSession);
        datosCliente.setLogin(login1);
        datosCliente.setRol("Cliente");
        datosCliente.setTerminal(terminal);

        datosTransaccion.setDatosCliente(datosCliente);


        datosTransaccion.setDispositivo("IOS");
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();
        geolocalizacion.setCiudad("Ac. 26 #69b9, Fontibón, Bogotá, Cundinamarca, Colombia");
        geolocalizacion.setLatitud("4.659349");
        geolocalizacion.setLongitud("-74.1086629");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(1);
        datosTransaccion.setIdAplicacion(0);
        datosTransaccion.setVersion("15.6.0");

        consultCupon.setDatosTransaccion(datosTransaccion);
        return consultCupon;

    }

    @Test
    public void consultCuponSuccessful(){

        ConsultCuponesINDto consultCupones = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultCupones);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("datosRespuesta.exito", equalTo(true));
    }

    @Test
    public void consultCuponContentCupones(){

        ConsultCuponesINDto consultCupones = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultCupones);

        Response response =
                given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI);
                //.then()
                //.statusCode(200)
                //.body("datosSalida.listaCupones", both(not(empty())).and(notNullValue()));

        ConsultCuponesOUTDto responseBody = response.getBody().as(ConsultCuponesOUTDto.class);

        if(responseBody.getDatosSalida().getListaCupones().equals(null)){
            Assert.assertTrue(false);
        }else {
            Assert.assertTrue(true);
        }

    }

}
