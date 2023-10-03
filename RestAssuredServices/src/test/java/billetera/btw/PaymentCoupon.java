package billetera.btw;
import com.google.gson.GsonBuilder;

import dto.paymentcoupon.ChanceNumero;
import dto.paymentcoupon.Loteria;
import dto.paymentcoupon.PaymentCouponINDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PaymentCoupon {

    public PaymentCoupon() {
        RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/juegosventaazar/realizarVenta";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }

    public static String numeroAleatorio(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(9000) + 1000;
        return Integer.toString(numeroAleatorio);
    }

    public static String transaccionSolicitud(){

        long fechaHoraActual = System.currentTimeMillis();
        return Integer.toString((int) fechaHoraActual);

    }
    public static PaymentCouponINDto initValue(){

        PaymentCouponINDto transaccion = new PaymentCouponINDto();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chance = new ChanceNumero();
        chance.setNumero(numeroAleatorio());
        chance.setSuperPlenoDirecto(3000);
        listChance.add(chance);

        ;

        ArrayList<Loteria> listLoteria = new ArrayList<>();
        Loteria loteria = new Loteria();
        loteria.setCodLoteria("29");
        listLoteria.add(loteria);

        //Seteo de valores
        transaccion.setChanceNumeros(listChance);
        transaccion.setLoterias(listLoteria);

        transaccion.setCodigoMedioPago("04");
        transaccion.setCodigoVendedor("740004");
        transaccion.setCodigoProducto("01");
        transaccion.setCodigoMuncipio("11001");
        transaccion.setValor(3000);
        transaccion.setCanalId("billetera");
        transaccion.setCodigoTerminal("ChIJHQALqDd4P44RvzDo");
        transaccion.setDireccionMac("XX-12-34-56-78-90");
        transaccion.setIdPromocion(0);
        transaccion.setIdTransaccionSolicitud(transaccionSolicitud());
        transaccion.setLatitud("4.6931861");
        transaccion.setLoginCliente("740004");
        transaccion.setLoginCliente("paacostam@gmail.com");
        transaccion.setLongitud("-74.1416127");
        transaccion.setTipoUsuario("VENDEDOR");
        transaccion.setTransactionTime(Long.parseLong(String.valueOf(Calendar.getInstance().getTimeInMillis())));
        transaccion.setVolEquipo("XXXX-1234");
        return transaccion;

    }

    /*paymentCouponSuccessful prueba la respuesta del servicio al enviar la peticion
    * correctamente que la respuesta sea exitosa*/

    @Test
    public void paymentCouponSuccessful(){

        PaymentCouponINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));
    }

    /*paymentCouponCodMedioPagoEmpty prueba la respuesta del servicio al enviar la peticion
     * con el atributo codigoMedioPago Empty*/

    @Test
    public void paymentCouponCodMedioPagoEmpty(){

        PaymentCouponINDto transaccion = initValue();
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

    /*paymentCouponCodMedioPagoIncorrect prueba la respuesta del servicio al enviar la peticion
     * con el atributo codigoMedioPago incorrecto*/

    @Test
    public void paymentCouponCodMedioPagoIncorrect(){

        PaymentCouponINDto transaccion = initValue();
        transaccion.setCodigoMedioPago("07");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*paymentCouponCodMedioCharactersSpecial prueba la respuesta del servicio al enviar la peticion
     * con el atributo codigoMedioPago con caracteres especiales */

    @Test
    public void paymentCouponCodMedioCharactersSpecial(){

        PaymentCouponINDto transaccion = initValue();
        transaccion.setCodigoMedioPago("$%&#44*4");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }


    /*paymentCouponCodVendedorEmpty prueba la respuesta del servicio al enviar la peticion
     * con el atributo codigoVendedor Empty*/

    @Test
    public void paymentCouponCodVendedorEmpty(){

        PaymentCouponINDto transaccion = initValue();
        transaccion.setCodigoVendedor("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("El vendedor no existe"));
    }

    /*paymentCouponCodVendedorIncorrect prueba la respuesta del servicio al enviar la peticion
     * con el atributo codigoVendedor Empty*/

    @Test
    public void paymentCouponCodVendedorIncorrect(){

        PaymentCouponINDto transaccion = initValue();
        transaccion.setCodigoVendedor("740*''");


        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("El vendedor no existe"));
    }



}
