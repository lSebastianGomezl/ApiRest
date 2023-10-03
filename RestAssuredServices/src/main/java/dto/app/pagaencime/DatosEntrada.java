package dto.app.pagaencime;

import java.util.ArrayList;

public class DatosEntrada {
    public ArrayList<LstProductoPromocionalPagaEncime> lstProductoPromocionalPagaEncime;
    public String idTipoTarjeta;
    public int numeroCuotas;
    public String cvv;
    public String fechaExpiracion;

    public ArrayList<LstProductoPromocionalPagaEncime> getLstProductoPromocionalPagaEncime() {
        return lstProductoPromocionalPagaEncime;
    }

    public void setLstProductoPromocionalPagaEncime(ArrayList<LstProductoPromocionalPagaEncime> lstProductoPromocionalPagaEncime) {
        this.lstProductoPromocionalPagaEncime = lstProductoPromocionalPagaEncime;
    }

    public String getIdTipoTarjeta() {
        return idTipoTarjeta;
    }

    public void setIdTipoTarjeta(String idTipoTarjeta) {
        this.idTipoTarjeta = idTipoTarjeta;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
}
