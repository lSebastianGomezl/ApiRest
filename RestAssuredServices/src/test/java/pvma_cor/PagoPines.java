package pvma_cor;

import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.consultargiro.response.ConsultarGiroOUTDto;
import dto.consultargirocriterio.response.ConsultGiroCriterioOUTDto;
import dto.giros.giroscaptacion.*;
import dto.giros.pagagiros.AgenciaOrigen;
import dto.giros.pagagiros.Ciudad;
import dto.giros.pagagiros.PagaGirosINDto;
import dto.pagopines.PagoPinesINDto;
import groovy.util.logging.Log;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import process.LoginService;
import process.consultargiro.ConsultarGiroService;
import process.consultargirocriterio.ConsultarGiroCriterioService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static io.restassured.RestAssured.*;

public class PagoPines {


    String autorizador;
    String idSesion;

    public PagoPines() {
        {
            RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/ChaxGW/rest/pines";
            RestAssured.basePath = "/realizarVenta";
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .build();



        }
    }

    public static String obtenerFecha(String formato){

        LocalDateTime fecha = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

        return fecha.format(formatter);

    }

    public static String fechaActual(){

        LocalDateTime currentDateTime = LocalDateTime.now();
        Instant instant = currentDateTime.atZone(ZoneId.systemDefault()).toInstant();
        long timestampMillis = instant.toEpochMilli();
        String valorFinal = Long.toString(timestampMillis);

        return valorFinal;

    }

    public PagoPinesINDto initValue() {

        String terminal = "862634041369599";


        String serie1;
        String serie2;
        String codigoVendedor;
        String documentoIdentidad;
        String codigo;
        String telefono;
        String codMunicipio;
        String puntoVenta;
        String codOficina;
        String eanCod = "799366414803";
        String tipoUsuario = "VENDEDOR";
        String canal = "PVMA";
        String idOperador = "1";
        String channel = "API";
        String source = "CHANNEL";
        String ip = "0.0.0.0";
        String valor = "24000";
        String email = "sebastian.gomez@dcsas.com.co";


        LoginOUTDto respuesta = LoginService.getDataLogin();
        autorizador = respuesta.getToken();
        idSesion = respuesta.getIdSesion();

        codigoVendedor = respuesta.getUsuario().getCodigo();

        serie1 = respuesta.papelerias.get(0).getSerie1();
        serie2 = respuesta.papelerias.get(0).getSerie2();

        codigo = respuesta.getUsuario().getCodigo();
        documentoIdentidad = respuesta.getUsuario().getDocumentoIdentidad();
        telefono = respuesta.getUsuario().getTelefono();
        codMunicipio = respuesta.getUsuario().getCodigoMunicipio();
        puntoVenta = respuesta.getTerminal().getCodPuntoVenta();
        codOficina = respuesta.getUsuario().getCodigoOficina();

        PagoPinesINDto pagoPines = new PagoPinesINDto();

        pagoPines.setIssuerId(terminal);
        pagoPines.setIssueDate(obtenerFecha("yyyyMMddHHmmss.SSS"));
        System.out.println(obtenerFecha("yyyyMMddHHmmss.SSS"));
        pagoPines.setPhoneNumber(telefono);
        pagoPines.setIp(ip);
        pagoPines.setSource(source);
        pagoPines.setAmount(valor);
        pagoPines.setIssuerLogin(documentoIdentidad);
        pagoPines.setEmail(email);
        pagoPines.setEanCode(eanCod);
        pagoPines.setZipCode(codMunicipio);
        pagoPines.setChannel(channel);
        pagoPines.setMunicipio(codMunicipio);
        pagoPines.setOficina(codOficina);
        pagoPines.setPuntoDeVenta(puntoVenta);
        pagoPines.setCodigoAsesor(codigo);
        pagoPines.setIdOperador(idOperador);
        pagoPines.setSerie1(serie1);
        pagoPines.setSerie2(serie2);
        pagoPines.setCodigoVendedor(codigoVendedor);
        pagoPines.setCanalId(canal);
        pagoPines.setCodigoTerminal(terminal);
        pagoPines.setTipoUsuario(tipoUsuario);

        return pagoPines;

    }



    /*pagoPinesSuccessful prueba que la respuesta del servicio sea
     * exitosa pin de Colombia IMVU DDP $24000 COP*/

    @Test
    public void pagoPinesSuccessful() {

        PagoPinesINDto pagaPines = initValue();



        String jsonBody = new GsonBuilder().create().toJson(pagaPines);

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
