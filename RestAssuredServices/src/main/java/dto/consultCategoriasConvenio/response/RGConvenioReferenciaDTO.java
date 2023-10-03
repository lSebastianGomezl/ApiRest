package dto.consultCategoriasConvenio.response;

import java.util.Date;

public class RGConvenioReferenciaDTO {

    private static final long serialVersionUID = 1L;
    private String id;
    private RGReferenciaDTO referenciaDTO;
    private RGRecaudoReferenciaDTO recaudoReferenciaDTO;
    private int orden;
    private Date fechaLimitePago;
    private String valorPagar;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Date getFechaLimitePago() {
        return fechaLimitePago;
    }

    public void setFechaLimitePago(Date fechaLimitePago) {
        this.fechaLimitePago = fechaLimitePago;
    }

    public String getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(String valorPagar) {
        this.valorPagar = valorPagar;
    }

    public RGReferenciaDTO getReferenciaDTO() {
        return referenciaDTO;
    }

    public void setReferenciaDTO(RGReferenciaDTO referenciaDTO) {
        this.referenciaDTO = referenciaDTO;
    }

    public RGRecaudoReferenciaDTO getRecaudoReferenciaDTO() {
        return recaudoReferenciaDTO;
    }

    public void setRecaudoReferenciaDTO(RGRecaudoReferenciaDTO recaudoReferenciaDTO) {
        this.recaudoReferenciaDTO = recaudoReferenciaDTO;
    }

}
