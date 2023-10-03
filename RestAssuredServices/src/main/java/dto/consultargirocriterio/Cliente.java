package dto.consultargirocriterio;

public class Cliente {

    public String tipoIdentificacion;
    public String identificacion;
    public boolean enrolado;
    public boolean exoneradoHuella;
    public boolean remitente;

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public boolean isEnrolado() {
        return enrolado;
    }

    public void setEnrolado(boolean enrolado) {
        this.enrolado = enrolado;
    }

    public boolean isExoneradoHuella() {
        return exoneradoHuella;
    }

    public void setExoneradoHuella(boolean exoneradoHuella) {
        this.exoneradoHuella = exoneradoHuella;
    }

    public boolean isRemitente() {
        return remitente;
    }

    public void setRemitente(boolean remitente) {
        this.remitente = remitente;
    }
}
