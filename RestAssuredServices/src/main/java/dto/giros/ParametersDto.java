package dto.giros;

public class ParametersDto {

   String autorizador;
   String  idSesion;
   String codigoVendedor;
   Boolean incluyeFlete;
   Boolean incluyeDomicilio;

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public Boolean getIncluyeFlete() {
        return incluyeFlete;
    }

    public void setIncluyeFlete(Boolean incluyeFlete) {
        this.incluyeFlete = incluyeFlete;
    }

    public Boolean getIncluyeDomicilio() {
        return incluyeDomicilio;
    }

    public void setIncluyeDomicilio(Boolean incluyeDomicilio) {
        this.incluyeDomicilio = incluyeDomicilio;
    }
}
