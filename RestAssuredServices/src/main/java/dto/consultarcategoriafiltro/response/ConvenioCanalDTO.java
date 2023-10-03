package dto.consultarcategoriafiltro.response;

public class ConvenioCanalDTO {

    public int fkIdCanal;
    public Object fkCodConvenio;
    public Object topeMinimo;
    public Object topeMaximo;
    public int id;

    public int getFkIdCanal() {
        return fkIdCanal;
    }

    public void setFkIdCanal(int fkIdCanal) {
        this.fkIdCanal = fkIdCanal;
    }

    public Object getFkCodConvenio() {
        return fkCodConvenio;
    }

    public void setFkCodConvenio(Object fkCodConvenio) {
        this.fkCodConvenio = fkCodConvenio;
    }

    public Object getTopeMinimo() {
        return topeMinimo;
    }

    public void setTopeMinimo(Object topeMinimo) {
        this.topeMinimo = topeMinimo;
    }

    public Object getTopeMaximo() {
        return topeMaximo;
    }

    public void setTopeMaximo(Object topeMaximo) {
        this.topeMaximo = topeMaximo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
