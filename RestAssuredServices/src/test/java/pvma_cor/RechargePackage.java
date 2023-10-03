package pvma_cor;

import com.google.gson.GsonBuilder;

import dto.LoginOUTDto;
import dto.RechargeDto;
import dto.RechargeINDto;
import dto.recargapaquetes.RecargaPaqueteINDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import process.LoginService;

import java.util.Calendar;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RechargePackage {

    String autorizador;
    String idSesion;
    String serie1;
    String serie2;
    String codVendedor;
    String canalId = "PVMA";
    String codTerminal = "862634041369599";
    int idTerminal = 46447;

    String tipoUsuario = "VENDEDOR";

    String codMunicipio;
    String codOficina;

    String codPuntoVenta;
    String tipoPuntoVenta;
    String operacionTransaccion = "VENTA";

    public RechargePackage(){
        {
            RestAssured.baseURI="http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/";
            RestAssured.basePath="recarga/";
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .build();

            LoginOUTDto respuesta = LoginService.getDataLogin();
            autorizador = respuesta.getToken();
            idSesion = respuesta.getIdSesion();
            codVendedor = respuesta.getUsuario().getCodigo();
            codMunicipio = respuesta.getUsuario().getCodigoMunicipio();
            codOficina = respuesta.getUsuario().getCodigoOficina();
            tipoPuntoVenta = respuesta.getTerminal().getTipoPuntoVenta();
            codPuntoVenta = respuesta.getTerminal().getCodPuntoVenta();

            serie1 = respuesta.papelerias.get(0).getSerie1();
            serie2 = respuesta.papelerias.get(0).getSerie2();



        }
    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    /*
     * Se crea el una una instancia del dto para inicializar los valores
     *
     * */

    public RecargaPaqueteINDto initValue(){

        RecargaPaqueteINDto recarga1 = new RecargaPaqueteINDto();
        recarga1.setRecarga(new RechargeDto());

        recarga1.getRecarga().setNumero("3006593822");
        recarga1.getRecarga().setCodOperador("4");
        recarga1.getRecarga().setCodPaquete("50129");
        recarga1.getRecarga().setMontoRecarga("3000");

        recarga1.setSerie1(serie1);
        recarga1.setSerie2(serie2);
        recarga1.setIdTransaccion(fechaActual());
        recarga1.setCodigoMuncipio(codMunicipio);
        recarga1.setCodigoOficina(codOficina);
        recarga1.setCodigoPuntoVenta(codPuntoVenta);
        recarga1.setTipoPuntoVenta(tipoPuntoVenta);
        recarga1.setCodigoVendedor(codVendedor);
        recarga1.setCodigoProducto("06");
        recarga1.setValor(3000);
        recarga1.setCanalId(canalId);
        recarga1.setCodigoTerminal(codTerminal);
        recarga1.setIdTerminal(idTerminal);
        recarga1.setTipoUsuario(tipoUsuario);
        recarga1.setSecuenciaTransaccion(3);
        recarga1.setTransactionTime(Calendar.getInstance().getTimeInMillis()+"");
        recarga1.setOperacionTransaccion(operacionTransaccion);
        recarga1.setSeriePrefijo(serie1);
        recarga1.setSerieActual(serie2);



        return recarga1;
    }

    /* ---------------------------- PAQUETES CLARO ------------------------------- */

    /*
     * El sendRechargePackageClaro1Test prueba que la recarga de claro Todo Incluido - 6 x 6  fue realizada con exito
     *
     * */

    @Test
    public void sendRechargePackageClaro1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();



        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageClaro2Test prueba que la recarga de claro Todo Incluido - 7 x 7  fue realizada con exito
     *
     * */


    @Test
    public void sendRechargePackageClaro2Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("4");
        rechargePackage.getRecarga().setCodPaquete("50130");
        rechargePackage.getRecarga().setMontoRecarga("5000");
        rechargePackage.setValor(5000);


        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }



    /*
     * El sendRechargePackageClaro3Test prueba que la recarga de claro Navegar ilimi (2 hrs)  fue realizada con exito
     *
     * */


    @Test
    public void sendRechargePackageClaro3Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("4");
        rechargePackage.getRecarga().setCodPaquete("50065");
        rechargePackage.getRecarga().setMontoRecarga("10000");
        rechargePackage.setValor(10000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }


    /* ----------------------------- PAQUETES MOVISTAR ------------------------------------- */


    /*
     * El sendRechargePackageMovistar1Test prueba que la recarga de movistar Combo prepago 10GB min y SMS ilim a To  fue realizada con exito
     *
     * */


    @Test
    public void sendRechargePackageMovistar1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("6");
        rechargePackage.getRecarga().setCodPaquete("5502066380000");
        rechargePackage.getRecarga().setMontoRecarga("17000");
        rechargePackage.setValor(17000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageMovistar2Test prueba que la recarga de movistar
     *  Paquete LDI: 80 Minutos a Chile. Peru.  fue realizada con exito
     * */


    @Test
    public void sendRechargePackageMovistar2Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("6");
        rechargePackage.getRecarga().setCodPaquete("5502066420000");
        rechargePackage.getRecarga().setMontoRecarga("30000");
        rechargePackage.setValor(30000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageMovistar3Test prueba que la recarga de movistar
     *  Navegacion Full Ilimitada con vigencia  fue realizada con exito
     * */


    @Test
    public void sendRechargePackageMovistar3Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("6");
        rechargePackage.getRecarga().setCodPaquete("5502062970000");
        rechargePackage.getRecarga().setMontoRecarga("10000");
        rechargePackage.setValor(10000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }


    /* ------------------------- PAQUETES TIGO ----------------------------------- */

    /*
     * El sendRechargePackageTigo1Test prueba que la recarga de tigo
     * 10 dias o 3GB + Minutos y SMS Ilimitad  fue realizada con exito
     * */


    @Test
    public void sendRechargePackageTigo1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("5");
        rechargePackage.getRecarga().setCodPaquete("7702138144146");
        rechargePackage.getRecarga().setMontoRecarga("11000");
        rechargePackage.setValor(11000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageTigo2Test prueba que la recarga de tigo
     * Minutos Ilimitado TD fue realizada con exito
     * */


    @Test
    public void sendRechargePackageTigo2Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("5");
        rechargePackage.getRecarga().setCodPaquete("7702138140308");
        rechargePackage.getRecarga().setMontoRecarga("4900");
        rechargePackage.setValor(4900);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }


    /*
     * El sendRechargePackageAvantel1Testt prueba que la recarga de tigo
     * 1dia o 5Min LDI  fue realizada con exito
     * */


    @Test
    public void sendRechargePackageTigo3Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("5");
        rechargePackage.getRecarga().setCodPaquete("7702138140346");
        rechargePackage.getRecarga().setMontoRecarga("3100");
        rechargePackage.setValor(3100);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /* ------------------------- PAQUETES AVANTEL ----------------------------------- */


    /*
     * El sendRechargePackageAvantel1Testt prueba que la recarga de avantel
     * Min Ilimitados+500MB+6Dias+Noches Grat  fue realizada con exito
     * */
    /*
    @Test
    public void sendRechargePackageAvantel1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("9");
        rechargePackage.getRecarga().setCodPaquete("5504003900000");
        rechargePackage.getRecarga().setMontoRecarga("6000");
        rechargePackage.setValor(6000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("exito", equalTo(true));
    }


     * El sendRechargePackageAvantel2Test prueba que la recarga de avantel
     * 250Min+ 2.048MB +30Dias+ WS+NochesGrat  fue realizada con exito
     *
    @Test
    public void sendRechargePackageAvantel2Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("9");
        rechargePackage.getRecarga().setCodPaquete("5504003960000");
        rechargePackage.getRecarga().setMontoRecarga("20000");
        rechargePackage.setValor(20000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }


     * El sendRechargePackageAvantel3Test prueba que la recarga de avantel
     * 40 Min + Whatsapp Ilimitado  fue realizada con exito
     *
    @Test
    public void sendRechargePackageAvantel3Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("9");
        rechargePackage.getRecarga().setCodPaquete("5504002610000");
        rechargePackage.getRecarga().setMontoRecarga("3000");
        rechargePackage.setValor(3000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

     */



    /* ------------------------- PAQUETES ETB ----------------------------------- */


    /*
     * El sendRechargePackageEtb1Test prueba que la recarga de ETB
     * 4GB+80 MinTD+400 Min WS+500MB(FB+YT+TW   fue realizada con exito
     * */
    @Test
    public void sendRechargePackageEtb1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("14");
        rechargePackage.getRecarga().setCodPaquete("5514003100000");
        rechargePackage.getRecarga().setMontoRecarga("20000");
        rechargePackage.setValor(20000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageEtb2Test prueba que la recarga de ETB
     * 16GB+300 MinTD+MinIlim(WS+FB)+100 SMS.  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageEtb2Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("14");
        rechargePackage.getRecarga().setCodPaquete("5514003200000");
        rechargePackage.getRecarga().setMontoRecarga("55000");
        rechargePackage.setValor(55000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageEtb3Test prueba que la recarga de ETB
     * Paquete Mixto 2GB  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageEtb3Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("14");
        rechargePackage.getRecarga().setCodPaquete("5514021000000");
        rechargePackage.getRecarga().setMontoRecarga("6000");
        rechargePackage.setValor(6000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /* ------------------------- PAQUETES MOVILEXITO ----------------------------------- */



    /*
     * El sendRechargePackageMovilExito1Test prueba que la recarga de MovilExito
     * XL (30 Dias 6 GB+WS+FB+Min Ilim+500 SM  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageMovilExito1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("15");
        rechargePackage.getRecarga().setCodPaquete("5521000595642");
        rechargePackage.getRecarga().setMontoRecarga("49900");
        rechargePackage.setValor(49900);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageMovilExito2Test prueba que la recarga de MovilExito
     * EXTRA (20 Dias 750 MB+WS+FB+Min Ilim+5  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageMovilExito2Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("15");
        rechargePackage.getRecarga().setCodPaquete("5521000115192");
        rechargePackage.getRecarga().setMontoRecarga("14900");
        rechargePackage.setValor(14900);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageMovilExito3Test prueba que la recarga de MovilExito
     * 7.5 GB. minutos ilimitados. Wahtsapp y  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageMovilExito3Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("15");
        rechargePackage.getRecarga().setCodPaquete("5521000595633");
        rechargePackage.getRecarga().setMontoRecarga("19900");
        rechargePackage.setValor(19900);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /* ------------------------- PAQUETES WINGS ----------------------------------- */


    /*
     * El sendRechargePackageWings1Test prueba que la recarga de Wings
     * 4GB para WhatsApp. Facebook e Instagra  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageWings1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("29");
        rechargePackage.getRecarga().setCodPaquete("5550000139919");
        rechargePackage.getRecarga().setMontoRecarga("1000");
        rechargePackage.setValor(1000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }


    /*
     * El sendRechargePackageWings2Test prueba que la recarga de Wings
     * Minutos a TDN. SMS a TON. 20GB y Whats  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageWings2Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("29");
        rechargePackage.getRecarga().setCodPaquete("5550000139916");
        rechargePackage.getRecarga().setMontoRecarga("40000");
        rechargePackage.setValor(40000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageWings3Test prueba que la recarga de Wings
     * Minutos a TDN. SMS a TON. Datos y What  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageWings3Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("29");
        rechargePackage.getRecarga().setCodPaquete("5550000139917");
        rechargePackage.getRecarga().setMontoRecarga("79900");
        rechargePackage.setValor(79900);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }


    /* ------------------------- PAQUETES WOM ----------------------------------- */

    /*
     * El sendRechargePackageWom1Test prueba que la recarga de Wom
     * Paquete Mixto 30D. Min Ilim +9GB+SMS(W  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageWom1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("31");
        rechargePackage.getRecarga().setCodPaquete("5573000001002");
        rechargePackage.getRecarga().setMontoRecarga("21000");
        rechargePackage.setValor(21000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }


    /*
     * El sendRechargePackageWom2Test prueba que la recarga de Wom
     * Paquete DATOS 3D. 500MB+SMS(Whatsapp+F  fue realizada con exito
     * */
    @Test
    public void sendRechargePackageWom2Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("31");
        rechargePackage.getRecarga().setCodPaquete("5573000001005");
        rechargePackage.getRecarga().setMontoRecarga("3500");
        rechargePackage.setValor(3500);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /*
     * El sendRechargePackageWom3Test prueba que la recarga de Wom
     * Paquete Mixto 10D. Min Ilim +2.5 GB+SM fue realizada con exito
     * */
    @Test
    public void sendRechargePackageWom3Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("31");
        rechargePackage.getRecarga().setCodPaquete("5573000001010");
        rechargePackage.getRecarga().setMontoRecarga("7500");
        rechargePackage.setValor(7500);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

    /* ------------------------- PAQUETES KALLEY ----------------------------------- */

    /*
     * El sendRechargePackageKelly1Test prueba que la recarga de Kalley
     * Min Ilimitados. SMS Ilimitados. 1GB y fue realizada con exito
     * */
    @Test
    public void sendRechargePackageKelly1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("25");
        rechargePackage.getRecarga().setCodPaquete("5504002640005");
        rechargePackage.getRecarga().setMontoRecarga("3000");
        rechargePackage.setValor(3000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }


    /*
     * El sendRechargePackageKelly2Test prueba que la recarga de Kalley
     * 600 Min. SMS Ilimitados y Chat de What fue realizada con exito
     * */
    @Test
    public void sendRechargePackageKelly2Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("25");
        rechargePackage.getRecarga().setCodPaquete("5504002640010");
        rechargePackage.getRecarga().setMontoRecarga("10000");
        rechargePackage.setValor(10000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }


    /* ------------------------- PAQUETES UNICORNMOBILE ----------------------------------- */

    /*
     * El sendRechargePackageUnicornMobile1Test prueba que la recarga de UNICORNMOBILE
     * SMS a TON. 12GB y Chat de WhatsApp y M fue realizada con exito
     *
    @Test
    public void sendRechargePackageUnicornMobile1Test() {

        RecargaPaqueteINDto rechargePackage = initValue();

        rechargePackage.setRecarga(new RechargeDto());

        rechargePackage.getRecarga().setNumero("3006593822");
        rechargePackage.getRecarga().setCodOperador("27");
        rechargePackage.getRecarga().setCodPaquete("5560000033009");
        rechargePackage.getRecarga().setMontoRecarga("25000");
        rechargePackage.setValor(25000);

        String jsonBody = new GsonBuilder().create().toJson(rechargePackage);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("realizarRecarga")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("0"));
    }

     */

}
