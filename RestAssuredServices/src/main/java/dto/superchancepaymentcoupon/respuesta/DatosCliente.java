package dto.superchancepaymentcoupon.respuesta;

public class DatosCliente {
    public String login;
    public String terminal;
    public Object idDeviceSession;
    public String idSession;
    public String rol;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Object getIdDeviceSession() {
        return idDeviceSession;
    }

    public void setIdDeviceSession(Object idDeviceSession) {
        this.idDeviceSession = idDeviceSession;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
