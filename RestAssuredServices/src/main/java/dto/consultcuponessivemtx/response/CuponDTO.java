package dto.consultcuponessivemtx.response;

public class CuponDTO {

    public String fechaInicio;
    public int idProducto;
    public int monto;
    public int idCupon;
    public int idSerie;
    public String fechaFin;
    public CuponDescripcion cuponDescripcion;
    public int idCarga;
    public String fechaCarga;
    public Object fechaRedimido;

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public CuponDescripcion getCuponDescripcion() {
        return cuponDescripcion;
    }

    public void setCuponDescripcion(CuponDescripcion cuponDescripcion) {
        this.cuponDescripcion = cuponDescripcion;
    }

    public int getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(int idCarga) {
        this.idCarga = idCarga;
    }

    public String getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Object getFechaRedimido() {
        return fechaRedimido;
    }

    public void setFechaRedimido(Object fechaRedimido) {
        this.fechaRedimido = fechaRedimido;
    }
}
