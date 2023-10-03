package dto.loginbtw.response;

public class LoginBtwOUTDto {
    public DatosSalida datosSalida;
    public DatosTransaccion datosTransaccion;
    public DatosRespuesta datosRespuesta;


    public DatosSalida getDatosSalida() {
        return datosSalida;
    }

    public void setDatosSalida(DatosSalida datosSalida) {
        this.datosSalida = datosSalida;
    }

    public DatosTransaccion getDatosTransaccion() {
        return datosTransaccion;
    }

    public void setDatosTransaccion(DatosTransaccion datosTransaccion) {
        this.datosTransaccion = datosTransaccion;
    }

    public DatosRespuesta getDatosRespuesta() {
        return datosRespuesta;
    }

    public void setDatosRespuesta(DatosRespuesta datosRespuesta) {
        this.datosRespuesta = datosRespuesta;
    }
}
