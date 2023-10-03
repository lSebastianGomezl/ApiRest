package billetera.app;
import com.google.gson.GsonBuilder;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
import dto.loginbtw.*;
import dto.loginbtw.response.LoginBtwOUTDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import process.LoginServiceBtw;
import process.clientecaracteristicas.ClienteCarateristicasServcie;
import process.consultcontenidodinamico.ConsultContenidoDinamicoService;

import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginBtw {


    String login1 = "sebastian.gomez@dcsas.com.co";
    String password = "12C4BDB852D2A7F21A47ECA11A8F5AE539089276E143525762DA3ECFC3B15960";
    String terminal = "ChIJCS5gz9F3P44RAZhIwcpbPMM,127.0.0.1";
    String idContenido1, idContenido2, idContenido3;
    String textoRojo1, textoRojo2, textoRojo3;
    String sigla1, sigla2, sigla3;
    String contenidoDinamico1;
    String contenidoDinamico2;
    String contenidoDinamico3;



    public LoginBtw() {

        RestAssured.baseURI = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/login";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();



        //idSession = consult.getDatosTransaccion().getDatosCliente().getIdSession();


        ConsultContenidoDinamicoOUTDto respuesta = ConsultContenidoDinamicoService.contenidoDinamicoData();
        System.out.println(respuesta);

        LoginBtwOUTDto login001 = LoginServiceBtw.getDataLogin();
        System.out.println(login001);

        String tokenId;
        String idSession;

        tokenId = login001.getDatosSalida().getTokenId();
        idSession = login001.getDatosTransaccion().getDatosCliente().getIdSession();
        System.out.println(tokenId);
        System.out.println(idSession);

        ClienteCaracteristicasOUTDto clientCharacters = ClienteCarateristicasServcie.clienteCaracteristicasData(tokenId,idSession);
        System.out.println(clientCharacters);

        for (int i = 0; i < respuesta.getDatosSalida().getLstContenidoDinamico().size(); i++) {
            contenidoDinamico1 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getConsecutivo();
            System.out.println("Consecutivo " + contenidoDinamico1);

            idContenido1 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getIdContenido();
            System.out.println("idContenido: " + idContenido1);

            textoRojo1 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getTextoRojo();
            System.out.println("texto " + textoRojo1);

            sigla1 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getSigla();
            System.out.println("sigla" + sigla1);

            if (sigla1.equals("TyC")) {
                break;
            }
        }

        for (int i = 0; i < respuesta.getDatosSalida().getLstContenidoDinamico().size(); i++) {
            contenidoDinamico2 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getConsecutivo();
            System.out.println("consecutivo" + contenidoDinamico2);

            idContenido2 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getIdContenido();
            System.out.println("idContenido: " + idContenido2);

            textoRojo2 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getTextoRojo();
            System.out.println("texto " + textoRojo2);

            sigla2 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getSigla();
            System.out.println("sigla" + sigla2);

            if (sigla2.equals("Portafolio")) {
                break;
            }
        }

        for (int i = 0; i < respuesta.getDatosSalida().getLstContenidoDinamico().size(); i++) {
            contenidoDinamico3 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getConsecutivo();
            System.out.println("consecutivo" + contenidoDinamico3);

            idContenido3 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getIdContenido();
            System.out.println("idContenido: " + idContenido3);

            textoRojo3 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getTextoRojo();
            System.out.println("texto " + textoRojo3);

            sigla3 = respuesta.getDatosSalida().getLstContenidoDinamico().get(i).getSigla();
            System.out.println("sigla" + sigla3);

            if (sigla3.equals("TratamientoDatos")) {
                break;
            }
        }



    }

    public static Long fechaActual() {

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public LoginBtwINDto initValue() {

        LoginBtwINDto login = new LoginBtwINDto();


        DatosEntrada datosEntrada = new DatosEntrada();

        datosEntrada.setPassword(password);
        datosEntrada.setLogin(login1);


        ArrayList<LstContenidoDinamico> listContenidoDinamico = new ArrayList<>();

        LstContenidoDinamico lstContenidoDinamico1 = new LstContenidoDinamico();

        lstContenidoDinamico1.setConsecutivo(contenidoDinamico1);
        lstContenidoDinamico1.setIdContenido(idContenido1);
        lstContenidoDinamico1.setSigla("TyC");
        lstContenidoDinamico1.setAceptado("ACEPTADO");
        lstContenidoDinamico1.setTextoMostrado("Acepto los " + textoRojo1);

        LstContenidoDinamico lstContenidoDinamico2 = new LstContenidoDinamico();
        lstContenidoDinamico2.setConsecutivo(contenidoDinamico2);
        lstContenidoDinamico2.setIdContenido(idContenido2);
        lstContenidoDinamico2.setSigla("Portafolio");
        lstContenidoDinamico2.setAceptado("ACEPTADO");
        lstContenidoDinamico2.setTextoMostrado("Conozco y acepto el " + textoRojo2 + "para el canal digital ");

        LstContenidoDinamico lstContenidoDinamico3 = new LstContenidoDinamico();
        lstContenidoDinamico3.setConsecutivo(contenidoDinamico3);
        lstContenidoDinamico3.setIdContenido(idContenido3);
        lstContenidoDinamico3.setSigla("TratamientoDatos");
        lstContenidoDinamico3.setAceptado("ACEPTADO");
        lstContenidoDinamico3.setTextoMostrado("Acepto " +textoRojo3);

        listContenidoDinamico.add(lstContenidoDinamico1);
        listContenidoDinamico.add(lstContenidoDinamico2);
        listContenidoDinamico.add(lstContenidoDinamico3);

        datosEntrada.setLstContenidoDinamico(listContenidoDinamico);


        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setId(1);
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();
        geolocalizacion.setCiudad("");
        geolocalizacion.setLatitud("4.659349");
        geolocalizacion.setLongitud("-74.1086629");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        DatosCliente datosCliente = new DatosCliente();
        datosCliente.setIdSession(fechaActual());
        datosCliente.setRol("Usuario");
        datosCliente.setTerminal(terminal);
        datosCliente.setLogin(login1);

        datosTransaccion.setDatosCliente(datosCliente);
        datosTransaccion.setVersion("15.6.0");
        datosTransaccion.setDispositivo("IOS");
        datosTransaccion.setCanal("APP");

        login.setDatosEntrada(datosEntrada);
        login.setDatosTransaccion(datosTransaccion);



        return login;

    }
    /*
    * El loginBtwSuccessful prueba que la autenticacion del usuario
    * sea  exitosa
    * */

    @Test
    public void loginBtwSuccessful() {

            LoginBtwINDto login1 = initValue();


            String jsonBody = new GsonBuilder().create().toJson(login1);

            given()
                    .body(jsonBody)
                    .relaxedHTTPSValidation()
                    .when()
                    .post(baseURI)
                    .then()
                    .statusCode(200)
                    .body("datosRespuesta.exito", equalTo(true));
        }

    /*
     * El geolocalizacionIncorrect prueba la respuesta del servicio al enviar
     * la geolocalizacion fuera de la cobertura de la app
     * */

    @Test
    public void geolocalizacionIncorrect() {

        LoginBtwINDto login = initValue();

        DatosTransaccion datosTransaccion = new DatosTransaccion();
        datosTransaccion.setId(1);
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();
        geolocalizacion.setCiudad("");
        geolocalizacion.setLatitud("37.9870400");
        geolocalizacion.setLongitud("-1.1300400");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        DatosCliente datosCliente = new DatosCliente();
        datosCliente.setIdSession(fechaActual());
        datosCliente.setRol("Usuario");
        datosCliente.setTerminal("FC3AE886-671B-4EB4-960E-859126D2C6D2");
        datosCliente.setLogin(login1);

        datosTransaccion.setDatosCliente(datosCliente);
        datosTransaccion.setVersion("15.6.0");
        datosTransaccion.setDispositivo("IOS");
        datosTransaccion.setCanal("APP");

        login.setDatosTransaccion(datosTransaccion);

        String jsonBody = new GsonBuilder().create().toJson(login);

        given()
                .body(jsonBody)
                .relaxedHTTPSValidation()
                .when()
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("datosRespuesta.exito", equalTo(false));
    }


    }
