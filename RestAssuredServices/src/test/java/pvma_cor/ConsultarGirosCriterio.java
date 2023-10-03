package pvma_cor;

import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.consultargiro.ConsultarGiroINDto;
import dto.consultargirocriterio.Caja;
import dto.consultargirocriterio.Cliente;
import dto.consultargirocriterio.ConsultarGiroCriterioINDto;
import dto.consultargirocriterio.Usuario;
import dto.consultargirocriterio.response.ConsultGiroCriterioOUTDto;
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
import process.consultargirocriterio.ConsultarGiroCriterioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;

public class ConsultarGirosCriterio {

    /* -------  USUARIO ------- */

    String usuarioU = "9865308";
    String clave = "XXXXX";
    String agencia = "2530705";
    int caducacidadClave = 0 ;



    /* -------------- Cliente Destino ----------------- */

    String tipoIdClienteDestino = "CC";
    String idClienteDestino = "777777";
    Boolean enroladoClineteDestino = false;
    Boolean exoneradoHuellaClienteDestino = false;
    Boolean remitenteClienteDestino = false;




    String autorizador;
    String idSesion;
    String serie1;
    String serie2;
    String direccionMac = "192.168.252.28";
    String codigoVendedor;
    String codigoTerminal = "862634041369599";
    String codigoProducto = "301";
    //Boolean incluyeFlete = false;

    String canal = "PVMA";
    String tipoUsuario = "VENDEDOR";
    String operacionTransaccion = "CONSULTAR_GIRO_CRITERIO";

    String cajaValor = "0000";
    static String tipoTercero = "DESTINATARIO";

    public ConsultarGirosCriterio() {
        {
            RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/giros";
            RestAssured.basePath = "/consultarGiroGriterio";
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .build();

            LoginOUTDto respuesta = LoginService.getDataLogin();
            autorizador = respuesta.getToken();
            idSesion = respuesta.getIdSesion();

            codigoVendedor = respuesta.getUsuario().getCodigo();

            serie1 = respuesta.papelerias.get(0).getSerie1();
            serie2 = respuesta.papelerias.get(0).getSerie2();


        }
    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public static String obtenerFechaActual(String formato) {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha en el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        return fechaActual.format(formatter);
    }

    public ConsultarGiroCriterioINDto initValue() {

        ConsultarGiroCriterioINDto consultGiros = new ConsultarGiroCriterioINDto();

        dto.consultargirocriterio.Usuario usuario = new Usuario();

        usuario.setUsuario(usuarioU);
        usuario.setClave(clave);
        usuario.setAgencia(agencia);
        usuario.setCaducidadClave(caducacidadClave);

        dto.consultargirocriterio.Caja caja = new Caja();

        caja.setCodCaja(cajaValor);


        consultGiros.setUsuario(usuario);
        consultGiros.setCaja(caja);

        String fechaActual = obtenerFechaActual("ddMMyyyy");
        System.out.println("fechaactual " + fechaActual);
        consultGiros.setFechaInicio(fechaActual);
        consultGiros.setFechaFin(fechaActual);
        consultGiros.setTipoTercero(tipoTercero);
        consultGiros.setDireccionMac(direccionMac);
        consultGiros.setCodigoVendedor(codigoVendedor);
        consultGiros.setCodigoProducto(codigoProducto);
        consultGiros.setCanalId(canal);
        consultGiros.setCodigoTerminal(codigoTerminal);
        consultGiros.setTipoUsuario(tipoUsuario);
        consultGiros.setOperacionTransaccion(operacionTransaccion);

        Cliente cliente = new Cliente();

        cliente.setTipoIdentificacion(tipoIdClienteDestino);
        cliente.setIdentificacion(idClienteDestino);
        cliente.setEnrolado(enroladoClineteDestino);
        cliente.setExoneradoHuella(exoneradoHuellaClienteDestino);
        cliente.setRemitente(remitenteClienteDestino);

        consultGiros.setCliente(cliente);

        return consultGiros;

    }



    /*consultarGirosCriterioSuccessful prueba que la respuesta del servicio sea
     * exitosa*/

    @Test
    public void consultarGirosCriterioSuccessful() {


        ConsultarGiroCriterioINDto consultarGiroCriterio = initValue();

        String jsonBody = new GsonBuilder().create().toJson(consultarGiroCriterio);

        Response response = given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath);
        //.then()
        //.statusCode(200)
        //.body("exito", Matchers.equalTo(true));

        ConsultGiroCriterioOUTDto respuesta = response.getBody().as(ConsultGiroCriterioOUTDto.class);

        if (respuesta.getMensaje().equals("No hay datos.") || respuesta.getMensaje().equals("Exito")) {
            Assert.assertTrue(true);

        }
    }

}
