package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.MaskeSaleOUTDto;
import dto.makesuperastro.ChanceNumero;
import dto.makesuperastro.Loteria;
import dto.makesuperastro.MakeSuperAstroINDto;
import dto.makesuperastro.MakeSuperAstroOUTDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import process.LoginService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MakeSuperAstro {

    String autorizador;
    String idSesion;
    String serie1;
    String serie2;


    public MakeSuperAstro(){
        RestAssured.baseURI="http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/astro/";
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

    public MakeSuperAstroINDto initValue(){
        MakeSuperAstroINDto astro = new MakeSuperAstroINDto();
        ArrayList<ChanceNumero> listNumero = new  ArrayList();
        ArrayList<Loteria> listLoteria = new ArrayList();

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(9000) + 1000;



        Loteria loteria = new Loteria();
        loteria.setCodLoteria("12");
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);



        ChanceNumero numero = new ChanceNumero();
        numero.setNumero(Integer.toString(numeroAleatorio));
        numero.setSigno("01");
        numero.setValor(1000);


        ChanceNumero numero2 = new ChanceNumero();
        numero2.setNumero(Integer.toString(numeroAleatorio));
        numero2.setSigno("02");
        numero2.setValor(1000);





        listLoteria.add(loteria);
        listNumero.add(numero);
        listNumero.add(numero2);

        astro.setCodigoVendedor("222230");
        astro.setCodigoProducto("03");
        astro.setValor(2000);
        astro.setCanalId("PVMA");
        astro.setCodigoTerminal("862634041369599");
        astro.setTipoUsuario("VENDEDOR");
        astro.setSecuenciaTransaccion(1);
        astro.setTransactionTime(Calendar.getInstance().getTimeInMillis()+"");
        astro.setOperacionTransaccion("VENTA");
        astro.setSeriePrefijo(serie1);
        astro.setSerieActual(serie2);
        astro.setChanceNumeros(listNumero);
        astro.setSerie1(serie1);
        astro.setSerie2(serie2);
        astro.setLoterias(listLoteria);
        return astro;
    }

    /*
    * El MakeSuperAstroTest prueba que se realice el super astro de forma exitosa
    * */

    @Test
    public void MakeSuperAstroTest(){
        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(astro1);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El MakeSuperAstroEmptyTest prueba los atributos de envio con valor vacio y los enteros en 0
     * */

    @Test
    public void MakeSuperAstroEmptyTest(){
        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();

        ArrayList<ChanceNumero> listNumero = new  ArrayList();
        ArrayList<Loteria> listLoteria = new ArrayList();

        Loteria loteria = new Loteria();
        loteria.setCodLoteria("");
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);



        ChanceNumero numero = new ChanceNumero();
        numero.setNumero("");
        numero.setSigno("");
        numero.setValor(0);

        listLoteria.add(loteria);
        listNumero.add(numero);

        astro1.setCodigoVendedor("");
        astro1.setCodigoProducto("");
        astro1.setValor(0);
        astro1.setCanalId("");
        astro1.setCodigoTerminal("");
        astro1.setTipoUsuario("");
        astro1.setSecuenciaTransaccion(0);
        astro1.setTransactionTime("");
        astro1.setOperacionTransaccion("");
        astro1.setSeriePrefijo("");
        astro1.setSerieActual("");
        astro1.setChanceNumeros(listNumero);
        astro1.setSerie1("");
        astro1.setSerie2("");
        astro1.setLoterias(listLoteria);


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(501)
                .body("mensaje", equalTo("Sesion no encontrada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codVendedorEmptyTest prueba el atributo de "codigoVendedor" con valor vacio.
     * */
    @Test
    public void codVendedorEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setCodigoVendedor("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(501)
                .body("mensaje", equalTo("Sesion no encontrada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El codVendedorIncorrectTest prueba el atributo de "codigoVendedor" con valor incorrecto.
     * */
    @Test
    public void codVendedorIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setCodigoVendedor("$%&*$#2323");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(501)
                .body("mensaje", equalTo("Sesion no encontrada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El codProductoEmptyTest prueba el atributo de "codigoProducto" con valor vacio.
     * */
    /*
    @Test
    public void codProductoEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setCodigoProducto("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("99"));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El codProductoIncorrectTest prueba el atributo de "codigoProducto" con valor incorrecto.
     * */
    @Test
    public void codProductoIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setCodigoProducto("#01");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Producto %s no parametrizado."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El valorEmptyTest prueba el atributo de "valor" con valor en 0.
     * */
    @Test
    public void valorEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setValor(0);


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El valorIncorrectTest prueba el atributo de "valor" con valor incorrecto.
     * */
    @Test
    public void valorIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setValor(-9000);


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El valorIncorrectTest2 prueba el atributo de "valor" con valor incorrecto la suma de valor no es el correcto.
     * */
    @Test
    public void valorIncorrectTest2(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setValor(4000);

        ArrayList<ChanceNumero> listNumero = new  ArrayList();
        ArrayList<Loteria> listLoteria = new ArrayList();

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(9000) + 1000;

        ChanceNumero numero2 = new ChanceNumero();
        numero2.setNumero(Integer.toString(numeroAleatorio));
        numero2.setSigno("02");
        numero2.setValor(1000);

        ChanceNumero numero3 = new ChanceNumero();
        numero3.setNumero(Integer.toString(numeroAleatorio));
        numero3.setSigno("02");
        numero3.setValor(1000);

        Loteria loteria = new Loteria();
        loteria.setCodLoteria("12");
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);



        listLoteria.add(loteria);
        listNumero.add(numero2);
        listNumero.add(numero3);
        astro1.setValor(3000);


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El canalIdEmptyTest prueba el atributo de "canalId" con valor vacio.
     * */
    @Test
    public void canalIdEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setCanalId("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }



    /*
     * El canalIdIncorrectTest prueba el atributo de "canalId" con valor incorrecto.
     * */
    @Test
    public void canalIdIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setCanalId("GANA");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El codTerminalEmptyTest prueba el atributo de "codigoTerminal" con valor vacio.
     * */
    @Test
    public void codTerminalEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setCodigoTerminal("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El codTerminalIncorrectTest prueba el atributo de "codigoTerminal" con valor incorrecto.
     * */
    @Test
    public void codTerminalIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setCodigoTerminal("$123423231*");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Terminal no existe."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El tipoUsuarioEmptyTest prueba el atributo de "tipoUsuario" con valor vacio.
     * */
    /*
    @Test
    public void tipoUsuarioEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setTipoUsuario("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El tipoUsuarioIncorrectTest prueba el atributo de "tipoUsuario" con valor incorrecto.
     * */
    @Test
    public void tipoUsuarioIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setTipoUsuario("SELECT * FROM MAESTRO_CHANCE");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("El vendedor no existe"));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El transactionTimeEmptyTest prueba el atributo de "transactionTime" con valor 0.
     * */
    /*
    @Test
    public void transactionTimeEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setTransactionTime("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El transactionTimeEmptyTest prueba el atributo de "transactionTime" con valor vacio.
     * */
    /*
    @Test
    public void transactionTimeIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setTransactionTime("-1676999915");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El secTransaccionEmptyTest prueba el atributo de "secuenciaTransaccion" con valor 0.
     * */
    /*
    @Test
    public void secTransaccionEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setSecuenciaTransaccion(0);


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El secTransaccionIncorrectTest prueba el atributo de "secuenciaTransaccion" con valor incorrecto.
     * */
    /*
    @Test
    public void secTransaccionIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setSecuenciaTransaccion(-14);


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El operTransaccionEmptyTest prueba el atributo de "operacionTransaccion" con valor vacio
     * */
    /*
    @Test
    public void operTransaccionEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setOperacionTransaccion("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El operTransaccionEmptyTest prueba el atributo de "operacionTransaccion" con valor vacio
     * */
    /*
    @Test
    public void operTransaccionIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setOperacionTransaccion("SELECT * FROM MAESTRO_CHANCE");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El seriePrefijoEmptyTest prueba el atributo de "seriePrefijo" con valor vacio
     * */
    /*
    @Test
    public void seriePrefijoEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setSeriePrefijo("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("La colilla ya se encuentra utilizada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El seriePrefijoIncorrectTest prueba el atributo de "seriePrefijo" con valor incorrecto
     * */
    @Test
    public void seriePrefijoIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setSeriePrefijo("VV18");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El serieActualEmptyTest prueba el atributo de "serieActual" con valor vacio
     * */
    /*
    @Test
    public void serieActualEmptyTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setSerieActual("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El serieActualIncorrectTest prueba el atributo de "serieActual" con valor incorrecto
     * */
    @Test
    public void serieActualIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setSerieActual("VQ18");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }



    /*
     * El chanceValorIncorrectTest prueba el atributo de "valor" con valor en 0
     * y valor apostado de 200000
     * */
    @Test
    public void chanceValorIncorrectTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();

        astro1.setValor(0);

        ArrayList<ChanceNumero> listNumero1 = new ArrayList();

        ChanceNumero numero1 = new ChanceNumero();
        numero1.setNumero("9898");
        numero1.setSigno("01");
        numero1.setValor(20000);
        listNumero1.add(numero1);

        astro1.setChanceNumeros(listNumero1);

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Valor a pagar no corresponde con el valor de la recarga."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }
    /*
     * El maxLineasTest prueba el maximo de lineas apostadas
     * */
    @Test
    public void maxLineasTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();

        ArrayList<ChanceNumero> listNumero1 = new ArrayList();

        ChanceNumero numero1 = new ChanceNumero();
        ChanceNumero numero2 = new ChanceNumero();
        ChanceNumero numero3 = new ChanceNumero();
        ChanceNumero numero4 = new ChanceNumero();
        ChanceNumero numero5 = new ChanceNumero();
        ChanceNumero numero6 = new ChanceNumero();

        numero1.setNumero("9898");
        numero1.setSigno("01");
        numero1.setValor(10000);

        numero2.setNumero("9800");
        numero2.setSigno("02");
        numero2.setValor(10000);

        numero3.setNumero("1111");
        numero3.setSigno("03");
        numero3.setValor(10000);

        numero4.setNumero("1234");
        numero4.setSigno("12");
        numero4.setValor(10000);

        numero5.setNumero("1244");
        numero5.setSigno("13");
        numero5.setValor(10000);

        numero6.setNumero("0433");
        numero6.setNumero("05");
        numero6.setValor(10000);


        listNumero1.add(numero1);
        listNumero1.add(numero2);
        listNumero1.add(numero3);
        listNumero1.add(numero4);
        listNumero1.add(numero5);

        astro1.setChanceNumeros(listNumero1);

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El minValorTest prueba el minimo valor apostado por Linea con un valor de 100
     * */
    @Test
    public void minValorTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setValor(400);

        ArrayList<ChanceNumero> listNumero1 = new ArrayList();

        ChanceNumero numero1 = new ChanceNumero();
        ChanceNumero numero2 = new ChanceNumero();
        ChanceNumero numero3 = new ChanceNumero();
        ChanceNumero numero4 = new ChanceNumero();

        numero1.setNumero("9898");
        numero1.setSigno("01");
        numero1.setValor(100);

        numero2.setNumero("9800");
        numero2.setSigno("02");
        numero2.setValor(100);

        numero3.setNumero("1111");
        numero3.setSigno("03");
        numero3.setValor(100);

        numero4.setNumero("1234");
        numero4.setSigno("12");
        numero4.setValor(100);



        listNumero1.add(numero1);
        listNumero1.add(numero2);
        listNumero1.add(numero3);
        listNumero1.add(numero4);


        astro1.setChanceNumeros(listNumero1);

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El maxValorTest prueba el minimo valor apostado por linea con un valor de 11000
     * */
    @Test
    public void maxValorTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setValor(44000);

        ArrayList<ChanceNumero> listNumero1 = new ArrayList();

        ChanceNumero numero1 = new ChanceNumero();
        ChanceNumero numero2 = new ChanceNumero();
        ChanceNumero numero3 = new ChanceNumero();
        ChanceNumero numero4 = new ChanceNumero();

        numero1.setNumero("9898");
        numero1.setSigno("01");
        numero1.setValor(11000);

        numero2.setNumero("9800");
        numero2.setSigno("02");
        numero2.setValor(11000);

        numero3.setNumero("1111");
        numero3.setSigno("03");
        numero3.setValor(11000);

        numero4.setNumero("1234");
        numero4.setSigno("12");
        numero4.setValor(11000);



        listNumero1.add(numero1);
        listNumero1.add(numero2);
        listNumero1.add(numero3);
        listNumero1.add(numero4);


        astro1.setChanceNumeros(listNumero1);

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*
     * El totalPagarTest prueba el atributo del "valor" del total a pagar sea la suma total de lo
     * apostado por linea
     * */
    @Test
    public void totalPagarTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setValor(0);

        ArrayList<ChanceNumero> listNumero1 = new ArrayList();

        ChanceNumero numero1 = new ChanceNumero();
        ChanceNumero numero2 = new ChanceNumero();
        ChanceNumero numero3 = new ChanceNumero();
        ChanceNumero numero4 = new ChanceNumero();

        numero1.setNumero("9898");
        numero1.setSigno("01");
        numero1.setValor(10000);

        numero2.setNumero("9800");
        numero2.setSigno("02");
        numero2.setValor(10000);

        numero3.setNumero("1111");
        numero3.setSigno("03");
        numero3.setValor(10000);

        numero4.setNumero("1234");
        numero4.setSigno("12");
        numero4.setValor(10000);



        listNumero1.add(numero1);
        listNumero1.add(numero2);
        listNumero1.add(numero3);
        listNumero1.add(numero4);


        astro1.setChanceNumeros(listNumero1);

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El validarLoteriasTest prueba que el codigo de las loterias.
     * */
    @Test
    public void validarLoteriasTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();
        astro1.setValor(60000);

        ArrayList<Loteria> listLoteria = new ArrayList();

        Loteria loteria1 = new Loteria();

        loteria1.setCodLoteria("12");
        loteria1.setCifrasSerie(0);
        loteria1.setCifrasNumero(0);

        Loteria loteria2 = new Loteria();

        loteria2.setCodLoteria("12");
        loteria2.setCifrasSerie(0);
        loteria2.setCifrasNumero(0);

        Loteria loteria3 = new Loteria();

        loteria3.setCodLoteria("12");
        loteria3.setCifrasSerie(0);
        loteria3.setCifrasNumero(0);

        listLoteria.add(loteria1);
        listLoteria.add(loteria2);
        listLoteria.add(loteria3);



        ArrayList<ChanceNumero> listNumero1 = new ArrayList();

        ChanceNumero numero1 = new ChanceNumero();
        ChanceNumero numero2 = new ChanceNumero();


        numero1.setNumero("9898");
        numero1.setSigno("01");
        numero1.setValor(10000);

        numero2.setNumero("9800");
        numero2.setSigno("02");
        numero2.setValor(10000);




        listNumero1.add(numero1);
        listNumero1.add(numero2);



        astro1.setChanceNumeros(listNumero1);
        astro1.setLoterias(listLoteria);

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        LocalTime horaActual = LocalTime.now();
        LocalTime horaCondicional = LocalTime.of(14,05);



        Response response =
                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta");

            MakeSuperAstroOUTDto responseBody = response.getBody().as(MakeSuperAstroOUTDto.class);

            if (horaActual.isAfter(horaCondicional)){
                Assert.assertTrue(responseBody.getMensaje().equals("Cierre de sorteo"));
            }else {
                Assert.assertTrue(responseBody.getCodigoRespuesta().equals("Exito"));
            }


                /*
                .then()
                .statusCode(200)
                .body("exito", equalTo(false));
                 */

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }


    /*TEST DE RESPUESTA*/

    /*
     * El tokenResponseTest prueba que el atributo "token" no llegue vacio ni nulo
     * */

    @Test
    public void tokenResponseTest(){
        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("token", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El apuestaRespuestaTest prueba que el atributo "apuestaRespuesta" no llegue vacio ni nulo
     * */

    @Test
    public void apuestaRespuestaTest(){
        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("apuestasRespuesta", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }



    /*
     * El idTransaccionRespuestaTest prueba que el atributo "idTransaccionRespuesta" no llegue vacio ni nulo
     * */

    @Test
    public void idTransaccionRespuestaTest(){
        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("idTransaccionRespuesta", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


    /*
     * El serie2ActualResponseTest prueba que el atributo "serie2" que llega en la respuesta
     * no sea mayor o igual al atributo "serie2Actual".
     *
     * */

    @Test
    public void serie2ActualResponseTest(){

        long startTime = System.currentTimeMillis();

        MakeSuperAstroINDto astro1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        Response response =given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta");

        MaskeSaleOUTDto respuestaBody = response.getBody().as(MaskeSaleOUTDto.class);

        if(Integer.parseInt(respuestaBody.getSerie2Actual()) <= Integer.parseInt(respuestaBody.getSerie2())){
            Assert.assertTrue(false);
        }else{
            Assert.assertTrue(true);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }



}
