package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.MaskeSaleOUTDto;
import dto.makesale.ApuestaRequestDto;
import dto.makesale.ChanceNumeroRequest;
import dto.makesale.LoteriasRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import process.LoginService;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ChanceClasico {

    String autorizador;
    String idSesion;
    String serie1;
    String serie2;

    int valor = 8000;
    int valorSugerido ;

    int tipoJuego;




    public ChanceClasico(){
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

    public int aproximar(int valor){

        BigDecimal fraccion;
        BigDecimal aux;
        BigDecimal resto;

        int adicion;

        // Divide el valor entre 100 utilizando el tipo BigDecimal
        // Esto da como resultado una fracción del 1% del valor original
        fraccion = new BigDecimal(valor).divide(new BigDecimal(100));

        // Toma la parte entera de la fracción utilizando la función floor
        aux = new BigDecimal(Math.floor(fraccion.intValue()));

        // Calcula el resto (diferencia entre la fracción y su parte entera)
        resto = fraccion.add(aux.negate());

        // De acuerdo con la lógica, si el resto está entre 0 y 0.10, se multiplica por 100 para convertirlo a un entero entre 0 y 10.
        // Esto implica que el método asume que el valor de entrada siempre estará entre 0 y 100.
        if (resto.intValue() >= 0 && resto.intValue() < 10) {
            resto = resto.multiply(new BigDecimal(100));
        }
        // Se calcula la cantidad a añadir al valor original (100 menos el valor del resto como entero)
        adicion = 100 - resto.intValue();

        // Se suma la cantidad adicionada al valor original y se devuelve el resultado como entero.
        return valor + adicion;

    }

    public  int valorTotal(){
        valorSugerido = (int) (valor * 1.19);
        int  valorUnico = aproximar(valorSugerido);
        return valorUnico;
    }

    public int modalidad(int valor){
        tipoJuego = valor / 2;
        return tipoJuego;
    }


    /*
     * Se crea el una una instancia del dto para inicializar los valores
     *
     * */

    public ApuestaRequestDto initValue(){

        ApuestaRequestDto makeSale1 = new ApuestaRequestDto();


        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();
        ArrayList<LoteriasRequest> listLoteria = new ArrayList<>();

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(9000) + 1000;



        ChanceNumeroRequest numero1 = new ChanceNumeroRequest(Integer.toString(numeroAleatorio),modalidad(valor), modalidad(valor));
        LoteriasRequest loteria1 = new LoteriasRequest("52",0,0);


        listChance.add(numero1);
        listLoteria.add(loteria1);


        makeSale1.setCodigoVendedor("222230");
        makeSale1.setCodigoProducto("01");
        makeSale1.setValor(valor);
        makeSale1.setValorSugerido(valorTotal());
        makeSale1.setCanalId("PVMA");
        makeSale1.setCodigoTerminal("862634041369599");
        makeSale1.setTipoUsuario("VENDEDOR");
        makeSale1.setSecuenciaTransaccion(1);
        makeSale1.setTransactionTime(Calendar.getInstance().getTimeInMillis()+"");
        makeSale1.setOperacionTransaccion("VENTA");
        makeSale1.setSeriePrefijo(serie1);
        makeSale1.setSerieActual(serie2);
        makeSale1.setIdPromocion(0);
        makeSale1.setChanceNumeros(listChance);
        makeSale1.setIdRifa(0);
        makeSale1.setSerie1(serie1);
        makeSale1.setSerie2(serie2);
        makeSale1.setLoterias(listLoteria);

        return makeSale1;

    }

    /*
     * El MakeSaleTest prueba que el chance tradicional fue realizada con exito
     *
     * */

    @Test
    public void MakeSaleTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();



        String jsonBody = new GsonBuilder().create().toJson(chance1);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El codVendedorEmpty prueba el campo "codigoVendedor" con valor vacio
     *
     * */

    @Test
    public void codVendedorEmptyTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto codVendedorEmpty = initValue();
        codVendedorEmpty.setCodigoVendedor("");

        String jsonBody = new GsonBuilder().create().toJson(codVendedorEmpty);


                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                //.statusCode(200)
                .body("mensaje", equalTo("Sesion no encontrada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codVendedorIncorrectTest prueba el campo "codigoVendedor" con valor incorrecto
     *
     * */

    @Test
    public void codVendedorIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto codVendedorIncorrect = initValue();
        codVendedorIncorrect.setCodigoVendedor("*****");

        String jsonBody = new GsonBuilder().create().toJson(codVendedorIncorrect);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                //.statusCode(200)
                .body("mensaje", equalTo("Sesion no encontrada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codVProductEmptyTest prueba el campo "codigoProducto" con valor vacio
     *
     * */

    @Test
    public void codVProductEmptyTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto codVProductEmptyTest = initValue();
        codVProductEmptyTest.setCodigoProducto("");

        String jsonBody = new GsonBuilder().create().toJson(codVProductEmptyTest);


                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                //.statusCode(200)
                .body("mensaje", equalTo("Producto %s no parametrizado."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codVProductIncorrectTest prueba el campo "codigoProducto" con valor vacio
     *
     * */

    @Test
    public void codVProductIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto codVProductIncorrect = initValue();
        codVProductIncorrect.setCodigoProducto("****");

        String jsonBody = new GsonBuilder().create().toJson(codVProductIncorrect);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                //.statusCode(200)
                .body("mensaje", equalTo("Producto %s no parametrizado."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El valorIncorrectTest prueba el campo "valor" con valor incorrecto
     *
     * */

    @Test
    public void valorIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto valorIncorrectTest = initValue();
        valorIncorrectTest.setValor(0);

        String jsonBody = new GsonBuilder().create().toJson(valorIncorrectTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("No se pudo completar la transaccion."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El valorSugeridoIncorrectTest prueba el campo "valorSugerido" con valor incorrecto
     *
     * */

    @Test
    public void valorSugeridoIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto valorSugeridoIncorrectTest = initValue();
        valorSugeridoIncorrectTest.setValorSugerido(10);

        String jsonBody = new GsonBuilder().create().toJson(valorSugeridoIncorrectTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Valor sugerido incorrecto."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El canalIdIncorrectTest prueba el campo "canalId" con valor incorrecto
     *
     * */

    @Test
    public void canalIdIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto canalIdIncorrectTest = initValue();
        canalIdIncorrectTest.setCanalId("GANA");

        String jsonBody = new GsonBuilder().create().toJson(canalIdIncorrectTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Canal no permitido."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El canalIdIncorrectTest prueba el campo "canalId" con valor vacio
     *
     * */

    @Test
    public void canalIdEmptyTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto canalIdEmptyTest = initValue();
        canalIdEmptyTest.setCanalId("");

        String jsonBody = new GsonBuilder().create().toJson(canalIdEmptyTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Canal no permitido."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codTerminalEmptyTest prueba el campo "codigoTerminal" con valor vacio
     *
     * */

    @Test
    public void codTerminalEmptyTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto codTerminalEmpty = initValue();
        codTerminalEmpty.setCodigoTerminal("");

        String jsonBody = new GsonBuilder().create().toJson(codTerminalEmpty);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("No se pudo completar la transaccion."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El codTerminalIncorrectTest prueba el campo "codigoTerminal" con valor incorrecto
     *
     * */

    @Test
    public void codTerminalIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto codTerminalEmpty = initValue();
        codTerminalEmpty.setCodigoTerminal("000000000000**");

        String jsonBody = new GsonBuilder().create().toJson(codTerminalEmpty);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("No se pudo completar la transaccion."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El tipoUsuarioEmptyTest prueba el campo "tipoUsuario" con valor vacio
     *
     * */

    @Test
    public void tipoUsuarioEmptyTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto tipoUsuarioEmpty = initValue();
        tipoUsuarioEmpty.setTipoUsuario("");

        String jsonBody = new GsonBuilder().create().toJson(tipoUsuarioEmpty);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El tipoUsuarioEmptyTest prueba el campo "tipoUsuario" con valor Incorrecto
     *
     * */

    @Test
    public void tipoUsuarioIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto tipoUsuarioIncorrectTest = initValue();
        tipoUsuarioIncorrectTest.setTipoUsuario("COMPRADOR*");

        String jsonBody = new GsonBuilder().create().toJson(tipoUsuarioIncorrectTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El operacionTransaccionEmptyTest prueba el campo "operacionTransaccion" con valor vacio
     *
     * */
   /*
    @Test
    public void operacionTransaccionEmptyTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto operacionTransaccionEmptyTest = initValue();
        operacionTransaccionEmptyTest.setOperacionTransaccion("");

        String jsonBody = new GsonBuilder().create().toJson(operacionTransaccionEmptyTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El operacionTransaccioIncorrectTest prueba el campo "secuenciaTransaccion" con valor incorrecto
     *
     * */
    /*
    @Test
    public void operacionTransaccioIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto operacionTransaccioIncorrectTest = initValue();
        operacionTransaccioIncorrectTest.setOperacionTransaccion("SELECT * FROM loterias");

        String jsonBody = new GsonBuilder().create().toJson(operacionTransaccioIncorrectTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El seriePrefijoEmptyTest prueba el campo "seriePrefijo" con valor vacio
     *
     * */
    /*
    @Test
        public void seriePrefijoEmptyTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto seriePrefijoEmptyTest = initValue();
        seriePrefijoEmptyTest.setSeriePrefijo("");

        String jsonBody = new GsonBuilder().create().toJson(seriePrefijoEmptyTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El seriePrefijoIncorrectTest prueba el campo "seriePrefijo" con valor Incorrecto
     *
     * */
    /*
    @Test
    public void seriePrefijoIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto seriePrefijoIncorrectTest = initValue();
        seriePrefijoIncorrectTest.setSeriePrefijo("SELECT * FROM papeleria");

        String jsonBody = new GsonBuilder().create().toJson(seriePrefijoIncorrectTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El serieActualIncorrectTest prueba el campo "serieActual" con valor Incorrecto
     *
     * */
    /*
    @Test
    public void serieActualIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serieActualIncorrectTest = initValue();
        serieActualIncorrectTest.setSerieActual("SELECT * FROM vendedor");

        String jsonBody = new GsonBuilder().create().toJson(serieActualIncorrectTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El serieActualEmptyTest prueba el campo "serieActual" con valor vacio
     *
     * */
    /*
    @Test
    public void serieActualEmptyTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serieActualEmptyTest = initValue();
        serieActualEmptyTest.setSerieActual("");

        String jsonBody = new GsonBuilder().create().toJson(serieActualEmptyTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El idPromocionIncorrectTest prueba el campo "idPromocion" con valor Incorrecto
     *
     * */


    @Test
    public void idPromocionIncorrectTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serieActualEmptyTest = initValue();
        serieActualEmptyTest.setIdPromocion(8);

        String jsonBody = new GsonBuilder().create().toJson(serieActualEmptyTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("No se encontro la promocion"));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El payValueMinTest prueba el total del saldo minimo  apostar
     *
     * */


    @Test
    public void payValueMinTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serieActualEmptyTest = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();

        ChanceNumeroRequest chance = new ChanceNumeroRequest("3656",50,50);

        listChance.add(chance);
        serieActualEmptyTest.setChanceNumeros(listChance);
        serieActualEmptyTest.setValorSugerido(0);


        String jsonBody = new GsonBuilder().create().toJson(serieActualEmptyTest);


                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo( "Valor apostado no permitido Minimo $ 80."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El payValueMaxTest prueba  el total del saldo maximo apostar
     *
     * */

    /*
    @Test
    public void payValueMaxTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serieActualEmptyTest = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();

        ChanceNumeroRequest chance = new ChanceNumeroRequest("3656",87000,50000);

        listChance.add(chance);
        serieActualEmptyTest.setChanceNumeros(listChance);
        serieActualEmptyTest.setValorSugerido(0);


        String jsonBody = new GsonBuilder().create().toJson(serieActualEmptyTest);


                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo( "Valor apostado no permitido Minimo $ 700."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El sameValueinSameLoteryTest prueba dos apuestas con el mismo numero y valor en la misma loteria
     *
     * */

    /*
    @Test
    public void sameValueinSameLoteryTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto sameValueinSameLoteryTest = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();

        ChanceNumeroRequest chance = new ChanceNumeroRequest("3656",1000,1000);
        ChanceNumeroRequest chance1 = new ChanceNumeroRequest("3656",1000,1000);


        listChance.add(chance);
        listChance.add(chance1);
        sameValueinSameLoteryTest.setChanceNumeros(listChance);


        String jsonBody = new GsonBuilder().create().toJson(sameValueinSameLoteryTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo( "Valor apostado no permitido Minimo $ 700."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El oneFigureWithSuperPlenoTest prueba  el numero de apuesta de una sola cifra en super pleno
     *
     * */


    @Test
    public void oneFigureWithSuperPlenoTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto sameValueinSameLoteryTest = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();

        ChanceNumeroRequest chance = new ChanceNumeroRequest("3",1000,1000);
        listChance.add(chance);

        sameValueinSameLoteryTest.setChanceNumeros(listChance);


        String jsonBody = new GsonBuilder().create().toJson(sameValueinSameLoteryTest);


                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo( false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


    /*
     * El oneFiguresWithPataTest prueba  el numero de apuesta de una cifra en pata
     *
     * */


    @Test
    public void oneFiguresWithPataTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto oneFiguresWithPataTest = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();

        ChanceNumeroRequest chance = new ChanceNumeroRequest("1",1000);
        listChance.add(chance);

        oneFiguresWithPataTest.setChanceNumeros(listChance);




        String jsonBody = new GsonBuilder().create().toJson(oneFiguresWithPataTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo( false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El twoFigureWithSuperPlenoTest prueba  el numero de apuesta de dos cifras en super pleno
     *
     * */


    @Test
    public void twoFigureWithSuperPlenoTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto twoFigureWithSuperPlenoTest = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();

        ChanceNumeroRequest chance = new ChanceNumeroRequest("35",1000,1000);
        listChance.add(chance);

        twoFigureWithSuperPlenoTest.setChanceNumeros(listChance);


        String jsonBody = new GsonBuilder().create().toJson(twoFigureWithSuperPlenoTest);


                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo( false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El fourFigureWithUnaAndPataTest prueba  el numero de apuesta de cuantro cifras en pata y una
     *
     * */

    /*
    @Test
    public void fourFigureWithUnaAndPataTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto fourFigureWithUnaAndPataTest = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();

        ChanceNumeroRequest chance = new ChanceNumeroRequest(1000,1000,"3656");
        listChance.add(chance);

        fourFigureWithUnaAndPataTest.setChanceNumeros(listChance);


        String jsonBody = new GsonBuilder().create().toJson(fourFigureWithUnaAndPataTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo( false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


    /*
     * El fourFigureWithUnaTest prueba  el numero de apuesta de cuatro cifras en una
     *
     * */

    /*
    @Test
    public void fourFigureWithUnaTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto fourFigureWithUnaTest = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();

        ChanceNumeroRequest chance = new ChanceNumeroRequest(1000,"3656");
        listChance.add(chance);

        fourFigureWithUnaTest.setChanceNumeros(listChance);


        String jsonBody = new GsonBuilder().create().toJson(fourFigureWithUnaTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo( false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El maxLinesTest prueba dos apuestas con el mismo numero y valor en la misma loteria
     *
     * */

    /*
    @Test
    public void maxLinesTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto maxLinesTest = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();

        ChanceNumeroRequest chance = new ChanceNumeroRequest("3656",1000,1000);
        ChanceNumeroRequest chance1 = new ChanceNumeroRequest("5898",1000,1000);
        ChanceNumeroRequest chance2 = new ChanceNumeroRequest("1235",1000,1000);
        ChanceNumeroRequest chance3 = new ChanceNumeroRequest("6525",1000,1000);
        ChanceNumeroRequest chance4 = new ChanceNumeroRequest("7987",1000,1000);
        ChanceNumeroRequest chance5 = new ChanceNumeroRequest("5214",1000,1000);
        //ChanceNumeroRequest chance6 = new ChanceNumeroRequest("5204",1000,1000);

        listChance.add(chance);
        listChance.add(chance1);
        listChance.add(chance2);
        listChance.add(chance3);
        listChance.add(chance4);
        listChance.add(chance5);
        //listChance.add(chance6);
        maxLinesTest.setChanceNumeros(listChance);
        maxLinesTest.setValor(6000);
        maxLinesTest.setValorSugerido(0);


        String jsonBody = new GsonBuilder().create().toJson(maxLinesTest);


        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", equalTo( false));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


    /*
     * El serie1EmptyTest prueba  el tributo "Serie1" con valor vacio
     *
     * */

    /*
    @Test
    public void serie1EmptyTest() {

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serie1EmptyTest = initValue();
        serie1EmptyTest.setSerie1("");
        //serie1EmptyTest.setSeriePrefijo("");

        String jsonBody = new GsonBuilder().create().toJson(serie1EmptyTest);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El serie1IncorrectTest prueba  el tributo "Serie1" con valor incorrecto
     *
     * */


    @Test
    public void serie1IncorrectTest() {

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serie1EmptyTest = initValue();
        serie1EmptyTest.setSerie1("XYZ343");

        String jsonBody = new GsonBuilder().create().toJson(serie1EmptyTest);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El serie2EmptyTest prueba  el tributo "Serie2" con valor vacio
     *
     * */


    @Test
    public void serie2EmptyTest() {

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serie2EmptyTest = initValue();
        serie2EmptyTest.setSerie2("");

        String jsonBody = new GsonBuilder().create().toJson(serie2EmptyTest);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El serie2Incorrectest prueba  el tributo "Serie2" con valor incorrecto
     *
     * */


    @Test
    public void serie2Incorrectest() {

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serie2EmptyTest = initValue();
        serie2EmptyTest.setSerie2("11111111");

        String jsonBody = new GsonBuilder().create().toJson(serie2EmptyTest);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El serie1AndSeriePrefijoEmptyTest prueba  el tributo "Serie1" y "seriePrefijo" con valor vacio,
     * por lo tanto no se refleja la serie de papeleria.
     *
     * */

    /*
    @Test
    public void serie1AndSeriePrefijoEmptyTest() {

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serie1EmptyTest = initValue();
        serie1EmptyTest.setSerie1("");
        serie1EmptyTest.setSeriePrefijo("");

        String jsonBody = new GsonBuilder().create().toJson(serie1EmptyTest);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El serie2AndSerieActualEmptyTest prueba  el tributo "Serie2" y "serieActual" con valor vacio,
     * por lo tanto no se refleja la serie2.
     *
     * */


    @Test
    public void serie2AndSerieActualEmptyTest() {

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto serie2AndSerieActualEmpty = initValue();
        serie2AndSerieActualEmpty.setSerie2("");
        serie2AndSerieActualEmpty.setSerieActual("");

        String jsonBody = new GsonBuilder().create().toJson(serie2AndSerieActualEmpty);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El loteriesEmptyTest prueba  el tributo "loterias" con valor vacio
     *
     * */


    @Test
    public void loteriesEmptyTest() {

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto loteriesEmptyTest = initValue();

        ArrayList<LoteriasRequest> listLoteries = new ArrayList<>();

        LoteriasRequest loteries = new LoteriasRequest();

        listLoteries.add(loteries);

        loteriesEmptyTest.setLoterias(listLoteries);

        String jsonBody = new GsonBuilder().create().toJson(loteriesEmptyTest);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Loteria no encontrada o no permitida para el producto seleccionado."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);


    }


    /*TEST DE RESPUESTA*/


    /*
     * El parametersApuestasRespuestasTest prueba que el parametro de apuestasRespuesta no llegue null ni vacio
     *
     * */

    @Test
    public void parametersApuestasRespuestasTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
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
     * El MakeSaleEmptyTest prueba que el chance tradicional se realiza con los campos (String) vacios
     * y los campos de tipo (int) en 0.
     *
     * */

    @Test
    public void MakeSaleEmptyTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        ArrayList<ChanceNumeroRequest> listChance = new ArrayList<>();
        ArrayList<LoteriasRequest> listLoteria = new ArrayList<>();

        ChanceNumeroRequest numero1 = new ChanceNumeroRequest("",0,0);
        LoteriasRequest loteria1 = new LoteriasRequest("",0,0);


        listChance.add(numero1);
        listLoteria.add(loteria1);


        chance1.setCodigoVendedor("");
        chance1.setCodigoProducto("");
        chance1.setValor(0);
        chance1.setValorSugerido(0);
        chance1.setCanalId("");
        chance1.setCodigoTerminal("");
        chance1.setTipoUsuario("");
        chance1.setSecuenciaTransaccion(0);
        chance1.setTransactionTime(Calendar.getInstance().getTimeInMillis()+"");
        chance1.setOperacionTransaccion("");
        chance1.setSeriePrefijo("");
        chance1.setSerieActual("");
        chance1.setIdPromocion(0);
        chance1.setChanceNumeros(listChance);
        chance1.setIdRifa(0);
        chance1.setSerie1("");
        chance1.setSerie2("");
        chance1.setLoterias(listLoteria);

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                //.statusCode(200)
                .body("mensaje", equalTo("Sesion no encontrada."));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);

    }

    /*
     * El serie1ResponseTest prueba que el atributo "serie1" que llega en la respuesta
     * no tenga un valor nulo o vacio.
     *
     * */

    @Test
    public void serie1ResponseTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("serie1", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El serie2ResponseTest prueba que el atributo "serie2" que llega en la respuesta
     * no tenga un valor nulo o vacio.
     *
     * */

    @Test
    public void serie2ResponseTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("serie2", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El serialUnicoResponseTest prueba que el atributo "serialUnico" que llega en la respuesta
     * no tenga un valor nulo o vacio.
     *
     * */

    @Test
    public void serialUnicoResponseTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("serialUnico", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El digitoChequeoResponseTest prueba que el atributo "digitoChequeo" que llega en la respuesta
     * no tenga un valor nulo o vacio.
     *
     * */

    @Test
    public void digitoChequeoResponseTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("digitoChequeo", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El valorTotalPagadoResponseTest prueba que el atributo "valorTotalPagado" que llega en la respuesta
     * no tenga un valor nulo o vacio.
     *
     * */

    @Test
    public void valorTotalPagadoResponseTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("valorTotalPagado", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }


    /*
     * El valorTotalApostadoResponseTest prueba que el atributo "valorTotalApostado" que llega en la respuesta
     * no tenga un valor nulo o vacio.
     *
     * */

    @Test
    public void valorTotalApostadoResponseTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("valorTotalApostado", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El valorTotalEncimeResponseTest prueba que el atributo "valorTotalEncime" que llega en la respuesta
     * no tenga un valor nulo o vacio.
     *
     * */

    @Test
    public void valorTotalEncimeResponseTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("valorTotalEncime", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El valorTotalIvaResponseTest prueba que el atributo "valorTotalIva" que llega en la respuesta
     * no tenga un valor nulo o vacio.
     *
     * */

    @Test
    public void valorTotalIvaResponseTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("valorTotalIva", both(not(empty())).and(notNullValue()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in millis: " + totalTime);
    }

    /*
     * El fechaSorteoResponseTest prueba que el atributo "fechaSorteo" que llega en la respuesta
     * no tenga un valor nulo o vacio.
     *
     * */

    @Test
    public void fechaSorteoResponseTest(){

        long startTime = System.currentTimeMillis();

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post("realizarVenta")
                .then()
                .statusCode(200)
                .body("fechaSorteo", both(not(empty())).and(notNullValue()));

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

        ApuestaRequestDto chance1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance1);

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
