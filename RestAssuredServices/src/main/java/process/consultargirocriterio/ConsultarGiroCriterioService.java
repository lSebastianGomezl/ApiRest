package process.consultargirocriterio;

import com.google.gson.GsonBuilder;
import dto.consultargirocriterio.Caja;
import dto.consultargirocriterio.Cliente;
import dto.consultargirocriterio.ConsultarGiroCriterioINDto;
import dto.consultargirocriterio.Usuario;
import dto.consultargirocriterio.response.ConsultGiroCriterioOUTDto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConsultarGiroCriterioService {

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
    static String operacionTransaccion = "CONSULTAR_GIRO_CRITERIO";

    static String autorizador;
    static String idSesion;
    static Boolean incluyeFlete;
    static Boolean incluyeDomicilio;



    public static String obtenerFechaActual(String formato) {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha en el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        return fechaActual.format(formatter);
    }


    public static ConsultGiroCriterioOUTDto consultarConceptosData(String pAutorizador, String pIdSesion,
                                                                   String pCodigoVendedor){

        autorizador = pAutorizador;
        idSesion = pIdSesion;
        codigoVendedor = pCodigoVendedor;


        try{

            String url = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/giros/consultarGiroGriterio";

            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestProperty("id-Sesion", idSesion);
            System.out.println(idSesion);
            connection.setRequestProperty("Autorizador", autorizador);
            System.out.println(autorizador);

            // Agregar cabeceras
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");



            ConsultarGiroCriterioINDto consultGiros = new ConsultarGiroCriterioINDto();

            Usuario usuario = new Usuario();

            usuario.setUsuario(usuarioU);
            usuario.setClave(clave);
            usuario.setAgencia(agencia);
            usuario.setCaducidadClave(caducacidadClave);

            Caja caja = new Caja();

            caja.setCodCaja(cajaValor);


            consultGiros.setUsuario(usuario);
            consultGiros.setCaja(caja);

            String fechaActual = obtenerFechaActual("ddMMyyyy");
            System.out.println("fechaactual " + fechaActual);
            consultGiros.setFechaInicio(fechaActual);
            consultGiros.setFechaFin(fechaActual);
            consultGiros.setTipoTercero(tipoTercero);
            consultGiros.setDireccionMac(direccionMac);
            consultGiros.setCodigoVendedor(codigoVendedor);
            consultGiros.setCodigoProducto(codigoProducto);
            consultGiros.setCanalId(canal);
            consultGiros.setCodigoTerminal(codigoTerminal);
            consultGiros.setTipoUsuario(tipoUsuario);
            consultGiros.setOperacionTransaccion(operacionTransaccion);

            Cliente cliente = new Cliente();

            cliente.setTipoIdentificacion(tipoIdClienteDestino);
            cliente.setIdentificacion(idClienteDestino);
            cliente.setEnrolado(enroladoClineteDestino);
            cliente.setExoneradoHuella(exoneradoHuellaClienteDestino);
            cliente.setRemitente(remitenteClienteDestino);

            consultGiros.setCliente(cliente);



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

            ConsultGiroCriterioOUTDto consultarGirosResponse = new ConsultGiroCriterioOUTDto();

            consultarGirosResponse = new GsonBuilder().create().fromJson(response.toString(), ConsultGiroCriterioOUTDto.class);

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
