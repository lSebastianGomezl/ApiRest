package dto.giros.giroscaptacion;

public class ClienteOrigen {

    public String tipoIdentificacion;
    public String identificacion;
    public String primerNombre;
    public String primerApellido;
    public String segundoApellido;
    public String direccion;
    public String email;
    public String telefono;
    public String celular;
    public boolean enrolado;
    public boolean exoneradoHuella;
    public String huella;
    public boolean remitente;

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isEnrolado() {
        return enrolado;
    }

    public void setEnrolado(boolean enrolado) {
        this.enrolado = enrolado;
    }

    public boolean isExoneradoHuella() {
        return exoneradoHuella;
    }

    public void setExoneradoHuella(boolean exoneradoHuella) {
        this.exoneradoHuella = exoneradoHuella;
    }

    public String getHuella() {
        return huella;
    }

    public void setHuella(String huella) {
        this.huella = huella;
    }

    public boolean isRemitente() {
        return remitente;
    }

    public void setRemitente(boolean remitente) {
        this.remitente = remitente;
    }
}
