package dto.chancemillonariopaymentcuopon;

import java.util.ArrayList;

public class DatosEntrada {

    public ArrayList<LstProductoChanceMillonario> lstProductoChanceMillonario;
    public String idTipoTarjeta;
    public int numeroCuotas;
    public String cvv;
    public String fechaExpiracion;
    public int idCupon;

    public ArrayList<LstProductoChanceMillonario> getLstProductoChanceMillonario() {
        return lstProductoChanceMillonario;
    }

    public void setLstProductoChanceMillonario(ArrayList<LstProductoChanceMillonario> lstProductoChanceMillonario) {
        this.lstProductoChanceMillonario = lstProductoChanceMillonario;
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

    public int getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }
}
