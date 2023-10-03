package dto;

import dto.login.Papeleria;
import dto.login.Parametro;
import dto.login.Terminal;
import dto.login.Usuario;

import java.util.ArrayList;

public class LoginOUTDto {

    public String codigoRespuesta;
    public boolean exito;
    public String fechaTransaccion;
    public String horaTransaccion;
    public String mensaje;
    public Object fechaTransactionTime;
    public ArrayList<Object> mensajes;
    public String token;
    public Usuario usuario;
    public Terminal terminal;
    public ArrayList<Papeleria> papelerias;
    public String idSesion;
    public ArrayList<Parametro> parametros;

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getFechaTransactionTime() {
        return fechaTransactionTime;
    }

    public void setFechaTransactionTime(Object fechaTransactionTime) {
        this.fechaTransactionTime = fechaTransactionTime;
    }

    public ArrayList<Object> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Object> mensajes) {
        this.mensajes = mensajes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public ArrayList<Papeleria> getPapelerias() {
        return papelerias;
    }

    public void setPapelerias(ArrayList<Papeleria> papelerias) {
        this.papelerias = papelerias;
    }

    public ArrayList<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<Parametro> parametros) {
        this.parametros = parametros;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
