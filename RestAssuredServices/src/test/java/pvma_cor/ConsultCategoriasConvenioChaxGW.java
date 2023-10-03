package pvma_cor;

import com.google.gson.GsonBuilder;
import dto.consultCategoriasConvenio.ConsultParemetersDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConsultCategoriasConvenioChaxGW {


    public ConsultCategoriasConvenioChaxGW() {
        {
            RestAssured.baseURI="https://chaxgwpru.dcsas.com.co:8543/ChaxGW/rest/recaudoGenerico/consultarCategoriasConvenio";
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .build();

        }
    }

    public ConsultParemetersDto initValue(){

        ConsultParemetersDto consult = new ConsultParemetersDto();

        consult.setCodigoVendedor("111111");
        consult.setCanalId("PC");
        consult.setCodigoTerminal("A807-32A7");
        consult.setTipoUsuario("VENDEDOR");

        return consult;
    }

    /*
    * consultCategoriasConvenio prueba que la peticion responda con exito
    */

    @Test
    public void consultCategoriasConvenio(){

        ConsultParemetersDto consultCategoriaConvenios = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultCategoriaConvenios);

        given()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .body(jsonBody)
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("categoriasConvenios", both(not(empty())).and(notNullValue()));
    }

    /*
     * consultCategoriasConvenioCanalMin prueba que la peticion responda con exito
     */

    @Test
    public void consultCategoriasConvenioCanalMin(){

        ConsultParemetersDto consultCategoriaConvenios = initValue();

        consultCategoriaConvenios.setCanalId("pc");

        String jsonBody = new GsonBuilder().create().toJson(consultCategoriaConvenios);

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("exito", equalTo(false));
    }



    /*
     * ConsultParametersTest prueba que como responde el servicio al enviar la data vacia
     */

    @Test
    public void consultCategoriasConvenioEmptyData(){

        ConsultParemetersDto consultCategoriaConvenios = initValue();

        consultCategoriaConvenios.setCodigoVendedor("");
        consultCategoriaConvenios.setCanalId("");
        consultCategoriaConvenios.setCodigoTerminal("");
        consultCategoriaConvenios.setTipoUsuario("");

        String jsonBody = new GsonBuilder().create().toJson(consultCategoriaConvenios);

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("mensaje", equalTo("Solicitud no autorizada."));
    }


    /*
     * consultCategoriasDataIncorrect prueba la respuesta del servicio al enviar la
     * data incorrecta
     */

    @Test
    public void consultCategoriasDataIncorrect(){

        ConsultParemetersDto consultCategoriaConvenios = initValue();

        consultCategoriaConvenios.setCodigoVendedor("222485");
        consultCategoriaConvenios.setCanalId("PVA");
        consultCategoriaConvenios.setCodigoTerminal("875562412824");
        consultCategoriaConvenios.setTipoUsuario("COMPRADOR");

        String jsonBody = new GsonBuilder().create().toJson(consultCategoriaConvenios);

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("exito", equalTo(false));
    }

    /*
     * consultCategoriasDataIncorrect prueba la respuesta del servicio al enviar la
     * el usuario vacio
     */

    @Test
    public void consultCategoriasUserEmpty(){

        ConsultParemetersDto consultCategoriaConvenios = initValue();

        consultCategoriaConvenios.setCodigoVendedor("");
        consultCategoriaConvenios.setCanalId("PC");
        consultCategoriaConvenios.setCodigoTerminal("A807-32A7");
        consultCategoriaConvenios.setTipoUsuario("VENDEDOR");

        String jsonBody = new GsonBuilder().create().toJson(consultCategoriaConvenios);

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("exito", equalTo(false));
    }


    /*
     * consultCategoriasDataIncorrect prueba la respuesta del servicio al enviar la
     * el usuario vacio
     */

    @Test
    public void consultCategoriasIncomplete(){

        ConsultParemetersDto consultCategoriaConvenios = initValue();

        consultCategoriaConvenios.setCodigoVendedor("");
        consultCategoriaConvenios.setCanalId("PC");
        consultCategoriaConvenios.setCodigoTerminal("");
        consultCategoriaConvenios.setTipoUsuario("");

        String jsonBody = new GsonBuilder().create().toJson(consultCategoriaConvenios);

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI)
                .then()
                //.statusCode(200)
                .body("exito", equalTo(false));
    }

}

