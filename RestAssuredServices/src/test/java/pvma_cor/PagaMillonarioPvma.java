package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.pagamillonarioPVMA.ChanceNumero;
import dto.pagamillonarioPVMA.Loteria;
import dto.pagamillonarioPVMA.PagaMillonarioPvmaINDto;
import dto.pagamillonarioPVMA.PagaMillonarioPvmaOUTDto;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PagaMillonarioPvma {
    String autorizador;
    String idSesion;
    String serie1;
    String serie2;

    public PagaMillonarioPvma() {
        RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/pagomillonario/realizarVenta";
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
    public PagaMillonarioPvmaINDto initValue(){

        PagaMillonarioPvmaINDto transaccion = new PagaMillonarioPvmaINDto();


        transaccion.setSerie1(serie1);
        transaccion.setSerie2(serie2);
        transaccion.setTipoUsuario("VENDEDOR");
        transaccion.setCodigoVendedor("222230");
        transaccion.setCodigoProducto("804");
        transaccion.setValor(3000);
        transaccion.setCanalId("PVMA");
        transaccion.setCodigoTerminal("862634041369599");
        transaccion.setTransactionTime(transaccionTime());
        transaccion.setSecuenciaTransaccion(1);

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;

        int numeroAleatorio2 = random.nextInt(8000) + 1000;
        int numeroAleatorio3 = random.nextInt(7000) + 1000;
        int numeroAleatorio4 = random.nextInt(6000) + 1000;
        int numeroAleatorio5 = random.nextInt(5000) + 1000;

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

        listChance.add(chance1);
        listChance.add(chance2);
        listChance.add(chance3);
        listChance.add(chance4);
        listChance.add(chance5);
        transaccion.setChanceNumeros(listChance);


        ArrayList<Loteria> listLoteria = new ArrayList<>();

        Loteria loteria1 = new Loteria();
        loteria1.setCodLoteria("29");
        loteria1.setCifrasNumero(0);
        loteria1.setCifrasSerie(0);

        Loteria loteria2 = new Loteria();
        loteria2.setCodLoteria("31");
        loteria2.setCifrasNumero(0);
        loteria2.setCifrasSerie(0);

        listLoteria.add(loteria1);
        listLoteria.add(loteria2);

        transaccion.setLoterias(listLoteria);

        return transaccion;


    }

    /*pagaMillonarioPvmaSuccessful prueba la respuesta del servicio al enviar la peticion
    * correctamente que la respuesta sea exitosa*/

    @Test
    public void pagaMillonarioPvmaSuccessful(){

        PagaMillonarioPvmaINDto transaccion = initValue();

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

    /*pagaMillonarioPvmaThreeFigureSuccessful prueba la respuesta del servicio al enviar la peticion
     * correctamente con numeros de 3 cifras que la respuesta sea exitosa*/
/*
    @Test
    public void pagaMillonarioPvmaThreeFigureSuccessful(){

        PagaMillonarioPvmaINDto transaccion = initValue();
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(900) + 100;

        int numeroAleatorio2 = random.nextInt(800) + 100;
        int numeroAleatorio3 = random.nextInt(700) + 100;
        int numeroAleatorio4 = random.nextInt(600) + 100;
        int numeroAleatorio5 = random.nextInt(500) + 100;

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

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

        listChance.add(chance1);
        listChance.add(chance2);
        listChance.add(chance3);
        listChance.add(chance4);
        listChance.add(chance5);
        transaccion.setChanceNumeros(listChance);

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

    /*pagaMillonarioMaxValue prueba la respuesta del servicio al enviar la peticion con el atributo
    * valor con un saldo maximo */

    @Test
    public void pagaMillonarioMaxValue(){

        PagaMillonarioPvmaINDto transaccion = initValue();

        transaccion.setValor(40000);


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

    /*pagaMillonarioMaxValue prueba la respuesta del servicio al enviar la peticion con el atributo
     * valor con un saldo minimo */

    @Test
    public void pagaMillonarioMinValue(){

        PagaMillonarioPvmaINDto transaccion = initValue();

        transaccion.setValor(2000);


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

    /*pagaMillonarioPvmaThreeNumber prueba la respuesta del servicio al enviar la peticion
     * con 3 numeros apostados */

    @Test
    public void pagaMillonarioPvmaThreeNumber(){

        PagaMillonarioPvmaINDto transaccion = initValue();
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(900) + 100;

        int numeroAleatorio2 = random.nextInt(800) + 100;
        int numeroAleatorio3 = random.nextInt(700) + 100;
        int numeroAleatorio4 = random.nextInt(600) + 100;
        int numeroAleatorio5 = random.nextInt(500) + 100;

        ArrayList<ChanceNumero> listChance = new ArrayList<>();

        ChanceNumero chance1 = new ChanceNumero();
        chance1.setNumero(String.valueOf(numeroAleatorio1));
        System.out.println(chance1);
        ChanceNumero chance2 = new ChanceNumero();
        chance2.setNumero(String.valueOf(numeroAleatorio2));
        System.out.println(chance2);
        ChanceNumero chance3 = new ChanceNumero();
        chance3.setNumero(String.valueOf(numeroAleatorio3));
        System.out.println(chance3);


        listChance.add(chance1);
        listChance.add(chance2);
        listChance.add(chance3);

        transaccion.setChanceNumeros(listChance);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("Numero de apuestas incorrecto. Debe jugar 5 apuestas."));
    }

    /*
     * El serie2ActualResponseTest prueba que el atributo "serie2" que llega en la respuesta
     * no sea mayor o igual al atributo "serie2Actual".
     *
     * */

    @Test
    public void serie2ActualResponseTest(){

        long startTime = System.currentTimeMillis();

        PagaMillonarioPvmaINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        Response response =given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI);

        PagaMillonarioPvmaOUTDto respuestaBody = response.getBody().as(PagaMillonarioPvmaOUTDto.class);

        if(Integer.parseInt(respuestaBody.getSerie2Actual()) <= Integer.parseInt(respuestaBody.getSerie2())){
            Assert.assertTrue(false);
        }else{
            Assert.assertTrue(true);
        }

    }


}
