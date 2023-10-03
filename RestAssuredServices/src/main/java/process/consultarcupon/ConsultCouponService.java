package process.consultarcupon;

import com.google.gson.GsonBuilder;
import dto.consultcuponessivemtx.*;
import dto.consultcuponessivemtx.response.ConsultCuponesOUTDto;


import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class ConsultCouponService {

   static String idSession = "3c25248d-ce51-40a7-8158-56d70581660d";
   static String login1 = "leceitracreta-2473@yopmail.com";
   static String tokenId = "eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2ODYyMzA4NzksImlzcyI6IntcInVzdWFyaW9cIjpcImxlY2VpdHJhY3JldGEtMjQ3M0B5b3BtYWlsLmNvbVwiLFwidGVybWluYWxcIjpcIkZDM0FFODg2LTY3MUItNEVCNC05NjBFLTg1OTEyNkQyQzZEMlwiLFwiY2FuYWxcIjpcIkFQUFwifSIsInN1YiI6ImI4Nzg3MGEyLThhNDktNGQzMi05ZmM3LTgyOTFhYmQ3MDI4ZCIsImV4cCI6MTY4NjMxNzI3OX0.uxtD7oftIEKfuPFNRqFrnZD67RQpzwMIkubu7WO7zhrSZ7pBV-syH-tUopCJBIutvQV1z7krVK8nE8ETXv31AQ";
   static String terminal = "FC3AE886-671B-4EB4-960E-859126D2C6D2";

    private static void preConfigConsultCuponService() throws KeyManagementException, NoSuchAlgorithmException, IOException {
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

    public static ConsultCuponesOUTDto consultCuponData() {
        try {
            preConfigConsultCuponService();


            String url = "https://chaxtest.pagatodo.com.co:8643/BilleteraVirtualWsService/rest/bva/json/consultarcupones";

            URL obj = new URL(url);

            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

            // Agregar cabeceras
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            ConsultCuponesINDto consultCupon = new ConsultCuponesINDto();

            DatosTransaccion datosTransaccion = new DatosTransaccion();

            DatosEntrada dato = new DatosEntrada();

            dato.setLogin("leceitracreta-2473@yopmail.com");
            dato.setTipoConsulta("CUPONES_ASIGNADOS");
            datosTransaccion.setCanal("APP");

            consultCupon.setDatosEntrada(dato);

            DatosCliente datosCliente = new DatosCliente();

            datosCliente.setIdSession(idSession);
            datosCliente.setLogin(login1);
            datosCliente.setRol("Usuario");
            datosCliente.setTerminal(terminal);

            datosTransaccion.setDatosCliente(datosCliente);


            datosTransaccion.setDispositivo("IOS");
            datosTransaccion.setFecha(fechaActual());

            Geolocalizacion geolocalizacion = new Geolocalizacion();
            geolocalizacion.setCiudad("Ac. 26 #69b9, Fontibón, Bogotá, Cundinamarca, Colombia");
            geolocalizacion.setLatitud("4.659349");
            geolocalizacion.setLongitud("-74.1086629");

            datosTransaccion.setGeolocalizacion(geolocalizacion);

            datosTransaccion.setTokenId(tokenId);
            datosTransaccion.setId(1);
            datosTransaccion.setIdAplicacion(0);
            datosTransaccion.setVersion("15.6.0");

            consultCupon.setDatosTransaccion(datosTransaccion);

            String jsonData = new GsonBuilder().create().toJson(consultCupon);


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


            ConsultCuponesOUTDto respuesta = new ConsultCuponesOUTDto();

            respuesta = new GsonBuilder().create().fromJson(response.toString(),ConsultCuponesOUTDto.class);

            return respuesta;

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }
        finally {
            System.out.println("Metodo before ejecutado");
        }


        }
    }

