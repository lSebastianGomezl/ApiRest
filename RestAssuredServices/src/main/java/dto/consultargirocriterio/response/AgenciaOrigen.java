package dto.consultargirocriterio.response;

import dto.giros.pagagiros.Ciudad;

public class AgenciaOrigen {

    public String codigo;
    public String nombre;
    public Ciudad ciudad;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
