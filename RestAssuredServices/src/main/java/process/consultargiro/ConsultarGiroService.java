package process.consultargiro;

import com.google.gson.GsonBuilder;
import dto.consultargiro.ConsultarGiroINDto;
import dto.consultargiro.response.ConsultarGiroOUTDto;
import dto.consultargirocriterio.Cliente;
import dto.consultargirocriterio.ConsultarGiroCriterioINDto;
import dto.consultargirocriterio.response.ConsultGiroCriterioOUTDto;
import dto.giros.giroscaptacion.Caja;
import dto.giros.giroscaptacion.Usuario;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConsultarGiroService {

    /* -------  USUARIO ------- */

    static String usuarioU = "9865308";
    static String clave = "XXXXX";
    static String agencia = "2530705";
    static int caducacidadClave = 0 ;

    /* -------- CAJA ------ */

    static String cajaValor = "0000";


    /* -------------- Cliente Destino ----------------- */

    static String tipoIdClienteDestino = "CC";
    static String idClienteDestino = "777777";
    static Boolean enroladoClineteDestino = false;
    static Boolean exoneradoHuellaClienteDestino = false;
    static Boolean remitenteClienteDestino = false;


    /* -------------- AGENCIA DESTINO -----------*/

    static String tipoTercero = "DESTINATARIO";
    static String direccionMac = "192.168.252.28";
    static String codigoVendedor ="222230";
    static String codigoProducto = "301";
    static String canal = "PVMA";
    static String codigoTerminal = "862634041369599";
    static String tipoUsuario = "VENDEDOR";
    static String operacionTransaccion = "CONSULTAR_GIRO";

    static String autorizador;
    static String idSesion;
    static Boolean incluyeFlete;
    static Boolean incluyeDomicilio;
    static String numReferencia;



    public static String obtenerFechaActual(String formato) {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha en el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        return fechaActual.format(formatter);
    }


    public static ConsultarGiroOUTDto consultarGirosData(String pAutorizador, String pIdSesion,
                  String pCodigoVendedor, String pNumReferencia){

        autorizador = pAutorizador;
        idSesion = pIdSesion;
        codigoVendedor = pCodigoVendedor;
        numReferencia = pNumReferencia;


        try{

            String url = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/giros/consultarGiro";

            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestProperty("id-Sesion", idSesion);
            System.out.println(idSesion);
            connection.setRequestProperty("Autorizador", autorizador);
            System.out.println(autorizador);

            // Agregar cabeceras
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");



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


            consultGiros.setReferencia(numReferencia);
            consultGiros.setDireccionMac(direccionMac);
            consultGiros.setCodigoVendedor(codigoVendedor);
            consultGiros.setCodigoProducto(codigoProducto);
            consultGiros.setCanalId(canal);
            consultGiros.setCodigoTerminal(codigoTerminal);
            consultGiros.setTipoUsuario(tipoUsuario);
            consultGiros.setOperacionTransaccion(operacionTransaccion);





            String jsonData = new GsonBuilder().create().toJson(consultGiros);

            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonData);
            wr.flush();
            wr.close();

            // Obtener respuesta
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ConsultarGiroOUTDto consultarGirosResponse = new ConsultarGiroOUTDto();

            consultarGirosResponse = new GsonBuilder().create().fromJson(response.toString(), ConsultarGiroOUTDto.class);

            return consultarGirosResponse;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            System.out.println("Metodo before ejecutado");
        }
    }
}
