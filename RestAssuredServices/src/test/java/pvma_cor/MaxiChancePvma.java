package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.maxichancePVMA.ChanceNumero;
import dto.maxichancePVMA.Loteria;
import dto.maxichancePVMA.MaxiChanceINDto;
import dto.maxichancePVMA.response.MaxiChanceOUTDto;
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


public class MaxiChancePvma {

    String autorizador;
    String idSesion;
    String serie1;
    String serie2;

    public MaxiChancePvma (){
        RestAssured.baseURI="http://sivemtxpru.dcsas.com.co:8180/ChaxGW/rest/juegosventaazar/chance/realizarVenta";
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

    public MaxiChanceINDto initValue(){

        MaxiChanceINDto transaccion = new MaxiChanceINDto();

        transaccion.setSerie1(serie1);
        transaccion.setSerie2(serie2);
        transaccion.setTipoUsuario("VENDEDOR");
        transaccion.setCodigoVendedor("222230");
        transaccion.setCodigoProducto("01");
        transaccion.setValor(9600);
        transaccion.setValorSugerido(9600);
        transaccion.setCanalId("PVMA");
        transaccion.setCodigoTerminal("862634041369599");
        transaccion.setIdPromocion(1);
        transaccion.setTransactionTime((Calendar.getInstance().getTimeInMillis()+""));
        transaccion.setSecuenciaTransaccion(2);

        ArrayList<ChanceNumero> listChanceNumero = new ArrayList<>();
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;
        int numeroAleatorio2 = random.nextInt(800) + 100;
        int numeroAleatorio3 = random.nextInt(7000) + 1000;
        int numeroAleatorio4 = random.nextInt(600) + 100;

        ChanceNumero chance1 = new ChanceNumero();

        chance1.setNumero(Integer.toString(numeroAleatorio1));
        chance1.setSuperPlenoDirecto(1200);

        ChanceNumero chance2 = new ChanceNumero();

        chance2.setNumero(Integer.toString(numeroAleatorio2));
        chance2.setPlenoDirecto(1200);

        ChanceNumero chance3 = new ChanceNumero();

        chance3.setNumero(Integer.toString(numeroAleatorio3));
        chance3.setSuperPlenoDirecto(1200);

        ChanceNumero chance4 = new ChanceNumero();

        chance4.setNumero(Integer.toString(numeroAleatorio4));
        chance4.setPlenoDirecto(1200);

        listChanceNumero.add(chance1);
        listChanceNumero.add(chance2);
        listChanceNumero.add(chance3);
        listChanceNumero.add(chance4);

        transaccion.setChanceNumeros(listChanceNumero);

        ArrayList<Loteria> listLoterias = new ArrayList<>();

        Loteria loteria1 = new Loteria();

        loteria1.setCodLoteria("31");
        loteria1.setCifrasSerie(0);
        loteria1.setCifrasNumero(0);

        Loteria loteria2 = new Loteria();

        loteria2.setCodLoteria("52");
        loteria2.setCifrasNumero(0);
        loteria2.setCifrasSerie(0);

        listLoterias.add(loteria1);
        listLoterias.add(loteria2);

        transaccion.setLoterias(listLoterias);

        return transaccion;

    }

    /*maxiChancePvmaSuccessful prueba la respuesta del servicio al enviar la peticion
    * con la data correcta */

    @Test
    public void maxiChancePvmaSuccessful(){

        MaxiChanceINDto maxiChance = initValue();

        String jsonBody = new GsonBuilder().create().toJson(maxiChance);

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

    /*maxiChancePvmaFourFiguresSuccessful prueba la respuesta del servicio al enviar la peticion
     * con los numeros de cuatro cifras */

    @Test
    public void maxiChancePvmaFourFiguresSuccessful(){

        MaxiChanceINDto maxiChance = initValue();

        ArrayList<ChanceNumero> listChanceNumero = new ArrayList<>();
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;
        int numeroAleatorio2 = random.nextInt(8000) + 1000;
        int numeroAleatorio3 = random.nextInt(7000) + 1000;
        int numeroAleatorio4 = random.nextInt(6000) + 1000;

        ChanceNumero chance1 = new ChanceNumero();

        chance1.setNumero(Integer.toString(numeroAleatorio1));
        chance1.setSuperPlenoDirecto(1200);

        ChanceNumero chance2 = new ChanceNumero();

        chance2.setNumero(Integer.toString(numeroAleatorio2));
        chance2.setSuperPlenoDirecto(1200);

        ChanceNumero chance3 = new ChanceNumero();

        chance3.setNumero(Integer.toString(numeroAleatorio3));
        chance3.setSuperPlenoDirecto(1200);

        ChanceNumero chance4 = new ChanceNumero();

        chance4.setNumero(Integer.toString(numeroAleatorio4));
        chance4.setSuperPlenoDirecto(1200);

        listChanceNumero.add(chance1);
        listChanceNumero.add(chance2);
        listChanceNumero.add(chance3);
        listChanceNumero.add(chance4);

        maxiChance.setChanceNumeros(listChanceNumero);

        String jsonBody = new GsonBuilder().create().toJson(maxiChance);

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

    /*maxiChancePvmaThreeFiguresSuccessful prueba la respuesta del servicio al enviar la peticion
     * con los numeros de tres cifras */

    @Test
    public void maxiChancePvmaThreeFiguresSuccessful(){

        MaxiChanceINDto maxiChance = initValue();

        ArrayList<ChanceNumero> listChanceNumero = new ArrayList<>();
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(900) + 100;
        int numeroAleatorio2 = random.nextInt(800) + 100;
        int numeroAleatorio3 = random.nextInt(700) + 100;
        int numeroAleatorio4 = random.nextInt(600) + 100;

        ChanceNumero chance1 = new ChanceNumero();

        chance1.setNumero(Integer.toString(numeroAleatorio1));
        chance1.setPlenoDirecto(1200);

        ChanceNumero chance2 = new ChanceNumero();

        chance2.setNumero(Integer.toString(numeroAleatorio2));
        chance2.setPlenoDirecto(1200);

        ChanceNumero chance3 = new ChanceNumero();

        chance3.setNumero(Integer.toString(numeroAleatorio3));
        chance3.setPlenoDirecto(1200);

        ChanceNumero chance4 = new ChanceNumero();

        chance4.setNumero(Integer.toString(numeroAleatorio4));
        chance4.setPlenoDirecto(1200);

        listChanceNumero.add(chance1);
        listChanceNumero.add(chance2);
        listChanceNumero.add(chance3);
        listChanceNumero.add(chance4);

        maxiChance.setChanceNumeros(listChanceNumero);

        String jsonBody = new GsonBuilder().create().toJson(maxiChance);

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

    /*maxiChancePvmaMaxValue prueba la respuesta del servicio al enviar la peticion
     * con el atributo "valor" con un valor que sobre pasa el valor maximo de la apuesta  */

    @Test
    public void maxiChancePvmaMaxValue(){

        MaxiChanceINDto maxiChance = initValue();

        maxiChance.setValor(50000);

        String jsonBody = new GsonBuilder().create().toJson(maxiChance);

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

    /*maxiChancePvmaMinValue prueba la respuesta del servicio al enviar la peticion
     * con el atributo "valor" con un valor que no alcanza al valor minimo de la apuesta  */

    @Test
    public void maxiChancePvmaMinValue(){

        MaxiChanceINDto maxiChance = initValue();

        maxiChance.setValor(0);

        String jsonBody = new GsonBuilder().create().toJson(maxiChance);

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

    /*maxiChancePvmaMaxBets prueba la respuesta del servicio al enviar la peticion
     * superando las lineas de apuestas  */

    @Test
    public void maxiChancePvmaMaxBets(){

        MaxiChanceINDto maxiChance = initValue();

        ArrayList<ChanceNumero> listChanceNumero = new ArrayList<>();
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;
        int numeroAleatorio2 = random.nextInt(8000) + 1000;
        int numeroAleatorio3 = random.nextInt(7000) + 1000;
        int numeroAleatorio4 = random.nextInt(6000) + 1000;
        int numeroAleatorio5 = random.nextInt(5000) + 1000;

        ChanceNumero chance1 = new ChanceNumero();

        chance1.setNumero(Integer.toString(numeroAleatorio1));
        chance1.setSuperPlenoDirecto(1200);

        ChanceNumero chance2 = new ChanceNumero();

        chance2.setNumero(Integer.toString(numeroAleatorio2));
        chance2.setSuperPlenoDirecto(1200);

        ChanceNumero chance3 = new ChanceNumero();

        chance3.setNumero(Integer.toString(numeroAleatorio3));
        chance3.setSuperPlenoDirecto(1200);

        ChanceNumero chance4 = new ChanceNumero();

        chance4.setNumero(Integer.toString(numeroAleatorio4));
        chance4.setSuperPlenoDirecto(1200);
        ChanceNumero chance5 = new ChanceNumero();

        chance5.setNumero(Integer.toString(numeroAleatorio5));
        chance5.setSuperPlenoDirecto(1200);

        listChanceNumero.add(chance1);
        listChanceNumero.add(chance2);
        listChanceNumero.add(chance3);
        listChanceNumero.add(chance4);
        listChanceNumero.add(chance5);

        maxiChance.setChanceNumeros(listChanceNumero);

        String jsonBody = new GsonBuilder().create().toJson(maxiChance);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .when()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("mensaje", Matchers.equalTo("La cantidad de numeros no corresponde a los numeros que tiene permitidos"));
    }

    /*
     * El serie2ActualResponseTest prueba que el atributo "serie2" que llega en la respuesta
     * no sea mayor o igual al atributo "serie2Actual".
     *
     * */

    @Test
    public void serie2ActualResponseTest(){

        long startTime = System.currentTimeMillis();

        MaxiChanceINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        Response response =given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI);

        MaxiChanceOUTDto respuestaBody = response.getBody().as(MaxiChanceOUTDto.class);

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
