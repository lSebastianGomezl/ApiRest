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

public class ConsultCategoriasConvenioFiltro {

    String autorizador;
    String idSesion;



    public ConsultCategoriasConvenioFiltro() {
        {
            RestAssured.baseURI="http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/recaudoGenerico/consultarConveniosPorFiltro";
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .build();



        }
    }

    public ConsultParemetersDto initValue(){

        ConsultParemetersDto consult = new ConsultParemetersDto();

        consult.setCanalId("PC");
        consult.setFiltroBusqueda("IGA");
        consult.setIdCategoria("7");

        return consult;
    }

    /*
    * consultCategoriasConvenioFiltro prueba que la peticion responda con exito
    */

    @Test
    public void consultCategoriasConvenioFiltro(){

        ConsultParemetersDto consultCategoriaConvenios = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultCategoriaConvenios);

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI)
                .then()
                .body("categoriasConvenios.7.size()",equalTo(3));
/*
        ColsutConvenioFiltroOUTDto respuesta = response.getBody().as(ColsutConvenioFiltroOUTDto.class);
        System.out.println(respuesta);

        for (int i = 0; i < respuesta.getCategoriasConvenios().get_7().size(); i++){
            if (respuesta.getCategoriasConvenios().get_7().get(i).get){
                System.out.println("hola" + respuesta.getCategoriasConvenios().get_7().get(i).getCodConvenio());
                Assert.assertTrue(true);
            }else
                Assert.assertTrue(false);
        }

 */
    }

    /*
     * filtroConMinCaracteres prueba que la peticion responda con exito
     */

    @Test
    public void filtroConMinCaracteres(){

        ConsultParemetersDto consult = initValue();

        consult.setCanalId("PC");
        consult.setFiltroBusqueda("iga");
        consult.setIdCategoria("7");

        String jsonBody = new GsonBuilder().create().toJson(consult);

                        given()
                        .contentType(ContentType.JSON)
                        .body(jsonBody)
                        .post(baseURI)
                        .then()
                        .body("categoriasConvenios.7.size()",equalTo(3));
    /*
        ColsutConvenioFiltroOUTDto respuesta = response.getBody().as(ColsutConvenioFiltroOUTDto.class);
        System.out.println(respuesta);

        for (int i = 0; i < respuesta.getCategoriasConvenios().get_7().size(); i++){
            if (respuesta.getCategoriasConvenios().get_7().get(i).getCodConvenio().equals("0010036702000")){
                System.out.println("hola" + respuesta.getCategoriasConvenios().get_7().get(i).getCodConvenio());
                Assert.assertTrue(true);
            }else
                Assert.assertTrue(false);
        }

     */
    }



    /*
     * filtroConCaracteresCombinados prueba que la peticion responda con exito
     */

    @Test
    public void filtroConCaracteresCombinados(){

        ConsultParemetersDto consult = initValue();

        consult.setCanalId("PC");
        consult.setFiltroBusqueda("IgA");
        consult.setIdCategoria("7");

        String jsonBody = new GsonBuilder().create().toJson(consult);

                given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI)
                .then()
                .body("categoriasConvenios.7.size()",equalTo(3));;
        /*
        ColsutConvenioFiltroOUTDto respuesta = response.getBody().as(ColsutConvenioFiltroOUTDto.class);
        System.out.println(respuesta);

        for (int i = 0; i < respuesta.getCategoriasConvenios().get_7().size(); i++){
            if (respuesta.getCategoriasConvenios().get_7().get(i).getCodConvenio().equals("0010036702000")){
                System.out.println("hola" + respuesta.getCategoriasConvenios().get_7().get(i).getCodConvenio());
                Assert.assertTrue(true);
            }else
                Assert.assertTrue(false);
        }

         */
    }


}

