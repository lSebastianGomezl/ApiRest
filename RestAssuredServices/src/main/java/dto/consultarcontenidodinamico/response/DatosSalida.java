package dto.consultarcontenidodinamico.response;

import java.util.ArrayList;

public class DatosSalida {

    public ArrayList<LstContenidoDinamico> lstContenidoDinamico;

    public ArrayList<LstContenidoDinamico> getLstContenidoDinamico() {
        return lstContenidoDinamico;
    }

    public void setLstContenidoDinamico(ArrayList<LstContenidoDinamico> lstContenidoDinamico) {
        this.lstContenidoDinamico = lstContenidoDinamico;
    }
}
