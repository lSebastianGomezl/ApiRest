package pvma_cor;

import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.calcularconceptos.CalcularConceptosOUTDto;
import dto.consultargiro.ConsultarGiroINDto;
import dto.consultargirocriterio.response.ConsultGiroCriterioOUTDto;
import dto.giros.giroscaptacion.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import process.LoginService;
import process.calcularconceptos.CalcularConceptosService;
import process.consultargirocriterio.ConsultarGiroCriterioService;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class ConsultarGiros {

    /* -------  USUARIO ------- */

    String usuarioU = "9865308";
    String clave = "XXXXX";
    String agencia = "2530705";
    String agenciaNombre = "GELSA CVS CUCUTA|GRUPO EMPRESARIAL EN LINEA SA";
    String agenciaDireccion = "GELSA CVS CUCUTA|GRUPO EMPRESARIAL EN LINEA SA";
    int caducacidadClave = 0 ;

    /* --------- Agencia Destino */

    String codigo = "4229";
    String nombre = "PAGATODO RE BOCHICA BAHIA  AAAA";

    /*-----------Cliente Origen ------------------*/

    String tipoIdentificacion = "CC";
    String identificacion = "122333";
    String primerNombre = "LOLA";
    String primerApellido = "LOPEZ";
    String segundoApellido = "PURITANA";
    String direccion = "CL 123";
    String email = "mauricio.quiroz@sured.com.co";
    String telefono = "5252525";
    String celular = "3054358999";
    Boolean enrolado = false;
    Boolean exoneradoHuella = true;
    String huella = "EXONERATE";
    Boolean remitente = false;

    /* -------------- Cliente Destino ----------------- */

    String tipoIdClienteDestino = "CC";
    String idClienteDestino = "777777";
    String primerNombreClienteDestino = "MONICA";
    String primerApellidoClienteDestino = "LOAIZA";
    String segundoApellidoClienteDestino = "null";
    String direccionClienteDestino = "CALLE 13 22";
    String emailClienteDestino = "MONICA.LOAIZA@CODESA.COM.CO";
    String telefonoClineteDestino = "6806656";
    String celularClineteDestino = "300279226";
    Boolean enroladoClineteDestino = false;
    Boolean exoneradoHuellaClienteDestino = false;
    Boolean remitenteClienteDestino = false;

    /* ------------ Caja ---------- */

    String caja = "0000";

    /*------- CONCEPTOS ------ */

    /* ------ CONCEPTO1 ------ */

    String codigoConcepto1 = "3";
    String nombreConcepto1 = "FALTA";
    int valorConcepto1;

    /* ------- CONCEPTO2 ------- */
    String codigoConcepto2 = "2";
    String nombreConcepto2 = "ENVIO";
    int valorConcepto2;



    String autorizador;
    String idSesion;
    String serie1;
    String serie2;
    String direccionMac = "192.168.252.28";
    String codigoVendedor;
    String codigoTerminal = "862634041369599";
    String codigoProducto = "301";
    //Boolean incluyeFlete = false;

    Boolean incluyeOtro = false;
    /*Valor con flete*/
    int valor = 80000;

    /*Valor sin flete el valor de la apuesta mas 6000*/
    int valorSinFlete = 86000;
    String canal = "PVMA";
    String tipoUsuario = "VENDEDOR";
    String operacionTransaccion = "CONSULTAR_GIRO";

    String numeroReferencia;

    String cajaValor = "0000";

    public ConsultarGiros() {
        {
            RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/giros";
            RestAssured.basePath = "/consultarGiro";
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

            ConsultGiroCriterioOUTDto respuestaConsulta = ConsultarGiroCriterioService.consultarConceptosData(autorizador, idSesion, codigoVendedor);
            System.out.println("El RESPONSE" + respuestaConsulta);

            String codAgenOrigen;
            String nomAgenOrigen;

            codAgenOrigen = respuestaConsulta.getGiros().get(0).getAgenciaOrigen().getCodigo();
            System.out.println("Codigo agencia" + codAgenOrigen);

            nomAgenOrigen = respuestaConsulta.getGiros().get(0).getAgenciaOrigen().getNombre();
            System.out.println("Nombre agencia " + nomAgenOrigen);


            numeroReferencia = respuestaConsulta.getGiros().get(0).getNumeroReferencia();
            System.out.println("Este es el numero de referencia" + numeroReferencia);

            int valorGiros;
            valorGiros = respuestaConsulta.getGiros().get(0).getValorGiro();
            System.out.println(valorGiros);

        }
    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public ConsultarGiroINDto initValue() {

        ConsultarGiroINDto consultGiros = new ConsultarGiroINDto();

        Usuario usuario = new Usuario();

        usuario.setUsuario(usuarioU);
        usuario.setClave(clave);
        usuario.setAgencia(agencia);
        usuario.setCaducidadClave(caducacidadClave);

        Caja caja = new Caja();

        caja.setCodCaja(cajaValor);
        consultGiros.setUsuario(usuario);
        consultGiros.setCaja(caja);


        consultGiros.setReferencia(numeroReferencia);
        consultGiros.setDireccionMac(direccionMac);
        consultGiros.setCodigoVendedor(codigoVendedor);
        consultGiros.setCodigoProducto(codigoProducto);
        consultGiros.setCanalId(canal);
        consultGiros.setCodigoTerminal(codigoTerminal);
        consultGiros.setTipoUsuario(tipoUsuario);
        consultGiros.setOperacionTransaccion(operacionTransaccion);

        return consultGiros;

    }



    /*ConsultarGirosSuccessful prueba que la respuesta del servicio sea
     * exitosa*/

    @Test
    public void consultarGirosSuccessful() {


        ConsultarGiroINDto consultarGiro = initValue();

        consultarGiro.setReferencia(numeroReferencia);

        String jsonBody = new GsonBuilder().create().toJson(consultarGiro);

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

    /*girosCaptacionSuccessful prueba que la respuesta del servicio sea
     * sin numero de referencia*/

    @Test
    public void ConsultarGirosSinReferencia() {


        ConsultarGiroINDto consultarGiro = initValue();
        consultarGiro.setReferencia("*611#");

        String jsonBody = new GsonBuilder().create().toJson(consultarGiro);

        given()
                .when()
                .header("Autorizador", autorizador)
                .header("id-Sesion", idSesion)
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(false));

    }


}
