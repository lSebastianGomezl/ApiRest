package dto.consultCategoriasConvenio.response;

import java.math.BigDecimal;
import java.util.Date;

public class RGFacturaDTO {
    private static final long serialVersionUID = 1L;

    private int idFactura;
    private String fkConvenio;
    private int fkArchivo;
    private String nitEmpresaPrincipal;
    private String numeroFactura;
    private Date fechaCarga;
    private Date fechaFacturacion;
    private Date fechaPrimerVencimiento;
    private Date fechaSegundoVencimiento;
    private String codServicioPrincipal;
    private BigDecimal valorServicioPrincipal;
    private String ciclo;
    private String direccion;
    private Date fechaPago;
    private String periodosFacturados;
    private String horaPago;
    private String nombresApellidos;
    private String estado;
    private Date fechaArchivo;
    private String horaGrabacionArchivo;
    private String numeroLote;
    private String descripcionServicioFacturado;
    private int idProcesolog;

    public int getIdFactura() {
        return idFactura;
    }
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    public String getFkConvenio() {
        return fkConvenio;
    }
    public void setFkConvenio(String fkConvenio) {
        this.fkConvenio = fkConvenio;
    }
    public int getFkArchivo() {
        return fkArchivo;
    }
    public void setFkArchivo(int fkArchivo) {
        this.fkArchivo = fkArchivo;
    }
    public String getNitEmpresaPrincipal() {
        return nitEmpresaPrincipal;
    }
    public void setNitEmpresaPrincipal(String nitEmpresaPrincipal) {
        this.nitEmpresaPrincipal = nitEmpresaPrincipal;
    }
    public String getNumeroFactura() {
        return numeroFactura;
    }
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    public Date getFechaCarga() {
        return fechaCarga;
    }
    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }
    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }
    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }
    public Date getFechaPrimerVencimiento() {
        return fechaPrimerVencimiento;
    }
    public void setFechaPrimerVencimiento(Date fechaPrimerVencimiento) {
        this.fechaPrimerVencimiento = fechaPrimerVencimiento;
    }
    public Date getFechaSegundoVencimiento() {
        return fechaSegundoVencimiento;
    }
    public void setFechaSegundoVencimiento(Date fechaSegundoVencimiento) {
        this.fechaSegundoVencimiento = fechaSegundoVencimiento;
    }
    public String getCodServicioPrincipal() {
        return codServicioPrincipal;
    }
    public void setCodServicioPrincipal(String codServicioPrincipal) {
        this.codServicioPrincipal = codServicioPrincipal;
    }
    public BigDecimal getValorServicioPrincipal() {
        return valorServicioPrincipal;
    }
    public void setValorServicioPrincipal(BigDecimal valorServicioPrincipal) {
        this.valorServicioPrincipal = valorServicioPrincipal;
    }
    public String getCiclo() {
        return ciclo;
    }
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Date getFechaPago() {
        return fechaPago;
    }
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
    public String getPeriodosFacturados() {
        return periodosFacturados;
    }
    public void setPeriodosFacturados(String periodosFacturados) {
        this.periodosFacturados = periodosFacturados;
    }
    public String getHoraPago() {
        return horaPago;
    }
    public void setHoraPago(String horaPago) {
        this.horaPago = horaPago;
    }
    public String getNombresApellidos() {
        return nombresApellidos;
    }
    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Date getFechaArchivo() {
        return fechaArchivo;
    }
    public void setFechaArchivo(Date fechaArchivo) {
        this.fechaArchivo = fechaArchivo;
    }
    public String getHoraGrabacionArchivo() {
        return horaGrabacionArchivo;
    }
    public void setHoraGrabacionArchivo(String horaGrabacionArchivo) {
        this.horaGrabacionArchivo = horaGrabacionArchivo;
    }
    public String getNumeroLote() {
        return numeroLote;
    }
    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }
    public String getDescripcionServicioFacturado() {
        return descripcionServicioFacturado;
    }
    public void setDescripcionServicioFacturado(String descripcionServicioFacturado) {
        this.descripcionServicioFacturado = descripcionServicioFacturado;
    }
    public int getIdProcesolog() {
        return idProcesolog;
    }
    public void setIdProcesolog(int idProcesolog) {
        this.idProcesolog = idProcesolog;
    }
}
