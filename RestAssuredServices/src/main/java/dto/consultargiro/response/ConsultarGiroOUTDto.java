package dto.consultargiro.response;

import dto.giros.pagagiros.AgenciaOrigen;
import dto.giros.giroscaptacion.AgenciaDestino;
import dto.giros.giroscaptacion.ClienteDestino;
import dto.giros.giroscaptacion.ClienteOrigen;
import dto.giros.giroscaptacion.Concepto;

import java.util.ArrayList;

public class ConsultarGiroOUTDto {

    public String codigoRespuesta;
    public boolean exito;
    public String fechaTransaccion;
    public String horaTransaccion;
    public String mensaje;
    public Object fechaTransactionTime;
    public ArrayList<Object> mensajes;
    public String token;
    public AgenciaOrigen agenciaOrigen;
    public AgenciaDestino agenciaDestino;
    public ClienteOrigen clienteOrigen;
    public ClienteDestino clienteDestino;
    public ArrayList<Concepto> conceptos;
    public int valorGiro;
    public String numeroFactura;
    public String prefijoFactura;
    public String notas;
    public String fechaGiro;
    public String horaGiro;

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getFechaTransactionTime() {
        return fechaTransactionTime;
    }

    public void setFechaTransactionTime(Object fechaTransactionTime) {
        this.fechaTransactionTime = fechaTransactionTime;
    }

    public ArrayList<Object> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Object> mensajes) {
        this.mensajes = mensajes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public ArrayList<Concepto> getConceptos() {
        return conceptos;
    }

    public void setConceptos(ArrayList<Concepto> conceptos) {
        this.conceptos = conceptos;
    }

    public int getValorGiro() {
        return valorGiro;
    }

    public void setValorGiro(int valorGiro) {
        this.valorGiro = valorGiro;
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

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getFechaGiro() {
        return fechaGiro;
    }

    public void setFechaGiro(String fechaGiro) {
        this.fechaGiro = fechaGiro;
    }

    public String getHoraGiro() {
        return horaGiro;
    }

    public void setHoraGiro(String horaGiro) {
        this.horaGiro = horaGiro;
    }
}
