package dto.consultCategoriasConvenio.response;

import java.util.List;

public class RGReferenciaDTO {

    private static final long serialVersionUID = 1L;
    private int Id;
    private String label;
    private String maximo;
    private String minimo;
    private String tipoReferencia;
    private List<RGConvenioReferenciaListaDTO> rgConvenioListasReferencias;

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getMaximo() {
        return maximo;
    }
    public void setMaximo(String maximo) {
        this.maximo = maximo;
    }
    public String getMinimo() {
        return minimo;
    }
    public void setMinimo(String minimo) {
        this.minimo = minimo;
    }
    public String getTipoReferencia() {
        return tipoReferencia;
    }
    public void setTipoReferencia(String tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public List<RGConvenioReferenciaListaDTO> getRgConvenioListasReferencias() {
        return rgConvenioListasReferencias;
    }
    public void setRgConvenioListasReferencias(List<RGConvenioReferenciaListaDTO> rgConvenioListasReferencias) {
        this.rgConvenioListasReferencias = rgConvenioListasReferencias;
    }
}
