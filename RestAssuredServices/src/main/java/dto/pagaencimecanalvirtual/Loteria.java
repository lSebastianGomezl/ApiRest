package dto.pagaencimecanalvirtual;

public class Loteria {

    public String codLoteria;
    public String nombreLoteria;
    public long fechaSorteo;
    public int cifrasNumero;
    public int cifrasSerie;

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

    public long getFechaSorteo() {
        return fechaSorteo;
    }

    public void setFechaSorteo(long fechaSorteo) {
        this.fechaSorteo = fechaSorteo;
    }

    public int getCifrasNumero() {
        return cifrasNumero;
    }

    public void setCifrasNumero(int cifrasNumero) {
        this.cifrasNumero = cifrasNumero;
    }

    public int getCifrasSerie() {
        return cifrasSerie;
    }

    public void setCifrasSerie(int cifrasSerie) {
        this.cifrasSerie = cifrasSerie;
    }
}
