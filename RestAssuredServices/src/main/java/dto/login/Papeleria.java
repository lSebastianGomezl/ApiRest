package dto.login;

public class Papeleria {

    public String serie1;
    public String serie2;
    public boolean enUso;
    public String codEntidad;
    public String rangoFinal;
    public String tipoChance;

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

    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    public String getCodEntidad() {
        return codEntidad;
    }

    public void setCodEntidad(String codEntidad) {
        this.codEntidad = codEntidad;
    }

    public String getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(String rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    public String getTipoChance() {
        return tipoChance;
    }

    public void setTipoChance(String tipoChance) {
        this.tipoChance = tipoChance;
    }
}
