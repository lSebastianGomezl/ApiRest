package process.calcularconceptos;

import com.google.gson.GsonBuilder;

import dto.LoginOUTDto;
import dto.calcularconceptos.CalcularConceptosINDto;
import dto.calcularconceptos.CalcularConceptosOUTDto;
import dto.giros.giroscaptacion.ClienteDestino;
import dto.giros.giroscaptacion.ClienteOrigen;
import dto.giros.giroscaptacion.Usuario;
import process.LoginService;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CalcularConceptosService {


    /* -------  USUARIO ------- */

    static String usuarioU = "9865308";
    static String clave = "XXXXX";
    static String agencia = "2530705";
    static int caducacidadClave = 0 ;

    /*-----------Cliente Origen ------------------*/

    static String tipoIdentificacion = "CC";
    static String identificacion = "122333";
    static Boolean enrolado = false;
    static Boolean exoneradoHuella = true;
    static Boolean remitente = false;

    /* -------------- Cliente Destino ----------------- */

    static String tipoIdClienteDestino = "CC";
    static String idClienteDestino = "777777";
    static Boolean enroladoClineteDestino = false;
    static Boolean exoneradoHuellaClienteDestino = false;
    static Boolean remitenteClienteDestino = false;


    /* -------------- AGENCIA DESTINO -----------*/

    static String agenciaDestino2 = "4249";
    //static Boolean incluyeFlete= false;
    static String direccionMac = "192.168.252.28";
    static String codigoVendedor ="222230";
    static String codigoProducto = "301";
    static int valor = 80000;
    static String canal = "PVMA";
    static String codigoTerminal = "862634041369599";
    static String tipoUsuario = "VENDEDOR";
    static String operacionTransaccion = "CALCULAR";

    static String autorizador;
    static String idSesion;
    static Boolean incluyeFlete;
    static Boolean incluyeDomicilio;
    static Boolean incluyeOtro = false;


    public static CalcularConceptosOUTDto consultarConceptosData(String pAutorizador, String pIdSesion,
                   String pCodigoVendedor, Boolean pIncluyeFlete, Boolean pIncluyeDomicilio){

        autorizador = pAutorizador;
        idSesion = pIdSesion;
        codigoVendedor = pCodigoVendedor;
        incluyeFlete = pIncluyeFlete;
        incluyeDomicilio = pIncluyeDomicilio;




        try{

            String url = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/giros/calcularConceptos";

            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestProperty("id-Sesion", idSesion);
            System.out.println(idSesion);
            connection.setRequestProperty("Autorizador", autorizador);
            System.out.println(autorizador);

            // Agregar cabeceras
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");



            CalcularConceptosINDto consultConceptos = new CalcularConceptosINDto();

            Usuario usuario = new Usuario();

            usuario.setUsuario(usuarioU);
            usuario.setClave(clave);
            usuario.setAgencia(agencia);
            usuario.setCaducidadClave(caducacidadClave);

            ClienteOrigen clienteOrigen = new ClienteOrigen();

            clienteOrigen.setTipoIdentificacion(tipoIdentificacion);
            clienteOrigen.setIdentificacion(identificacion);
            clienteOrigen.setEnrolado(enrolado);
            clienteOrigen.setExoneradoHuella(exoneradoHuella);
            clienteOrigen.setRemitente(remitente);

            ClienteDestino clienteDestino = new ClienteDestino();

            clienteDestino.setTipoIdentificacion(tipoIdClienteDestino);
            clienteDestino.setIdentificacion(idClienteDestino);
            clienteDestino.setEnrolado(enroladoClineteDestino);
            clienteDestino.setExoneradoHuella(exoneradoHuellaClienteDestino);
            clienteDestino.setRemitente(remitenteClienteDestino);


            consultConceptos.setUsuario(usuario);
            consultConceptos.setClienteOrigen(clienteOrigen);
            consultConceptos.setClienteDestino(clienteDestino);

            consultConceptos.setAgenciaDestino(agenciaDestino2);
            consultConceptos.setIncluyeFlete(incluyeFlete);
            consultConceptos.setIncluyeDomicilio(incluyeDomicilio);
            consultConceptos.setIncluyeOtro(incluyeOtro);
            consultConceptos.setDireccionMac(direccionMac);
            consultConceptos.setCodigoVendedor(codigoVendedor);
            consultConceptos.setCodigoProducto(codigoProducto);
            consultConceptos.setValor(valor);
            consultConceptos.setCanalId(canal);
            consultConceptos.setCodigoTerminal(codigoTerminal);
            consultConceptos.setTipoUsuario(tipoUsuario);
            consultConceptos.setAutorizador(autorizador);
            consultConceptos.setIdSesion(idSesion);
            consultConceptos.setOperacionTransaccion(operacionTransaccion);



            String jsonData = new GsonBuilder().create().toJson(consultConceptos);

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

            CalcularConceptosOUTDto conceptosResponse = new CalcularConceptosOUTDto();

            conceptosResponse = new GsonBuilder().create().fromJson(response.toString(), CalcularConceptosOUTDto.class);

            return conceptosResponse;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            System.out.println("Metodo before ejecutado");
        }
    }
}
