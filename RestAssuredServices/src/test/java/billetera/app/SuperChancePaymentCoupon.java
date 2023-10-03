package billetera.app;

import com.google.gson.GsonBuilder;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.comunclass.Geolocalizacion;
import dto.comunclass.Loteria;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
import dto.consultcuponessivemtx.response.ConsultCuponesOUTDto;
import dto.loginbtw.response.LoginBtwOUTDto;
import dto.superchancepaymentcoupon.DatosCliente;
import dto.superchancepaymentcoupon.DatosEntrada;
import dto.superchancepaymentcoupon.DatosTransaccion;
import dto.superchancepaymentcoupon.LstProductoPromocionalSuperChance;
import dto.superchancepaymentcoupon.Moldalidade;
import dto.superchancepaymentcoupon.SuperChancePaymentCouponINDto;
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

public class SuperChancePaymentCoupon {

    int idSerial;
    String tokenId ;

    String terminal = "044E757B-457D-4358-812A-12598B0B6439";

    String idSession;

    String descripcion;
    String login1 = "leceitracreta-2473@yopmail.com";

    public SuperChancePaymentCoupon() {

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
            if (descripcion.startsWith("CUPON SUPER CHANCE (")){
                break;
            }
        }

    }
    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public static String numeroAleatorio(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;

        return Integer.toString(numeroAleatorio1);
    }

    public SuperChancePaymentCouponINDto initValue(){

        SuperChancePaymentCouponINDto transaccion = new SuperChancePaymentCouponINDto();

        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoPromocionalSuperChance> listProductoSuperChance = new ArrayList<>();

        LstProductoPromocionalSuperChance superChance = new LstProductoPromocionalSuperChance();

        superChance.setCantidad(1);
        superChance.setValorIva(479);
        superChance.setValorPagar(3000);
        superChance.setFechaSorteo(fechaActual());
        superChance.setValorProducto(3000);
        superChance.setValorUnitario(3000);
        superChance.setCantidadPuntos(0);
        superChance.setValorDescuento(0);
        superChance.setIdProductoTrans(1);
        superChance.setValorTransaccion(0);
        superChance.setTipoTarifaTransaccion("COBRA");

        ArrayList<Loteria> listLoterias = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("11");
        loteria.setNombreLoteria("Boyacá");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);

        listLoterias.add(loteria);

        superChance.setLoterias(listLoterias);

        ArrayList<Moldalidade> listModalidades = new ArrayList<>();

        Moldalidade modalidad = new Moldalidade();

        modalidad.setChanceModalidad("SUPER_PLENO_DIRECTO");
        modalidad.setValor(3000);
        modalidad.setNumero(numeroAleatorio());

        listModalidades.add(modalidad);
        superChance.setMoldalidades(listModalidades);

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
        datosCliente.setTerminal(terminal);

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
        datosTransaccion.setVersion("15.6.0");

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);


        return transaccion;

    }

    @Test
    public void SuperChancePaymentCouponSuccessful() {


        SuperChancePaymentCouponINDto transaccion = initValue();

        String jsonBody = new GsonBuilder().create().toJson(transaccion);


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
