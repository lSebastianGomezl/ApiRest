package dto.app.response;

public class ListProductoRespuestaW {

    public int idProductoTrans;
    public RespuestaCaracteristicas respuestaCaracteristicas;
    public String mensaje;
    public boolean exito;
    public Object codigo;

    public int getIdProductoTrans() {
        return idProductoTrans;
    }

    public void setIdProductoTrans(int idProductoTrans) {
        this.idProductoTrans = idProductoTrans;
    }

    public RespuestaCaracteristicas getRespuestaCaracteristicas() {
        return respuestaCaracteristicas;
    }

    public void setRespuestaCaracteristicas(RespuestaCaracteristicas respuestaCaracteristicas) {
        this.respuestaCaracteristicas = respuestaCaracteristicas;
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
