package process.clientecaracteristicas;

import com.google.gson.GsonBuilder;
import dto.clientecaracteristica.Caracteristicas;
import dto.clientecaracteristica.ClienteCaracteristicasINDto;
import dto.clientecaracteristica.DatosCliente;
import dto.clientecaracteristica.DatosTransaccion;
import dto.clientecaracteristica.response.ClienteCaracteristicasOUTDto;
import dto.comunclass.Geolocalizacion;
import dto.consultarcontenidodinamico.response.ConsultContenidoDinamicoOUTDto;
import dto.loginbtw.response.LoginBtwOUTDto;
import process.LoginServiceBtw;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class ClienteCarateristicasServcie {

    private static void preConfigClientCharactersService() throws KeyManagementException, NoSuchAlgorithmException, IOException {
        // Crear un objeto de confianza que acepte todos los certificados
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
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
    public static String fechaActual(){

        long fechaHoraActual = System.currentTimeMillis();
        String valorActual = String.valueOf(fechaHoraActual);
        System.out.println(" fecha :" + fechaHoraActual);
        return valorActual;

    }
    public static Long fecha(){

        long fechaHoraActual = System.currentTimeMillis();


        return fechaHoraActual;

    }

    public static ClienteCaracteristicasOUTDto clienteCaracteristicasData(String tokenId, String idSesion){

        try{
            preConfigClientCharactersService();

            String url = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/clienteCaracteristicas";

            URL obj = new URL(url);

            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

            // Agregar cabeceras
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            ClienteCaracteristicasINDto clienteCaracteristicas = new ClienteCaracteristicasINDto();

            Caracteristicas caracteristicas = new Caracteristicas();

            caracteristicas.setIdSession(idSesion);
            clienteCaracteristicas.setCaracteristicas(caracteristicas);

            clienteCaracteristicas.setLogin("sebastian.gomez@dcsas.com.co");

            DatosTransaccion transaccion = new DatosTransaccion();

            transaccion.setId(1);
            transaccion.setFecha(fecha());

            Geolocalizacion geolocalizacion = new Geolocalizacion();

            geolocalizacion.setCiudad("Ac. 26 #69b9, Fontibón, Bogotá, Cundinamarca, Colombia");
            geolocalizacion.setLatitud("4.659349");
            geolocalizacion.setLongitud("-74.1086629");

            transaccion.setGeolocalizacion(geolocalizacion);

            DatosCliente datosCliente = new DatosCliente();

            datosCliente.setIdSession(fechaActual());
            datosCliente.setLogin("sebastian.gomez@dcsas.com.co");
            datosCliente.setRol("Usuario");
            datosCliente.setTerminal("ChIJCS5gz9F3P44RAZhIwcpbPMM,127.0.0.1");

            transaccion.setDatosCliente(datosCliente);



            transaccion.setTokenId(tokenId);
            //System.out.println("Este es el tokenId" + responseLogin.getDatosSalida().getTokenId());
            transaccion.setVersion("15.6.0");
            transaccion.setDispositivo("IOS");
            transaccion.setCanal("APP");

            clienteCaracteristicas.setDatosTransaccion(transaccion);

            String jsonData = new GsonBuilder().create().toJson(clienteCaracteristicas);

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

            ClienteCaracteristicasOUTDto clientResponse = new ClienteCaracteristicasOUTDto();

            clientResponse = new GsonBuilder().create().fromJson(response.toString(), ClienteCaracteristicasOUTDto.class);

            return clientResponse;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            System.out.println("Metodo before ejecutado");
        }
    }
}
