package dto.giros.giroscaptacion;

import java.util.ArrayList;

public class GirosCaptacionINDto {

    public Usuario usuario;
    public AgenciaDestino agenciaDestino;
    public ClienteOrigen clienteOrigen;
    public ClienteDestino clienteDestino;
    public Caja caja;
    public ArrayList<Concepto> conceptos;
    public String serie1;
    public String serie2;
    public boolean incluyeFlete;
    public boolean incluyeDomicilio;
    public boolean incluyeOtro;
    public String direccionMac;
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

    public AgenciaDestino getAgenciaDestino() {
        return agenciaDestino;
    }

    public void setAgenciaDestino(AgenciaDestino agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
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

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public ArrayList<Concepto> getConceptos() {
        return conceptos;
    }

    public void setConceptos(ArrayList<Concepto> conceptos) {
        this.conceptos = conceptos;
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

    public boolean isIncluyeFlete() {
        return incluyeFlete;
    }

    public void setIncluyeFlete(boolean incluyeFlete) {
        this.incluyeFlete = incluyeFlete;
    }

    public boolean isIncluyeDomicilio() {
        return incluyeDomicilio;
    }

    public void setIncluyeDomicilio(boolean incluyeDomicilio) {
        this.incluyeDomicilio = incluyeDomicilio;
    }

    public boolean isIncluyeOtro() {
        return incluyeOtro;
    }

    public void setIncluyeOtro(boolean incluyeOtro) {
        this.incluyeOtro = incluyeOtro;
    }

    public String getDireccionMac() {
        return direccionMac;
    }

    public void setDireccionMac(String direccionMac) {
        this.direccionMac = direccionMac;
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
