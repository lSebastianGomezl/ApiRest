package dto.pagamillonarioPVMA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PagaMillonarioPvmaOUTDto {
    public String serie1;
    public String serie2;
    public String serialUnico;
    public String digitoChequeo;
    public int valorTotalPagado;
    public int valorTotalApostado;
    public int valorTotalIva;
    public String fechaSorteo;
    public ArrayList<ApuestasRespuestum> apuestasRespuesta;
    public ArrayList<LoteriaR> loterias;
    public String codigoRespuesta;
    public boolean exito;
    public String fechaTransaccion;
    public String horaTransaccion;
    public long idTransaccionRespuesta;
    public String mensaje;
    public ArrayList<Object> mensajes;
    public String serie2Actual;

    @JsonIgnore
    public String fechaTransactionTime;

    public String getFechaTransactionTime() {
        return fechaTransactionTime;
    }

    public void setFechaTransactionTime(String fechaTransactionTime) {
        this.fechaTransactionTime = fechaTransactionTime;
    }

    public String getSerie1() {
        return serie1;
    }

    public void setSerie1(String serie1) {
        this.serie1 = serie1;
    }

    public String getSerie2() {
        return serie2;
    }

    public void setSerie2(String serie2) {
        this.serie2 = serie2;
    }

    public String getSerialUnico() {
        return serialUnico;
    }

    public void setSerialUnico(String serialUnico) {
        this.serialUnico = serialUnico;
    }

    public String getDigitoChequeo() {
        return digitoChequeo;
    }

    public void setDigitoChequeo(String digitoChequeo) {
        this.digitoChequeo = digitoChequeo;
    }

    public int getValorTotalPagado() {
        return valorTotalPagado;
    }

    public void setValorTotalPagado(int valorTotalPagado) {
        this.valorTotalPagado = valorTotalPagado;
    }

    public int getValorTotalApostado() {
        return valorTotalApostado;
    }

    public void setValorTotalApostado(int valorTotalApostado) {
        this.valorTotalApostado = valorTotalApostado;
    }

    public int getValorTotalIva() {
        return valorTotalIva;
    }

    public void setValorTotalIva(int valorTotalIva) {
        this.valorTotalIva = valorTotalIva;
    }

    public String getFechaSorteo() {
        return fechaSorteo;
    }

    public void setFechaSorteo(String fechaSorteo) {
        this.fechaSorteo = fechaSorteo;
    }

    public ArrayList<ApuestasRespuestum> getApuestasRespuesta() {
        return apuestasRespuesta;
    }

    public void setApuestasRespuesta(ArrayList<ApuestasRespuestum> apuestasRespuesta) {
        this.apuestasRespuesta = apuestasRespuesta;
    }

    public ArrayList<LoteriaR> getLoterias() {
        return loterias;
    }

    public void setLoterias(ArrayList<LoteriaR> loterias) {
        this.loterias = loterias;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public long getIdTransaccionRespuesta() {
        return idTransaccionRespuesta;
    }

    public void setIdTransaccionRespuesta(long idTransaccionRespuesta) {
        this.idTransaccionRespuesta = idTransaccionRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<Object> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Object> mensajes) {
        this.mensajes = mensajes;
    }

    public String getSerie2Actual() {
        return serie2Actual;
    }

    public void setSerie2Actual(String serie2Actual) {
        this.serie2Actual = serie2Actual;
    }
}
