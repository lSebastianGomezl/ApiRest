package dto.calcularconceptos;

import dto.giros.giroscaptacion.ClienteDestino;
import dto.giros.giroscaptacion.ClienteOrigen;
import dto.giros.giroscaptacion.Usuario;

public class CalcularConceptosINDto {

    public Usuario usuario;
    public ClienteOrigen clienteOrigen;
    public ClienteDestino clienteDestino;
    public String agenciaDestino;
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

    public String autorizador;
    public String idSesion;


    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getAgenciaDestino() {
        return agenciaDestino;
    }

    public void setAgenciaDestino(String agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
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
}
