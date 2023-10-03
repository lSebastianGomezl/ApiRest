package dto.pagaencimePVMA;

import java.util.ArrayList;

public class PagaEncimePVMAINDto {

    public int idPromocion;
    public ArrayList<ChanceNumero> chanceNumeros;
    public int idRifa;
    public String serie1;
    public String serie2;
    public ArrayList<Loteria> loterias;
    public String codigoVendedor;
    public String codigoProducto;
    public int valor;
    public int valorSugerido;
    public String canalId;
    public String codigoTerminal;
    public String tipoUsuario;
    public int secuenciaTransaccion;
    public String idSesion;
    public String token;
    public Long transactionTime;
    public String operacionTransaccion;
    public String seriePrefijo;
    public String serieActual;


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

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValorSugerido() {
        return valorSugerido;
    }

    public void setValorSugerido(int valorSugerido) {
        this.valorSugerido = valorSugerido;
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

    public int getSecuenciaTransaccion() {
        return secuenciaTransaccion;
    }

    public void setSecuenciaTransaccion(int secuenciaTransaccion) {
        this.secuenciaTransaccion = secuenciaTransaccion;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getOperacionTransaccion() {
        return operacionTransaccion;
    }

    public void setOperacionTransaccion(String operacionTransaccion) {
        this.operacionTransaccion = operacionTransaccion;
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
}
