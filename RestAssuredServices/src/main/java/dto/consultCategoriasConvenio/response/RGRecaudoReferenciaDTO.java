package dto.consultCategoriasConvenio.response;

import java.util.Date;

public class RGRecaudoReferenciaDTO {

    private static final long serialVersionUID = 1L;
    private int Id;
    private String valor;
    private Date fechaVencimiento;
    private RGRecaudoDTO recaudoDTO;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public RGRecaudoDTO getRecaudoDTO() {
        return recaudoDTO;
    }
    public void setRecaudoDTO(RGRecaudoDTO recaudoDTO) {
        this.recaudoDTO = recaudoDTO;
    }
}
