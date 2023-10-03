package dto.consultCategoriasConvenio.response;

public class RGFormaCapturaDTO {
    private static final long serialVersionUID = 1L;
    private int Id;
    private String nombre;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
