package dto.maxichancepaymentcoupon.response;

public class ListProductoRespuestaW {

    public RespuestaCaracteristicas respuestaCaracteristicas;
    public int idProductoTrans;
    public String mensaje;
    public boolean exito;
    public Object codigo;

    public RespuestaCaracteristicas getRespuestaCaracteristicas() {
        return respuestaCaracteristicas;
    }

    public void setRespuestaCaracteristicas(RespuestaCaracteristicas respuestaCaracteristicas) {
        this.respuestaCaracteristicas = respuestaCaracteristicas;
    }

    public int getIdProductoTrans() {
        return idProductoTrans;
    }

    public void setIdProductoTrans(int idProductoTrans) {
        this.idProductoTrans = idProductoTrans;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public Object getCodigo() {
        return codigo;
    }

    public void setCodigo(Object codigo) {
        this.codigo = codigo;
    }
}
