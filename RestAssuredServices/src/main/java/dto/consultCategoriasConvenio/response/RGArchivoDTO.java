package dto.consultCategoriasConvenio.response;

public class RGArchivoDTO {
    private static final long serialVersionUID = 1L;
    private int Id;
    private String nombre;
    private String claseValidadora;
    private String claseExportadora;
    private String separador;
    private String ignorarPrimeraLinea;
    private String ignorarUltimaLinea;
    private String ftpIp;
    private String ftpPuerto;
    private String ftpSeguro;
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getClaseValidadora() {
        return claseValidadora;
    }
    public void setClaseValidadora(String claseValidadora) {
        this.claseValidadora = claseValidadora;
    }
    public String getClaseExportadora() {
        return claseExportadora;
    }
    public void setClaseExportadora(String claseExportadora) {
        this.claseExportadora = claseExportadora;
    }
    public String getSeparador() {
        return separador;
    }
    public void setSeparador(String separador) {
        this.separador = separador;
    }
    public String getIgnorarPrimeraLinea() {
        return ignorarPrimeraLinea;
    }
    public void setIgnorarPrimeraLinea(String ignorarPrimeraLinea) {
        this.ignorarPrimeraLinea = ignorarPrimeraLinea;
    }
    public String getIgnorarUltimaLinea() {
        return ignorarUltimaLinea;
    }
    public void setIgnorarUltimaLinea(String ignorarUltimaLinea) {
        this.ignorarUltimaLinea = ignorarUltimaLinea;
    }
    public String getFtpIp() {
        return ftpIp;
    }
    public void setFtpIp(String ftpIp) {
        this.ftpIp = ftpIp;
    }
    public String getFtpPuerto() {
        return ftpPuerto;
    }
    public void setFtpPuerto(String ftpPuerto) {
        this.ftpPuerto = ftpPuerto;
    }
    public String getFtpSeguro() {
        return ftpSeguro;
    }
    public void setFtpSeguro(String ftpSeguro) {
        this.ftpSeguro = ftpSeguro;
    }
}
