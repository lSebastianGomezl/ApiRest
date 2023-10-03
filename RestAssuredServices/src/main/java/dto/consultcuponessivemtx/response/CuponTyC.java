package dto.consultcuponessivemtx.response;

public class CuponTyC {

    public String consecutivo;
    public String sigla;
    public String textoMostrado;
    public Object aceptado;
    public String idContenido;
    public String textoRojo;
    public boolean check;
    public String path;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getTextoMostrado() {
        return textoMostrado;
    }

    public void setTextoMostrado(String textoMostrado) {
        this.textoMostrado = textoMostrado;
    }

    public Object getAceptado() {
        return aceptado;
    }

    public void setAceptado(Object aceptado) {
        this.aceptado = aceptado;
    }

    public String getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(String idContenido) {
        this.idContenido = idContenido;
    }

    public String getTextoRojo() {
        return textoRojo;
    }

    public void setTextoRojo(String textoRojo) {
        this.textoRojo = textoRojo;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
