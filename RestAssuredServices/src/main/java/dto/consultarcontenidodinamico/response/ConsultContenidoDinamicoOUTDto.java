package dto.consultarcontenidodinamico.response;

public class ConsultContenidoDinamicoOUTDto {

    public DatosSalida datosSalida;
    public Object datosTransaccion;
    public DatosRespuesta datosRespuesta;


    public DatosSalida getDatosSalida() {
        return datosSalida;
    }

    public void setDatosSalida(DatosSalida datosSalida) {
        this.datosSalida = datosSalida;
    }

    public Object getDatosTransaccion() {
        return datosTransaccion;
    }

    public void setDatosTransaccion(Object datosTransaccion) {
        this.datosTransaccion = datosTransaccion;
    }

    public DatosRespuesta getDatosRespuesta() {
        return datosRespuesta;
    }

    public void setDatosRespuesta(DatosRespuesta datosRespuesta) {
        this.datosRespuesta = datosRespuesta;
    }
}
