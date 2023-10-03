package dto.consultparameters;

public class MotivosAnulacion {
    public int idMotivo;
    public String motivo;
    public Object codProducto;

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Object getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Object codProducto) {
        this.codProducto = codProducto;
    }
}
