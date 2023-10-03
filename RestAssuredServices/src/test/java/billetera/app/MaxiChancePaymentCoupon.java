package billetera.app;

import com.google.gson.GsonBuilder;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.comunclass.Geolocalizacion;
import dto.comunclass.Loteria;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
import dto.consultcuponessivemtx.response.ConsultCuponesOUTDto;
import dto.loginbtw.response.LoginBtwOUTDto;
import dto.maxichancepaymentcoupon.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import process.LoginServiceBtw;
import process.clientecaracteristicas.ClienteCarateristicasServcie;
import process.consultarcupon.ConsultCouponService;
import process.consultcontenidodinamico.ConsultContenidoDinamicoService;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class MaxiChancePaymentCoupon {

    int idCupon;
    String tokenId ;

    String idSession;

    String descripcion;
    String login1 = "leceitracreta-2473@yopmail.com";

    public MaxiChancePaymentCoupon() {
        RestAssured.baseURI = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/producto/pagar";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
        //Se ejecuta el servicio de contenido dinamico
        ConsultContenidoDinamicoOUTDto consult = ConsultContenidoDinamicoService.contenidoDinamicoData();

        LoginBtwOUTDto respuesta = LoginServiceBtw.getDataLogin();
        System.out.println("esta es la respuesta: " +respuesta);

        tokenId = respuesta.getDatosSalida().getTokenId();
        System.out.println("Este es el tokenId actual " + tokenId );

        idSession = respuesta.getDatosTransaccion().getDatosCliente().getIdSession();
        System.out.println("Este es el idSession actual" + idSession);


        ClienteCaracteristicasOUTDto clientService = ClienteCarateristicasServcie.clienteCaracteristicasData(tokenId, idSession);

        ConsultCuponesOUTDto respuestaCoupon = ConsultCouponService.consultCuponData();

        for(int i = 0; i < respuestaCoupon.getDatosSalida().getListaCupones().size(); i++ ){
            idCupon = respuestaCoupon.getDatosSalida().getListaCupones().get(i).getCuponDTO().getIdSerie();
            System.out.println("Hola este es el idSerial" + idCupon);


            descripcion = respuestaCoupon.getDatosSalida().getListaCupones().get(i).getCuponDTO().getCuponDescripcion().getDescripcion();
            System.out.println("Hola este es la descripcion del cupon: " + descripcion);
            System.out.println("idCupon" + idCupon);
            if (descripcion.startsWith("CUPON MAXICHANCE")){
                break;
            }
        }
    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public MaxiChancePaymentCouponINDto initValue(){

        MaxiChancePaymentCouponINDto transacccion = new MaxiChancePaymentCouponINDto();

        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoPromocionalMaxiChance> listMaxiChance = new ArrayList<>();

        LstProductoPromocionalMaxiChance maxiChance = new LstProductoPromocionalMaxiChance();

        maxiChance.setCantidad(1);
        maxiChance.setValorIva(950);
        maxiChance.setValorPagar(6000);
        maxiChance.setFechaSorteo(fechaActual());
        maxiChance.setValorProducto(6000);
        maxiChance.setValorUnitario(6000);
        maxiChance.setCantidadPuntos(0);
        maxiChance.setValorDescuento(0);
        maxiChance.setIdProductoTrans(1);
        maxiChance.setValorTransaccion(0);
        maxiChance.setTipoTarifaTransaccion("COBRA");

        ArrayList<Loteria> listLoteria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("29");
        loteria.setNombreLoteria("Cafeterito Noche");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);

        listLoteria.add(loteria);



        ArrayList<Moldalidade> listModalidales = new ArrayList<>();

        Moldalidade modalidad = new Moldalidade();

        modalidad.setChanceModalidad("SUPER_PLENO_DIRECTO");
        modalidad.setValor(6000);
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(9000) + 1000;

        modalidad.setNumero(Integer.toString(numeroAleatorio));

        listModalidales.add(modalidad);

        maxiChance.setLoterias(listLoteria);
        maxiChance.setMoldalidades(listModalidales);

        listMaxiChance.add(maxiChance);
        datosEntrada.setLstProductoPromocionalMaxiChance(listMaxiChance);

        datosEntrada.setIdTipoTarjeta("CUPON");
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");
        datosEntrada.setIdCupon(idCupon);

        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setCanal("APP");

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession(idSession);
        datosCliente.setLogin(login1);
        datosCliente.setRol("Usuario");
        datosCliente.setTerminal("DC5A6D4D-49E2-48D6-9A56-7C595B0CCFD9");

        datosTransaccion.setDatosCliente(datosCliente);

        datosTransaccion.setDispositivo("IOS");
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad("Ac. 26 #69b9, Fontibón, Bogotá, Cundinamarca, Colombia");
        geolocalizacion.setLatitud("4.659349");
        geolocalizacion.setLongitud("-74.1086629");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(1);
        datosTransaccion.setIdAplicacion(0);
        datosTransaccion.setVersion("15.5.3");

        transacccion.setDatosEntrada(datosEntrada);
        transacccion.setDatosTransaccion(datosTransaccion);

        return transacccion;

    }


    @Test
    public void maxiChanceSuccessful(){

        MaxiChancePaymentCouponINDto maxiChance = initValue();


        String jsonBody = new GsonBuilder().create().toJson(maxiChance);

        given()
                .body(jsonBody)
                .when()
                .relaxedHTTPSValidation()
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("datosRespuesta.exito", Matchers.equalTo(true));

    }
}
