package dto.app.pagatriple;

public class Apuesta {

    public int idApuesta;
    public String numeroApostado;
    public String codLoteria;
    public String nombreLoteria;
    public int valorPagado;

    public int getIdApuesta() {
        return idApuesta;
    }

    public void setIdApuesta(int idApuesta) {
        this.idApuesta = idApuesta;
    }

    public String getNumeroApostado() {
        return numeroApostado;
    }

    public void setNumeroApostado(String numeroApostado) {
        this.numeroApostado = numeroApostado;
    }

    public String getCodLoteria() {
        return codLoteria;
    }

    public void setCodLoteria(String codLoteria) {
        this.codLoteria = codLoteria;
    }

    public String getNombreLoteria() {
        return nombreLoteria;
    }

    public void setNombreLoteria(String nombreLoteria) {
        this.nombreLoteria = nombreLoteria;
    }

    public int getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(int valorPagado) {
        this.valorPagado = valorPagado;
    }
}
