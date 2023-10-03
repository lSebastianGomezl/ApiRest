package billetera.app;

import com.google.gson.GsonBuilder;
import dto.chanceclasicopaymentcoupon.*;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.comunclass.Geolocalizacion;
import dto.comunclass.Loteria;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
import dto.consultcuponessivemtx.response.ConsultCuponesOUTDto;
import dto.loginbtw.response.LoginBtwOUTDto;
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

public class ChanceClasicoPaymentCoupon {

    int idSerial;
    String tokenId ;

    String idSession;

    String descripcion;
    String login1 = "leceitracreta-2473@yopmail.com";

    public ChanceClasicoPaymentCoupon() {

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
            idSerial = respuestaCoupon.getDatosSalida().getListaCupones().get(i).getCuponDTO().getIdCupon();
            System.out.println("Hola este es el idSerial" + idSerial);
            descripcion = respuestaCoupon.getDatosSalida().getListaCupones().get(i).getCuponDTO().getCuponDescripcion().getDescripcion();
            System.out.println("Hola este es la descripcion del cupon: " + descripcion);
            System.out.println("idCupon" + idSerial);
            if (descripcion.startsWith("CUPON CHANCE (")){
                break;
            }
        }

    }
    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public ChanceClasicoPaymentCouponINDto initValue(){

        ChanceClasicoPaymentCouponINDto chanceClasico = new ChanceClasicoPaymentCouponINDto();

        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoChance> listChance = new ArrayList<>();

        LstProductoChance chance = new LstProductoChance();
        chance.setCantidad(1);
        chance.setValorIva(200);
        chance.setValorPagar(3000);
        chance.setFechaSorteo(fechaActual());
        chance.setValorProducto(2800);
        chance.setValorUnitario(700);
        chance.setCantidadPuntos(0);
        chance.setValorDescuento(0);
        chance.setIdProductoTrans(1);
        chance.setValorTransaccion(0);
        chance.setTipoTarifaTransaccion("COBRA");

        ArrayList<Loteria> listLoterias = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("29");
        loteria.setNombreLoteria("Cafeterito Noche");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);

        listLoterias.add(loteria);
        chance.setLoterias(listLoterias);

        //datosEntrada.setLstProductoChance(listChance);

        ArrayList<Moldalidade> listModalidades = new ArrayList<>();

        Moldalidade modalidad = new Moldalidade();

        modalidad.setChanceModalidad("PATA");
        modalidad.setValor(2800);

        Random number = new Random();

        number.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = number.nextInt(99) + 10;
        modalidad.setNumero(Integer.toString(numeroAleatorio1));

        listModalidades.add(modalidad);

        chance.setMoldalidades(listModalidades);

        listChance.add(chance);


        datosEntrada.setLstProductoChance(listChance);

        datosEntrada.setIdTipoTarjeta("CUPON");
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");
        datosEntrada.setIdCupon(idSerial);

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


        chanceClasico.setDatosTransaccion(datosTransaccion);
        chanceClasico.setDatosEntrada(datosEntrada);

        return chanceClasico;

    }
    @Test
    public void ChanceClasicoPaymentCouponSuccessful(){

        ChanceClasicoPaymentCouponINDto chance = initValue();

        String jsonBody = new GsonBuilder().create().toJson(chance);

        given()
                .body(jsonBody)
                .when()
                .relaxedHTTPSValidation()
                .post(baseURI)
                .then()
                .statusCode(200)
                .body("exito", Matchers.equalTo(true));
    }
}
