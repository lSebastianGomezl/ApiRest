package dto.makesale;

public class LoteriasRequest {

    public String codLoteria;
    public int cifrasNumero;
    public int cifrasSerie;

    public LoteriasRequest() {
    }

    public LoteriasRequest(String codLoteria, int cifrasNumero, int cifrasSerie) {
        this.codLoteria = codLoteria;
        this.cifrasNumero = cifrasNumero;
        this.cifrasSerie = cifrasSerie;
    }

    public String getCodLoteria() {
        return codLoteria;
    }

    public void setCodLoteria(String codLoteria) {
        this.codLoteria = codLoteria;
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
