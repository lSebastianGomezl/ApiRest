package dto.consultCategoriasConvenio.response;

import java.math.BigDecimal;
import java.util.Date;

public class RGRecaudoDTO {

    private static final long serialVersionUID = 1L;
    private int Id;
    private int fkConvenio;
    private String nitEmpresa;
    private Date fechaPago;
    private String horaPago;
    private BigDecimal valorPagado;
    private BigDecimal valorRecibido;
    private String estado;
    private String numeroAutorizacion;
    private String codigoRespuesta;
    private String descripcionRespuesta;
    private String mensajeError;
    private String codMunicipio;
    private String codOficina;
    private String codVendedor;
    private String codPtoVenta;
    private String codArriendo;
    private String codCanal;
    private String usuario;
    private String caja;
    private String terminal;
    private String volumen;
    private BigDecimal idTransaccion;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public int getFkConvenio() {
        return fkConvenio;
    }
    public void setFkConvenio(int fkConvenio) {
        this.fkConvenio = fkConvenio;
    }
    public String getNitEmpresa() {
        return nitEmpresa;
    }
    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }
    public Date getFechaPago() {
        return fechaPago;
    }
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
    public String getHoraPago() {
        return horaPago;
    }
    public void setHoraPago(String horaPago) {
        this.horaPago = horaPago;
    }
    public BigDecimal getValorPagado() {
        return valorPagado;
    }
    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }
    public BigDecimal getValorRecibido() {
        return valorRecibido;
    }
    public void setValorRecibido(BigDecimal valorRecibido) {
        this.valorRecibido = valorRecibido;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }
    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }
    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }
    public void setDescripcionRespuesta(String descripcionRespuesta) {
        this.descripcionRespuesta = descripcionRespuesta;
    }
    public String getMensajeError() {
        return mensajeError;
    }
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
    public String getCodMunicipio() {
        return codMunicipio;
    }
    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }
    public String getCodOficina() {
        return codOficina;
    }
    public void setCodOficina(String codOficina) {
        this.codOficina = codOficina;
    }
    public String getCodVendedor() {
        return codVendedor;
    }
    public void setCodVendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }
    public String getCodPtoVenta() {
        return codPtoVenta;
    }
    public void setCodPtoVenta(String codPtoVenta) {
        this.codPtoVenta = codPtoVenta;
    }
    public String getCodArriendo() {
        return codArriendo;
    }
    public void setCodArriendo(String codArriendo) {
        this.codArriendo = codArriendo;
    }
    public String getCodCanal() {
        return codCanal;
    }
    public void setCodCanal(String codCanal) {
        this.codCanal = codCanal;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getCaja() {
        return caja;
    }
    public void setCaja(String caja) {
        this.caja = caja;
    }
    public String getTerminal() {
        return terminal;
    }
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
    public String getVolumen() {
        return volumen;
    }
    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }
    public BigDecimal getIdTransaccion() {
        return idTransaccion;
    }
    public void setIdTransaccion(BigDecimal idTransaccion) {
        this.idTransaccion = idTransaccion;
    }
}
