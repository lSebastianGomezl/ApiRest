package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.RechargeDto;
import dto.RechargeINDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import process.LoginService;

import java.util.Calendar;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SendRecharge {

    String autorizador;
    String idSesion;
    String serie1;
    String serie2;

    public SendRecharge(){
        {
            RestAssured.baseURI="http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/";
            RestAssured.basePath="recarga/";
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .build();

            LoginOUTDto respuesta = LoginService.getDataLogin();
            autorizador = respuesta.getToken();
            idSesion = respuesta.getIdSesion();

            serie1 = respuesta.papelerias.get(0).getSerie1();
            serie2 = respuesta.papelerias.get(0).getSerie2();



        }
    }

    /*
     * Se crea el una una instancia del dto para inicializar los valores
     *
     * */

    public RechargeINDto initValue(){

        RechargeINDto recarga1 = new RechargeINDto();
        recarga1.setRecarga(new RechargeDto());


            recarga1.getRecarga().setNumero("3006593822");
            recarga1.getRecarga().setCodOperador("4");
            recarga1.getRecarga().setCodPaquete("50129");
            recarga1.getRecarga().setMontoRecarga("8000");
            recarga1.setSerie1(serie1);
            recarga1.setSerie2(serie2);
            recarga1.setCodigoVendedor("222230");
            recarga1.setCodigoProducto("06");
            recarga1.setValor("8000");
            recarga1.setCanalId("PVMA");
            recarga1.setCodigoTerminal("862634041369599");
            recarga1.setTipoUsuario("VENDEDOR");
            recarga1.setSecuenciaTransaccion("1");
            recarga1.setTransactionTime(Calendar.getInstance().getTimeInMillis()+"");
        return recarga1;
    }

    /*
     * El sendRechargeTest prueba que la recarga fue realizada con exito
     *
     * */

    @Test
    public void sendRechargeTest() {

        RechargeINDto requestSendRechage = initValue();

        String jsonBody = new GsonBuilder().create().toJson(requestSendRechage);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargeAtributoRecargaTest prueba que el atributo de "recarga"
     * no llegue nulo o vacio
     * */


    @Test
    public void sendRechargeAtributoRecargaTest() {

        RechargeINDto requesRechargeAtributo = initValue();


        String jsonBody = new GsonBuilder().create().toJson(requesRechargeAtributo);



                given()
                //.contentType(ContentType.JSON)
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("recarga", both(not(empty())).and(notNullValue()));
    }

    /*
     * El sendRechargeWithoutDataTest prueba que al pasar los campos totalmente vacios
     * el vendedor no pueda realizar la recarga y el el mensaje sea
     * Sesion no encontrada
     * */

    @Test
    public void sendRechargeWithoutDataTest() {

        RechargeINDto requesRechargeWithoutData = initValue();

        requesRechargeWithoutData.getRecarga().setNumero("");
        requesRechargeWithoutData.getRecarga().setCodOperador("");
        requesRechargeWithoutData.getRecarga().setCodPaquete("");
        requesRechargeWithoutData.getRecarga().setMontoRecarga("");
        requesRechargeWithoutData.setSerie1("");
        requesRechargeWithoutData.setSerie2("");
        requesRechargeWithoutData.setCodigoVendedor("");
        requesRechargeWithoutData.setCodigoProducto("");
        requesRechargeWithoutData.setValor("");
        requesRechargeWithoutData.setCanalId("");
        requesRechargeWithoutData.setCodigoTerminal("");
        requesRechargeWithoutData.setTipoUsuario("");
        requesRechargeWithoutData.setSecuenciaTransaccion("");

        String jsonBody = new GsonBuilder().create().toJson(requesRechargeWithoutData);

                given()
                //.contentType(ContentType.JSON)
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(501)
                .body("mensaje", equalTo("Sesion no encontrada."));
    }

    /*
     * El sendRechargeCodOperadorEmptyTest prueba que las validacion al pasar
     * el campo de "codOperador" vacio el vendedor no pueda realizar la recarga.
     * */

    @Test
    public void sendRechargeCodOperadorEmptyTest() {

        RechargeINDto requestRechargeCodOperadorEmpty = initValue();

        requestRechargeCodOperadorEmpty.getRecarga().setCodOperador("");

        String jsonBody = new GsonBuilder().create().toJson(requestRechargeCodOperadorEmpty);


                given()
                //.contentType(ContentType.JSON)
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Codigo del operador requerido."));
    }

    /*
     * El sendRechargeCodPaqueteEmptyTest prueba que las validacion al pasar
     * el campo de "codPaquete" vacio el vendedor no pueda realizar la recarga.
     * */

    @Test
    public void sendRechargeCodPaqueteEmptyTest() {

        RechargeINDto requestRechargeCodPaquetEmpty = initValue();
        requestRechargeCodPaquetEmpty.getRecarga().setCodPaquete("");

        String jsonBody = new GsonBuilder().create().toJson(requestRechargeCodPaquetEmpty);

                given()
                //.contentType(ContentType.JSON)
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargeMontoRecargaEmptyTest prueba que las validacion al pasar
     * el campo de "montoRecarga" vacio el vendedor no pueda realizar la recarga.
     * */

    @Test
    public void sendRechargeMontoRecargaEmptyTest() {

        RechargeINDto requestRechargeMontoEmpty = initValue();
        requestRechargeMontoEmpty .getRecarga().setMontoRecarga("");

        String jsonBody = new GsonBuilder().create().toJson(requestRechargeMontoEmpty);


                given()
                //.contentType(ContentType.JSON)
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Valor de la recarga requerido."));
    }

    /*
     * El sendRechargeNumeroEmptyTest prueba que las validacion al pasar
     * el campo de "numero" vacio el vendedor no pueda realizar la recarga.
     * */

    @Test
    public void sendRechargeNumeroEmptyTest() {

        RechargeINDto requestRechargeNumeroEmpty = initValue();

        requestRechargeNumeroEmpty.getRecarga().setNumero("");

        String jsonBody = new GsonBuilder().create().toJson(requestRechargeNumeroEmpty);



                given()
                //.contentType(ContentType.JSON)
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Numero celular requerido."));
    }

}
