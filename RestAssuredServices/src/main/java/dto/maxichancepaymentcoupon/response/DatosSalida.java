package dto.maxichancepaymentcoupon.response;

import java.util.ArrayList;

public class DatosSalida {
    public Object url;
    public Saldo saldo;
    public int idTransaccion;
    public Object nombre;
    public ArrayList<Object> mensajes;
    public Object comprobante;
    public ArrayList<ListProductoRespuestaW> listProductoRespuestaWs;
    public SaldoCupones saldoCupones;

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Saldo getSaldo() {
        return saldo;
    }

    public void setSaldo(Saldo saldo) {
        this.saldo = saldo;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Object> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Object> mensajes) {
        this.mensajes = mensajes;
    }

    public Object getComprobante() {
        return comprobante;
    }

    public void setComprobante(Object comprobante) {
        this.comprobante = comprobante;
    }

    public ArrayList<ListProductoRespuestaW> getListProductoRespuestaWs() {
        return listProductoRespuestaWs;
    }

    public void setListProductoRespuestaWs(ArrayList<ListProductoRespuestaW> listProductoRespuestaWs) {
        this.listProductoRespuestaWs = listProductoRespuestaWs;
    }

    public SaldoCupones getSaldoCupones() {
        return saldoCupones;
    }

    public void setSaldoCupones(SaldoCupones saldoCupones) {
        this.saldoCupones = saldoCupones;
    }
}
