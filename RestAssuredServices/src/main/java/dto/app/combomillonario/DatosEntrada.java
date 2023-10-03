package dto.app.combomillonario;

public class DatosEntrada {

    public ProductoComboMillonario productoComboMillonario;
    public String idTipoTarjeta;
    public int numeroCuotas;

    public ProductoComboMillonario getProductoComboMillonario() {
        return productoComboMillonario;
    }

    public void setProductoComboMillonario(ProductoComboMillonario productoComboMillonario) {
        this.productoComboMillonario = productoComboMillonario;
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
