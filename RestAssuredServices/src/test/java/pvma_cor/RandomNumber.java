package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;

import dto.randomnumber.RandomNumberINDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import process.LoginService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RandomNumber{

    String autorizador;
    String idSesion;
    String serie1;
    String serie2;

    public RandomNumber(){
        RestAssured.baseURI="http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/juegosventaazar/";
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

    public RandomNumberINDto initValue(){
        RandomNumberINDto number1 = new RandomNumberINDto();

        number1.setCodigoVendedor("222230");
        number1.setCanalId("PVMA");
        number1.setCodigoTerminal("862634041369599");
        number1.setTipoUsuario("VENDEDOR");
        number1.setCantidadCifras(3);
        number1.setCantidadNumeros(1);

        return number1;
    }

    /*
     * El randomNumberTest prueba que el numero aleatorio que se solicito
     *  fue realizada con exito
     *
     * */

    @Test
    public void randomNumberTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();

        String jsonBody = new GsonBuilder().create().toJson(numero);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


    /*
     * El randomNumberEmptyDataTest prueba los con los valores del request vacio.
     *
     * */

    @Test
    public void randomNumberEmptyDataTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCodigoVendedor("");
        numero.setCanalId("");
        numero.setCodigoTerminal("");
        numero.setTipoUsuario("");
        numero.setCantidadCifras(0);
        numero.setCantidadNumeros(0);

        String jsonBody = new GsonBuilder().create().toJson(numero);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .body("mensaje", equalTo("Sesion no encontrada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codVendedorEmptyTest prueba el atributo "codigoVendedor" del request con valor vacio
     *
     * */

    @Test
    public void codVendedorEmptyTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCodigoVendedor("");

        String jsonBody = new GsonBuilder().create().toJson(numero);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                //.statusCode(200)
                .body("mensaje", equalTo("Sesion no encontrada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codVendedorIncorrectTest prueba el atributo "codigoVendedor" del request con valor incorrecto
     *
     * */

    @Test
    public void codVendedorIncorrectTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCodigoVendedor("12345*.,");

        String jsonBody = new GsonBuilder().create().toJson(numero);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                //.statusCode(200)
                .body("mensaje", equalTo("Sesion no encontrada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


    /*
     * El canalIdEmptyTest prueba el atributo "canalId" del request con valor vacio
     *
     * */

    @Test
    public void canalIdEmptyTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCanalId("");

        String jsonBody = new GsonBuilder().create().toJson(numero);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(500);


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El canalIdIncorrectTest prueba el atributo "canalId" del request con valor Incorrecto
     *
     * */

    @Test
    public void canalIdIncorrectTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCanalId("GANA");

        String jsonBody = new GsonBuilder().create().toJson(numero);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(500);


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codTerminalEmptyTest prueba el atributo "codigoTerminal" del request con valor vacio
     *
     * */

    @Test
    public void codTerminalEmptyTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCodigoTerminal("");

        String jsonBody = new GsonBuilder().create().toJson(numero);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Terminal no existe."));


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codTerminalIncorrectTest prueba el atributo "codigoTerminal" del request con valor incorrecto
     *
     * */

    @Test
    public void codTerminalIncorrectTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCodigoTerminal("363545*.?$");

        String jsonBody = new GsonBuilder().create().toJson(numero);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Terminal no existe."));


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


    /*
     * El tipoUsuarioEmptyTest prueba el atributo "tipoUsuario" del request con valor vacio
     *
     * */
    /*
    @Test
    public void tipoUsuarioEmptyTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setTipoUsuario("");

        String jsonBody = new GsonBuilder().create().toJson(numero);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

     */
    /*
     * El tipoUsuarioIncorrectTest prueba el atributo "tipoUsuario" del request con valor Incorrecto
     *
     * */
    /*
    @Test
    public void tipoUsuarioIncorrectTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setTipoUsuario("SELECT * FROM vendedores");

        String jsonBody = new GsonBuilder().create().toJson(numero);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


     */

    /*
     * El cantidadCifrasCeroTest prueba el atributo "cantidadCifras" del request con valor 0
     *
     * */
/*
    @Test
    public void cantidadCifrasCeroTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCantidadCifras(0);

        String jsonBody = new GsonBuilder().create().toJson(numero);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El cantidadCifrasIncorrectTest prueba el atributo "cantidadCifras" del request con valor incorrecto
     *
     * */
/*
    @Test
    public void cantidadCifrasIncorrectTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCantidadCifras(100);

        String jsonBody = new GsonBuilder().create().toJson(numero);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El cantidadNumerosCeroTest prueba el atributo "cantidadNumeros" del request con valor 0
     *
     * */
/*
    @Test
    public void cantidadNumerosCeroTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCantidadNumeros(0);

        String jsonBody = new GsonBuilder().create().toJson(numero);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El cantidadNumerosIncorrectTest prueba el atributo "cantidadNumeros" del request con valor incorrecto
     *
     * */
/*
    @Test
    public void cantidadNumerosIncorrectTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();
        numero.setCantidadNumeros(-10);

        String jsonBody = new GsonBuilder().create().toJson(numero);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


    //TEST DE RESPUESTA

    /*
     * El numerosGeneradosTest prueba el atributo "numerosGenerados" no llegue nulo ni vacio.
     *
     * */

    @Test
    public void numerosGeneradosTest(){

        long startTime = System.currentTimeMillis();

        RandomNumberINDto numero = initValue();


        String jsonBody = new GsonBuilder().create().toJson(numero);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("generarNumeroAleatorio")
                .then()
                .statusCode(200)
                .body("numerosGenerados", both(not(empty())).and(notNullValue()));


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }



}
