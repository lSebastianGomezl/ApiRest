package dto.app.pagamillonario;

import dto.comunclass.Loteria;

import java.util.ArrayList;

public class LstProductoPagaMillonario {

    public int cantidad;
    public int valorIva;
    public int valorPagar;
    public long fechaSorteo;
    public int valorProducto;
    public int valorUnitario;
    public int cantidadPuntos;
    public int valorDescuento;
    public int idProductoTrans;
    public int valorTransaccion;
    public String codigoModalidad;
    public String tipoTarifaTransaccion;
    public ArrayList<String> numeros;
    public ArrayList<Loteria> loterias;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValorIva() {
        return valorIva;
    }

    public void setValorIva(int valorIva) {
        this.valorIva = valorIva;
    }

    public int getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(int valorPagar) {
        this.valorPagar = valorPagar;
    }

    public long getFechaSorteo() {
        return fechaSorteo;
    }

    public void setFechaSorteo(long fechaSorteo) {
        this.fechaSorteo = fechaSorteo;
    }

    public int getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(int valorProducto) {
        this.valorProducto = valorProducto;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getCantidadPuntos() {
        return cantidadPuntos;
    }

    public void setCantidadPuntos(int cantidadPuntos) {
        this.cantidadPuntos = cantidadPuntos;
    }

    public int getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(int valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public int getIdProductoTrans() {
        return idProductoTrans;
    }

    public void setIdProductoTrans(int idProductoTrans) {
        this.idProductoTrans = idProductoTrans;
    }

    public int getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(int valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public String getCodigoModalidad() {
        return codigoModalidad;
    }

    public void setCodigoModalidad(String codigoModalidad) {
        this.codigoModalidad = codigoModalidad;
    }

    public String getTipoTarifaTransaccion() {
        return tipoTarifaTransaccion;
    }

    public void setTipoTarifaTransaccion(String tipoTarifaTransaccion) {
        this.tipoTarifaTransaccion = tipoTarifaTransaccion;
    }

    public ArrayList<String> getNumeros() {
        return numeros;
    }

    public void setNumeros(ArrayList<String> numeros) {
        this.numeros = numeros;
    }

    public ArrayList<Loteria> getLoterias() {
        return loterias;
    }

    public void setLoterias(ArrayList<Loteria> loterias) {
        this.loterias = loterias;
    }
}
