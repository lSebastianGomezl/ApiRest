package dto.consultcuponessivemtx.response;

import dto.loginbtw.response.Mensaje;

import java.util.ArrayList;

public class DatosSalida {

    public ArrayList<String> mensajes;
    public ArrayList<ListaCupones> listaCupones;

    public ArrayList<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<String> mensajes) {
        this.mensajes = mensajes;
    }

    public ArrayList<ListaCupones> getListaCupones() {
        return listaCupones;
    }

    public void setListaCupones(ArrayList<ListaCupones> listaCupones) {
        this.listaCupones = listaCupones;
    }
}
