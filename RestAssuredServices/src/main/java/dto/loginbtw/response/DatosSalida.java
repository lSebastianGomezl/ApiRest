package dto.loginbtw.response;

import java.util.ArrayList;

public class DatosSalida {

    public int timeout;
    public Usuario usuario;
    public ArrayList<Mensaje> mensajes;
    public String tokenId;
    public boolean autenticado;
    public long ultimoAcceso;
    public String ultimaIP;
    public ArrayList<String> lstProductosActivos;
    public boolean statusMenu;
    public String msjMenus;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public long getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(long ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getUltimaIP() {
        return ultimaIP;
    }

    public void setUltimaIP(String ultimaIP) {
        this.ultimaIP = ultimaIP;
    }

    public ArrayList<String> getLstProductosActivos() {
        return lstProductosActivos;
    }

    public void setLstProductosActivos(ArrayList<String> lstProductosActivos) {
        this.lstProductosActivos = lstProductosActivos;
    }

    public boolean isStatusMenu() {
        return statusMenu;
    }

    public void setStatusMenu(boolean statusMenu) {
        this.statusMenu = statusMenu;
    }

    public String getMsjMenus() {
        return msjMenus;
    }

    public void setMsjMenus(String msjMenus) {
        this.msjMenus = msjMenus;
    }
}
