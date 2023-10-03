package dto.consultarcategoriafiltro.response;

import java.util.ArrayList;

public class ColsutConvenioFiltroOUTDto {

    public Object codigoRespuesta;
    public boolean exito;
    public Object fechaTransaccion;
    public Object horaTransaccion;
    public Object fechaTransactionTime;
    public ArrayList<Object> mensajes;
    public CategoriasConvenios categoriasConvenios;
    public ArrayList<Object> referencias;


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

    public Object getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Object fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Object getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(Object horaTransaccion) {
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

    public CategoriasConvenios getCategoriasConvenios() {
        return categoriasConvenios;
    }

    public void setCategoriasConvenios(CategoriasConvenios categoriasConvenios) {
        this.categoriasConvenios = categoriasConvenios;
    }

    public ArrayList<Object> getReferencias() {
        return referencias;
    }

    public void setReferencias(ArrayList<Object> referencias) {
        this.referencias = referencias;
    }
}
