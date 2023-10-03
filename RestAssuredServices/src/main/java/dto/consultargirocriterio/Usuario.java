package dto.consultargirocriterio;

public class Usuario {

    public String usuario;
    public String clave;
    public String agencia;
    public int caducidadClave;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public int getCaducidadClave() {
        return caducidadClave;
    }

    public void setCaducidadClave(int caducidadClave) {
        this.caducidadClave = caducidadClave;
    }
}
