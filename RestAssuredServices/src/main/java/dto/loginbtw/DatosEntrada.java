package dto.loginbtw;

import java.util.ArrayList;

public class DatosEntrada {

    public ArrayList<LstContenidoDinamico> lstContenidoDinamico;
    public String password;
    public String login;

    public ArrayList<LstContenidoDinamico> getLstContenidoDinamico() {
        return lstContenidoDinamico;
    }

    public void setLstContenidoDinamico(ArrayList<LstContenidoDinamico> lstContenidoDinamico) {
        this.lstContenidoDinamico = lstContenidoDinamico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
