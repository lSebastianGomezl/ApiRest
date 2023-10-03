package pvma_cor;

import com.google.gson.GsonBuilder;
import dto.LoginOUTDto;
import dto.consultargiro.response.ConsultarGiroOUTDto;
import dto.consultargirocriterio.response.ConsultGiroCriterioOUTDto;
import dto.giros.giroscaptacion.*;
import dto.giros.pagagiros.AgenciaOrigen;
import dto.giros.pagagiros.Ciudad;
import dto.giros.pagagiros.PagaGirosINDto;
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

import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class GirosPago {

    /* -------  USUARIO ------- */

    String usuarioU = "9865308";
    String clave = "XXXXX";
    String agencia = "2530705";
    String agenciaNombre = "GELSA CVS CUCUTA|GRUPO EMPRESARIAL EN LINEA SA";
    String agenciaDireccion = "GELSA CVS CUCUTA|GRUPO EMPRESARIAL EN LINEA SA";
    int caducacidadClave = 0 ;



    /* ------------ Caja ---------- */

    String caja = "0000";




    String numeroReferencia;
    String autorizador;
    String idSesion;
    String serie1;
    String serie2;
    String direccionMac = "192.168.252.28";
    String codigoVendedor;
    String codigoTerminal = "862634041369599";
    String codigoProducto = "301";
    String  codOficina = "15";
    String codPuntoVenta = "001";
    int valorGiros;

    String canal = "PVMA";
    String tipoUsuario = "VENDEDOR";
    String operacionTransaccion = "PAGAR_GIRO";



    public GirosPago() {
        {
            RestAssured.baseURI = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/giros";
            RestAssured.basePath = "/pagarGiro";
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

        }
    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public PagaGirosINDto initValue() {


        PagaGirosINDto pagaGiros = new PagaGirosINDto();

        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioU);
        usuario.setClave(clave);
        usuario.setAgencia(agencia);
        usuario.setAgenciaNombre(agenciaNombre);
        usuario.setAgenciaDireccion(agenciaDireccion);
        usuario.setCaducidadClave(caducacidadClave);

        pagaGiros.setUsuario(usuario);


        pagaGiros.setSerie1(serie1);
        pagaGiros.setSerie2(serie2);

        Caja caja1 =  new Caja();
        caja1.setCodCaja(caja);

        pagaGiros.setCaja(caja1);

        pagaGiros.setNotas("*");





        pagaGiros.setIdTransaccion(fechaActual());
        pagaGiros.setDireccionMac(direccionMac);
        pagaGiros.setCodigoOficina(codOficina);
        pagaGiros.setCodigoPuntoVenta(codPuntoVenta);
        pagaGiros.setCodigoVendedor(codigoVendedor);
        pagaGiros.setCodigoProducto(codigoProducto);
        pagaGiros.setCanalId(canal);
        pagaGiros.setCodigoTerminal(codigoTerminal);
        pagaGiros.setTipoUsuario(tipoUsuario);
        pagaGiros.setOperacionTransaccion(operacionTransaccion);
        pagaGiros.setSeriePrefijo(serie1);
        pagaGiros.setSerieActual(serie2);


        return pagaGiros;

    }



    /*girosCaptacionSuccessful prueba que la respuesta del servicio sea
     * exitosa con flete*/

    @Test
    public void girosPagoSuccessful() {



        ConsultarGiroOUTDto consultaResponse = ConsultarGiroService.consultarGirosData(autorizador, idSesion,
                codigoVendedor, numeroReferencia);
        System.out.println("El RESPONSE DE CONSULTAR GIROS ES: " + consultaResponse);



        valorGiros = consultaResponse.getValorGiro();

        String numeroFactura;
        String preFijoFactura;

        numeroFactura = consultaResponse.getNumeroFactura();
        System.out.println("no factura " + numeroFactura);

        preFijoFactura = consultaResponse.getPrefijoFactura();
        System.out.println("pre fijo factura " + preFijoFactura);


        /*-----------Cliente Origen ------------------*/

        String tipoIdentificacion;
        String identificacion;
        String primerNombre;
        String primerApellido;
        String segundoApellido;
        String direccion;
        String email;
        String telefono;
        String celular;
        Boolean enrolado = false;
        Boolean exoneradoHuella = true;
        Boolean remitente = false;

        tipoIdentificacion = consultaResponse.getClienteOrigen().getTipoIdentificacion();
        identificacion = consultaResponse.getClienteOrigen().getIdentificacion();
        primerNombre = consultaResponse.getClienteOrigen().getPrimerNombre();
        primerApellido = consultaResponse.getClienteOrigen().getPrimerApellido();
        segundoApellido = consultaResponse.getClienteOrigen().getSegundoApellido();
        direccion = consultaResponse.getClienteOrigen().getDireccion();
        email = consultaResponse.getClienteOrigen().getEmail();
        telefono = consultaResponse.getClienteOrigen().getTelefono();
        celular = consultaResponse.getClienteOrigen().getCelular();



        /* -------------- Cliente Destino ----------------- */

        String tipoIdClienteDestino;
        String idClienteDestino;
        String primerNombreClienteDestino;
        String primerApellidoClienteDestino;
        String segundoApellidoClienteDestino;
        String direccionClienteDestino;
        String emailClienteDestino;
        String telefonoClineteDestino;
        String celularClineteDestino;
        Boolean enroladoClineteDestino = true;
        Boolean exoneradoHuellaClienteDestino = true;
        Boolean remitenteClienteDestino = false;

        tipoIdClienteDestino = consultaResponse.getClienteDestino().getTipoIdentificacion();
        idClienteDestino = consultaResponse.getClienteDestino().getIdentificacion();
        primerNombreClienteDestino = consultaResponse.getClienteDestino().getPrimerNombre();
        primerApellidoClienteDestino = consultaResponse.getClienteDestino().getPrimerApellido();
        segundoApellidoClienteDestino = consultaResponse.getClienteDestino().getSegundoApellido();
        direccionClienteDestino = consultaResponse.getClienteDestino().getDireccion();
        emailClienteDestino = consultaResponse.getClienteDestino().getEmail();
        telefonoClineteDestino = consultaResponse.getClienteDestino().getTelefono();
        celularClineteDestino = consultaResponse.getClienteDestino().getCelular();


        String nombreOrigen;
        String codigoOrigen;

        codigoOrigen = consultaResponse.getAgenciaOrigen().getCodigo();
        nombreOrigen = consultaResponse.getAgenciaOrigen().getNombre();

        String nomCiudAgenOrig;
        String codDaneCiudOrig;
        String nomDepartCiudOrig;

        nomCiudAgenOrig = consultaResponse.getAgenciaOrigen().getCiudad().getNombreCiudad();
        codDaneCiudOrig = consultaResponse.getAgenciaOrigen().getCiudad().getCodigoDane();
        nomDepartCiudOrig = consultaResponse.getAgenciaOrigen().getCiudad().getNombreDepartamento();


        String nombreCiudDestino;
        String codiCiudDestino;

        nombreCiudDestino = consultaResponse.getAgenciaDestino().getNombre();
        codiCiudDestino= consultaResponse.getAgenciaDestino().getCodigo();

        String nomCiudAgenDest;
        String codDaneCiudDest;
        String nomDepartCiudDest;

        nomCiudAgenDest = consultaResponse.getAgenciaDestino().getCiudad().getNombreCiudad();
        codDaneCiudDest = consultaResponse.getAgenciaDestino().getCiudad().getCodigoDane();
        nomDepartCiudDest = consultaResponse.getAgenciaDestino().getCiudad().getNombreDepartamento();

        /* ------ CONCEPTO1 ------ */
        String codigoConcepto1;
        String nombreConcepto1;
        int valorConcepto1;
        codigoConcepto1 = consultaResponse.getConceptos().get(0).getCodigo();
        nombreConcepto1 = consultaResponse.getConceptos().get(0).getNombre();
        valorConcepto1 = consultaResponse.getConceptos().get(0).getValor();
        System.out.println("info 1 concepto " + codigoConcepto1 + " " + nombreConcepto1 + " " + valorConcepto1);

        /* ------- CONCEPTO2 ------- */
        String codigoConcepto2;
        String nombreConcepto2;
        int valorConcepto2;
        codigoConcepto2 = consultaResponse.getConceptos().get(1).getCodigo();
        nombreConcepto2 = consultaResponse.getConceptos().get(1).getNombre();
        valorConcepto2 = consultaResponse.getConceptos().get(1).getValor();
        System.out.println("info 2 concepto " + codigoConcepto2 + " " + nombreConcepto2 + " " + valorConcepto2);

        /*------- CONCEPTO 3 ----- */
        String codigoConcepto3;
        String nombreConcepto3;
        int valorConcepto3;
        codigoConcepto3 = consultaResponse.getConceptos().get(2).getCodigo();
        nombreConcepto3 = consultaResponse.getConceptos().get(2).getNombre();
        valorConcepto3 = consultaResponse.getConceptos().get(2).getValor();
        System.out.println("info 3 concepto " + codigoConcepto3 + " " + nombreConcepto3 + " " + valorConcepto3);




        PagaGirosINDto pagaGiros = new PagaGirosINDto();


        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioU);
        usuario.setClave(clave);
        usuario.setAgencia(agencia);
        usuario.setAgenciaNombre(agenciaNombre);
        usuario.setAgenciaDireccion(agenciaDireccion);
        usuario.setCaducidadClave(caducacidadClave);

        pagaGiros.setUsuario(usuario);


        pagaGiros.setSerie1(serie1);
        pagaGiros.setSerie2(serie2);

        Caja caja1 =  new Caja();
        caja1.setCodCaja(caja);

        pagaGiros.setCaja(caja1);

        pagaGiros.setNotas("*");





        pagaGiros.setIdTransaccion(fechaActual());
        pagaGiros.setDireccionMac(direccionMac);
        pagaGiros.setCodigoOficina(codOficina);
        pagaGiros.setCodigoPuntoVenta(codPuntoVenta);
        pagaGiros.setCodigoVendedor(codigoVendedor);
        pagaGiros.setCodigoProducto(codigoProducto);
        pagaGiros.setCanalId(canal);
        pagaGiros.setValor(valorGiros);
        pagaGiros.setCodigoTerminal(codigoTerminal);
        pagaGiros.setTipoUsuario(tipoUsuario);
        pagaGiros.setOperacionTransaccion(operacionTransaccion);
        pagaGiros.setSeriePrefijo(serie1);
        pagaGiros.setSerieActual(serie2);

        /*-----------------------*/

        pagaGiros.setReferencia(numeroReferencia);
        pagaGiros.setNumeroFactura(numeroFactura);
        pagaGiros.setPrefijoFactura(preFijoFactura);

        /*Cliente origen*/

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
        clienteOrigen.setExoneradoHuella(exoneradoHuella);
        clienteOrigen.setRemitente(remitente);

        pagaGiros.setClienteOrigen(clienteOrigen);

        /*Cliente destino */

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

        pagaGiros.setClienteDestino(clienteDestino);

        /* agencia origen*/

        AgenciaOrigen agenciaOrigen = new AgenciaOrigen();

        agenciaOrigen.setCodigo(codigoOrigen);
        agenciaOrigen.setNombre(nombreOrigen);

        /*ciudad origen*/
        Ciudad ciudadOrigen = new Ciudad();

        ciudadOrigen.setNombreCiudad(nomCiudAgenOrig);
        ciudadOrigen.setCodigoDane(codDaneCiudOrig);
        ciudadOrigen.setNombreDepartamento(nomDepartCiudOrig);

        agenciaOrigen.setCiudad(ciudadOrigen);


        /* Agencia destino */

        AgenciaDestino agenciaDestino = new AgenciaDestino();

        agenciaDestino.setNombre(nombreCiudDestino);
        agenciaDestino.setCodigo(codiCiudDestino);

        Ciudad ciudadAgenDest = new Ciudad();

        ciudadAgenDest.setNombreCiudad(nomCiudAgenDest);
        ciudadAgenDest.setCodigoDane(codDaneCiudDest);
        ciudadAgenDest.setNombreDepartamento(nomDepartCiudDest);

        agenciaDestino.setCiudad(ciudadAgenDest);
        pagaGiros.setAgenciaDestino(agenciaDestino);
        pagaGiros.setAgenciaOrigen(agenciaOrigen);

        ArrayList<Concepto> conceptos = new ArrayList();

        Concepto concepto1 = new Concepto();

        concepto1.setCodigo(codigoConcepto1);
        concepto1.setNombre(nombreConcepto1);
        concepto1.setValor(valorConcepto1);


        Concepto concepto2 = new Concepto();

        concepto2.setCodigo(codigoConcepto2);
        concepto2.setNombre(nombreConcepto2);
        concepto2.setValor(valorConcepto2);

        Concepto concepto3 = new Concepto();

        concepto3.setCodigo(codigoConcepto3);
        concepto3.setNombre(nombreConcepto3);
        concepto3.setValor(valorConcepto3);

        conceptos.add(concepto1);
        conceptos.add(concepto2);
        conceptos.add(concepto3);

        pagaGiros.setConceptos(conceptos);



        String jsonBody = new GsonBuilder().create().toJson(pagaGiros);

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
     * exitosa sin flete

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

     */


}
