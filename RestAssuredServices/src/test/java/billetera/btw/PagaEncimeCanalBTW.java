package billetera.btw;
import com.google.gson.GsonBuilder;
import dto.pagaencimeBTW.ChanceNumero;
import dto.pagaencimeBTW.Loteria;
import dto.pagaencimeBTW.PagaEncimeINDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.*;


public class PagaEncimeCanalBTW {


    public PagaEncimeCanalBTW(){
        RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/juegosventaazar/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    public static String idTransaccionSolicitud(){

        LocalDateTime fechaHoraActual = LocalDateTime.now(); // crea un objeto de tipo LocalDateTime con la hora y fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddHms"); // crea un objeto DateTimeFormatter con el formato deseado
        String consecutivo = fechaHoraActual.format(formatter);
        System.out.println(consecutivo);
        return  consecutivo;

    }

    public Long trasaccionTime(){
        long fechaHoraActual = System.currentTimeMillis();
        return fechaHoraActual;
    }


    public PagaEncimeINDto initValue(){
        PagaEncimeINDto transaccion = new PagaEncimeINDto();

        transaccion.setIdPromocion(134);

        ArrayList<ChanceNumero> listChanceNumeros = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        Random random = new Random();
        int numeroAleatorio = random.nextInt(900) + 100; //genera un número aleatorio entre 100 y 999
        String numero = Integer.toString(numeroAleatorio);
        chanceNumero.setNumero(numero);
        chanceNumero.setSuperPlenoCombinado(0);
        chanceNumero.setSuperPlenoDirecto(0);
        chanceNumero.setPlenoCombinado(0);
        chanceNumero.setPlenoDirecto(6000);
        chanceNumero.setPata(0);
        chanceNumero.setUna(0);

        listChanceNumeros.add(chanceNumero);

        transaccion.setChanceNumeros(listChanceNumeros);

        transaccion.setIdRifa(0);

        ArrayList<Loteria> listLoterias = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("31");

        listLoterias.add(loteria);

        transaccion.setLoterias(listLoterias);

        transaccion.setLogin("740001");
        transaccion.setDireccionMac("XX-12-34-56-78-90");
        transaccion.setVolEquipo("XXXX-1234");


        transaccion.setIdTransaccionSolicitud(idTransaccionSolicitud());
        transaccion.setCodigoMuncipio("11001");
        transaccion.setCodigoVendedor("740001");
        transaccion.setCodigoMedioPago("01");
        transaccion.setCodigoProducto("01");
        transaccion.setLoginCliente("sebastian.gomez@dcsas.com.co");
        transaccion.setValor(6000);
        transaccion.setCanalId("billetera");
        transaccion.setCodigoTerminal("ChIJFyY8VYSeP44R5-dw");
        transaccion.setTipoUsuario("VENDEDOR");
        transaccion.setLatitud("4.646804");
        transaccion.setLongitud("-74.071924");
        transaccion.setChecksum("07414CA4648A4F55242D836F4E2BCF4B5E0C7046");
        transaccion.setTransactionTime(trasaccionTime());

        return transaccion;

    }

    public PagaEncimeINDto initValueEmpty(){
        PagaEncimeINDto transaccion = new PagaEncimeINDto();
        transaccion.setIdPromocion(0);

        ArrayList<ChanceNumero> listChanceNumeros = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        chanceNumero.setNumero("");
        chanceNumero.setSuperPlenoCombinado(0);
        chanceNumero.setSuperPlenoDirecto(0);
        chanceNumero.setPlenoCombinado(0);
        chanceNumero.setPlenoDirecto(0);
        chanceNumero.setPata(0);
        chanceNumero.setUna(0);

        listChanceNumeros.add(chanceNumero);

        transaccion.setChanceNumeros(listChanceNumeros);

        transaccion.setIdRifa(0);

        ArrayList<Loteria> listLoterias = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("");

        listLoterias.add(loteria);

        transaccion.setLoterias(listLoterias);

        transaccion.setLogin("");
        transaccion.setDireccionMac("");
        transaccion.setVolEquipo("");


        transaccion.setIdTransaccionSolicitud("");
        transaccion.setCodigoMuncipio("");
        transaccion.setCodigoVendedor("");
        transaccion.setCodigoMedioPago("");
        transaccion.setCodigoProducto("");
        transaccion.setLoginCliente("");
        transaccion.setValor(0);
        transaccion.setCanalId("");
        transaccion.setCodigoTerminal("");
        transaccion.setTipoUsuario("");
        transaccion.setLatitud("");
        transaccion.setLongitud("");
        transaccion.setChecksum("");
        transaccion.setTransactionTime(0);

        return transaccion;
    }

    /*pagaEncimeSuccessful prueba que el servicio responda correctamente al
    * enviar la data correctamente */

    @Test
    public void pagaEncimeSuccessful(){

        PagaEncimeINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje" , Matchers.equalTo("Exito"));
    }

    /*pagaEncimeEmptyAndCeroValue prueba como responde el servicio al enviar la data
    * con campos vacios y los tipo enteros en 0 */

    @Test
    public void pagaEncimeEmptyAndCeroValue(){

        PagaEncimeINDto transaccion = initValueEmpty();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(401)
                .body("mensaje", Matchers.equalTo("Solicitud no autorizada."));
    }

    /*pagaEncimeNumeroEmpty prueba como responde el servicio al enviar la data
     * con el atributo de numero de apuesta en blanco */

    @Test
    public void pagaEncimeNumeroEmpty(){
        PagaEncimeINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        chanceNumero.setNumero("");
        chanceNumero.setPlenoDirecto(6000);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
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
    public void pagaEncimeNumeroIncorrect(){
        PagaEncimeINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        chanceNumero.setNumero("3254");
        chanceNumero.setPlenoDirecto(6000);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Cantidad de cifras no permitidas"));

    }

    /*pagaEncimePlenoDirectoEmpty prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto con valor en cero */

    @Test
    public void pagaEncimePlenoDirectoCero(){
        PagaEncimeINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        chanceNumero.setNumero("123");
        chanceNumero.setPlenoDirecto(0);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Modalidad de juego invalida."));

    }

    /*pagaEncimePlenoDirectoEmpty prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto con valor incorrecto negativo */

    @Test
    public void pagaEncimePlenoDirectoIncorrect(){
        PagaEncimeINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();

        chanceNumero.setNumero("123");
        chanceNumero.setPlenoDirecto(-6000);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Modalidad de juego invalida."));

    }

    /*pagaEncimeCodLoteriaEmpty prueba como responde el servicio al enviar la data
     * con el atributo de codLoteria con valor vacio */

    @Test
    public void pagaEncimeCodLoteriaEmpty(){
        PagaEncimeINDto transaccion = initValue();

        ArrayList<Loteria> listCodLoteria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("");



        listCodLoteria.add(loteria);
        transaccion.setLoterias(listCodLoteria);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Loteria no encontrada o no permitida para el producto seleccionado."));

    }

    /*pagaEncimeIdTransaccopmSolicitudEmpty prueba como responde el servicio al enviar la data
     * con el atributo de IdTransaccopmSolicitudcon valor vacio */

    @Test
    public void pagaEncimeIdTransaccioSolicitudEmpty(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setIdTransaccionSolicitud("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo(false));

    }

    /*pagaEncimeCodVendedorEmpty prueba como responde el servicio al enviar la data
     * con el atributo de codVendedor con valor vacio */

    @Test
    public void pagaEncimeCodVendedorEmpty(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCodigoVendedor("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("El vendedor no existe"));

    }

    /*pagaEncimeCodVendedorIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de codVendedor con valor incorrecto */

    @Test
    public void pagaEncimeCodVendedorIncorrect(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCodigoVendedor("K-77*OI");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("El vendedor no existe"));

    }

    /*pagaEncimeCodMedioPagoEmpty prueba como responde el servicio al enviar la data
     * con el atributo de codMedioPago con valor vacio */

    @Test
    public void pagaEncimeCodMedioPagoEmpty(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCodigoMedioPago("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeCodMedioPagoIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de codMedioPago con valor incorrecto */

    @Test
    public void pagaEncimeCodMedioPagoIncorrect(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCodigoMedioPago("*014");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeCodProductoEmpty prueba como responde el servicio al enviar la data
     * con el atributo de codigoProducto con valor vacio */

    @Test
    public void pagaEncimeCodProductoEmpty(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCodigoProducto("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Producto %s no parametrizado."));
    }

    /*pagaEncimeCodProductoIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de codigoProducto con valor incorrecto */

    @Test
    public void pagaEncimeCodProductoIncorrect(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCodigoProducto("99_0");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Producto %s no parametrizado."));
    }


    /*pagaEncimeDiferentValue prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto con un valor diferente al atributo valor que hace referencia al valor total a pagar */

    @Test
    public void pagaEncimeDiferentValueBet(){
        PagaEncimeINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();


        chanceNumero.setPlenoDirecto(6000);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);
        transaccion.setValor(10200);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Valor total a pagar no corresponde con los valores de las apuestas."));

    }

    /*pagaEncimeMaxValue prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto y valor con un valor superior al permitido  */

    @Test
    public void pagaEncimeMaxValueBet(){
        PagaEncimeINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();


        chanceNumero.setPlenoDirecto(11000);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);
        transaccion.setValor(11000);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Cantidad de cifras no permitidas"));

    }

    /*pagaEncimeMinValue prueba como responde el servicio al enviar la data
     * con el atributo de plenoDirecto y valor con un valor minimo al permitido  */

    @Test
    public void pagaEncimeMimValueBet(){
        PagaEncimeINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chanceNumero = new ChanceNumero();


        chanceNumero.setPlenoDirecto(200);


        listChance.add(chanceNumero);
        transaccion.setChanceNumeros(listChance);
        transaccion.setValor(200);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Total tiquete no permitido Minimo $ 700"));

    }

    /*pagaEncimeCanalIdEmpty prueba como responde el servicio al enviar la data
     * con el atributo de canalId con valor vacio */

    @Test
    public void pagaEncimeCanalIdEmpty(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCanalId("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(401)
                .body("mensaje", Matchers.equalTo("Solicitud no autorizada."));
    }

    /*pagaEncimeCanalIdIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de canalId con valor Incorrecto */

    @Test
    public void pagaEncimeCanalIdIncorrect(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCanalId("TIGO");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
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
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCodigoTerminal("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
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
        PagaEncimeINDto transaccion = initValue();

        transaccion.setCodigoTerminal("XXX-*908843");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
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
        PagaEncimeINDto transaccion = initValue();

        transaccion.setTipoUsuario("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaEncimeTipoUsuarioIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de tipoUsuario con valor incorrecto */

    @Test
    public void pagaEncimeTipoUsuarioIncorrect(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setTipoUsuario("COMPRADOR");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Terminal no existe."));
    }
    /*pagaEncimeLatitudEmpty prueba como responde el servicio al enviar la data
     * con el atributo de latitud con valor vacio */

    @Test
    public void pagaEncimeLatitudEmpty(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setLatitud("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo(false));
    }

    /*pagaEncimeLatitudIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de latitud con valor incorrecto */

    @Test
    public void pagaEncimeLatitudIncorrect(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setLatitud("1.25509");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo(false));
    }

    /*pagaEncimeLongitudEmpty prueba como responde el servicio al enviar la data
     * con el atributo de longitud con valor vacio */

    @Test
    public void pagaEncimeLongitudEmpty(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setLongitud("");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo(false));
    }


    /*pagaEncimeLongitudIncorrect prueba como responde el servicio al enviar la data
     * con el atributo de longitud con valor incorrecto */

    @Test
    public void pagaEncimeLongitudIncorrect(){
        PagaEncimeINDto transaccion = initValue();

        transaccion.setLongitud("-70.23511518");
        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI + "realizarVenta")
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo(false));
    }



}
