package billetera.btw;
import com.google.gson.GsonBuilder;
import dto.superastrocanalvirtual.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class SuperAstroCanalVirtual {

    public SuperAstroCanalVirtual() {
        RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/superastro/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
    /*
    private static int contador = 229;

    public static String getProximoNumero() {
        int length = 7;
        contador++;
        return String.format("%1$-" + length + "s", "0000000" + contador);
    }

     */

    public static String consecutivoNumero(){

        LocalDateTime fechaHoraActual = LocalDateTime.now(); // crea un objeto de tipo LocalDateTime con la hora y fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddHHmmssSS"); // crea un objeto DateTimeFormatter con el formato deseado
        String consecutivo = fechaHoraActual.format(formatter);
        System.out.println(consecutivo);
        return  consecutivo;

    }






    public SuperAstroVirualINDto initValue() {

        SuperAstroVirualINDto transaccion = new SuperAstroVirualINDto();

        DetallesApuestas detalle1 = new DetallesApuestas();

        ArrayList<Apuestm> listApuestas = new ArrayList<>();

        Apuestm apuesta1 = new Apuestm();

        apuesta1.setNumeroAJugar("1111");
        apuesta1.setMonto(500);
        apuesta1.setSigno(2);

        listApuestas.add(apuesta1);
        detalle1.setApuesta(listApuestas);

        Sorteos sorteo1 = new Sorteos();

        ArrayList<String> codigoSorteo = new ArrayList<>();

        codigoSorteo.add("2");
        sorteo1.setCodigoSorteo(codigoSorteo);

        transaccion.setCantidadSorteos("1");
        transaccion.setSorteos(sorteo1);
        transaccion.setCantidadApuestas("1");
        transaccion.setDetalleApuestas(detalle1);
        transaccion.setTipoIdentificacion("1");
        transaccion.setNumeroIdentificacion("89798798");
        transaccion.setIdTransaccion("112022");
        transaccion.setSerieTerminal("XXXX-1234");
        transaccion.setCodigoVendedor("740001");
        transaccion.setCodigoPuntoDeVenta("0");
        transaccion.setRegistroDANE("11001");
        transaccion.setSerie("A2023");
        transaccion.setConsecutivo(consecutivoNumero());
        //transaccion.setConsecutivo(getProximoNumero());
        //System.out.println(getProximoNumero());
        transaccion.setIdSesion("146E3881674228BDB061EBB3C7F6C463.nodo3");
        transaccion.setIdTipoTarjeta("MI_SALDO");
        transaccion.setLatitud("4.579524");
        transaccion.setLongitud("-74.1574289");
        transaccion.setSesion("0");

        LocalDateTime fechaHoraActual = LocalDateTime.now(); // crea un objeto de tipo LocalDateTime con la hora y fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss.SSS"); // crea un objeto DateTimeFormatter con el formato deseado
        String fechaHoraActualConFormato = fechaHoraActual.format(formatter);

        System.out.println(fechaHoraActualConFormato);

        transaccion.setFechaHoraPeticion(fechaHoraActualConFormato);
        transaccion.setCanalId("billetera");

        return transaccion;

    }

    /* PRUEBAS DESARROLLADOR*/

    /*
     * Antes de las 14:05 pm el test debe ser exitoso de lo contrario respuesta "SORTEO CERRADO"
     *
     * */

    @Test
    public void superAstroVirtualUnoSorteoSuccessful() {

        SuperAstroVirualINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                //.statusCode(200)
                .body("dscEstado", equalTo("VENTA EXITOSA"));

    }



    /*
     * Antes de las 14:05 pm el test debe ser exitoso de lo contrario respuesta "SORTEO CERRADO"
     *
     * */


    @Test
    public void superAstroVirtualDosSorteoSuccessful() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadSorteos("2");
        //transaccion.setConsecutivo(getProximoNumero());

        Sorteos sorteo1 = new Sorteos();

        ArrayList<String> codigoSorteo = new ArrayList<>();

        codigoSorteo.add("1");
        codigoSorteo.add("2");
        sorteo1.setCodigoSorteo(codigoSorteo);
        transaccion.setSorteos(sorteo1);

        LocalTime horaActual = LocalTime.now();
        LocalTime horaCondicional = LocalTime.of(14,05);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        Response response = given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/");

        System.out.println("RESPUESTA" + response.prettyPrint());

        SuperAstroOUTDto respuestaBody = response.getBody().as(SuperAstroOUTDto.class);

        System.out.println(respuestaBody.getDscEstado());


                if (horaActual.isAfter(horaCondicional)){
                    Assert.assertTrue(respuestaBody.getDscEstado().equals("SORTEO CERRADO"));
                }else {
                    Assert.assertTrue(respuestaBody.getDscEstado().equals("VENTA EXITOSA"));
                }

    }

    /*
     * superAstroVirtualIncorrectUnoSorteoSuccessful envia el valor de 1 sola cantidad e sorteo
     * y enviamos 2 codigos de sorteos.
     * */

    @Test
    public void superAstroVirtualIncorrectUnoSorteoSuccessful() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadSorteos("2");

        Sorteos sorteo1 = new Sorteos();

        ArrayList<String> codigoSorteo = new ArrayList<>();

        codigoSorteo.add("1");
        codigoSorteo.add("2");
        sorteo1.setCodigoSorteo(codigoSorteo);
        transaccion.setSorteos(sorteo1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                //.statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * superAstroVirtualIncorrectTresSorteoSuccessful envia el valor de 3 sola cantidad e sorteo
     * y enviamos 3 codigos de sorteos.
     * */

    @Test
    public void superAstroVirtualIncorrectTresSorteoSuccessful() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadSorteos("3");

        Sorteos sorteo1 = new Sorteos();

        ArrayList<String> codigoSorteo = new ArrayList<>();

        codigoSorteo.add("1");
        codigoSorteo.add("2");
        codigoSorteo.add("3");
        sorteo1.setCodigoSorteo(codigoSorteo);
        transaccion.setSorteos(sorteo1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                //.statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * Este metodo prueba el atributo "candidadSorteo" con valor vacio.
     * */
    @Test
    public void cantidadSorteosEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadSorteos(" ");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

                given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo cantidadSorteos vacio o inexistente "));

    }

    /*
     * Este metodo prueba el atributo "candidadSorteo" con valor incorrecto.
     * */
    @Test
    public void cantidadSorteosIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadSorteos("*");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(500);
                //.body("dscEstado", equalTo("VENTA EXITOSA"));

    }

    /*
     * Este metodo prueba el atributo "codigoSorteos" con valor vacio.
     * */

    @Test
    public void codigoSorteosEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        Sorteos sorteo1 = new Sorteos();

        ArrayList<String> codigoSorteo = new ArrayList<>();

        codigoSorteo.add("");
        codigoSorteo.add("");

        sorteo1.setCodigoSorteo(codigoSorteo);
        transaccion.setSorteos(sorteo1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * Este metodo prueba el atributo "codigoSorteos" con valor incorrecto.
     * */

    @Test
    public void codigoSorteosIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        Sorteos sorteo1 = new Sorteos();

        ArrayList<String> codigoSorteo = new ArrayList<>();

        codigoSorteo.add("63");
        codigoSorteo.add("*!");

        sorteo1.setCodigoSorteo(codigoSorteo);
        transaccion.setSorteos(sorteo1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * Este metodo prueba el atributo "cantidadApuestas" con valor vacio.
     * */

    @Test
    public void cantidadApuestasEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadApuestas("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo cantidadApuestas vacio o inexistente "));

    }

    /*
     * Este metodo prueba 4 puestas con el mismo numero de apuesta.
     * */

    @Test
    public void cantidadApuestasIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadApuestas("4");

        DetallesApuestas detalle1 = new DetallesApuestas();

        ArrayList<Apuestm> listApuestas = new ArrayList<>();

        Apuestm apuesta1 = new Apuestm();

        apuesta1.setNumeroAJugar("0000");
        apuesta1.setMonto(500);
        apuesta1.setSigno(6);

        Apuestm apuesta2 = new Apuestm();

        apuesta2.setNumeroAJugar("0000");
        apuesta2.setMonto(500);
        apuesta2.setSigno(5);

        Apuestm apuesta4 = new Apuestm();

        apuesta4.setNumeroAJugar("0000");
        apuesta4.setMonto(500);
        apuesta4.setSigno(4);

        Apuestm apuesta5 = new Apuestm();

        apuesta5.setNumeroAJugar("0000");
        apuesta5.setMonto(500);
        apuesta5.setSigno(3);



        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta4);
        listApuestas.add(apuesta5);

        detalle1.setApuesta(listApuestas);
        transaccion.setDetalleApuestas(detalle1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Apuesta invalida "));

    }


    /*
     * Este metodo prueba el valor del monto de la apuesta negativo.
     * */

    @Test
    public void montoNegativoIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadApuestas("2");

        DetallesApuestas detalle1 = new DetallesApuestas();

        ArrayList<Apuestm> listApuestas = new ArrayList<>();

        Apuestm apuesta1 = new Apuestm();

        apuesta1.setNumeroAJugar("3652");
        apuesta1.setMonto(-60000);
        apuesta1.setSigno(2);

        Apuestm apuesta2 = new Apuestm();

        apuesta2.setNumeroAJugar("5985");
        apuesta2.setMonto(-5848);
        apuesta2.setSigno(10);




        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);


        detalle1.setApuesta(listApuestas);
        transaccion.setDetalleApuestas(detalle1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("MONTO NO VALIDO 1"));

    }

    /*
     * Este metodo prueba la cantidad superior con la disponibilidad
     * de los signos.
     * */

    @Test
    public void signoCantidadMaxIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadApuestas("1");

        DetallesApuestas detalle1 = new DetallesApuestas();

        ArrayList<Apuestm> listApuestas = new ArrayList<>();

        Apuestm apuesta1 = new Apuestm();

        apuesta1.setNumeroAJugar("9874");
        apuesta1.setMonto(6000);
        apuesta1.setSigno(16);

        listApuestas.add(apuesta1);


        detalle1.setApuesta(listApuestas);
        transaccion.setDetalleApuestas(detalle1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * Este metodo prueba la cantidad negativa con la disponibilidad
     * de los signos.
     * */

    @Test
    public void signoCantidadMinIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadApuestas("2");

        DetallesApuestas detalle1 = new DetallesApuestas();

        ArrayList<Apuestm> listApuestas = new ArrayList<>();

        Apuestm apuesta1 = new Apuestm();

        apuesta1.setNumeroAJugar("9874");
        apuesta1.setMonto(6000);
        apuesta1.setSigno(5);

        Apuestm apuesta2 = new Apuestm();

        apuesta2.setNumeroAJugar("5985");
        apuesta2.setMonto(5848);
        apuesta2.setSigno(-10);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);


        detalle1.setApuesta(listApuestas);
        transaccion.setDetalleApuestas(detalle1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * Este metodo prueba el mismo nuemro de apuesta con el mismo signo.
     * */

    @Test
    public void astroSameNumberAndSign() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadApuestas("2");

        DetallesApuestas detalle1 = new DetallesApuestas();

        ArrayList<Apuestm> listApuestas = new ArrayList<>();

        Apuestm apuesta1 = new Apuestm();

        apuesta1.setNumeroAJugar("9874");
        apuesta1.setMonto(6000);
        apuesta1.setSigno(5);

        Apuestm apuesta2 = new Apuestm();

        apuesta2.setNumeroAJugar("9874");
        apuesta2.setMonto(6000);
        apuesta2.setSigno(5);

        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);


        detalle1.setApuesta(listApuestas);
        transaccion.setDetalleApuestas(detalle1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Apuesta invalida "));

    }




    /*
     * Este metodo prueba el maximo de cantidad de apuestas a realizar
     * */

    @Test
    public void cantidadApuestasMaxCantidadApuestas() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCantidadApuestas("5");

        DetallesApuestas detalle1 = new DetallesApuestas();

        ArrayList<Apuestm> listApuestas = new ArrayList<>();

        Apuestm apuesta1 = new Apuestm();

        apuesta1.setNumeroAJugar("1112");
        apuesta1.setMonto(500);
        apuesta1.setSigno(2);

        Apuestm apuesta2 = new Apuestm();

        apuesta2.setNumeroAJugar("1113");
        apuesta2.setMonto(500);
        apuesta2.setSigno(2);

        Apuestm apuesta3 = new Apuestm();

        apuesta3.setNumeroAJugar("1114");
        apuesta3.setMonto(500);
        apuesta3.setSigno(2);

        Apuestm apuesta4 = new Apuestm();

        apuesta4.setNumeroAJugar("1115");
        apuesta4.setMonto(500);
        apuesta4.setSigno(2);

        Apuestm apuesta5 = new Apuestm();

        apuesta5.setNumeroAJugar("1115");
        apuesta5.setMonto(500);
        apuesta5.setSigno(2);



        listApuestas.add(apuesta1);
        listApuestas.add(apuesta2);
        listApuestas.add(apuesta3);
        listApuestas.add(apuesta4);
        listApuestas.add(apuesta5);

        detalle1.setApuesta(listApuestas);
        transaccion.setDetalleApuestas(detalle1);

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Maximo 4 apuestas por tiquete "));

    }


    /*
     * El metodo prueba el atributo "tipoIdentificacion" con valor vacio.
     *
     * */

    @Test
    public void tipoIdentificacionEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setTipoIdentificacion(" ");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                //.statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo tipoIdentificacion vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "tipoIdentificacion" con valor incorrecto.
     *
     * */

    @Test
    public void tipoIdentificacionIcorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setTipoIdentificacion("??");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                //.statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "tipoIdentificacion" con valor incorrecto.
     *
     * */

    @Test
    public void tipoIdentificacionIcorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setTipoIdentificacion("0");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                //.statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "numeroIdentificacion" con valor vacio.
     *
     * */

    @Test
    public void numeroIdentificacionEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setNumeroIdentificacion(" ");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                //.statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo numeroIdentificacion vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "numeroIdentificacion" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void numeroIdentificacionIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setNumeroIdentificacion("*?");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(500);
                //.body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "numeroIdentificacion" con valor incorrecto.
     *
     * */

    @Test
    public void numeroIdentificacionIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setNumeroIdentificacion("00000");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: numeroIdentificacion no existe"));

    }

    /*
     * El metodo prueba el atributo "idTransaccion" con valor vacio.
     *
     * */

    @Test
    public void idTransaccionEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setIdTransaccion("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

                given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo idTransaccion vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "idTransaccion" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void idTransaccionIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setIdTransaccion("*/?¡#$");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(500);
                //.body("dscEstado", equalTo("Peticion invalida: Campo idTransaccion vacio o inexistente "));

    }
    /*
     * El metodo prueba el atributo "idTransaccion" con valor incorrecto.
     * Este campo le falta validaciones ya que la transaccion es exitosa colocando un numero
     * x
     * */

    @Test
    public void idTransaccionIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setIdTransaccion("0000000000");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }


    /*
     * El metodo prueba el atributo "seriTerminal" con valor vacio.
     *
     * */

    @Test
    public void serieTerminalEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setSerieTerminal("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo SerieTerminal vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "seriTerminal" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void serieTerminalIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setSerieTerminal("SELECT*FROM");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: punto de venta no existe"));

    }

    /*
     * El metodo prueba el atributo "seriTerminal" con valor incorrecto.
     *
     * */

    @Test
    public void serieTerminalIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setSerieTerminal("YYYY-4321");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: punto de venta no existe"));

    }

    /*
     * El metodo prueba el atributo "codigoVendedor" con valor vacio.
     *
     * */

    @Test
    public void codigoVendedorlEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCodigoVendedor("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo codigoVendedor vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "codigoVendedor" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void codigoVendedorlIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCodigoVendedor("SELECT*FROM VENDEDORES");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(500);
                //.body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "codigoVendedor" con valor incorrecto.
     *
     * */

    @Test
    public void codigoVendedorlIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCodigoVendedor("7537993");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

                given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "codigoPuntoDeVenta" con valor vacio.
     *
     * */

    @Test
    public void codigoPuntoDeVentaEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCodigoPuntoDeVenta("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

                given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo codigoVendedor vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "codigoPuntoDeVenta" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void codigoPuntoDeVentaIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCodigoPuntoDeVenta("SELECT * FROM VENDEDORES");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo codigoVendedor vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "codigoPuntoDeVenta" con valor incorrecto.
     *
     * */

    @Test
    public void codigoPuntoDeVentaIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCodigoPuntoDeVenta("#586954*");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo codigoVendedor vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "registroDane" con valor vacio.
     *
     * */

    @Test
    public void registroDaneEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setRegistroDANE("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo registroDANE vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "registroDane" con valor incorrecto caracteres especiales.
     *
     * */

    @Test
    public void registroDaneIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setRegistroDANE("#*1018/");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

                given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "registroDane" con valor incorrecto.
     *
     * */

    @Test
    public void registroDaneIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setRegistroDANE("00001");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("DANE INVALIDO"));

    }

    /*
     * El metodo prueba el atributo "serie" con valor vacio.
     *
     * */

    @Test
    public void serieEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setSerie("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo serie vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "serie" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void serieIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setSerie("ZZ0*#");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo serie vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "serie" con valor incorrecto.
     *
     * */

    @Test
    public void serieIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setSerie("00000");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo serie vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "consecutivo" con valor vacio.
     *
     * */

    @Test
    public void consecutivoEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setConsecutivo("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("dscEstado", equalTo("Peticion invalida: Campo consecutivo vacio o inexistente "));

    }

    /*
     * El metodo prueba el atributo "consecutivo" con valor incorrecto caracteres especiales.
     *
     * */

    @Test
    public void consecutivoIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setConsecutivo("//*0454#");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "consecutivo" con valor incorrecto.
     *
     * */

    @Test
    public void consecutivoIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setConsecutivo("0000000002");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "idSesion" con valor vacio.
     *
     * */

    @Test
    public void idSesionEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setIdSesion("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "idSesion" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void idSesionIncorret() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setIdSesion("SELECT * FROM ASESORES");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "idSesion" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void idSesionIncorret2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setIdSesion("00000542879855asdasdakmoiasdas");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "idTipoTarjeta" con valor vacio.
     *
     * */

    @Test
    public void idTipoTarjetaEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setIdTipoTarjeta("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "idTipoTarjeta" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void idTipoTarjetaIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setIdTipoTarjeta("SELECT * FROM MAESTRO_CHANCE");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "idTipoTarjeta" con valor incorrecto.
     *
     * */

    @Test
    public void idTipoTarjetaIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setIdTipoTarjeta("SALDO_TOTAL");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }


    /*
     * El metodo prueba el atributo "latitud" con valor vacio.
     *
     * */

    @Test
    public void latitudEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setLatitud("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "latitud" con valor incorrecto con caracteres especiales.
     *
     * */

    @Test
    public void latitudIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setLatitud("CAUCA- #***27");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "latitud" con valor incorrecto.
     *
     * */

    @Test
    public void latitudIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setLatitud("0.20455");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "longitud" con valor vacio.
     *
     * */

    @Test
    public void longitudEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setLongitud("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "longitud" con valor incorrecto caracteres especiales.
     *
     * */

    @Test
    public void longitudIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setLongitud("SELECT * FROM VENTAS");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "longitud" con valor incorrecto .
     *
     * */

    @Test
    public void longitudIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setLongitud("720215");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "sesion" con valor vacio .
     *
     * */

    @Test
    public void sesionEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setSesion("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "sesion" con valor incorrecto con caracteres especiales .
     *
     * */

    @Test
    public void sesionIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setSesion("*/#$#");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "sesion" con valor incorrecto.
     *
     * */

    @Test
    public void sesionIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setSesion("52");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "fechaHoraPeticion" con valor vacio .
     *
     * */

    @Test
    public void fechaHoraPeticionEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setFechaHoraPeticion("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "fechaHoraPeticion" con valor incorrecto con atributos especiales .
     *
     * */

    @Test
    public void fechaHoraPeticionIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setFechaHoraPeticion("***%*");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(500);
                //.body("exitoso", equalTo(false));

    }

    /*
     * El metodo prueba el atributo "fechaHoraPeticion" con valor incorrecto con atributos especiales .
     *
     * */

    @Test
    public void fechaHoraPeticionIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setFechaHoraPeticion("13/03/2023:00:00:00.500");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }


    /*
     * El metodo prueba el atributo "canalId" con valor vacio .
     *
     * */

    @Test
    public void canalEmpty() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCanalId("");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }
    /*
     * El metodo prueba el atributo "canalId" con valor incorrecto con caracteres especiales .
     *
     * */

    @Test
    public void canalIncorrect() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCanalId("#*PVMA*#");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }


    /*
     * El metodo prueba el atributo "canalId" con valor incorrecto.
     *
     * */

    @Test
    public void canalIncorrect2() {

        SuperAstroVirualINDto transaccion = initValue();

        transaccion.setCanalId("GANA");

        String jsonBody = new GsonBuilder().create().toJson(transaccion);

        given()
                .body(jsonBody)
                .post(baseURI + "ventamanual/")
                .then()
                .statusCode(200)
                .body("exitoso", equalTo(false));

    }







}
