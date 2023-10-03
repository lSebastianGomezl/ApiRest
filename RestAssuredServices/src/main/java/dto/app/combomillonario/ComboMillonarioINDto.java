package dto.app.combomillonario;

import dto.comunclass.DatosTransaccion;

public class ComboMillonarioINDto {

    public DatosEntrada datosEntrada;
    public DatosTransaccion datosTransaccion;

    public DatosEntrada getDatosEntrada() {
        return datosEntrada;
    }

    public void setDatosEntrada(DatosEntrada datosEntrada) {
        this.datosEntrada = datosEntrada;
    }

    public DatosTransaccion getDatosTransaccion() {
        return datosTransaccion;
    }

    public void setDatosTransaccion(DatosTransaccion datosTransaccion) {
        this.datosTransaccion = datosTransaccion;
    }
}
