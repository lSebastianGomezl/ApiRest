package pvma_cor;

import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.balotoPVMA.BalotoINDto;
import dto.balotoPVMA.Board;
import dto.consultcuadreactual.CuadreActualINDto;
import dto.consultcuadreactual.CuadreActualOUTDto;
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
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Baloto {

    String autorizador;
    String idSesion;

    String serie1;
    String serie2;

    public Baloto() {
        {
            RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/baloto/";
            RestAssured.basePath = "realizarApuesta";
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
    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public BalotoINDto initValue() {

        BalotoINDto baloto = new BalotoINDto();

        baloto.setDuration(1);
        baloto.setGameName("BALOTO");
        baloto.setPrice(5700);

        ArrayList<Board> listBoard = new ArrayList<>();

        Board board = new Board();
        board.setQuickpick(true);
        board.setStake(5700);
        board.setAddonPlayed(false);

        listBoard.add(board);

        baloto.setBoards(listBoard);
        baloto.setSerie1(serie1);
        baloto.setSerie2(serie2);
        baloto.setProvocarTimeout(false);
        baloto.setCodigoVendedor("222230");
        baloto.setValor(5700);
        baloto.setCanalId("PVMA");
        baloto.setCodigoTerminal("862634041369599");
        baloto.setTipoUsuario("VENDEDOR");
        baloto.setSecuenciaTransaccion(1);
        baloto.setTransactionTime(fechaActual());
        baloto.setSeriePrefijo(serie1);
        baloto.setSerieActual(serie2);
        return baloto;

    }


    /*balotoSuccessful prueba que la respuesta del servicio sea
     * exitosa*/
    @Test
    public void balotoSuccessful() {

       BalotoINDto baloto = initValue();

        String jsonBody = new GsonBuilder().create().toJson(baloto);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));

    }

    /*
    * balotoTwoSuccessful prueba un sorteo manual - 1 fecha sin revancha
    * */

    @Test
    public void balotoTwoSuccessful() {

        BalotoINDto baloto = initValue();

        ArrayList<Board> listBoard = new ArrayList<>();

        Board board = new Board();
        board.setQuickpick(false);
        board.setStake(5700);

        ArrayList<String> listSelection = new ArrayList<>();

        listSelection.add("23");
        listSelection.add("41");
        listSelection.add("38");
        listSelection.add("10");
        listSelection.add("05");
        listSelection.add("11");
        board.setSelections(listSelection);

        board.setAddonPlayed(false);

        listBoard.add(board);

        baloto.setBoards(listBoard);

        String jsonBody = new GsonBuilder().create().toJson(baloto);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));

    }

    /*
     * balotoThreeSuccessful prueba un sorteo automatico - 1 fecha con revancha
     * */

    @Test
    public void balotoThreeSuccessful() {

        BalotoINDto baloto = initValue();

        baloto.setPrice(7800);

        ArrayList<Board> listBoard = new ArrayList<>();

        Board board = new Board();
        board.setQuickpick(true);
        board.setStake(5700);
        board.setAddonPlayed(true);

        listBoard.add(board);

        baloto.setBoards(listBoard);

        baloto.setValor(7800);

        String jsonBody = new GsonBuilder().create().toJson(baloto);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));

    }


    /*
     * balotoFourSuccessful prueba un baloto- mas de una fecha
     * */
    @Test
    public void balotoFourSuccessful() {

        BalotoINDto baloto = initValue();

        baloto.setDuration(4);
        baloto.setPrice(22800);

        ArrayList<Board> listBoard = new ArrayList<>();

        Board board = new Board();
        board.setQuickpick(false);
        board.setStake(5700);

        ArrayList<String> listSelection = new ArrayList<>();

        listSelection.add("31");
        listSelection.add("40");
        listSelection.add("29");
        listSelection.add("10");
        listSelection.add("07");
        listSelection.add("01");
        board.setSelections(listSelection);

        board.setAddonPlayed(false);

        listBoard.add(board);

        baloto.setBoards(listBoard);

        baloto.setValor(22800);
        baloto.setSecuenciaTransaccion(5);

        String jsonBody = new GsonBuilder().create().toJson(baloto);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));

    }

    /*
     * balotoFiveSuccessful prueba un baloto- mas de una fecha
     * */
    @Test
    public void balotoFiveSuccessful() {

        BalotoINDto baloto = initValue();

        baloto.setDuration(1);
        baloto.setPrice(21300);

        ArrayList<Board> listBoard = new ArrayList<>();

        Board board = new Board();
        board.setQuickpick(true);
        board.setStake(5700);
        board.setAddonPlayed(false);

        Board board1 = new Board();

        board1.setQuickpick(false);
        board1.setStake(5700);

        ArrayList<String> listSelection = new ArrayList<>();

        listSelection.add("01");
        listSelection.add("18");
        listSelection.add("21");
        listSelection.add("39");
        listSelection.add("43");
        listSelection.add("16");
        board1.setSelections(listSelection);

        board1.setAddonPlayed(true);

        Board board2 = new Board();
        board2.setQuickpick(true);
        board2.setStake(5700);
        board2.setAddonPlayed(true);


        listBoard.add(board);
        listBoard.add(board1);
        listBoard.add(board2);

        baloto.setBoards(listBoard);

        baloto.setValor(21300);
        baloto.setSecuenciaTransaccion(6);

        String jsonBody = new GsonBuilder().create().toJson(baloto);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));

    }

    /*
     * balotoSixSuccessful prueba mas de un baloto - mas de una fecha
     * */
    @Test
    public void balotoSixSuccessful() {

        BalotoINDto baloto = initValue();


        baloto.setDuration(9);
        baloto.setPrice(243000);

        ArrayList<Board> listBoard = new ArrayList<>();

        Board board = new Board();
        board.setQuickpick(true);
        board.setStake(5700);
        board.setAddonPlayed(false);

        Board board1 = new Board();

        board1.setQuickpick(true);
        board1.setStake(5700);
        board1.setAddonPlayed(true);


        Board board2 = new Board();
        board2.setQuickpick(false);
        board2.setStake(5700);


        ArrayList<String> listSelection = new ArrayList<>();

        listSelection.add("02");
        listSelection.add("13");
        listSelection.add("24");
        listSelection.add("35");
        listSelection.add("43");
        listSelection.add("16");

        board2.setAddonPlayed(false);

        Board board3 = new Board();
        board3.setQuickpick(false);
        board3.setStake(5700);


        ArrayList<String> listSelection2 = new ArrayList<>();

        listSelection2.add("08");
        listSelection2.add("16");
        listSelection2.add("24");
        listSelection2.add("32");
        listSelection2.add("40");
        listSelection2.add("01");

        board3.setAddonPlayed(true);

        board2.setSelections(listSelection);
        board3.setSelections(listSelection2);


        listBoard.add(board);
        listBoard.add(board1);
        listBoard.add(board2);

        baloto.setBoards(listBoard);

        baloto.setValor(21300);
        baloto.setSecuenciaTransaccion(6);

        String jsonBody = new GsonBuilder().create().toJson(baloto);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));

    }

}
