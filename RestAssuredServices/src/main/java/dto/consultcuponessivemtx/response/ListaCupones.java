package dto.consultcuponessivemtx.response;

public class ListaCupones {
    public String fkIdEstado;
    public int idTransaccion;
    public int fkIdCliente;
    public CuponDTO cuponDTO;

    public String getFkIdEstado() {
        return fkIdEstado;
    }

    public void setFkIdEstado(String fkIdEstado) {
        this.fkIdEstado = fkIdEstado;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getFkIdCliente() {
        return fkIdCliente;
    }

    public void setFkIdCliente(int fkIdCliente) {
        this.fkIdCliente = fkIdCliente;
    }

    public CuponDTO getCuponDTO() {
        return cuponDTO;
    }

    public void setCuponDTO(CuponDTO cuponDTO) {
        this.cuponDTO = cuponDTO;
    }
}
