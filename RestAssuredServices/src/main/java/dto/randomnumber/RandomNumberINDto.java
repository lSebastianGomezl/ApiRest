package dto.randomnumber;

public class RandomNumberINDto {

    public String codigoVendedor;
    public String canalId;
    public String codigoTerminal;
    public String tipoUsuario;
    public int cantidadCifras;
    public int cantidadNumeros;

    public RandomNumberINDto() {
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getCanalId() {
        return canalId;
    }

    public void setCanalId(String canalId) {
        this.canalId = canalId;
    }

    public String getCodigoTerminal() {
        return codigoTerminal;
    }

    public void setCodigoTerminal(String codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getCantidadCifras() {
        return cantidadCifras;
    }

    public void setCantidadCifras(int cantidadCifras) {
        this.cantidadCifras = cantidadCifras;
    }

    public int getCantidadNumeros() {
        return cantidadNumeros;
    }

    public void setCantidadNumeros(int cantidadNumeros) {
        this.cantidadNumeros = cantidadNumeros;
    }
}
