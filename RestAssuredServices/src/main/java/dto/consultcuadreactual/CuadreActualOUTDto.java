package dto.consultcuadreactual;

import java.util.ArrayList;

public class CuadreActualOUTDto {

    public String codigoRespuesta;
    public boolean exito;
    public String fechaTransaccion;
    public String horaTransaccion;
    public Object fechaTransactionTime;
    public ArrayList<Object> mensajes;
    public String token;
    public String mensajeSp;
    public String ventaTotal;
    public ArrayList<CuadreProducto> cuadreProductos;

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

    public String getMensajeSp() {
        return mensajeSp;
    }

    public void setMensajeSp(String mensajeSp) {
        this.mensajeSp = mensajeSp;
    }

    public String getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(String ventaTotal) {
        this.ventaTotal = ventaTotal;
    }

    public ArrayList<CuadreProducto> getCuadreProductos() {
        return cuadreProductos;
    }

    public void setCuadreProductos(ArrayList<CuadreProducto> cuadreProductos) {
        this.cuadreProductos = cuadreProductos;
    }
}
