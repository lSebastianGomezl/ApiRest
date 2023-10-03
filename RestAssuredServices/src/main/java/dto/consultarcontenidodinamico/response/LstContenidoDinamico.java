package dto.consultarcontenidodinamico.response;

public class LstContenidoDinamico {
    public String consecutivo;
    public String sigla;
    public String idContenido;
    public String textoRojo;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
