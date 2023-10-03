package billetera.app;
import com.google.gson.GsonBuilder;
import dto.app.chancemillonario.ChanceMillonarioINDto;
import dto.app.chancemillonario.LstProductoChanceMillonario;
import dto.app.pagamillonario.DatosEntrada;
import dto.app.pagamillonario.LstProductoPagaMillonario;
import dto.app.pagamillonario.PagaMillonarioINDto;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.comunclass.DatosCliente;
import dto.comunclass.DatosTransaccion;
import dto.comunclass.Geolocalizacion;
import dto.comunclass.Loteria;
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

public class PagaMillonarioApp {

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

    public PagaMillonarioApp(){

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
        int numeroAleatorio = random.nextInt(9000) + 1000;

        return Integer.toString(numeroAleatorio);
    }
    public static String numeroAleatorio1(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(5000) + 4000;

        return Integer.toString(numeroAleatorio1);
    }
    public static String numeroAleatorio2(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio2 = random.nextInt(4000) + 3000;

        return Integer.toString(numeroAleatorio2);
    }
    public static String numeroAleatorio3(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio3 = random.nextInt(3000) + 2000;

        return Integer.toString(numeroAleatorio3);
    }
    public static String numeroAleatorio4(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio4 = random.nextInt(2000) + 1000;

        return Integer.toString(numeroAleatorio4);
    }

    public static String numeroAleatorioThree(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio = random.nextInt(900) + 100;

        return Integer.toString(numeroAleatorio);
    }
    public static String numeroAleatorioThree1(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio1 = random.nextInt(500) + 400;

        return Integer.toString(numeroAleatorio1);
    }
    public static String numeroAleatorioThree2(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio2 = random.nextInt(400) + 300;

        return Integer.toString(numeroAleatorio2);
    }
    public static String numeroAleatorioThree3(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio3 = random.nextInt(300) + 200;

        return Integer.toString(numeroAleatorio3);
    }
    public static String numeroAleatorioThree4(){
        Random random = new Random();

        random.setSeed(System.currentTimeMillis());
        int numeroAleatorio4 = random.nextInt(200) + 100;

        return Integer.toString(numeroAleatorio4);
    }

    public PagaMillonarioINDto initValue(){

        PagaMillonarioINDto transaccion = new PagaMillonarioINDto();

        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoPagaMillonario> lstPagaMillonario = new ArrayList<>();

        LstProductoPagaMillonario pagaMillonario = new LstProductoPagaMillonario();


        pagaMillonario.setCantidad(1);
        pagaMillonario.setValorIva(479);
        pagaMillonario.setValorPagar(3000);
        pagaMillonario.setFechaSorteo(fechaActual());
        pagaMillonario.setValorProducto(3000);
        pagaMillonario.setValorUnitario(3000);
        pagaMillonario.setCantidadPuntos(0);
        pagaMillonario.setValorDescuento(0);
        pagaMillonario.setIdProductoTrans(1);
        pagaMillonario.setValorTransaccion(0);
        pagaMillonario.setCodigoModalidad("1");
        pagaMillonario.setTipoTarifaTransaccion(idTipoTarifaTransaccion);

        ArrayList<String> numeros = new ArrayList<>();

        numeros.add(numeroAleatorio());
        numeros.add(numeroAleatorio1());
        numeros.add(numeroAleatorio2());
        numeros.add(numeroAleatorio3());
        numeros.add(numeroAleatorio4());

        pagaMillonario.setNumeros(numeros);


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

        pagaMillonario.setLoterias(listLotieria);

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");

        lstPagaMillonario.add(pagaMillonario);

        datosEntrada.setLstProductoPagaMillonario(lstPagaMillonario);

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


    @Test
    public void PagaMillonarioFourSuccessful() {


        PagaMillonarioINDto transaccion = initValue();

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

    @Test
    public void PagaMillonarioThreeSuccessful() {


        PagaMillonarioINDto transaccion = initValue();


        DatosEntrada datosEntrada = new DatosEntrada();

        ArrayList<LstProductoPagaMillonario> lstPagaMillonario = new ArrayList<>();

        LstProductoPagaMillonario pagaMillonario = new LstProductoPagaMillonario();


        pagaMillonario.setCantidad(1);
        pagaMillonario.setValorIva(479);
        pagaMillonario.setValorPagar(3000);
        pagaMillonario.setFechaSorteo(fechaActual());
        pagaMillonario.setValorProducto(3000);
        pagaMillonario.setValorUnitario(3000);
        pagaMillonario.setCantidadPuntos(0);
        pagaMillonario.setValorDescuento(0);
        pagaMillonario.setIdProductoTrans(1);
        pagaMillonario.setValorTransaccion(0);
        pagaMillonario.setCodigoModalidad("1");
        pagaMillonario.setTipoTarifaTransaccion(idTipoTarifaTransaccion);

        ArrayList<String> numeros = new ArrayList<>();

        numeros.add(numeroAleatorioThree());
        numeros.add(numeroAleatorioThree1());
        numeros.add(numeroAleatorioThree2());
        numeros.add(numeroAleatorioThree3());
        numeros.add(numeroAleatorioThree4());

        pagaMillonario.setNumeros(numeros);


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

        pagaMillonario.setLoterias(listLotieria);

        datosEntrada.setIdTipoTarjeta(idTipoTarjeta);
        datosEntrada.setNumeroCuotas(0);
        datosEntrada.setCvv("");
        datosEntrada.setFechaExpiracion("");

        lstPagaMillonario.add(pagaMillonario);

        datosEntrada.setLstProductoPagaMillonario(lstPagaMillonario);

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
