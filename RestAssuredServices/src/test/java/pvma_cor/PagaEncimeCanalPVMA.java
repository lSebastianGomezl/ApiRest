package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.pagaencimePVMA.ChanceNumero;
import dto.pagaencimePVMA.Loteria;
import dto.pagaencimePVMA.PagaEncimePVMAINDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import process.LoginService;


import java.util.ArrayList;

import java.util.Random;

import static io.restassured.RestAssured.*;

public class PagaEncimeCanalPVMA {

    String autorizador;
    String idSesion;
    String serie1;
    String serie2;


    public PagaEncimeCanalPVMA() {
        RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/juegosventaazar/";
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


    public Long trasaccionTime() {
        Long fechaHoraActual = System.currentTimeMillis();
        System.out.println(fechaHoraActual);
        return fechaHoraActual;
    }

    public PagaEncimePVMAINDto initValue() {

        PagaEncimePVMAINDto transaccion = new PagaEncimePVMAINDto();

        transaccion.setIdPromocion(134);

        ArrayList<ChanceNumero> listChance = new ArrayList<>();
        ChanceNumero chance = new ChanceNumero();

        Random random = new Random();
        int numeroAleatorio = random.nextInt(900) + 100; //genera un número aleatorio entre 100 y 999
        String numero = Integer.toString(numeroAleatorio);

        chance.setNumero(numero);
        chance.setPlenoDirecto(1800);

        listChance.add(chance);

        transaccion.setChanceNumeros(listChance);
        transaccion.setIdRifa(0);
        transaccion.setSerie1(serie1);
        transaccion.setSerie2(serie2);

        ArrayList<Loteria> listLoteria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("52");
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);
        listLoteria.add(loteria);


        transaccion.setLoterias(listLoteria);

        transaccion.setCodigoVendedor("222230");
        transaccion.setCodigoProducto("01");
        transaccion.setValor(1800);
        transaccion.setValorSugerido(1800);
        transaccion.setCanalId("PVMA");
        transaccion.setCodigoTerminal("862634041369599");
        transaccion.setTipoUsuario("VENDEDOR");
        transaccion.setSecuenciaTransaccion(1);
        transaccion.setIdSesion(idSesion);
        transaccion.setToken(autorizador);
        transaccion.setTransactionTime(trasaccionTime());
        transaccion.setOperacionTransaccion("VENTA");
        transaccion.setSeriePrefijo(serie1);
        transaccion.setSerieActual(serie2);
        return transaccion;
    }

    public PagaEncimePVMAINDto initValueEmpty() {

        PagaEncimePVMAINDto transaccion = new PagaEncimePVMAINDto();

        transaccion.setIdPromocion(134);

        ArrayList<ChanceNumero> listChance = new ArrayList<>();
        ChanceNumero chance = new ChanceNumero();

        Random random = new Random();
        int numeroAleatorio = random.nextInt(900) + 100; //genera un número aleatorio entre 100 y 999
        String numero = Integer.toString(numeroAleatorio);

        chance.setNumero("");
        chance.setPlenoDirecto(0);

        listChance.add(chance);

        transaccion.setChanceNumeros(listChance);
        transaccion.setIdRifa(0);
        transaccion.setSerie1("");
        transaccion.setSerie2("");

        ArrayList<Loteria> listLoteria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("");
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);
        listLoteria.add(loteria);


        transaccion.setLoterias(listLoteria);

        transaccion.setCodigoVendedor("");
        transaccion.setCodigoProducto("");
        transaccion.setValor(0);
        transaccion.setValorSugerido(0);
        transaccion.setCanalId("");
        transaccion.setCodigoTerminal("");
        transaccion.setTipoUsuario("");
        transaccion.setSecuenciaTransaccion(0);
        transaccion.setIdSesion("");
        transaccion.setToken("");
        transaccion.setTransactionTime(0L);
        transaccion.setOperacionTransaccion("");
        transaccion.setSeriePrefijo("");
        transaccion.setSerieActual("");
        return transaccion;
    }

    @Test
    public void pagaEncimePvmaSuccessful() {

        PagaEncimePVMAINDto transaccion = initValue();

        String json = new GsonBuilder().create().toJson(transaccion);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(json)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Exito"));

    }

    /*pagaEncimeEmptyAndCeroValue prueba como responde el servicio al enviar la data
     * con campos vacios y los tipo enteros en 0 */

    @Test
    public void pagaEncimePvmaEmptyAndCeroValue() {

        PagaEncimePVMAINDto transaccion = initValueEmpty();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                //.statusCode(401)
                .body("mensaje", Matchers.equalTo("Sesion no encontrada."));
    }

    /*pagaEncimeNumeroEmpty prueba como responde el servicio al enviar la data
     * con el atributo de numero de apuesta en blanco */

    @Test
    public void pagaEncimePvmaNumeroEmpty() {
        PagaEncimePVMAINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        chanceNumero.setNumero("");
        chanceNumero.setPlenoDirecto(1800);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Cantidad de cifras no permitidas"));

    }

    /*pagaEnvimeNumeroIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de numero de cuatro cifras teniendo en cuenta que la regla de negocio
     * permite solo de tres cifras */

    @Test
    public void pagaEncimeNumeroIncorrect() {
        PagaEncimePVMAINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        chanceNumero.setNumero("6985");
        chanceNumero.setPlenoDirecto(1800);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Cantidad de cifras no permitidas"));

    }

    /*pagaEncimePlenoDirectoEmpty prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto con valor en cero */
/*
    @Test
    public void pagaEncimePlenoDirectoCero() {
        PagaEncimePVMAINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        chanceNumero.setNumero("895");
        chanceNumero.setPlenoDirecto(1800);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Modalidad de juego invalida."));

    }

    /*pagaEncimePlenoDirectoEmpty prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto con valor incorrecto negativo */

    @Test
    public void pagaEncimePlenoDirectoIncorrect() {
        PagaEncimePVMAINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        chanceNumero.setNumero("895");
        chanceNumero.setPlenoDirecto(-5000);

        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Modalidad de juego invalida."));

    }

    /*pagaEncimeCodLoteriaEmpty prueba como responde el servicio al enviar la data
     * con el atributo de codLoteria con valor vacio */

    @Test
    public void pagaEncimeCodLoteriaEmpty() {
        PagaEncimePVMAINDto transaccion = initValue();

        ArrayList<Loteria> listCodLoteria = new ArrayList<>();
        Loteria loteria = new Loteria();
        loteria.setCodLoteria("");
        listCodLoteria.add(loteria);
        transaccion.setLoterias(listCodLoteria);
        loteria.setCodLoteria("");

        listCodLoteria.add(loteria);
        transaccion.setLoterias(listCodLoteria);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Loteria no encontrada o no permitida para el producto seleccionado."));

    }

    /*pagaEncimeCodVendedorEmpty prueba como responde el servicio al enviar la data
     * con el atributo de codVendedor con valor vacio */

    @Test
    public void pagaEncimeCodVendedorEmpty() {
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setCodigoVendedor("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(501)
                .body("mensaje", Matchers.equalTo("Sesion no encontrada."));

    }

    /*pagaEncimeCodVendedorIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de codVendedor con valor incorrecto */

    @Test
    public void pagaEncimeCodVendedorIncorrect() {
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setCodigoVendedor("K-77*OI");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(501)
                .body("mensaje", Matchers.equalTo("Sesion no encontrada."));

    }

    /*pagaEncimeCodProductoEmpty prueba como responde el servicio al enviar la data
     * con el atributo de codigoProducto con valor vacio */

    @Test
    public void pagaEncimeCodProductoEmpty() {
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setCodigoProducto("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Producto %s no parametrizado."));
    }

    /*pagaEncimeCodProductoIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de codigoProducto con valor incorrecto */

    @Test
    public void pagaEncimeCodProductoIncorrect() {
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setCodigoProducto("99_0");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Producto %s no parametrizado."));
    }

    /*pagaEncimeDiferentValue prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto con un valor diferente al atributo valor que hace referencia al valor total a pagar */

    @Test
    public void pagaEncimeDiferentValueBet() {
        PagaEncimePVMAINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();


        chanceNumero.setPlenoDirecto(1800);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);
        transaccion.setValor(10200);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Valor sugerido incorrecto."));
    }


    /*pagaEncimeMaxValue prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto y valor con un valor superior al permitido  */

    @Test
    public void pagaEncimeMaxValueBet() {
        PagaEncimePVMAINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();


        chanceNumero.setPlenoDirecto(11000);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);
        transaccion.setValor(11000);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Valor sugerido incorrecto."));

    }

    /*pagaEncimeMinValue prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto y valor con un valor minimo al permitido  */

    @Test
    public void pagaEncimeMimValueBet() {
        PagaEncimePVMAINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();


        chanceNumero.setPlenoDirecto(200);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);
        transaccion.setValor(200);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Cantidad de cifras no permitidas"));

    }

    /*pagaEncimeCanalIdEmpty prueba como responde el servicio al enviar la data
     * con el atributo de canalId con valor vacio */
/*
    @Test
    public void pagaEncimeCanalIdEmpty(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setCanalId("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(401)
                .body("mensaje", Matchers.equalTo("Solicitud no autorizada."));
    }

    /*pagaEncimeCanalIdIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de canalId con valor Incorrecto */
/*
    @Test
    public void pagaEncimeCanalIdIncorrect(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setCanalId("TIGO");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(401)
                .body("mensaje", Matchers.equalTo("Solicitud no autorizada."));
    }

    /*pagaEncimeCodTerminalEmpty prueba como responde el servicio al enviar la data
     * con el atributo de codigoTerminal con valor vacio */

    @Test
    public void pagaEncimeCodTerminalEmpty(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setCodigoTerminal("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("No se pudo completar la transaccion."));
    }

    /*pagaEncimeCodTerminalIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de codigoTerminal con valor incorrecto */

    @Test
    public void pagaEncimeCodTerminalIncorrect(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setCodigoTerminal("XXX-*908843");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeTipoUsuarioEmpty prueba como responde el servicio al enviar la data
     * con el atributo de tipoUsuario con valor vacio */

    @Test
    public void pagaEncimeTipoUsuarioEmpty(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setTipoUsuario("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeTipoUsuarioIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de tipoUsuario con valor incorrecto */
/*
    @Test
    public void pagaEncimeTipoUsuarioIncorrect(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setTipoUsuario("COMPRADOR");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Terminal no existe."));
    }

    /*pagaEncimeSecuenciaTransaccionIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de secuenciaTransaccion con valor incorrecto */
/*
    @Test
    public void pagaEncimeSecuenciaTransaccionIncorrect(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setSecuenciaTransaccion(9685854);
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo(false));
    }

    /*pagaEncimeIdSesionEmpty prueba como responde el servicio al enviar la data
     * con el atributo de idSesion con valor vacio */
/*
    @Test
    public void pagaEncimeIdSesionEmpty(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setIdSesion("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeIdSesionIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de idSesion con valor vacio */
/*
    @Test
    public void pagaEncimeIdSesionIncorrect(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setIdSesion("SELECT * FROM VENDEDORES");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeTokenEmpty prueba como responde el servicio al enviar la data
     * con el atributo de token con valor vacio */
/*
    @Test
    public void pagaEncimeTokenEmpty(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setToken("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
        //System.out.println(autorizador);
        //System.out.println(idSesion);
    }

    /*pagaEncimeTokenIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de token con valor incorrecto */
/*
    @Test
    public void pagaEncimeTokenIncorrect(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setToken("SELECT * FROM VENDEDORES");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeTransaccionTimeCero prueba como responde el servicio al enviar la data
     * con el atributo de transactionTime con valor null */
/*
    @Test
    public void pagaEncimeTransaccionTimeCero(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setTransactionTime(null);
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeOperacionTransaccionEmpty prueba como responde el servicio al enviar la data
     * con el atributo de operacionTransaccion con valor vacio */
/*
    @Test
    public void pagaEncimeOperacionTransaccionEmpty(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setOperacionTransaccion("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeOperacionTransaccionIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de operacionTransaccion con valor incorrecto */
/*
    @Test
    public void pagaEncimeOperacionTransaccionIncorrect(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setOperacionTransaccion("MAESTRO_CHANCE *.");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeSeriePrefijoEmpty prueba como responde el servicio al enviar la data
     * con el atributo de seriePrefijo con valor vacio */
/*
    @Test
    public void pagaEncimeSeriePrefijoEmpty(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setSeriePrefijo("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeSeriePrefijoIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de seriePrefijo con valor incorrecto */
/*
    @Test
    public void pagaEncimeSeriePrefijoIncorrect(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setSeriePrefijo("XX895");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeSerieActualEmpty prueba como responde el servicio al enviar la data
     * con el atributo de serieActual con valor vacio */
/*
    @Test
    public void pagaEncimeSerieActualEmpty(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setSerieActual("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeSerieActualIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de serieActual con valor incorrecto */
/*


    @Test
    public void pagaEncimeSerieActualIncorrect(){
        PagaEncimePVMAINDto transaccion = initValue();

        transaccion.setSerieActual("*******");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }*/

}
