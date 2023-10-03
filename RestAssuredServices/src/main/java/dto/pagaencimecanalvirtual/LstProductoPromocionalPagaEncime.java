package dto.pagaencimecanalvirtual;

import java.util.ArrayList;

public class LstProductoPromocionalPagaEncime {

    public ArrayList<Moldalidade> moldalidades;
    public ArrayList<Loteria> loterias;
    public long fechaSorteo;
    public int valorUnitario;
    public int cantidad;
    public int valorProducto;
    public int valorDescuento;
    public int valorIva;
    public String tipoTarifaTransaccion;
    public int valorTransaccion;
    public int valorPagar;
    public int cantidadPuntos;
    public int idProductoTrans;

    public ArrayList<Moldalidade> getMoldalidades() {
        return moldalidades;
    }

    public void setMoldalidades(ArrayList<Moldalidade> moldalidades) {
        this.moldalidades = moldalidades;
    }

    public ArrayList<Loteria> getLoterias() {
        return loterias;
    }

    public void setLoterias(ArrayList<Loteria> loterias) {
        this.loterias = loterias;
    }

    public long getFechaSorteo() {
        return fechaSorteo;
    }

    public void setFechaSorteo(long fechaSorteo) {
        this.fechaSorteo = fechaSorteo;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(int valorProducto) {
        this.valorProducto = valorProducto;
    }

    public int getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(int valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public int getValorIva() {
        return valorIva;
    }

    public void setValorIva(int valorIva) {
        this.valorIva = valorIva;
    }

    public String getTipoTarifaTransaccion() {
        return tipoTarifaTransaccion;
    }

    public void setTipoTarifaTransaccion(String tipoTarifaTransaccion) {
        this.tipoTarifaTransaccion = tipoTarifaTransaccion;
    }

    public int getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(int valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public int getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(int valorPagar) {
        this.valorPagar = valorPagar;
    }

    public int getCantidadPuntos() {
        return cantidadPuntos;
    }

    public void setCantidadPuntos(int cantidadPuntos) {
        this.cantidadPuntos = cantidadPuntos;
    }

    public int getIdProductoTrans() {
        return idProductoTrans;
    }

    public void setIdProductoTrans(int idProductoTrans) {
        this.idProductoTrans = idProductoTrans;
    }
}
