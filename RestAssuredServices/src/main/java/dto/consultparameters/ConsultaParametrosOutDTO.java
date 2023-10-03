package dto.consultparameters;

import java.util.ArrayList;

public class ConsultaParametrosOutDTO {
    public Object codigoRespuesta;
    public boolean exito;
    public String fechaTransaccion;
    public String horaTransaccion;
    public Object fechaTransactionTime;
    public ArrayList<Object> mensajes;
    public String token;
    public ArrayList<Loteria> loterias;

    public ArrayList<Loteria> getLoterias() {
        return loterias;
    }

    public void setLoterias(ArrayList<Loteria> loterias) {
        this.loterias = loterias;
    }

    public ArrayList<MotivosAnulacion> getMotivosAnulacion() {
        return motivosAnulacion;
    }

    public void setMotivosAnulacion(ArrayList<MotivosAnulacion> motivosAnulacion) {
        this.motivosAnulacion = motivosAnulacion;
    }

    public ArrayList<MotivosAnulacion> motivosAnulacion;

    public Object getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Object codigoRespuesta) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
