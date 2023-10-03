package dto.consultargirocriterio.response;

import dto.giros.giroscaptacion.ClienteDestino;
import dto.giros.giroscaptacion.ClienteOrigen;

import java.util.ArrayList;

public class Giro {

    public AgenciaOrigen agenciaOrigen;
    public ClienteOrigen clienteOrigen;
    public ClienteDestino clienteDestino;
    public ArrayList<Object> conceptos;
    public int valorGiro;
    public String numeroReferencia;
    public String estado;

    public AgenciaOrigen getAgenciaOrigen() {
        return agenciaOrigen;
    }

    public void setAgenciaOrigen(AgenciaOrigen agenciaOrigen) {
        this.agenciaOrigen = agenciaOrigen;
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

    public ArrayList<Object> getConceptos() {
        return conceptos;
    }

    public void setConceptos(ArrayList<Object> conceptos) {
        this.conceptos = conceptos;
    }

    public int getValorGiro() {
        return valorGiro;
    }

    public void setValorGiro(int valorGiro) {
        this.valorGiro = valorGiro;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
