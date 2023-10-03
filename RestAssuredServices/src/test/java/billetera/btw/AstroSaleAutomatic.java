package billetera.btw;
import com.google.gson.GsonBuilder;
import dto.saleastroautomatic.AstroAutomaticINDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AstroSaleAutomatic {

    public AstroSaleAutomatic(){
        RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/superastro/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    public static String consecutivoNumero(){

        LocalDateTime fechaHoraActual = LocalDateTime.now(); // crea un objeto de tipo LocalDateTime con la hora y fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddHHmmssSS"); // crea un objeto DateTimeFormatter con el formato deseado
        String consecutivo = fechaHoraActual.format(formatter);
        System.out.println(consecutivo);

        return  consecutivo;
    }

    public AstroAutomaticINDto initValue(){

        AstroAutomaticINDto astro1 = new AstroAutomaticINDto();

        astro1.setCodigoSorteo("2");
        astro1.setMonto("6700");
        astro1.setTipoIdentificacion("1");
        astro1.setNumeroIdentificacion("89798798");
        astro1.setIdTransaccion("5017654");
        astro1.setSerieTerminal("XXXX-1234");
        astro1.setCodigoVendedor("740001");
        astro1.setCodigoPuntoDeVenta("0");
        astro1.setRegistroDANE("25754");
        astro1.setSerie("AAAAA");
        astro1.setConsecutivo(consecutivoNumero());
        astro1.setIdSesion("f0106206-7083-407f-8093-3b2a417298df");
        astro1.setIdTipoTarjeta("MI_SALDO");
        astro1.setLatitud("4.5913271");
        astro1.setLongitud("-74.2057471");
        astro1.setSesion("0");


        LocalDateTime fechaHoraActual = LocalDateTime.now(); // crea un objeto de tipo LocalDateTime con la hora y fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss.SSS"); // crea un objeto DateTimeFormatter con el formato deseado
        String fechaHoraActualConFormato = fechaHoraActual.format(formatter);

        astro1.setFechaHoraPeticion(fechaHoraActualConFormato);
        astro1.setCanalId("billetera");
        return astro1;
    }

    @Test
    public void astroAutomaticDosSuccessful() {

        AstroAutomaticINDto astro1 = initValue();

        String jsonBody = new GsonBuilder().create().toJson(astro1);

                given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(200)
                .body("exitoso", equalTo(true));
    }

    @Test
    public void astroAutomaticUnoSuccessful() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCodigoSorteo("1");

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(200);
                .body("exitoso", equalTo(true));
    }
    /*astroAutomaticDataEmpty valida la respuesta del servicio al enviar el objeto con los valores vacios*/

    @Test
    public void astroAutomaticDataEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCodigoSorteo("");
        astro1.setMonto("");
        astro1.setTipoIdentificacion("");
        astro1.setNumeroIdentificacion("");
        astro1.setIdTransaccion("");
        astro1.setSerieTerminal("");
        astro1.setCodigoVendedor("");
        astro1.setCodigoPuntoDeVenta("");
        astro1.setRegistroDANE("");
        astro1.setSerie("");
        astro1.setConsecutivo("");
        astro1.setIdSesion("");
        astro1.setIdTipoTarjeta("");
        astro1.setLatitud("");
        astro1.setLongitud("");
        astro1.setSesion("");

        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo tipoIdentificacion vacio o inexistente "));
    }

    /*astroAutomaticCodigoSorteoEmpty valida la respuesta del servicio al enviar el atributo
     * codigoSorteo vacios */

    @Test
    public void astroAutomaticCodigoSorteoaEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCodigoSorteo("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticCodigoSorteoEmpty valida la respuesta del servicio al enviar el atributo
     * codigoSorteo con valores incorrectos */

    @Test
    public void astroAutomaticCodigoSorteoIncorrecto() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCodigoSorteo("3");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                .statusCode(500);
                //.body("exitoso", equalTo(false));
    }


    /*astroAutomaticMontoEmpty valida la respuesta del servicio al enviar el atributo
     * Monto con valores vacios */

    @Test
    public void astroAutomaticMontoEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setMonto("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo monto vacio o inexistente "));
    }


    /*astroAutomaticMontoEmpty valida la respuesta del servicio al enviar el atributo
     * monto con valor incorrecto */

    @Test
    public void astroAutomaticMontoIncorret() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setMonto("-6700");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("MONTO NO VALIDO 6"));
    }


    /*astroAutomaticMontoMax valida la respuesta del servicio al enviar el atributo
     * monto con valor maximo */

    @Test
    public void astroAutomaticMontoMax() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setMonto("11000");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("MONTO NO VALIDO 6"));
    }



    /*astroAutomaticCodigoIdentiEmpty valida la respuesta del servicio al enviar el atributo
     * TipoIdentificacion con valor vacio */

    @Test
    public void astroAutomaticTipoIdentiEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setTipoIdentificacion("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo tipoIdentificacion vacio o inexistente "));
    }

    /*astroAutomaticMontoEmpty valida la respuesta del servicio al enviar el atributo
     * TipoIdentificacion con valor incorrecto */

    @Test
    public void astroAutomaticTipoIdentifIncorret() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setTipoIdentificacion("001");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticNumeroIdentiEmpty valida la respuesta del servicio al enviar el atributo
     * NumeroIdentificacion con valor vacio */

    @Test
    public void astroAutomaticNumeroIdentiEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setNumeroIdentificacion("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo tipoIdentificacion vacio o inexistente "));
    }

    /*
    *
    * */


    /*astroAutomaticNumeroIdentiIncorrect valida la respuesta del servicio al enviar el atributo
     * NumeroIdentificacion con valor incorrecto */

    @Test
    public void astroAutomaticNumeroIdentiIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setNumeroIdentificacion("7537993");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: numeroIdentificacion no existe"));
    }

    /*astroAutomaticIdTransaccionEmpty valida la respuesta del servicio al enviar el atributo
     * idTransaccion con valor vacio */

    @Test
    public void astroAutomaticIdTransaccionEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setIdTransaccion("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo idTransaccion vacio o inexistente "));
    }

    /*astroAutomaticIdTransaccionIncorrect valida la respuesta del servicio al enviar el atributo
     * idTransaccion con valor Incorrecto */

    @Test
    public void astroAutomaticIdTransaccionIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setIdTransaccion("-0000001");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticserieTermEmpty valida la respuesta del servicio al enviar el atributo
     * SerieTerminal con valor vacio */

    @Test
    public void astroAutomaticSerieTermEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setSerieTerminal("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo SerieTerminal vacio o inexistente "));
    }

    /*astroAutomaticserieTermIncorrect valida la respuesta del servicio al enviar el atributo
     * SerieTerminal con valor Incorrecto */

    @Test
    public void astroAutomaticSerieTermIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setSerieTerminal("ZZZZ-6789");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("PUNTO VENTA NO EXISTE"));
    }


    /*astroAutomaticcodVenderoEmpty valida la respuesta del servicio al enviar el atributo
     * codigoVendedor con valor vacio */

    @Test
    public void astroAutomaticCodVendedorEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCodigoVendedor("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo codigoVendedor vacio o inexistente "));
    }


    /*astroAutomaticcodVenderoIncorrect valida la respuesta del servicio al enviar el atributo
     * codigoVendedor con valor Incorrecto */

    @Test
    public void astroAutomaticCodVendedorIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCodigoVendedor("41900787");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("VENDEDOR NO EXISTE"));
    }

    /*astroAutomaticCodPuntoVEmpty valida la respuesta del servicio al enviar el atributo
     * codigoPuntoDeVenta con valor vacio */

    @Test
    public void astroAutomaticCodPuntoVEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCodigoPuntoDeVenta("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticCodPuntoVEmpty valida la respuesta del servicio al enviar el atributo
     * codigoPuntoDeVenta con valor incorrecto */

    @Test
    public void astroAutomaticCodPuntoVIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCodigoPuntoDeVenta("SELECT * FROM VENDEDOR");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticResgistroDANEEmpty valida la respuesta del servicio al enviar el atributo
     * registroDANE con valor vacio */

    @Test
    public void astroAutomaticRegistroDaneEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setRegistroDANE("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo registroDANE vacio o inexistente "));
    }

    /*astroAutomaticResgistroDaneIncorrect valida la respuesta del servicio al enviar el atributo
     * registroDANE con valor incorrecto */

    @Test
    public void astroAutomaticResgistroDaneIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCodigoPuntoDeVenta("SELECT * FROM VENDEDOR");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }


    /*astroAutomaticSerieEmpty valida la respuesta del servicio al enviar el atributo
     * serie con valor vacio */

    @Test
    public void astroAutomaticSerieEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setSerie("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo serie vacio o inexistente "));
    }

    /*astroAutomaticSerieIncorrect valida la respuesta del servicio al enviar el atributo
     * Serie con valor incorrecto */

    @Test
    public void astroAutomaticSerieIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setSerie("BBBBB");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }


    /*astroAutomaticConsecutivoEmpty valida la respuesta del servicio al enviar el atributo
     * Consecutivo con valor vacio */

    @Test
    public void astroAutomaticConsecutivoEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setConsecutivo("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("dscEstado", equalTo("Peticion invalida: Campo consecutivo vacio o inexistente "));
    }

    /*astroAutomaticConsecutivoIncorrect valida la respuesta del servicio al enviar el atributo
     * consecutivo con valor incorrecto */

    @Test
    public void astroAutomaticConsecutivoIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        //astro1.setSerie("BBBBB");
        astro1.setConsecutivo("8888888888");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }


    /*astroAutomaticConsecutivoCaracteresEsp valida la respuesta del servicio al enviar el atributo
     * consecutivo con valor con caracteres especiales */

    @Test
    public void astroAutomaticConsecutivoCaracteresEsp() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setConsecutivo("**$#2*****");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticIdSesionEmpty valida la respuesta del servicio al enviar el atributo
     * idSesion con valor vacio */

    @Test
    public void astroAutomaticIdSesionEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setIdSesion("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticIdSesionIncorrect valida la respuesta del servicio al enviar el atributo
     * idSesion con valor incorrecto */

    @Test
    public void astroAutomaticIdSesionIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setIdSesion("NotengoIdSesionyPuedoRealizarLaTransaccion");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }


    /*astroAutomaticIdTipoTarjetaEmpty valida la respuesta del servicio al enviar el atributo
     * idTipoTarjeta con valor vacio */

    @Test
    public void astroAutomaticIdTipoTarjetaEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setIdTipoTarjeta("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticIdTipoTarjetaIncorrect valida la respuesta del servicio al enviar el atributo
     * idTipoTarjeta con valor incorrecto */

    @Test
    public void astroAutomaticIdTipoTarjetaIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setIdTipoTarjeta("MI_SALDO_ESTA_VACIO");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }


    /*astroAutomaticLatitudEmpty valida la respuesta del servicio al enviar el atributo
     * latitud con valor vacio */

    @Test
    public void astroAutomaticLatitudEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setLatitud("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticLatitudIncorrect valida la respuesta del servicio al enviar el atributo
     * latitud con valor incorrecto */

    @Test
    public void astroAutomaticLatitudIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setLatitud("42.315407");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticLatitudCaracteresEsp valida la respuesta del servicio al enviar el atributo
     * latitud con valor con caracteres especiales */

    @Test
    public void astroAutomaticLatitudCaracteresEsp() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setLatitud("SELECT * FROM MAESTRO_CHANCE");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticLongitudEmpty valida la respuesta del servicio al enviar el atributo
     * longitud con valor vacio */

    @Test
    public void astroAutomaticLongitudEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setLongitud("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticLongitudIncorrect valida la respuesta del servicio al enviar el atributo
     * longitud con valor incorrecto */

    @Test
    public void astroAutomaticLongitudIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setLongitud("43.356892");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticLongitudCaracteresEsp valida la respuesta del servicio al enviar el atributo
     * longitud con valor con caracteres especiales */

    @Test
    public void astroAutomaticLongitudCaracteresEsp() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setLatitud("$LoteryaChance***; 43.356892");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticSesionEmpty valida la respuesta del servicio al enviar el atributo
     * sesion con valor vacio */

    @Test
    public void astroAutomaticSesionEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setSesion("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticSesionIncorrect valida la respuesta del servicio al enviar el atributo
     * sesion con valor incorrecto */

    @Test
    public void astroAutomaticSesionIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setSesion("99999999999999999999999999");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticSesionEmpty valida la respuesta del servicio al enviar el atributo
     * fechaHoraPeticion con valor vacio */

    @Test
    public void astroAutomaticFechaHoraEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setFechaHoraPeticion("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticFechaHoraIncorrect valida la respuesta del servicio al enviar el atributo
     * fechaHoraPeticion con valor fuera del rango */

    @Test
    public void astroAutomaticFechaHoraFueraRang() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setFechaHoraPeticion("01/01/2030:11:08:58.211");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticFechaHoraIncorrect valida la respuesta del servicio al enviar el atributo
     * fechaHoraPeticion con valor fuera del rango */

    @Test
    public void astroAutomaticFechaHoraIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setFechaHoraPeticion("25/04/2023:12:33:00.211");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }



    /*astroAutomaticCanalIdEmpty valida la respuesta del servicio al enviar el atributo
     * CanalId con valor vacio */

    @Test
    public void astroAutomaticCanalIdEmpty() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setCanalId("");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }

    /*astroAutomaticCanalIdIncorrect valida la respuesta del servicio al enviar el atributo
     * CanalId con valor incorrecto */

    @Test
    public void astroAutomaticCanalIdIncorrect() {

        AstroAutomaticINDto astro1 = initValue();
        astro1.setSesion("CARACOL");


        String jsonBody = new GsonBuilder().create().toJson(astro1);

        given()
                .body(jsonBody)
                .post(baseURI + "ventaautomatica/")
                .then()
                //.statusCode(500);
                .body("exitoso", equalTo(false));
    }



}
