package dto.consultCategoriasConvenio.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RGConvenioDTO {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private String codConvenio;
    @JsonIgnore
    private String codConvenioAliado;
    @JsonIgnore
    private String nitEmpresa;
    @JsonIgnore
    private String razonSocial;
    @JsonIgnore
    private String descripcionCorta;
    @JsonIgnore
    private String correo_informativo;
    @JsonIgnore
    private int fkTitularGestor;
    @JsonIgnore
    private Date fechaRegistro;
    @JsonIgnore
    private Date fechaUltimaModificacion;
    @JsonIgnore
    private String estado;
    @JsonIgnore
    private int fkIdFormaCaptura;
    @JsonIgnore
    private String cbReferencia;
    @JsonIgnore
    private String cbCodigoEan;
    @JsonIgnore
    private String cbFechaLimitePago;
    @JsonIgnore
    private String cbValorPagar;
    @JsonIgnore
    private String consulta;
    @JsonIgnore
    private BigDecimal topeConvenioMinimo;
    @JsonIgnore
    private BigDecimal topeConvenioMaximo;
    @JsonIgnore
    private String formaPago;
    @JsonIgnore
    private String aceptaDecimal;
    @JsonIgnore
    private String pagoVencidoAcepta;
    @JsonIgnore
    private String pagosVencidosFechaLimite;
    @JsonIgnore
    private int pagosVencidosCantDias;
    @JsonIgnore
    private int fkArchivoIntercambio;
    @JsonIgnore
    private int fkCategoria;
    @JsonIgnore
    private String cuentaContable;
    @JsonIgnore
    private String conValidacion;
    @JsonIgnore
    private String url;
    @JsonIgnore
    private String validacionFactura;
    @JsonIgnore
    private String webServicePersonalizado;

    private RGConvenioCanalDTO convenioCanalDTO;
    private RGConvenioRedHabilitadaDTO convenioRedHabilitadaDTO;
    private RGFormaCapturaDTO formaCapturaDTO;
    private RGCategoriaDTO categoriaDTO;
    private RGTitularGestorDTO titularGestorDTO;
    private List<RGConvenioReferenciaDTO> convenioReferenciaDTO;
    private RGArchivoDTO archivoDTO;
    private RGFacturaDTO facturaDTO;

    public String getCodConvenio() {
        return codConvenio;
    }
    public void setCodConvenio(String codConvenio) {
        this.codConvenio = codConvenio;
    }
    public String getCodConvenioAliado() {
        return codConvenioAliado;
    }
    public void setCodConvenioAliado(String codConvenioAliado) {
        this.codConvenioAliado = codConvenioAliado;
    }
    public String getNitEmpresa() {
        return nitEmpresa;
    }
    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getDescripcionCorta() {
        return descripcionCorta;
    }
    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }
    public String getCorreo_informativo() {
        return correo_informativo;
    }
    public void setCorreo_informativo(String correo_informativo) {
        this.correo_informativo = correo_informativo;
    }
    public int getFkTitularGestor() {
        return fkTitularGestor;
    }
    public void setFkTitularGestor(int fkTitularGestor) {
        this.fkTitularGestor = fkTitularGestor;
    }
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getFkIdFormaCaptura() {
        return fkIdFormaCaptura;
    }
    public void setFkIdFormaCaptura(int fkIdFormaCaptura) {
        this.fkIdFormaCaptura = fkIdFormaCaptura;
    }
    public String getCbReferencia() {
        return cbReferencia;
    }
    public void setCbReferencia(String cbReferencia) {
        this.cbReferencia = cbReferencia;
    }
    public String getCbCodigoEan() {
        return cbCodigoEan;
    }
    public void setCbCodigoEan(String cbCodigoEan) {
        this.cbCodigoEan = cbCodigoEan;
    }
    public String getCbFechaLimitePago() {
        return cbFechaLimitePago;
    }
    public void setCbFechaLimitePago(String cbFechaLimitePago) {
        this.cbFechaLimitePago = cbFechaLimitePago;
    }
    public String getCbValorPagar() {
        return cbValorPagar;
    }
    public void setCbValorPagar(String cbValorPagar) {
        this.cbValorPagar = cbValorPagar;
    }
    public String getConsulta() {
        return consulta;
    }
    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }
    public BigDecimal getTopeConvenioMinimo() {
        return topeConvenioMinimo;
    }
    public void setTopeConvenioMinimo(BigDecimal topeConvenioMinimo) {
        this.topeConvenioMinimo = topeConvenioMinimo;
    }
    public BigDecimal getTopeConvenioMaximo() {
        return topeConvenioMaximo;
    }
    public void setTopeConvenioMaximo(BigDecimal topeConvenioMaximo) {
        this.topeConvenioMaximo = topeConvenioMaximo;
    }
    public String getFormaPago() {
        return formaPago;
    }
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    public String getAceptaDecimal() {
        return aceptaDecimal;
    }
    public void setAceptaDecimal(String aceptaDecimal) {
        this.aceptaDecimal = aceptaDecimal;
    }
    public String getPagoVencidoAcepta() {
        return pagoVencidoAcepta;
    }
    public void setPagoVencidoAcepta(String pagoVencidoAcepta) {
        this.pagoVencidoAcepta = pagoVencidoAcepta;
    }
    public String getPagosVencidosFechaLimite() {
        return pagosVencidosFechaLimite;
    }
    public void setPagosVencidosFechaLimite(String pagosVencidosFechaLimite) {
        this.pagosVencidosFechaLimite = pagosVencidosFechaLimite;
    }
    public int getPagosVencidosCantDias() {
        return pagosVencidosCantDias;
    }
    public void setPagosVencidosCantDias(int pagosVencidosCantDias) {
        this.pagosVencidosCantDias = pagosVencidosCantDias;
    }
    public int getFkArchivoIntercambio() {
        return fkArchivoIntercambio;
    }
    public void setFkArchivoIntercambio(int fkArchivoIntercambio) {
        this.fkArchivoIntercambio = fkArchivoIntercambio;
    }
    public int getFkCategoria() {
        return fkCategoria;
    }
    public void setFkCategoria(int fkCategoria) {
        this.fkCategoria = fkCategoria;
    }
    public String getConValidacion() {
        return conValidacion;
    }
    public void setConValidacion(String conValidacion) {
        this.conValidacion = conValidacion;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getValidacionFactura() {
        return validacionFactura;
    }
    public void setValidacionFactura(String validacionFactura) {
        this.validacionFactura = validacionFactura;
    }
    public String getWebServicePersonalizado() {
        return webServicePersonalizado;
    }
    public void setWebServicePersonalizado(String webServicePersonalizado) {
        this.webServicePersonalizado = webServicePersonalizado;
    }
    public RGConvenioCanalDTO getConvenioCanalDTO() {
        return convenioCanalDTO;
    }
    public void setConvenioCanalDTO(RGConvenioCanalDTO convenioCanalDTO) {
        this.convenioCanalDTO = convenioCanalDTO;
    }
    public RGConvenioRedHabilitadaDTO getConvenioRedHabilitadaDTO() {
        return convenioRedHabilitadaDTO;
    }
    public void setConvenioRedHabilitadaDTO(RGConvenioRedHabilitadaDTO convenioRedHabilitadaDTO) {
        this.convenioRedHabilitadaDTO = convenioRedHabilitadaDTO;
    }
    public RGFormaCapturaDTO getFormaCapturaDTO() {
        return formaCapturaDTO;
    }
    public void setFormaCapturaDTO(RGFormaCapturaDTO formaCapturaDTO) {
        this.formaCapturaDTO = formaCapturaDTO;
    }
    public RGCategoriaDTO getCategoriaDTO() {
        return categoriaDTO;
    }
    public void setCategoriaDTO(RGCategoriaDTO categoriaDTO) {
        this.categoriaDTO = categoriaDTO;
    }
    public RGTitularGestorDTO getTitularGestorDTO() {
        return titularGestorDTO;
    }
    public void setTitularGestorDTO(RGTitularGestorDTO titularGestorDTO) {
        this.titularGestorDTO = titularGestorDTO;
    }

    public RGArchivoDTO getArchivoDTO() {
        return archivoDTO;
    }
    public void setArchivoDTO(RGArchivoDTO archivoDTO) {
        this.archivoDTO = archivoDTO;
    }
    public List<RGConvenioReferenciaDTO> getConvenioReferenciaDTO() {
        return convenioReferenciaDTO;
    }
    public void setConvenioReferenciaDTO(List<RGConvenioReferenciaDTO> convenioReferenciaDTO) {
        this.convenioReferenciaDTO = convenioReferenciaDTO;
    }
    public RGFacturaDTO getFacturaDTO() {
        return facturaDTO;
    }
    public void setFacturaDTO(RGFacturaDTO facturaDTO) {
        this.facturaDTO = facturaDTO;
    }
    public String getCuentaContable() {
        return cuentaContable;
    }
    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }
}
