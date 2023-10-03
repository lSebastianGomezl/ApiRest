package dto.app.superastro;

public class VentaSuperAstro {

    public String tipoIdentificacion;
    public String numeroIdentificacion;
    public String registroDANE;
    public String idTipoTarjeta;
    public String cantidadSorteos;
    public Sorteos sorteos;
    public String cantidadApuestas;
    public DetalleApuestas detalleApuestas;
    public String tipoVenta;
    public String codigoSorteo;
    public String monto;

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getRegistroDANE() {
        return registroDANE;
    }

    public void setRegistroDANE(String registroDANE) {
        this.registroDANE = registroDANE;
    }

    public String getIdTipoTarjeta() {
        return idTipoTarjeta;
    }

    public void setIdTipoTarjeta(String idTipoTarjeta) {
        this.idTipoTarjeta = idTipoTarjeta;
    }

    public String getCantidadSorteos() {
        return cantidadSorteos;
    }

    public void setCantidadSorteos(String cantidadSorteos) {
        this.cantidadSorteos = cantidadSorteos;
    }

    public Sorteos getSorteos() {
        return sorteos;
    }

    public void setSorteos(Sorteos sorteos) {
        this.sorteos = sorteos;
    }

    public String getCantidadApuestas() {
        return cantidadApuestas;
    }

    public void setCantidadApuestas(String cantidadApuestas) {
        this.cantidadApuestas = cantidadApuestas;
    }

    public DetalleApuestas getDetalleApuestas() {
        return detalleApuestas;
    }

    public void setDetalleApuestas(DetalleApuestas detalleApuestas) {
        this.detalleApuestas = detalleApuestas;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getCodigoSorteo() {
        return codigoSorteo;
    }

    public void setCodigoSorteo(String codigoSorteo) {
        this.codigoSorteo = codigoSorteo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
