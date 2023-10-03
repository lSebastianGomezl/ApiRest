package pvma_cor;
import com.google.gson.GsonBuilder;
import dto.LoginINDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;



public class LoginRestTest {


    public LoginRestTest(){
        RestAssured.baseURI="http://sivemtxpru.dcsas.com.co:8180/SivemTxWS/rest/seguridad/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }


    public LoginINDto initValue(){
        LoginINDto login = new LoginINDto();

        login.setCodigoVendedor("40429963");
        login.setCanalId("PVMA");
        login.setCodigoTerminal("862634041369599");
        login.setTipoUsuario("VENDEDOR");
        login.setClave("4063200952234FEC0E8739CBEA17AE579FB6E994ED9BDC264B183578F2324789");
        return login;
    }

    //CC399D73903F06EE694032AB0538F05634FF7E1CE5E8E50AC330A871484F34CF
    //9865308
    //862634041369599
 /*
    public LoginINDto initValue(){
        LoginINDto login = new LoginINDto();

        login.setCodigoVendedor("9865308");
        login.setCanalId("PVA");
        login.setCodigoTerminal("194T40283");
        login.setTipoUsuario("VENDEDOR");
        login.setClave("CC399D73903F06EE694032AB0538F05634FF7E1CE5E8E50AC330A871484F34CF");
        return login;
    }


    /*
    * El loginSuccessfulTest prueba que la autenticacion del usuario
    * sea  exitosa
    * */
    @Test
    public void loginSuccessfulTest() {

        LoginINDto successfulData = initValue();

        String jsonBody = new GsonBuilder().create().toJson(successfulData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("mensaje", equalTo("Exito"));


        //System.out.println(response);
    }

    /*
     * El loginValuePaperBinTest prueba que el atributo "papelerias" no llegue
     *  en nulo o vacio.
     * */
    @Test
    public void loginValuePaperBinTest() {

        LoginINDto valuePaperData = initValue();
        String jsonBody = new GsonBuilder().create().toJson(valuePaperData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("papelerias", both(not(empty())).and(notNullValue()));
    }

    /*
     * El loginValueUserTest prueba que el atributo "usuario" no llegue
     *  en nulo o vacio.
     * */

    @Test
    public void loginValueUserTest() {
        LoginINDto valueUserData = initValue();
        String jsonBody = new GsonBuilder().create().toJson(valueUserData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("usuario", both(not(empty())).and(notNullValue()));
    }

    /*
     * El loginValueTerminalTest prueba que el atributo "terminal" no llegue
     *  en nulo o vacio.
     * */

    @Test
    public void loginValueTerminalTest() {
        LoginINDto valueTerminalData = initValue();
        String jsonBody = new GsonBuilder().create().toJson(valueTerminalData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("terminal", notNullValue());
    }

    /*
     * El loginValueParametersTest prueba que el atributo "parametros" no llegue
     *  en nulo o vacio.
     * */

    @Test
    public void loginValueParametersTest() {

        LoginINDto valueParametersData = initValue();
        String jsonBody = new GsonBuilder().create().toJson(valueParametersData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("parametros", both(not(empty())).and(notNullValue()));
    }

    /*
     * El loginValueIdSessionTest prueba que el atributo "idSesion" no llegue
     *  en nulo o vacio.
     * */

    @Test
    public void loginValueIdSessionTest() {

        LoginINDto valueIdSesionData = initValue();
        String jsonBody = new GsonBuilder().create().toJson(valueIdSesionData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("idSesion", both(not(empty())).and(notNullValue()));
    }
    /*
     * El loginFieldEmptyTest prueba que las validacion al pasar los campos completamente vacios
     * el usuario no pueda autenticarse y le muestra el mensaje que el vendedor no existe.
     * */

    @Test
    public void loginFieldEmptyTest() {
        LoginINDto fieldsEmptyData = initValue();

        fieldsEmptyData.setCodigoVendedor("");
        fieldsEmptyData.setCanalId("");
        fieldsEmptyData.setCodigoTerminal("");
        fieldsEmptyData.setTipoUsuario("");

        String jsonBody = new GsonBuilder().create().toJson(fieldsEmptyData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("El vendedor no existe"));


        //System.out.println(response);
    }

    /*
     * El loginIncorrectDataTest prueba que las validacion cuando el usuario ingresa datos incorrectos
     * el usuario no pueda autenticarse y le muestra el mensaje que el vendedor no existe.
     * */

    @Test
    public void loginIncorrectDataTest() {

        LoginINDto incorrectData = initValue();

        incorrectData.setCodigoVendedor("1094956106");
        incorrectData.setCodigoTerminal("419907877534994");

        String jsonBody = new GsonBuilder().create().toJson(incorrectData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("El vendedor no existe"));

    }

    /*
     * El loginCodigoTerminalEmpty prueba que las validacion cuando el usuario ingresa datos
     * sin contraseña no pueda autenticarse y le muestra el mensaje Terminal no existe.
     * */

    @Test
    public void loginCodigoTerminalEmpty() {

        LoginINDto codigoTerminalData = initValue();

        codigoTerminalData.setCodigoTerminal("");

        String jsonBody = new GsonBuilder().create().toJson(codigoTerminalData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Terminal no existe."));

    }

    /*
     * El LoginCodigoVendedorEmptyTest prueba que las validacion cuando el usuario ingresa datos
     * sin codigoVendedor no pueda autenticarse y le muestra el mensaje El vendedor no existe.
     * */
    @Test
    public void LoginCodigoVendedorEmptyTest() {

        LoginINDto codigoVendedorEmpty = initValue();

        codigoVendedorEmpty.setCodigoVendedor("");

        String jsonBody = new GsonBuilder().create().toJson(codigoVendedorEmpty);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("El vendedor no existe"));

    }

    /*
     * El LoginCanalIdEmptyTest prueba que las validacion cuando el usuario ingresa datos
     * sin canalId no pueda autenticarse ya que este campo no puede estar vacio, por lo tanto
     * deveulve el codigo de respuesta 99.
     * */

    @Test
    public void LoginCanalIdEmptyTest() {

        LoginINDto canalIdEmpty = initValue();
        canalIdEmpty.setCanalId("");

        String jsonBody = new GsonBuilder().create().toJson(canalIdEmpty);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("99"));

    }

    /*
     * El LoginCanalIdValueChangeTest prueba que las validacion que el campo canalId se encuentra en
     * la capacidad de recibir cualquier tipo de valor inclusive caracteres especiales,por lo tanto
     * la prueba deberia recibir un codigoRespuesta 99 ya que el valor insertado no es el adecuado.
     * */
    /*
    @Test
    public void LoginCanalIdValueChangeTest() {

        LoginINDto canalIdValueData = initValue();
        canalIdValueData.setCanalId("====");

        String jsonBody = new GsonBuilder().create().toJson(canalIdValueData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .statusCode(200)
                .body("codigoRespuesta", equalTo("99"));

    }

     */

    /*
     * El LoginClaveValueTest prueba que la autenticacion del usuario
     * sea  exitosa añadiendo el atributo de clave
     * */

    @Test
    public  void LoginClaveValueTest(){
        LoginINDto claveValueData = initValue();

        claveValueData.setClave("4063200952234FEC0E8739CBEA17AE579FB6E994ED9BDC264B183578F2324789");

        String jsonBody = new GsonBuilder().create().toJson(claveValueData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .body("token", notNullValue())
                .body("mensaje", equalTo("Exito"));
    }

    /*
     * El LoginClaveValueTest prueba que la autenticacion del usuario
     * sea  incorrecta ya que  se añade el atributo  "clave" con un valor vacio.
     * */

    @Test
    public  void LoginClaveEmptyTest(){
        LoginINDto claveEmptyData = initValue();

        claveEmptyData.setClave("");

        String jsonBody = new GsonBuilder().create().toJson(claveEmptyData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .body("mensaje", equalTo("Clave incorrecta."))
                .body("exito", equalTo(false));
    }

    /*
     * El LoginClaveValueTest prueba la validacion autenticacion del usuario
     * de el atributo "clave" con un valor erroneo.
     * */

    @Test
    public  void LoginClaveIncorrectTest(){
        LoginINDto claveIncorrectData = initValue();

        claveIncorrectData.setClave("1234");

        String jsonBody = new GsonBuilder().create().toJson(claveIncorrectData);

                given()
                //.contentType(ContentType.JSON)
                .body(jsonBody)
                .post(baseURI+"autenticarUsuario/")
                .then()
                .body("mensaje", equalTo("Clave incorrecta."))
                .body("exito", equalTo(false));
    }


}
