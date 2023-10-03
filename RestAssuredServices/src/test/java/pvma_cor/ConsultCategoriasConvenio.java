package pvma_cor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import dto.consultCategoriasConvenio.ConsultParemetersDto;
import dto.consultCategoriasConvenio.response.RGConvenioDTO;
import dto.consultCategoriasConvenio.response.RespuestaConveniosReferenciasDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConsultCategoriasConvenio {

    String autorizador;
    String idSesion;



    public ConsultCategoriasConvenio() {
        {
            RestAssured.baseURI="http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/recaudoGenerico/consultarCategoriasConvenio";
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
    * ConsultParametersTest prueba que la peticion responda con exito
    */

    @Test
    public void consultCategoriasConvenio(){

        ConsultParemetersDto consultCategoriaConvenios = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultCategoriaConvenios);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI);
                //.then()
                //.statusCode(200)
                //.body("categoriasConvenios", both(not(empty())).and(notNullValue()));

        RespuestaConveniosReferenciasDto respuesta = response.getBody().as(RespuestaConveniosReferenciasDto.class);
        System.out.println("esta es la respuesta: " + respuesta);

        //Recorrer un map por sus claves
        /*
        for(String value : respuesta.getCategoriasConvenios().keySet()){
            System.out.println("cod categoria: " + value);
        }

         */

        //Recorrer un map por sus valores
        for(List<RGConvenioDTO>  listConvenios : respuesta.getCategoriasConvenios().values()){
            System.out.println("Cant Convenios: " + listConvenios.size());
            if (listConvenios.size() == 0){
                Assert.assertTrue(false);
            }
                Assert.assertTrue(true);
        }
        /*
        //Recorrer un map por clave, valor
        for(Map.Entry<String, List<RGConvenioDTO>> contenido  : respuesta.getCategoriasConvenios().entrySet()){
            System.out.println("La categoria : " + contenido.getKey() +" tiene "+contenido.getValue().size()+ " convenios ");
        }

         */



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

