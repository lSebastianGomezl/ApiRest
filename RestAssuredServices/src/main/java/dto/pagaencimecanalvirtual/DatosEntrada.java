package dto.pagaencimecanalvirtual;

import java.util.ArrayList;

public class DatosEntrada {
    public ArrayList<LstProductoPromocionalPagaEncime> lstProductoPromocionalPagaEncime;
    public String idTipoTarjeta;
    public int numeroCuotas;

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
}
