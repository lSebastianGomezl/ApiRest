package dto.consultCategoriasConvenio.response;

public class RGConvenioCanalDTO {
    private static final long serialVersionUID = 1L;
    private int Id;
    private int fkIdCanal;
    private String fkCodConvenio;
    private String topeMinimo;
    private String topeMaximo;
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public int getFkIdCanal() {
        return fkIdCanal;
    }
    public void setFkIdCanal(int fkIdCanal) {
        this.fkIdCanal = fkIdCanal;
    }
    public String getTopeMinimo() {
        return topeMinimo;
    }
    public void setTopeMinimo(String topeMinimo) {
        this.topeMinimo = topeMinimo;
    }
    public String getTopeMaximo() {
        return topeMaximo;
    }
    public void setTopeMaximo(String topeMaximo) {
        this.topeMaximo = topeMaximo;
    }
    public String getFkCodConvenio() {
        return fkCodConvenio;
    }
    public void setFkCodConvenio(String fkCodConvenio) {
        this.fkCodConvenio = fkCodConvenio;
    }
}
