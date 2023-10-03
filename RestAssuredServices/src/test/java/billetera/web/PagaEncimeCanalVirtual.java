package billetera.web;

import com.google.gson.GsonBuilder;
import dto.pagaencimecanalvirtual.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class PagaEncimeCanalVirtual {
    static String tokenId = "eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2ODM3MzgwMjQsImlzcyI6IntcInVzdWFyaW9cIjpcInNlYmFzdGlhbi5nb21lekBkY3Nhcy5jb20uY29cIixcInRlcm1pbmFsXCI6XCJDaElKRnlZOFZZU2VQNDRSNS1kdzBvcDFBaDAsMTI3LjAuMC4xXCIsXCJjYW5hbFwiOlwiV0VCXCJ9Iiwic3ViIjoiQUNFQkZCNEEyMUE1MkUzMjRGRjE2OUMxRTczMEJFQTEiLCJleHAiOjE2ODM4MjQ0MjV9.1XNSJk9Prx30aXz_yrnJmIVYjMHD1Gm3rSsViiv3DraXvY2QCTVSKuwSdKVqJCa0dHGRRt1wwCRpdgpDoxAAbQ";
    static String idSesion = "7B7053FD30C4C07E45F5A6E14E7B6F4A";
    public PagaEncimeCanalVirtual() {
        RestAssured.baseURI = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/producto/";
        RestAssured.basePath = "pagar";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }

    public static long consecutivoNumero(){

        long fechaHoraActual = System.currentTimeMillis();
        return fechaHoraActual;

    }


    /*Inicialización de valores*/
    public static PagaEncimeINDto initValue(){

        PagaEncimeINDto transaccion = new PagaEncimeINDto();

        DatosEntrada dato1 = new DatosEntrada();




        ArrayList<LstProductoPromocionalPagaEncime> listProducto = new ArrayList<>();
        ArrayList<Moldalidade> listModalidades = new ArrayList<>();
        ArrayList<Loteria> listLoteria = new ArrayList<>();
        LstProductoPromocionalPagaEncime producto = new LstProductoPromocionalPagaEncime();

        Moldalidade modalidad  = new Moldalidade();

        modalidad.setChanceModalidad("PLENO_DIRECTO");
        modalidad.setValor(6000);
        modalidad.setNumero("123");


        Loteria loteria = new Loteria();


        loteria.setCodLoteria("31");
        loteria.setNombreLoteria("Paisita Noche");
        loteria.setFechaSorteo(consecutivoNumero());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);

        listModalidades.add(modalidad);
        listLoteria.add(loteria);

        producto.setMoldalidades(listModalidades);
        producto.setLoterias(listLoteria);


        producto.setFechaSorteo(consecutivoNumero());
        producto.setValorUnitario(6000);
        producto.setCantidad(1);
        producto.setValorProducto(6000);
        producto.setValorDescuento(0);
        producto.setValorIva(958);
        producto.setTipoTarifaTransaccion("COBRA");
        producto.setValorTransaccion(0);
        producto.setValorPagar(6000);
        producto.setCantidadPuntos(0);
        producto.setIdProductoTrans(1);

        listProducto.add(producto);

        dato1.setLstProductoPromocionalPagaEncime(listProducto);
        dato1.setIdTipoTarjeta("MI_SALDO");
        dato1.setNumeroCuotas(0);


        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setId(1);
        datosTransaccion.setFecha(consecutivoNumero());

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setLogin("sebastian.gomez@dcsas.com.co");
        datosCliente.setRol("Cliente");
        datosCliente.setTerminal("ChIJFyY8VYSeP44R5-dw0op1Ah0,127.0.0.1");
        datosCliente.setIdSession(idSesion);
        datosTransaccion.setDatosCliente(datosCliente);

        datosTransaccion.setCanal("WEB");
        datosTransaccion.setTokenId(tokenId);

        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad("ID_CIUDAD_USUARIO");
        geolocalizacion.setLatitud("4.646804");
        geolocalizacion.setLongitud("-74.071924");

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setDispositivo("WEB");
        datosTransaccion.setIdAplicacion(0);

        transaccion.setDatosEntrada(dato1);
        transaccion.setDatosTransaccion(datosTransaccion);


        return transaccion;

    }

    @Test
    public void pagaEncimeSuccessful() {

        PagaEncimeINDto data = initValue();

        String jsonBody = new GsonBuilder().create().toJson(data);

                given()
                .relaxedHTTPSValidation()
                .when()
                .body(jsonBody)
                .post(baseURI + basePath)
                .then()
                .statusCode(200)
                .body("datosRespuesta.mensaje", equalTo("OPERACION EXITOSA"));

    }
}
