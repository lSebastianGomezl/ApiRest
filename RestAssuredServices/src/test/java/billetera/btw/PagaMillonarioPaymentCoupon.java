package billetera.btw;
import com.google.gson.GsonBuilder;
import dto.pagamillonariopaymentcupon.ChanceNumero;
import dto.pagamillonariopaymentcupon.Loteria;
import dto.pagamillonariopaymentcupon.PagaMillonarioCouponINDto;
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

public class PagaMillonarioPaymentCoupon {

    public PagaMillonarioPaymentCoupon() {
        RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/pagomillonario/realizarVenta";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }


    public static int numeroAleatorioOne(int min, int max){
        Random random = new Random();

        return random.nextInt(max - min + 1) + min;
    }

    public static String numeroAleatorioTwo(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(4000) + 3000;
        return Integer.toString(numeroAleatorio);
    }
    public static String numeroAleatorioThree(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(1100) + 5000;
        return Integer.toString(numeroAleatorio);
    }

    public static String numeroAleatorioFour(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(1000) + 2000;
        return Integer.toString(numeroAleatorio);
    }
    public static String numeroAleatorioFive(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(2000) + 1000;
        return Integer.toString(numeroAleatorio);
    }



    public static String serie2(){

        LocalDateTime fechaHoraActual = LocalDateTime.now(); // crea un objeto de tipo LocalDateTime con la hora y fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMhsS"); // crea un objeto DateTimeFormatter con el formato deseado
        String consecutivo = fechaHoraActual.format(formatter);

        return  consecutivo;

    }

    public static String transaccionSolicitud(){
        LocalDateTime fechaHoraActual = LocalDateTime.now(); // crea un objeto de tipo LocalDateTime con la hora y fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMhsS"); // crea un objeto DateTimeFormatter con el formato deseado
        String transaccionId = fechaHoraActual.format(formatter);

        return transaccionId;

    }
    public static PagaMillonarioCouponINDto initValue(){

        PagaMillonarioCouponINDto transaccion = new PagaMillonarioCouponINDto();

        ArrayList<ChanceNumero> listChance = new ArrayList<>();
    int numeroAleatorio = numeroAleatorioOne(1000,9000);
        ChanceNumero chance1 = new ChanceNumero();
        chance1.setNumero(Integer.toString(numeroAleatorio));


        ChanceNumero chance2 = new ChanceNumero();
        chance2.setNumero(numeroAleatorioTwo());


        ChanceNumero chance3 = new ChanceNumero();
        chance3.setNumero(numeroAleatorioThree());


        ChanceNumero chance4 = new ChanceNumero();
        chance4.setNumero(numeroAleatorioFour());


        ChanceNumero chance5 = new ChanceNumero();
        chance5.setNumero(numeroAleatorioFive());


        listChance.add(chance1);
        listChance.add(chance2);
        listChance.add(chance3);
        listChance.add(chance4);
        listChance.add(chance5);

        transaccion.setChanceNumeros(listChance);

        transaccion.setSerie1("#1JF");
        transaccion.setSerie2(serie2());


        ArrayList<Loteria> listLoteria = new ArrayList<>();
        Loteria loteria1 = new Loteria();
        loteria1.setCodLoteria("52");

        Loteria loteria2 = new Loteria();
        loteria2.setCodLoteria("31");


        listLoteria.add(loteria1);
        listLoteria.add(loteria2);

        //Seteo de valores
        transaccion.setChanceNumeros(listChance);
        transaccion.setLoterias(listLoteria);

        transaccion.setLogin("740004");
        transaccion.setDireccionMac("XX-12-34-56-78-90");
        //Prioridad
        transaccion.setVolEquipo("XXXX-1234");
        /*------------------------------------*/
        transaccion.setIdTransaccionSolicitud(transaccionSolicitud());
        System.out.println("Este es el idTransaccionSolicitud: " + transaccionSolicitud());
        //Prioridad
        transaccion.setCodigoVendedor("740004");
        transaccion.setCodigoMedioPago("04");
        transaccion.setCodigoProducto("624");
        /*------------------------------------*/
        transaccion.setLoginCliente("paacostam@gmail.com");
        transaccion.setValor(3000);
        //Prioridad
        transaccion.setCanalId("billetera");
        /*------------------------------------*/
        transaccion.setCodigoTerminal("ChIJHQALqDd4P44RvzDo");
        transaccion.setTipoUsuario("VENDEDOR");
        transaccion.setLatitud("4.6931861");
        transaccion.setLongitud("-74.1416127");
        transaccion.setTransactionTime(Long.parseLong(String.valueOf(Calendar.getInstance().getTimeInMillis())));
        System.out.println("este es el transaccionTime: " + transaccion.transactionTime);
        transaccion.setSeriePrefijo("#1JF");
        transaccion.setSerieActual(serie2());
        System.out.println("Esta es la serie2 : " + serie2());

        return transaccion;

    }

    /*pagaMillonarioPaymentCouponSuccessful prueba la respuesta del servicio al enviar la peticion
    * correctamente que la respuesta sea exitosa*/

    @Test
    public void pagaMillonarioPaymentCouponSuccessful(){

        PagaMillonarioCouponINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));
    }

    /*pagaMillonarioCodVendedorEmpty prueba la respuesta del servicio al enviar la peticion
     * con el valor del atributo codVendedor vacio */

    @Test
    public void pagaMillonarioCodVendedorEmpty(){

        PagaMillonarioCouponINDto transaccion = initValue();

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
    /*pagaMillonarioCodVendedorIncorrect prueba la respuesta del servicio al enviar la peticion
     * con el valor del atributo codVendedor incorrecto */

    @Test
    public void pagaMillonarioCodVendedorIncorrect(){

        PagaMillonarioCouponINDto transaccion = initValue();

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

    /*pagaMillonarioCodMedioPagoEmpty prueba la respuesta del servicio al enviar la peticion
     * con el valor del atributo codMedioPago vacio */

    @Test
    public void pagaMillonarioCodMedioPagoEmpty(){

        PagaMillonarioCouponINDto transaccion = initValue();

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


    /*pagaMillonarioCodMedioPagoIncorrect prueba la respuesta del servicio al enviar la peticion
     * con el valor del atributo codMedioPago incorrecto */

    @Test
    public void pagaMillonarioCodMedioPagoIncorrect(){

        PagaMillonarioCouponINDto transaccion = initValue();

        transaccion.setCodigoMedioPago("090989938");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*pagaMillonarioCodProductoEmpty prueba la respuesta del servicio al enviar la peticion
     * con el valor del atributo codProducto vacio */

    @Test
    public void pagaMillonarioCodProductoEmpty(){

        PagaMillonarioCouponINDto transaccion = initValue();

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

    /*pagaMillonarioCodProductoIncorrect prueba la respuesta del servicio al enviar la peticion
     * con el valor del atributo codProducto incorrecto */

    @Test
    public void pagaMillonarioCodProductoIncorrect(){

        PagaMillonarioCouponINDto transaccion = initValue();

        transaccion.setCodigoProducto("896");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }
    /*pagaMillonarioCanalId prueba la respuesta del servicio al enviar la peticion
     * con el valor del atributo canalId Empty */

    @Test
    public void pagaMillonarioCanalIdEmpty(){

        PagaMillonarioCouponINDto transaccion = initValue();

        transaccion.setCanalId("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .body("exito", Matchers.equalTo(false));
    }
    /*pagaMillonarioCanalIdIncorrect prueba la respuesta del servicio al enviar la peticion
     * con el valor del atributo canalId incorrecto */

    @Test
    public void pagaMillonarioCanalIdIncorrect(){

        PagaMillonarioCouponINDto transaccion = initValue();

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
