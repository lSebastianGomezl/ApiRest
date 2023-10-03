package dto;

public class RechargeDto {

    private String numero;
    private String codOperador;
    private String codPaquete;
    private String montoRecarga;


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodOperador() {
        return codOperador;
    }

    public void setCodOperador(String codOperador) {
        this.codOperador = codOperador;
    }

    public String getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(String codPaquete) {
        this.codPaquete = codPaquete;
    }

    public String getMontoRecarga() {
        return montoRecarga;
    }

    public void setMontoRecarga(String montoRecarga) {
        this.montoRecarga = montoRecarga;
    }
}
