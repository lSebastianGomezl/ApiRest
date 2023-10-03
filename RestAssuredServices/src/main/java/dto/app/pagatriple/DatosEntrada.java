package dto.app.pagatriple;

public class DatosEntrada {

    public String idTipoTarjeta;
    public VentaICC ventaICC;
    public int numeroCuotas;
    public String cvv;
    public String fechaExpiracion;

    public String getIdTipoTarjeta() {
        return idTipoTarjeta;
    }

    public void setIdTipoTarjeta(String idTipoTarjeta) {
        this.idTipoTarjeta = idTipoTarjeta;
    }

    public VentaICC getVentaICC() {
        return ventaICC;
    }

    public void setVentaICC(VentaICC ventaICC) {
        this.ventaICC = ventaICC;
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
