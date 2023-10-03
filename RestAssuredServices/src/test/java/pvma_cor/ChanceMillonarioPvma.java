package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.chancemillonarioPVMA.ChanceMillonarioPvmaINDto;
import dto.chancemillonarioPVMA.ChanceNumero;
import dto.chancemillonarioPVMA.Loteria;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import process.LoginService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ChanceMillonarioPvma {

    String autorizador;
    String idSesion;
    String serie1;
    String serie2;

    public ChanceMillonarioPvma() {
        RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/chancemillonario/realizarVenta";
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

    public String transaccionTime(){
        long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
        String currentTimeString = String.valueOf(currentTimeInMillis);
        String tenCharacterString = currentTimeString.substring(0, 10);
        return tenCharacterString;
    }

    public ChanceMillonarioPvmaINDto initValue(){

        ChanceMillonarioPvmaINDto transaccion = new ChanceMillonarioPvmaINDto();

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;

        int numeroAleatorio2 = random.nextInt(8000) + 1000;
        int numeroAleatorio3 = random.nextInt(7000) + 1000;
        int numeroAleatorio4 = random.nextInt(6000) + 1000;
        int numeroAleatorio5 = random.nextInt(5000) + 1000;

        ArrayList<ChanceNumero> listChanceNumero = new ArrayList<>();

        ChanceNumero chance1 = new ChanceNumero();
        chance1.setNumero(String.valueOf(numeroAleatorio1));
        System.out.println(chance1);
        ChanceNumero chance2 = new ChanceNumero();
        chance2.setNumero(String.valueOf(numeroAleatorio2));
        System.out.println(chance2);
        ChanceNumero chance3 = new ChanceNumero();
        chance3.setNumero(String.valueOf(numeroAleatorio3));
        System.out.println(chance3);
        ChanceNumero chance4 = new ChanceNumero();
        chance4.setNumero(String.valueOf(numeroAleatorio4));
        System.out.println(chance4);
        ChanceNumero chance5 = new ChanceNumero();
        chance5.setNumero(String.valueOf(numeroAleatorio5));
        System.out.println(chance5);

        listChanceNumero.add(chance1);
        listChanceNumero.add(chance2);
        listChanceNumero.add(chance3);
        listChanceNumero.add(chance4);
        listChanceNumero.add(chance5);

        transaccion.setChanceNumeros(listChanceNumero);

        ArrayList<Loteria> listLoteria = new ArrayList<>();

        Loteria loteria1 = new Loteria();

        loteria1.setCodLoteria("52");
        loteria1.setCifrasNumero(0);
        loteria1.setCifrasSerie(0);

        Loteria loteria2 = new Loteria();

        loteria2.setCodLoteria("29");
        loteria2.setCifrasNumero(0);
        loteria2.setCifrasSerie(0);

        listLoteria.add(loteria1);
        listLoteria.add(loteria2);

        transaccion.setLoterias(listLoteria);

        transaccion.setTipoUsuario("VENDEDOR");
        transaccion.setValor(5000);
        transaccion.setCodigoTerminal("862634041369599");
        transaccion.setCanalId("PVMA");
        transaccion.setCodigoVendedor("222230");
        transaccion.setCodigoProducto("809");
        transaccion.setCodModalidad("1");
        transaccion.setSerie1(serie1);
        transaccion.setSerie2(serie2);
        transaccion.setTransactionTime(transaccionTime());
        transaccion.setSecuenciaTransaccion(1);
        return transaccion;
    }

    /*chanceMillonarioPvmaSuccessful prueba la respuesta del servicio al enviar la peticion
     * correctamente que la respuesta sea exitosa*/

    @Test
    public void chanceMillonarioPvmaSuccessful(){

        ChanceMillonarioPvmaINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));
    }

    /*chanceMillonarioMaxValue prueba la respuesta del servicio al enviar la peticion con el atributo
     * valor con un saldo maximo*/

    @Test
    public void chanceMillonarioMaxValue(){

        ChanceMillonarioPvmaINDto transaccion = initValue();

        transaccion.setValor(10000);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }
    /*chanceMillonarioMinValue prueba la respuesta del servicio al enviar la peticion con el atributo
     * valor con un saldo minimo*/

    @Test
    public void chanceMillonarioMinValue(){

        ChanceMillonarioPvmaINDto transaccion = initValue();

        transaccion.setValor(4999);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }

    /*chanceMillonarioMinValue prueba la respuesta del servicio al enviar la peticion con solo dos numeros
    * apostados*/

    @Test
    public void chanceMillonarioTwoNumbers(){

        ChanceMillonarioPvmaINDto transaccion = initValue();

        ArrayList<ChanceNumero> listChanceNumeros = new ArrayList<>();

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;

        int numeroAleatorio2 = random.nextInt(8000) + 1000;

        ChanceNumero numero1 = new ChanceNumero();
        numero1.setNumero(Integer.toString(numeroAleatorio1));

        ChanceNumero numero2 = new ChanceNumero();
        numero2.setNumero(Integer.toString(numeroAleatorio2));

        listChanceNumeros.add(numero1);
        listChanceNumeros.add(numero2);

        transaccion.setChanceNumeros(listChanceNumeros);


        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));
    }


}
