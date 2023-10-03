package dto.pagamillonariopaymentcupon;

import java.util.ArrayList;

public class PagaMillonarioCouponINDto {

    public ArrayList<ChanceNumero> chanceNumeros;
    public String serie1;
    public String serie2;
    public ArrayList<Loteria> loterias;
    public String login;

    public String direccionMac;
    public String volEquipo;
    public String idTransaccionSolicitud;
    public String codigoVendedor;
    public String codigoMedioPago;
    public String codigoProducto;
    public String loginCliente;
    public int valor;
    public String canalId;
    public String codigoTerminal;
    public String tipoUsuario;
    public String latitud;

    public ArrayList<ChanceNumero> getChanceNumero() {
        return chanceNumeros;
    }

    public void setChanceNumeros(ArrayList<ChanceNumero> chanceNumero) {
        this.chanceNumeros = chanceNumero;
    }

    public String getSerie1() {
        return serie1;
    }

    public void setSerie1(String serie1) {
        this.serie1 = serie1;
    }

    public String getSerie2() {
        return serie2;
    }

    public void setSerie2(String serie2) {
        this.serie2 = serie2;
    }

    public ArrayList<Loteria> getLoterias() {
        return loterias;
    }

    public void setLoterias(ArrayList<Loteria> loterias) {
        this.loterias = loterias;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDireccionMac() {
        return direccionMac;
    }

    public void setDireccionMac(String direccionMac) {
        this.direccionMac = direccionMac;
    }

    public String getVolEquipo() {
        return volEquipo;
    }

    public void setVolEquipo(String volEquipo) {
        this.volEquipo = volEquipo;
    }

    public String getIdTransaccionSolicitud() {
        return idTransaccionSolicitud;
    }

    public void setIdTransaccionSolicitud(String idTransaccionSolicitud) {
        this.idTransaccionSolicitud = idTransaccionSolicitud;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getCodigoMedioPago() {
        return codigoMedioPago;
    }

    public void setCodigoMedioPago(String codigoMedioPago) {
        this.codigoMedioPago = codigoMedioPago;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getLoginCliente() {
        return loginCliente;
    }

    public void setLoginCliente(String loginCliente) {
        this.loginCliente = loginCliente;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getCanalId() {
        return canalId;
    }

    public void setCanalId(String canalId) {
        this.canalId = canalId;
    }

    public String getCodigoTerminal() {
        return codigoTerminal;
    }

    public void setCodigoTerminal(String codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getSeriePrefijo() {
        return seriePrefijo;
    }

    public void setSeriePrefijo(String seriePrefijo) {
        this.seriePrefijo = seriePrefijo;
    }

    public String getSerieActual() {
        return serieActual;
    }

    public void setSerieActual(String serieActual) {
        this.serieActual = serieActual;
    }

    public String longitud;
    public long transactionTime;
    public String seriePrefijo;
    public String serieActual;
}
