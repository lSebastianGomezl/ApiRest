package dto.randomnumber;

import java.util.ArrayList;

public class RandomNumberOUTDto {

    public ArrayList<String> numerosGenerados;
    public String codigoRespuesta;
    public boolean exito;
    public String fechaTransaccion;
    public Object fechaTransactionTime;
    public String horaTransaccion;
    public String mensaje;
    public ArrayList<Object> mensajes;
    public String token;


    public ArrayList<String> getNumerosGenerados() {
        return numerosGenerados;
    }

    public void setNumerosGenerados(ArrayList<String> numerosGenerados) {
        this.numerosGenerados = numerosGenerados;
    }

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

    public Object getFechaTransactionTime() {
        return fechaTransactionTime;
    }

    public void setFechaTransactionTime(Object fechaTransactionTime) {
        this.fechaTransactionTime = fechaTransactionTime;
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

    public ArrayList<Object> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Object> mensajes) {
        this.mensajes = mensajes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
