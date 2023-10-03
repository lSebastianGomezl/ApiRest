package billetera.app;

import com.google.gson.GsonBuilder;
import dto.chancemillonariopaymentcuopon.*;
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

public class ChanceMillonarioPaymentCoupon {

    int idCupon;
    static String tokenId ;

    String idSession;

    String descripcion;

    public ChanceMillonarioPaymentCoupon() {
        RestAssured.baseURI = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/producto/pagar";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

        //Se ejecuta el servicio de contenido dinamico
        ConsultContenidoDinamicoOUTDto consult = ConsultContenidoDinamicoService.contenidoDinamicoData();

        LoginBtwOUTDto respuesta = LoginServiceBtw.getDataLogin();

        tokenId = respuesta.getDatosSalida().getTokenId();
        System.out.println("Este es el tokenId actual " + tokenId );

        idSession = respuesta.getDatosTransaccion().getDatosCliente().getIdSession();
        System.out.println("Este es el idSession actual" + idSession);

        ClienteCaracteristicasOUTDto clientService = ClienteCarateristicasServcie.clienteCaracteristicasData(tokenId,idSession);

        ConsultCuponesOUTDto respuestaCoupon = ConsultCouponService.consultCuponData();

        for(int i = 0; i < respuestaCoupon.getDatosSalida().getListaCupones().size(); i++ ){
            idCupon = respuestaCoupon.getDatosSalida().getListaCupones().get(i).getCuponDTO().getIdSerie();
            System.out.println("Hola este es el idCupon" + idCupon);
            descripcion = respuestaCoupon.getDatosSalida().getListaCupones().get(i).getCuponDTO().getCuponDescripcion().getDescripcion();
            System.out.println("Hola este es la descripcion del cupon: " + descripcion);

            if (descripcion.startsWith("CUPON CHANCEMILLONARIO")){
                break;
            }
        }

        }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public ChanceMillonarioPaymentCouponINDto initValue(){

        ChanceMillonarioPaymentCouponINDto transaccion = new ChanceMillonarioPaymentCouponINDto();

        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoChanceMillonario> listProductChance = new ArrayList<>();

        LstProductoChanceMillonario producto = new LstProductoChanceMillonario();
        producto.setCantidad(1);
        producto.setValorIva(798);
        producto.setValorPagar(5000);
        producto.setFechaSorteo(fechaActual());
        producto.setValorProducto(5000);
        producto.setValorUnitario(5000);
        producto.setCantidadPuntos(0);
        producto.setValorDescuento(0);
        producto.setIdProductoTrans(1);
        producto.setValorTransaccion(0);
        producto.setCodigoModalidad("1");
        producto.setTipoTarifaTransaccion("COBRA");

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(9000) + 1000;

        int numeroAleatorio2 = random.nextInt(8000) + 1000;
        int numeroAleatorio3 = random.nextInt(7000) + 1000;
        int numeroAleatorio4 = random.nextInt(6000) + 1000;
        int numeroAleatorio5 = random.nextInt(5000) + 1000;

        ArrayList<String> numeros = new ArrayList<>();

        numeros.add(Integer.toString(numeroAleatorio1));
        numeros.add(Integer.toString(numeroAleatorio2));
        numeros.add(Integer.toString(numeroAleatorio3));
        numeros.add(Integer.toString(numeroAleatorio4));
        numeros.add(Integer.toString(numeroAleatorio5));

        producto.setNumeros(numeros);



        ArrayList<Loteria> listLoterias = new ArrayList<>();

        Loteria loteria  = new Loteria();

        loteria.setCodLoteria("29");
        loteria.setNombreLoteria("Cafeterito Noche");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);
        Loteria loteria2  = new Loteria();

        loteria2.setCodLoteria("52");
        loteria2.setNombreLoteria("Chontico Noche");
        loteria2.setFechaSorteo(fechaActual());
        loteria2.setCifrasNumero(0);
        loteria2.setCifrasSerie(0);

        listLoterias.add(loteria);
        listLoterias.add(loteria2);

        producto.setLoterias(listLoterias);

        listProductChance.add(producto);

        datosEntrada.setLstProductoChanceMillonario(listProductChance);

        datosEntrada.setIdTipoTarjeta("CUPON");
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");
        datosEntrada.setIdCupon(idCupon);


        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setCanal("APP");


        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession(idSession);
        datosCliente.setLogin("leceitracreta-2473@yopmail.com");
        datosCliente.setRol("Usuario");
        datosCliente.setTerminal("FC3AE886-671B-4EB4-960E-859126D2C6D2");

        datosTransaccion.setDispositivo("IOS");
        datosTransaccion.setFecha(fechaActual());

        datosTransaccion.setDatosCliente(datosCliente);

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
    public void chanceMillonarioPaymentCouponSuccessful() {


            ChanceMillonarioPaymentCouponINDto transaccion = initValue();

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
