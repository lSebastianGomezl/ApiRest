package process.consultcontenidodinamico;

import com.google.gson.GsonBuilder;
import dto.comunclass.Geolocalizacion;
import dto.consultarcontenidodinamico.ConsultContenidoDinamicoINDto;
import dto.consultarcontenidodinamico.DatosCliente;
import dto.consultarcontenidodinamico.DatosEntrada;
import dto.consultarcontenidodinamico.DatosTransaccion;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class ConsultContenidoDinamicoService {

    private static void preConfigContenidoDinamicoService() throws KeyManagementException, NoSuchAlgorithmException, IOException {
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



    public static long fechaActual() {

        long fechaHoraActual = System.currentTimeMillis();
        System.out.println(" fecha :" + fechaHoraActual);
        return fechaHoraActual;

    }

    public static ConsultContenidoDinamicoOUTDto contenidoDinamicoData() {
        try {

            preConfigContenidoDinamicoService();

            String url = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/consultarContenidoDinamico";

            URL obj = new URL(url);

           HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();


            // Agregar cabeceras
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            ConsultContenidoDinamicoINDto consult = new ConsultContenidoDinamicoINDto();

            DatosEntrada datosEntrada = new DatosEntrada();

            consult.setDatosEntrada(datosEntrada);

            DatosTransaccion datosTransaccion = new DatosTransaccion();

            datosTransaccion.setId(1);
            datosTransaccion.setFecha(fechaActual());

            Geolocalizacion geolocalizacion = new Geolocalizacion();

            geolocalizacion.setCiudad("Ac. 26 #69b9, Fontibón, Bogotá, Cundinamarca, Colombia");
            geolocalizacion.setLongitud("4.659349");
            geolocalizacion.setLatitud("-74.1086629");

            datosTransaccion.setGeolocalizacion(geolocalizacion);

            DatosCliente datosCliente = new DatosCliente();

            datosCliente.setIdSession(Integer.toString((int) fechaActual()));
            datosCliente.setRol("Usuario");
            datosCliente.setTerminal("ChIJCS5gz9F3P44RAZhIwcpbPMM,127.0.0.1");

            datosTransaccion.setDatosCliente(datosCliente);

            datosTransaccion.setVersion("15.6.0");
            datosTransaccion.setDispositivo("IOS");
            datosTransaccion.setCanal("APP");
            datosTransaccion.setIdAplicacion(0);

            consult.setDatosTransaccion(datosTransaccion);


            String jsonData = new GsonBuilder().create().toJson(consult);

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

            ConsultContenidoDinamicoOUTDto consultResponse = new ConsultContenidoDinamicoOUTDto();

            consultResponse = new GsonBuilder().create().fromJson(response.toString(), ConsultContenidoDinamicoOUTDto.class);

            return consultResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            System.out.println("Metodo before ejecutado");
        }
    }
}
