package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.consultCategoriasConvenio.ConsultParemetersDto;
import dto.LoginOUTDto;
import dto.consultparameters.ConsultaParametrosOutDTO;
import dto.consultparameters.Loteria;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import process.LoginService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConsultParameters {

    String autorizador;
    String idSesion;



    public ConsultParameters() {
        {
            RestAssured.baseURI="http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/";
            RestAssured.basePath="administracion/";
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .build();

            LoginOUTDto respuesta = LoginService.getDataLogin();
            autorizador = respuesta.getToken();
            idSesion = respuesta.getIdSesion();


        }
    }

    public ConsultParemetersDto initValue(){

        ConsultParemetersDto consult = new ConsultParemetersDto();

        consult.setCodigoVendedor("222230");
        consult.setCanalId("PVMA");
        consult.setCodigoTerminal("862634041369599");
        consult.setTipoUsuario("VENDEDOR");
        consult.setProductos("00");

        return consult;
    }

    /*
    * ConsultParametersTest prueba que la peticion responda con exito
    */

    @Test
    public void consultParametersTest(){

        ConsultParemetersDto consultAllParemeters = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultAllParemeters);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales")
                .then()
                .statusCode(200)
                .body("exito", equalTo(true));
    }
    /*
     * consultMotivosAnulacionTest prueba que el atributo de "motivosAnulacion"
     * no llegue null o vacio
     */


    @Test
    public void consultMotivosAnulacionTest(){

        ConsultParemetersDto consultlstModalidades = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultlstModalidades);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales")
                .then()
                .statusCode(200)
                .body("motivosAnulacion", both(not(empty())).and(notNullValue()));
    }


    /*
     * consultLoteriasTest prueba que el atributo de "Loterias"
     * no llegue null o vacio
     */
    @Test
    public void consulLoteriasTest(){

        ConsultParemetersDto consultlstModalidades = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultlstModalidades);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales")
                .then()
                .statusCode(200)
                .body("loterias", both(not(empty())).and(notNullValue()));
    }
    /*
    @Test
    public void consultlstlstLoterias10Test(){

        ConsultParemetersDto consultlstLoterias = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultlstLoterias );

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametros")
                .then()
                .body("lstLoterias.codigo.find { it.equals('10') }", equalTo("10"));
                //.body("lstLoterias.codigo", both(not(empty())).and(notNullValue()));

    }
    @Test
    public void consultlstlstLoterias12Test(){

        ConsultParemetersDto consultlstLoterias = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultlstLoterias);

        String response  = given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametros")
                .then()
                .body("lstLoterias.codigo.find { it.equals('12') }", equalTo("12"))
                .extract().asString();

    }

     */


    /*
     * consultlstLoteriasValuesTest prueba que el atributo de "Loterias" dentro de su objeto
     * el atributo codLoterias siempre contenga el valor de 10, 12 o 51
     */

    @Test
    public void consultlstLoteriasValuesTest(){

        ConsultParemetersDto consultlstLoterias = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultlstLoterias );

        Response response  = given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales");

        ConsultaParametrosOutDTO respuestaBody =  response.getBody().as(ConsultaParametrosOutDTO.class);

        boolean exitoso = false;

        for(int i = 0; i<respuestaBody.getLoterias().size();i++){
                Loteria loteria = respuestaBody.getLoterias().get(i);

            if(loteria.getCodLoteria().equals("10") || loteria.getCodLoteria().equals("12") || loteria.getCodLoteria().equals("51")){
                exitoso = true;
                break;
            }
        }

        Assert.assertTrue(exitoso);

    }

    /*
     * consultLoteriasTest prueba que el atributo de "Parametros"
     * no llegue null o vacio
     */
    @Test
    public void consultParametrosTest(){

        ConsultParemetersDto consultmParametros = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultmParametros );

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales")
                .then()
                .statusCode(200)
                .body("parametros", both(not(empty())).and(notNullValue()));
    }
    /*
     * codVendedorEmptyTest prueba el campo "codigoVendedor" cuando su valor es vacio
     */
    @Test
    public void codVendedorEmptyTest(){

        ConsultParemetersDto codVendedorEmpty = initValue();

        codVendedorEmpty.setCodigoVendedor("");

        String jsonBody = new GsonBuilder().create().toJson(codVendedorEmpty);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales")
                .then()
                //.statusCode(200)
                .body("mensaje",equalTo("Sesion no encontrada."));
    }

    /*
     * canalIdEmptyTest prueba el campo "canalId" cuando su valor es vacio
     */
    @Test
    public void canalIdEmptyTest(){

        ConsultParemetersDto canalIdEmpty = initValue();

        canalIdEmpty.setCanalId("");

        String jsonBody = new GsonBuilder().create().toJson(canalIdEmpty);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales")
                .then()
                .statusCode(204);
    }

    /*
     * codTerminalEmptyTest prueba el campo "codigoTerminal" cuando su valor es vacio
     */
    @Test
    public void codTerminalEmptyTest(){

        ConsultParemetersDto codTerminalEmpty = initValue();

        codTerminalEmpty.setCodigoTerminal("");

        String jsonBody = new GsonBuilder().create().toJson(codTerminalEmpty);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales")
                .then()
                .statusCode(200)
                .body("mensaje",equalTo("Solicitud incorrecta. : codigoTerminal es requerido"));
    }

    /*
     * typeUserEmptyTest prueba el campo "typeUser" cuando su valor es vacio
     */
    /*
    @Test
    public void typeUserEmptyTest(){

        ConsultParemetersDto codTerminalEmpty = initValue();

        codTerminalEmpty.setTipoUsuario("");

        String jsonBody = new GsonBuilder().create().toJson(codTerminalEmpty);

                given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("99"));
    }

    /*
     * typeUserEmptyTest prueba el campo "typeUser" cuando su valor es diferente al requerido
     */
    /*
    @Test
    public void typeUserChangeValueTest(){

        ConsultParemetersDto typeUserChangeValue = initValue();

        typeUserChangeValue.setTipoUsuario("SELECT * FROM vendedor");

        String jsonBody = new GsonBuilder().create().toJson(typeUserChangeValue);

        given()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post("consultarParametrosGenerales")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("99"));
    }

     */



}

