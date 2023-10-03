package pvma_cor;

import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.calcularconceptos.CalcularConceptosOUTDto;
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

import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class GirosCaptacion {

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
    String operacionTransaccion = "VENTA";

    public GirosCaptacion() {
        {
            RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/giros";
            RestAssured.basePath = "/captarGiro";
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

    public GirosCaptacionINDto initValue() {

        GirosCaptacionINDto girosCaptacion = new GirosCaptacionINDto();


        Usuario usuario = new Usuario();

        usuario.setUsuario(usuarioU);
        usuario.setClave(clave);
        usuario.setAgencia(agencia);
        usuario.setAgenciaNombre(agenciaNombre);
        usuario.setAgenciaDireccion(agenciaDireccion);
        usuario.setCaducidadClave(caducacidadClave);

        AgenciaDestino agenciaDestino = new AgenciaDestino();

        agenciaDestino.setCodigo(codigo);
        agenciaDestino.setNombre(nombre);

        ClienteOrigen clienteOrigen = new ClienteOrigen();

        clienteOrigen.setTipoIdentificacion(tipoIdentificacion);
        clienteOrigen.setIdentificacion(identificacion);
        clienteOrigen.setPrimerNombre(primerNombre);
        clienteOrigen.setPrimerApellido(primerApellido);
        clienteOrigen.setSegundoApellido(segundoApellido);
        clienteOrigen.setDireccion(direccion);
        clienteOrigen.setEmail(email);
        clienteOrigen.setTelefono(telefono);
        clienteOrigen.setCelular(celular);
        clienteOrigen.setEnrolado(enrolado);
        clienteOrigen.setExoneradoHuella(true);
        clienteOrigen.setHuella(huella);
        clienteOrigen.setRemitente(remitente);

        ClienteDestino clienteDestino = new ClienteDestino();

        clienteDestino.setTipoIdentificacion(tipoIdClienteDestino);
        clienteDestino.setIdentificacion(idClienteDestino);
        clienteDestino.setPrimerNombre(primerNombreClienteDestino);
        clienteDestino.setSegundoApellido(segundoApellidoClienteDestino);
        clienteDestino.setDireccion(direccionClienteDestino);
        clienteDestino.setEmail(emailClienteDestino);
        clienteDestino.setTelefono(telefonoClineteDestino);
        clienteDestino.setCelular(celularClineteDestino);
        clienteDestino.setEnrolado(enroladoClineteDestino);
        clienteDestino.setExoneradoHuella(exoneradoHuellaClienteDestino);
        clienteDestino.setRemitente(remitenteClienteDestino);

        Caja caja1 =  new Caja();

        caja1.setCodCaja(caja);


        ArrayList<Concepto> conceptos = new ArrayList();

        Concepto concepto1 = new Concepto();

        concepto1.setCodigo(codigoConcepto1);
        concepto1.setNombre(nombreConcepto1);
        concepto1.setValor(valorConcepto1);


        Concepto concepto2 = new Concepto();

        concepto2.setCodigo(codigoConcepto2);
        concepto2.setNombre(nombreConcepto2);
        concepto2.setValor(valorConcepto2);

        conceptos.add(concepto1);
        conceptos.add(concepto2);


        girosCaptacion.setUsuario(usuario);
        girosCaptacion.setClienteOrigen(clienteOrigen);
        girosCaptacion.setAgenciaDestino(agenciaDestino);
        girosCaptacion.setClienteDestino(clienteDestino);
        girosCaptacion.setCaja(caja1);
        girosCaptacion.setConceptos(conceptos);


        girosCaptacion.setSerie1(serie1);
        girosCaptacion.setSerie2(serie2);


        girosCaptacion.setIncluyeOtro(incluyeOtro);
        girosCaptacion.setDireccionMac(direccionMac);
        girosCaptacion.setCodigoVendedor(codigoVendedor);
        girosCaptacion.setCodigoProducto(codigoProducto);
        girosCaptacion.setValor(valor);
        girosCaptacion.setCanalId(canal);
        girosCaptacion.setCodigoTerminal(codigoTerminal);
        girosCaptacion.setTipoUsuario(tipoUsuario);
        girosCaptacion.setOperacionTransaccion(operacionTransaccion);
        girosCaptacion.setSeriePrefijo(serie1);
        girosCaptacion.setSerieActual(serie2);

        return girosCaptacion;

    }



    /*girosCaptacionSuccessful prueba que la respuesta del servicio sea
     * exitosa con flete*/

    @Test
    public void girosCaptacionSuccessful() {
        Boolean incluyeFlete = true;
        Boolean incluyeDomicilio =false;

        CalcularConceptosOUTDto respuestaConceptos = CalcularConceptosService.consultarConceptosData(autorizador,idSesion,codigoVendedor, incluyeFlete, incluyeDomicilio);
        System.out.println("El RESPONSE"+ respuestaConceptos);

        valorConcepto1 = respuestaConceptos.conceptos.get(0).getValor();
        System.out.println("Este es el valor 1"+valorConcepto1);
        valorConcepto2 = respuestaConceptos.conceptos.get(1).getValor();
        System.out.println("Este es el valor 2"+valorConcepto2);

        GirosCaptacionINDto girosCaptacion = initValue();

        girosCaptacion.setIncluyeFlete(incluyeFlete);


        String jsonBody = new GsonBuilder().create().toJson(girosCaptacion);

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
     * exitosa sin flete*/

    @Test
    public void girosCaptacionSinFleteSuccessful() {

        Boolean incluyeFlete = false;

        Boolean incluyeDomicilio = false;

        CalcularConceptosOUTDto respuestaConceptos = CalcularConceptosService.consultarConceptosData(autorizador,idSesion,codigoVendedor, incluyeFlete, incluyeDomicilio);
        System.out.println("El RESPONSE"+ respuestaConceptos);

        valorConcepto1 = respuestaConceptos.conceptos.get(0).getValor();
        System.out.println("Este es el valor 1"+valorConcepto1);
        valorConcepto2 = respuestaConceptos.conceptos.get(1).getValor();
        System.out.println("Este es el valor 2"+valorConcepto2);

        GirosCaptacionINDto girosCaptacion = initValue();

        girosCaptacion.setIncluyeFlete(incluyeFlete);
        girosCaptacion.setValor(valorSinFlete);
        String jsonBody = new GsonBuilder().create().toJson(girosCaptacion);

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

    /*girosCaptacionConDomicilioSuccessful prueba que la respuesta del servicio sea
     * exitosa con domicilio

    @Test
    public void girosCaptacionConDomicilioSuccessful() {

        Boolean incluyeFlete = false;

        Boolean incluyeDomicilio = true;

        CalcularConceptosOUTDto respuestaConceptos = CalcularConceptosService.consultarConceptosData(autorizador,idSesion,codigoVendedor, incluyeFlete, incluyeDomicilio);
        System.out.println("El RESPONSE"+ respuestaConceptos);

        valorConcepto1 = respuestaConceptos.conceptos.get(0).getValor();
        System.out.println("Este es el valor 1"+valorConcepto1);
        valorConcepto2 = respuestaConceptos.conceptos.get(1).getValor();
        System.out.println("Este es el valor 2"+valorConcepto2);

        GirosCaptacionINDto girosCaptacion = initValue();

        girosCaptacion.setIncluyeFlete(incluyeFlete);
        girosCaptacion.setIncluyeDomicilio(incluyeDomicilio);
        girosCaptacion.setValor(valorSinFlete);
        String jsonBody = new GsonBuilder().create().toJson(girosCaptacion);

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

     */


}
