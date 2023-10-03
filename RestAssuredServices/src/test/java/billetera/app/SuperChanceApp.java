package billetera.app;
import com.google.gson.GsonBuilder;
import dto.app.chance.ChanceClasicoINDto;
import dto.app.chance.LstProductoChance;
import dto.app.superchance.DatosEntrada;
import dto.app.superchance.LstProductoPromocionalSuperChance;
import dto.app.superchance.SuperChanceINDto;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.comunclass.*;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
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
import process.consultcontenidodinamico.ConsultContenidoDinamicoService;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SuperChanceApp {

    String tokenId ;
    String usuario;
    String canalId;
    String idSession;
    String terminal ;
    String login1;
    String idTipoTarjeta = "MI_SALDO";
    String idTipoTarifaTransaccion = "COBRA";
    String dispositivo;
    String ciudad;
    String longitud;
    String latitud;
    int id;

    String version;

    public SuperChanceApp(){

        RestAssured.baseURI = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/producto/pagar";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

        //Se ejecuta el servicio de contenido dinamico
        ConsultContenidoDinamicoOUTDto consult = ConsultContenidoDinamicoService.contenidoDinamicoData();

        LoginBtwOUTDto respuesta = LoginServiceBtw.getDataLogin();
        System.out.println("esta es la respuesta: " +respuesta);

        login1 = respuesta.getDatosTransaccion().getDatosCliente().getLogin();
        System.out.println("Este ese login " + login1);

        tokenId = respuesta.getDatosSalida().getTokenId();
        System.out.println("Este es el tokenId actual " + tokenId );

        idSession = respuesta.getDatosTransaccion().getDatosCliente().getIdSession();
        System.out.println("Este es el idSession actual" + idSession);

        terminal = respuesta.getDatosTransaccion().getDatosCliente().getTerminal();
        System.out.println("Esta es la terminal:" + terminal);

        canalId = respuesta.getDatosTransaccion().getCanal();
        System.out.println("Este es el canal " + canalId);

        usuario = respuesta.getDatosTransaccion().getDatosCliente().getRol();
        System.out.println("este es el usuario " + usuario);

        dispositivo = respuesta.getDatosTransaccion().getDispositivo();
        System.out.println("Este es el dispositivo " + dispositivo);

        ciudad = respuesta.getDatosTransaccion().getGeolocalizacion().getCiudad();
        System.out.println("ciudad " + ciudad);

        longitud = respuesta.getDatosTransaccion().getGeolocalizacion().getLongitud();
        System.out.println("longitud " + longitud);

        latitud = respuesta.getDatosTransaccion().getGeolocalizacion().getLatitud();
        System.out.println("latitud " + latitud);

        id = respuesta.getDatosTransaccion().getId();
        System.out.println("id " + id);

        version = respuesta.getDatosTransaccion().getVersion();
        System.out.println("version" + version);

        ClienteCaracteristicasOUTDto clientService = ClienteCarateristicasServcie.clienteCaracteristicasData(tokenId, idSession);
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

    public static String numeroAleatorioPd(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(900) + 100;

        return Integer.toString(numeroAleatorio1);
    }

    public SuperChanceINDto initValue(){

        SuperChanceINDto transaccion = new SuperChanceINDto();

        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoPromocionalSuperChance> listProductSuperChance = new ArrayList<>();

        LstProductoPromocionalSuperChance superChance = new LstProductoPromocionalSuperChance();

        superChance.setCantidad(1);
        superChance.setValorIva(287);
        superChance.setValorPagar(1800);
        superChance.setFechaSorteo(fechaActual());
        superChance.setValorProducto(1800);
        superChance.setValorUnitario(1800);
        superChance.setCantidadPuntos(0);
        superChance.setValorDescuento(0);
        superChance.setIdProductoTrans(1);
        superChance.setValorTransaccion(0);
        superChance.setTipoTarifaTransaccion(idTipoTarifaTransaccion);

        ArrayList<Loteria> listLotieria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("52");
        loteria.setNombreLoteria("Chontico Noche");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);

        Loteria loteria2 = new Loteria();

        loteria2.setCodLoteria("29");
        loteria2.setNombreLoteria("Cafeterito Noche");
        loteria2.setFechaSorteo(fechaActual());
        loteria2.setCifrasNumero(0);
        loteria2.setCifrasSerie(0);

        listLotieria.add(loteria);
        listLotieria.add(loteria2);

        superChance.setLoterias(listLotieria);



        ArrayList<Moldalidade> listModalidad = new ArrayList<>();

        Moldalidade modalidad = new Moldalidade();

        modalidad.setChanceModalidad("SUPER_PLENO_DIRECTO");
        modalidad.setValor(900);
        modalidad.setNumero(numeroAleatorio());

        listModalidad.add(modalidad);
        superChance.setMoldalidades(listModalidad);

        listProductSuperChance.add(superChance);


        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");

        datosEntrada.setLstProductoPromocionalSuperChance(listProductSuperChance);

        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setCanal(canalId);

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession(idSession);
        datosCliente.setLogin(login1);
        datosCliente.setRol(usuario);
        datosCliente.setTerminal(terminal);

        datosTransaccion.setDatosCliente(datosCliente);


        datosTransaccion.setDispositivo(dispositivo);
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);


        return transaccion;
    }


    /*SuperChanceSuccessful prueba la respuesta del servicio al enviar la data con super pleno directo
     * */
    @Test
    public void SuperChanceSpdSuccessful() {


        SuperChanceINDto  transaccion = initValue();

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

    /*SuperChanceSuccessful prueba la respuesta del servicio al enviar la data con  pleno directo
     * */
    @Test
    public void SuperChancePdSuccessful() {


        SuperChanceINDto  transaccion = initValue();

        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoPromocionalSuperChance> listProductSuperChance = new ArrayList<>();

        LstProductoPromocionalSuperChance superChance = new LstProductoPromocionalSuperChance();

        superChance.setCantidad(1);
        superChance.setValorIva(798);
        superChance.setValorPagar(5000);
        superChance.setFechaSorteo(fechaActual());
        superChance.setValorProducto(5000);
        superChance.setValorUnitario(5000);
        superChance.setCantidadPuntos(0);
        superChance.setValorDescuento(0);
        superChance.setIdProductoTrans(1);
        superChance.setValorTransaccion(0);
        superChance.setTipoTarifaTransaccion(idTipoTarifaTransaccion);

        ArrayList<Loteria> listLotieria = new ArrayList<>();

        Loteria loteria = new Loteria();

        loteria.setCodLoteria("52");
        loteria.setNombreLoteria("Chontico Noche");
        loteria.setFechaSorteo(fechaActual());
        loteria.setCifrasNumero(0);
        loteria.setCifrasSerie(0);

        Loteria loteria2 = new Loteria();

        loteria2.setCodLoteria("29");
        loteria2.setNombreLoteria("Cafeterito Noche");
        loteria2.setFechaSorteo(fechaActual());
        loteria2.setCifrasNumero(0);
        loteria2.setCifrasSerie(0);

        listLotieria.add(loteria);
        listLotieria.add(loteria2);

        superChance.setLoterias(listLotieria);



        ArrayList<Moldalidade> listModalidad = new ArrayList<>();

        Moldalidade modalidad = new Moldalidade();

        modalidad.setChanceModalidad("PLENO_DIRECTO");
        modalidad.setValor(2500);
        modalidad.setNumero(numeroAleatorioPd());

        listModalidad.add(modalidad);
        superChance.setMoldalidades(listModalidad);

        listProductSuperChance.add(superChance);


        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");

        datosEntrada.setLstProductoPromocionalSuperChance(listProductSuperChance);

        DatosTransaccion datosTransaccion = new DatosTransaccion();

        datosTransaccion.setCanal(canalId);

        DatosCliente datosCliente = new DatosCliente();

        datosCliente.setIdSession(idSession);
        datosCliente.setLogin(login1);
        datosCliente.setRol(usuario);
        datosCliente.setTerminal(terminal);

        datosTransaccion.setDatosCliente(datosCliente);


        datosTransaccion.setDispositivo(dispositivo);
        datosTransaccion.setFecha(fechaActual());

        Geolocalizacion geolocalizacion = new Geolocalizacion();

        geolocalizacion.setCiudad(ciudad);
        geolocalizacion.setLatitud(latitud);
        geolocalizacion.setLongitud(longitud);

        datosTransaccion.setGeolocalizacion(geolocalizacion);

        datosTransaccion.setTokenId(tokenId);
        datosTransaccion.setId(id);
        datosTransaccion.setVersion(version);

        transaccion.setDatosTransaccion(datosTransaccion);
        transaccion.setDatosEntrada(datosEntrada);

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
