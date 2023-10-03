package process;

import com.google.gson.GsonBuilder;
import dto.LoginINDto;
import dto.LoginOUTDto;
import dto.RechargeINDto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginService {

    public static LoginOUTDto getDataLogin(){
        try {
            String url = "http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/seguridad/autenticarUsuario/";

            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Agregar cabeceras
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            LoginINDto login = new LoginINDto();

            login.setCodigoVendedor("40429963");
            login.setCanalId("PVMA");
            login.setCodigoTerminal("862634041369599");
            login.setTipoUsuario("VENDEDOR");
            login.setClave("4063200952234FEC0E8739CBEA17AE579FB6E994ED9BDC264B183578F2324789");


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
            }
            in.close();

            LoginOUTDto respuesta = new LoginOUTDto();


            respuesta = new GsonBuilder().create().fromJson(response.toString(),LoginOUTDto.class);



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
