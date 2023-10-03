package process;

import com.google.gson.GsonBuilder;

import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
import dto.loginbtw.*;
import dto.loginbtw.response.LoginBtwOUTDto;
import process.clientecaracteristicas.ClienteCarateristicasServcie;
import process.consultcontenidodinamico.ConsultContenidoDinamicoService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


/* Desactivar SSL*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class LoginServiceBtw {

    static String idSession;

    static String login1 = "sebastian.gomez@dcsas.com.co";
    static String password = "12C4BDB852D2A7F21A47ECA11A8F5AE539089276E143525762DA3ECFC3B15960";

    static String terminal = "ChIJCS5gz9F3P44RAZhIwcpbPMM,127.0.0.1";




    private static void preConfigLoginServiceBtw() throws KeyManagementException, NoSuchAlgorithmException, IOException {
        // Crear un objeto de confianza que acepte todos los certificados
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                }
        };

        // Crear un contexto SSL y deshabilitar la verificación del certificado
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        // Establecer el contexto SSL en la conexión HTTPS
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // Desactivar la verificación del nombre de host
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

    }

    public static long fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public static LoginBtwOUTDto getDataLogin(){

        try {
            preConfigLoginServiceBtw();


            String url = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/login";

            URL obj = new URL(url);

            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

            // Agregar cabeceras
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");




           //ConsultContenidoDinamicoOUTDto respuesta = ConsultContenidoDinamicoService.contenidoDinamicoData();
           // System.out.println(respuesta);




            LoginBtwINDto login = new LoginBtwINDto();

            DatosTransaccion  datosTransaccion = new DatosTransaccion();
            datosTransaccion.setCanal("APP");

            DatosCliente datosCliente = new DatosCliente();
            datosCliente.setIdSession(fechaActual());
            datosCliente.setLogin(login1);
            datosCliente.setRol("Usuario");
            datosCliente.setTerminal(terminal);

            datosTransaccion.setDatosCliente(datosCliente);

            datosTransaccion.setDispositivo("IOS");
            datosTransaccion.setFecha(fechaActual());
            datosTransaccion.setId(1);
            datosTransaccion.setIdAplicacion(0);
            datosTransaccion.setVersion("15.6.0");

            Geolocalizacion geolocalizacion = new Geolocalizacion();
            geolocalizacion.setCiudad("");
            geolocalizacion.setLatitud("4.659349");
            geolocalizacion.setLongitud("-74.1086629");

            datosTransaccion.setGeolocalizacion(geolocalizacion);

            login.setDatosTransaccion(datosTransaccion);

            DatosEntrada datosEntrada = new DatosEntrada();

            ArrayList<LstContenidoDinamico> listContenidoDinamico = new ArrayList<>();

            LstContenidoDinamico lstContenidoDinamico1 = new LstContenidoDinamico();

            lstContenidoDinamico1.setConsecutivo("12");
            lstContenidoDinamico1.setIdContenido("13001");
            lstContenidoDinamico1.setSigla("TyC");
            lstContenidoDinamico1.setAceptado("ACEPTADO");
            lstContenidoDinamico1.setTextoMostrado("Acepto los Terminos y Condiciones 2023");


            LstContenidoDinamico lstContenidoDinamico2 = new LstContenidoDinamico();
            lstContenidoDinamico2.setConsecutivo("10");
            lstContenidoDinamico2.setIdContenido("9002");
            lstContenidoDinamico2.setSigla("Portafolio");
            lstContenidoDinamico2.setAceptado("ACEPTADO");
            lstContenidoDinamico2.setTextoMostrado("Conozco y acepto el Portafolio actual habilitado para el canal digital ");

            LstContenidoDinamico lstContenidoDinamico3 = new LstContenidoDinamico();
            lstContenidoDinamico3.setConsecutivo("5");
            lstContenidoDinamico3.setIdContenido("9001");
            lstContenidoDinamico3.setSigla("TratamientoDatos");
            lstContenidoDinamico3.setAceptado("ACEPTADO");
            lstContenidoDinamico3.setTextoMostrado("Acepto Politica de tratamiento de datos Personales");

            listContenidoDinamico.add(lstContenidoDinamico1);
            listContenidoDinamico.add(lstContenidoDinamico2);
            listContenidoDinamico.add(lstContenidoDinamico3);


            datosEntrada.setLstContenidoDinamico(listContenidoDinamico);

            datosEntrada.setPassword(password);
            datosEntrada.setLogin(login1);

            login.setDatosEntrada(datosEntrada);





            String jsonData = new GsonBuilder().create().toJson(login);



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
                System.out.println(inputLine);
            }
            in.close();

            LoginBtwOUTDto responseLogin = new LoginBtwOUTDto();


            responseLogin = new GsonBuilder().create().fromJson(response.toString(),LoginBtwOUTDto.class);



            return responseLogin;

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }
        finally {
            System.out.println("Metodo before ejecutado");
        }

    }
}
