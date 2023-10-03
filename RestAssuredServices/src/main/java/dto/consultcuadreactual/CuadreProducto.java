package dto.consultcuadreactual;

public class CuadreProducto {

    public String codProducto;
    public String nombre;
    public String valorVenta;
    public String imprimeCuadreLoteria;

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(String valorVenta) {
        this.valorVenta = valorVenta;
    }

    public String getImprimeCuadreLoteria() {
        return imprimeCuadreLoteria;
    }

    public void setImprimeCuadreLoteria(String imprimeCuadreLoteria) {
        this.imprimeCuadreLoteria = imprimeCuadreLoteria;
    }

    @Override
    public String toString() {
        return "CuadreProducto{" +
                "codProducto='" + codProducto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", valorVenta='" + valorVenta + '\'' +
                ", imprimeCuadreLoteria='" + imprimeCuadreLoteria + '\'' +
                '}';
    }
}
