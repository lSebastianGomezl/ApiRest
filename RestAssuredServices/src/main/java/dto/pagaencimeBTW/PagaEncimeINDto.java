package dto.pagaencimeBTW;

import java.util.ArrayList;

public class PagaEncimeINDto {
    public int idPromocion;
    public ArrayList<ChanceNumero> chanceNumeros;
    public int idRifa;
    public ArrayList<Loteria> loterias;
    public String login;
    public String direccionMac;
    public String volEquipo;
    public String idTransaccionSolicitud;
    public String codigoMuncipio;
    public String codigoVendedor;
    public String codigoMedioPago;
    public String codigoProducto;
    public String loginCliente;
    public int valor;
    public String canalId;
    public String codigoTerminal;
    public String tipoUsuario;
    public String latitud;
    public String longitud;
    public String checksum;
    public long transactionTime;

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public ArrayList<ChanceNumero> getChanceNumeros() {
        return chanceNumeros;
    }

    public void setChanceNumeros(ArrayList<ChanceNumero> chanceNumeros) {
        this.chanceNumeros = chanceNumeros;
    }

    public int getIdRifa() {
        return idRifa;
    }

    public void setIdRifa(int idRifa) {
        this.idRifa = idRifa;
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

    public String getCodigoMuncipio() {
        return codigoMuncipio;
    }

    public void setCodigoMuncipio(String codigoMuncipio) {
        this.codigoMuncipio = codigoMuncipio;
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

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
    }
}
