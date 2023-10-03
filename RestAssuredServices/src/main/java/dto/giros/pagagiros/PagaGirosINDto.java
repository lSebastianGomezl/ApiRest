package dto.giros.pagagiros;

import dto.giros.giroscaptacion.*;

import java.util.ArrayList;

public class PagaGirosINDto {

    public Usuario usuario;
    public String serie1;
    public String serie2;
    public Caja caja;
    public String referencia;
    public String numeroFactura;
    public String prefijoFactura;
    public ClienteOrigen clienteOrigen;
    public ClienteDestino clienteDestino;
    public String notas;
    public AgenciaOrigen agenciaOrigen;
    public AgenciaDestino agenciaDestino;
    public ArrayList<Concepto> conceptos;
    public long idTransaccion;
    public String direccionMac;
    public String codigoMuncipio;
    public String codigoOficina;
    public String codigoPuntoVenta;
    public String tipoPuntoVenta;
    public String codigoVendedor;
    public String codigoProducto;
    public int valor;
    public String canalId;
    public String codigoTerminal;
    public String tipoUsuario;
    public String operacionTransaccion;
    public String seriePrefijo;
    public String serieActual;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getPrefijoFactura() {
        return prefijoFactura;
    }

    public void setPrefijoFactura(String prefijoFactura) {
        this.prefijoFactura = prefijoFactura;
    }

    public ClienteOrigen getClienteOrigen() {
        return clienteOrigen;
    }

    public void setClienteOrigen(ClienteOrigen clienteOrigen) {
        this.clienteOrigen = clienteOrigen;
    }

    public ClienteDestino getClienteDestino() {
        return clienteDestino;
    }

    public void setClienteDestino(ClienteDestino clienteDestino) {
        this.clienteDestino = clienteDestino;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public AgenciaOrigen getAgenciaOrigen() {
        return agenciaOrigen;
    }

    public void setAgenciaOrigen(AgenciaOrigen agenciaOrigen) {
        this.agenciaOrigen = agenciaOrigen;
    }

    public AgenciaDestino getAgenciaDestino() {
        return agenciaDestino;
    }

    public void setAgenciaDestino(AgenciaDestino agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
    }

    public ArrayList<Concepto> getConceptos() {
        return conceptos;
    }

    public void setConceptos(ArrayList<Concepto> conceptos) {
        this.conceptos = conceptos;
    }

    public long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getDireccionMac() {
        return direccionMac;
    }

    public void setDireccionMac(String direccionMac) {
        this.direccionMac = direccionMac;
    }

    public String getCodigoMuncipio() {
        return codigoMuncipio;
    }

    public void setCodigoMuncipio(String codigoMuncipio) {
        this.codigoMuncipio = codigoMuncipio;
    }

    public String getCodigoOficina() {
        return codigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }

    public String getCodigoPuntoVenta() {
        return codigoPuntoVenta;
    }

    public void setCodigoPuntoVenta(String codigoPuntoVenta) {
        this.codigoPuntoVenta = codigoPuntoVenta;
    }

    public String getTipoPuntoVenta() {
        return tipoPuntoVenta;
    }

    public void setTipoPuntoVenta(String tipoPuntoVenta) {
        this.tipoPuntoVenta = tipoPuntoVenta;
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
