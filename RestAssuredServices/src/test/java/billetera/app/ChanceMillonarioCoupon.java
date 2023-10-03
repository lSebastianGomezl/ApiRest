package billetera.app;

import com.google.gson.GsonBuilder;
import dto.chancemillonarioPvmaCoupon.ChanceMillonarioCouponINDto;
import dto.chancemillonarioPvmaCoupon.ChanceNumero;
import dto.chancemillonarioPvmaCoupon.Loteria;
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
import java.util.Calendar;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ChanceMillonarioCoupon {

    public ChanceMillonarioCoupon() {
        RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/chancemillonario/realizarVenta";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }


    public static String serie2(){

        LocalDateTime fechaHoraActual = LocalDateTime.now(); // crea un objeto de tipo LocalDateTime con la hora y fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMhsS"); // crea un objeto DateTimeFormatter con el formato deseado
        String consecutivo = fechaHoraActual.format(formatter);

        return  consecutivo;

    }

    public static String transaccionSolicitud(){
        LocalDateTime fechaHoraActual = LocalDateTime.now(); // crea un objeto de tipo LocalDateTime con la hora y fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMhs"); // crea un objeto DateTimeFormatter con el formato deseado
        String transaccionId = fechaHoraActual.format(formatter);

        return transaccionId;

    }
    public static ChanceMillonarioCouponINDto initValue(){

        ChanceMillonarioCouponINDto transaccion = new ChanceMillonarioCouponINDto();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;

        int numeroAleatorio2 = random.nextInt(8000) + 1000;
        int numeroAleatorio3 = random.nextInt(7000) + 1000;
        int numeroAleatorio4 = random.nextInt(6000) + 1000;
        int numeroAleatorio5 = random.nextInt(5000) + 1000;
        ChanceNumero chance1 = new ChanceNumero();
        chance1.setNumero(Integer.toString(numeroAleatorio1));

        ChanceNumero chance2 = new ChanceNumero();
        chance2.setNumero(Integer.toString(numeroAleatorio2));

        ChanceNumero chance3 = new ChanceNumero();
        chance3.setNumero(Integer.toString(numeroAleatorio3));


        ChanceNumero chance4 = new ChanceNumero();
        chance4.setNumero(Integer.toString(numeroAleatorio4));


        ChanceNumero chance5 = new ChanceNumero();
        chance5.setNumero(Integer.toString(numeroAleatorio5));

        ArrayList<Loteria> listLoteria = new ArrayList<>();

        Loteria loteria1 = new Loteria();
        loteria1.setCodLoteria("31");

        Loteria loteria2 = new Loteria();
        loteria2.setCodLoteria("52");

        listLoteria.add(loteria1);
        listLoteria.add(loteria2);

        transaccion.setLoterias(listLoteria);


        listChance.add(chance1);
        listChance.add(chance2);
        listChance.add(chance3);
        listChance.add(chance4);
        listChance.add(chance5);
        transaccion.setChanceNumeros(listChance);
        transaccion.setCodModalidad("1");
        transaccion.setLogin("740004");
        transaccion.setDireccionMac("XX-12-34-56-78-90");
        transaccion.setVolEquipo("XXXX-1234");
        transaccion.setCodigoMuncipio("11001");
        transaccion.setCodigoMuncipioSivical("25269");
        transaccion.setCodigoOficina("74");
        transaccion.setCodigoPuntoVenta("001");
        transaccion.setTipoPuntoVenta("1");
        transaccion.setCodigoVendedor("740004");
        transaccion.setCodigoMedioPago("04");
        transaccion.setCodigoProducto("623");
        transaccion.setLoginCliente("paacostam@gmail.com");
        transaccion.setValor(5000);
        transaccion.setCanalId("billetera");
        transaccion.setCodigoTerminal("XXXX-1234");
        transaccion.setTipoUsuario("VENDEDOR");
        transaccion.setLatitud("4.6931861");
        transaccion.setLongitud("-74.1416127");
        transaccion.setTransactionTime(Long.parseLong(String.valueOf(Calendar.getInstance().getTimeInMillis())));
        transaccion.setIdTransaccionSolicitud(transaccionSolicitud());
        transaccion.setSerie1("#1GF");
        transaccion.setSerie2(serie2());
        transaccion.setSeriePrefijo("#1GF");
        transaccion.setSerieActual(serie2());

        return transaccion;


    }

    /*pagaMillonarioPaymentCouponSuccessful prueba la respuesta del servicio al enviar la peticion
    * correctamente que la respuesta sea exitosa*/

    @Test
    public void chanceMillonarioPaymentCouponSuccessful(){

        ChanceMillonarioCouponINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));
    }

    /*chanceMillonarioCodVendedorEmpty prueba la respuesta del servicio al enviar la peticion
     * con el atributo codigoVendedor vacio*/

    @Test
    public void chanceMillonarioCodVendedorEmpty(){

        ChanceMillonarioCouponINDto transaccion = initValue();

        transaccion.setCodigoVendedor("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*chanceMillonarioCodVendedorIncorrect prueba la respuesta del servicio al enviar la peticion
     * con el atributo codigoVendedor incorrecto*/

    @Test
    public void chanceMillonarioCodVendedorIncorrect(){

        ChanceMillonarioCouponINDto transaccion = initValue();

        transaccion.setCodigoVendedor("710001");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*chanceMillonarioCodMedioPagoEmpty prueba la respuesta del servicio al enviar la peticion
     * con el atributo codMedioPago vacio */

    @Test
    public void chanceMillonarioCodMedioPagoEmpty(){

        ChanceMillonarioCouponINDto transaccion = initValue();

        transaccion.setCodigoMedioPago("");


        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*chanceMillonarioCodMedioPagoIncorrect prueba la respuesta del servicio al enviar la peticion
     * con el atributo codMedioPago incorrecto */

    @Test
    public void chanceMillonarioCodMedioPagoIncorrect(){

        ChanceMillonarioCouponINDto transaccion = initValue();

        transaccion.setCodigoMedioPago("*090");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*chanceMillonarioCodProductoEmpty prueba la respuesta del servicio al enviar la peticion
     * con el atributo codProducto vacio */

    @Test
    public void chanceMillonarioCodProductoEmpty(){

        ChanceMillonarioCouponINDto transaccion = initValue();

        transaccion.setCodigoProducto("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*chanceMillonarioCodProductoIncorrect prueba la respuesta del servicio al enviar la peticion
     * con el atributo codProducto vacio */

    @Test
    public void chanceMillonarioCodProductoIncorrect(){

        ChanceMillonarioCouponINDto transaccion = initValue();

        transaccion.setCodigoProducto("*/983");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*chanceMillonarioCanalIdEmpty prueba la respuesta del servicio al enviar la peticion
     * con el atributo canalId vacio */

    @Test
    public void chanceMillonarioCanalIdEmpty(){

        ChanceMillonarioCouponINDto transaccion = initValue();

        transaccion.setCanalId("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*chanceMillonarioCanalIdEmpty prueba la respuesta del servicio al enviar la peticion
     * con el atributo canalId vacio */

    @Test
    public void chanceMillonarioCanalIdIncorrect(){

        ChanceMillonarioCouponINDto transaccion = initValue();

        transaccion.setCanalId("pvma_cor");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }





}
