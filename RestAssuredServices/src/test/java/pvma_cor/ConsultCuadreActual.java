package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.consultcuadreactual.CuadreActualINDto;
import dto.consultcuadreactual.CuadreActualOUTDto;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import process.LoginService;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class ConsultCuadreActual {

    String autorizador;
    String idSesion;

    public ConsultCuadreActual() {
        {
            RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/administracion/";
            RestAssured.basePath = "consultarCuadreActual";
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .build();

            LoginOUTDto respuesta = LoginService.getDataLogin();
            autorizador = respuesta.getToken();
            idSesion = respuesta.getIdSesion();

        }
    }

    public CuadreActualINDto initValue() {

        CuadreActualINDto cuadreActual = new CuadreActualINDto();

        cuadreActual.setCodigoVendedor("222230");
        cuadreActual.setCanalId("PVMA");
        cuadreActual.setCodigoTerminal("862634041369599");
        cuadreActual.setTipoUsuario("VENDEDOR");
        cuadreActual.setIdSesion(idSesion);
        cuadreActual.setOperacionTransaccion("CUADRE_ACTUAL");
        return cuadreActual;
    }

    public CuadreActualINDto initValueEmpty() {

        CuadreActualINDto cuadreActualEmpty = new CuadreActualINDto();

        cuadreActualEmpty.setCodigoVendedor("");
        cuadreActualEmpty.setCanalId("");
        cuadreActualEmpty.setCodigoTerminal("");
        cuadreActualEmpty.setTipoUsuario("");
        cuadreActualEmpty.setIdSesion("");
        cuadreActualEmpty.setOperacionTransaccion("");
        return cuadreActualEmpty;
    }

    /*cuadreActualSuccessful prueba que la respuesta del servicio sea
     * exitosa*/
    @Test
    public void cuadreActualSuccessful() {

        CuadreActualINDto cuadreActual = initValue();

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));

    }

    /*cuadreActualEmpty prueba que la respuesta del servicio al enviar la data con valores vacios*/
    @Test
    public void cuadreActualEmpty() {

        CuadreActualINDto cuadreActual = initValueEmpty();

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(501)
                .body("exito", Matchers.equalTo(false));
    }

    /*cuadreActualCodVendedorEmpty prueba que la respuesta del servicio al
     * enviar el atributo codVendedor vacio*/
    @Test
    public void cuadreActualCodVendedorEmpty() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setCodigoVendedor("");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(501)
                .body("exito", Matchers.equalTo(false));

    }

    /*cuadreActualCodVendedorIncorrect prueba que la respuesta del servicio al
    * enviar el atributo codVendedor incorrecto*/
    @Test
    public void cuadreActualCodVendedorIncorrect() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setCodigoVendedor("*GENERATOR&%");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(501)
                .body("exito", Matchers.equalTo(false));

    }

    /*cuadreActualCanlIdEmpty prueba que la respuesta del servicio al
     * enviar el atributo canalId vacio*/
    /*
    @Test
    public void cuadreActualCanalIdEmpty() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setCanalId("");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));

    }

    /*cuadreActualCanalIdIncorrect prueba que la respuesta del servicio al
     * enviar el atributo CanalId incorrecto*/
    /*
    @Test
    public void cuadreActualCanalIdIncorrect() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setCanalId("CLARO");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));

    }


    /*cuadreActualCodigoTerminalEmpty prueba que la respuesta del servicio al
     * enviar el atributo CodigoTerminal vacio*/
    /*
    @Test
    public void cuadreActualCodigoTerminalEmpty() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setCodigoTerminal("");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));

    }

    /*cuadreActualCodigoTerminalIncorrect prueba que la respuesta del servicio al
     * enviar el atributo CodigoTerminal incorrecto*/
    /*
    @Test
    public void cuadreActualCodigoTerminalIncorrect() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setCodigoTerminal("%$##$##$#");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));

    }

    /*cuadreActualTipoUsuarioEmpty prueba que la respuesta del servicio al
     * enviar el atributo TipoUsuario vacio*/
    /*
    @Test
    public void cuadreActualTipoUsuarioEmpty() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setTipoUsuario("");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));

    }

    /*cuadreActualTipoUsuarioIncorrect prueba que la respuesta del servicio al
     * enviar el atributo TipoUsuario incorrecto*/
    /*
    @Test
    public void cuadreActualTipoUsuarioIncorrect() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setCodigoTerminal("%&$#$%&%");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));

    }

    /*cuadreActualIdSesionEmpty prueba que la respuesta del servicio al
     * enviar el atributo IdSesion vacio*/
    /*
    @Test
    public void cuadreActualIdSesionEmpty() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setIdSesion("");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));

    }

    /*cuadreActualIdSesionIncorrect prueba que la respuesta del servicio al
     * enviar el atributo IdSesion incorrecto*/
    /*
    @Test
    public void cuadreActualIdSesionIncorrect() {

        CuadreActualINDto cuadreActual = initValue();
        cuadreActual.setIdSesion("%&$#$%&%");

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));

    }


    /*-------------------- PRUEBAS DE ATRIBUTOS DE RESPUESTA -------------------------*/

    /*cuadreActualCuadreProductos prueba que la respuesta del servicio el atributo
     * cuadreProductos no llegue de valor null o vacio*/
    @Test
    public void cuadreActualCuadreProductos() {

        CuadreActualINDto cuadreActual = initValue();

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("cuadreProductos", both(not(empty())).and(notNullValue()));

    }

    /*cuadreActualCodProducto prueba que la respuesta del servicio el atributo
     * codProducto que esta dentro del atributo cuadreProductos no llegue de valor null o vacio*/
    @Test
    public void cuadreActualCodProducto() {

        CuadreActualINDto cuadreActual = initValue();

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("cuadreProductos.codProducto", both(not(empty())).and(notNullValue()));

    }

    /*cuadreActualNombre prueba que la respuesta del servicio el atributo
     * nombre que esta dentro del atributo cuadreProductos no llegue de valor null o vacio*/
    @Test
    public void cuadreActualNombre() {

        CuadreActualINDto cuadreActual = initValue();

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("cuadreProductos.nombre", both(not(empty())).and(notNullValue()));

    }

    /*cuadreActualvalorVenta prueba que la respuesta del servicio el atributo
     * valorVenta que esta dentro del atributo cuadreProductos no llegue de valor null o vacio*/
    @Test
    public void cuadreActualvalorVenta() {

        CuadreActualINDto cuadreActual = initValue();

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("cuadreProductos.valorVenta", both(not(empty())).and(notNullValue()));

    }

    /*cuadreActualimprimeCuadreLoteria prueba que la respuesta del servicio el atributo
     * imprimeCuadreLoteria que esta dentro del atributo cuadreProductos no llegue de valor null o vacio*/
    @Test
    public void cuadreActualimprimeCuadreLoteria() {

        CuadreActualINDto cuadreActual = initValue();

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("cuadreProductos.imprimeCuadreLoteria", both(not(empty())).and(notNullValue()));

    }


    /*cuadreActualimprimeCuadreLoteriaValue prueba que la respuesta del servicio el atributo
     * imprimeCuadreLoteria que esta dentro del atributo cuadreProductos sea de valor SI*/
    @Test
    public void cuadreActualimprimeCuadreLoteriaValue() {

        CuadreActualINDto cuadreActual = initValue();

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        Response response = given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath);


        CuadreActualOUTDto respuestaBody = response.getBody().as(CuadreActualOUTDto.class);


        if(respuestaBody.getMensajeSp().equals("tNo existen liquidaciones ...,,z")){
            Assert.assertTrue(true);
            return;
        }

        Map<String, String> products = new HashMap<>();

        products.put("812","SI");
        products.put("124","SI");
        products.put("123","SI");
        products.put("503","SI");
        products.put("501","SI");
        products.put("502","SI");
        products.put("504","SI");
        products.put("505","SI");
        products.put("506","SI");
        products.put("863","SI");
        products.put("01","SI");
        products.put("805","SI");
        products.put("806","SI");
        products.put("809","SI");
        products.put("02","SI");
        products.put("866","SI");


        for (int i = 0; i < respuestaBody.getCuadreProductos().size(); i++) {
            if (products.containsValue(respuestaBody.getCuadreProductos().get(i).getCodProducto())){
                if (products.get(respuestaBody.getCuadreProductos().get(i).getCodProducto()).equals(respuestaBody.getCuadreProductos().get(i).getImprimeCuadreLoteria())) {
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }

        }

    }



    /*cuadreActualToken prueba que la respuesta del servicio el atributo
     * el atributo token no llegue vacio*/
    @Test
    public void cuadreActualToken() {

        CuadreActualINDto cuadreActual = initValue();

        String jsonBody = new GsonBuilder().create().toJson(cuadreActual);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("token", both(not(empty())).and(notNullValue()));

    }

}
