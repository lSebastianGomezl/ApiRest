package dto.consultCategoriasConvenio.response;

public class RGCategoriaDTO {
    private static final long serialVersionUID = 1L;
    private int id;
    private String codigo;
    private String nombre;
    private Long cantidadConvenios;



    public RGCategoriaDTO() {
    }



    public RGCategoriaDTO(int id, String codigo, String nombre,Long cantidadConvenios) {
        super();
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidadConvenios = cantidadConvenios;
    }


    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getCodigo() {
        return codigo;
    }



    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getCantidadConvenios() {
        return cantidadConvenios;
    }



    public void setCantidadConvenios(Long cantidad) {
        this.cantidadConvenios = cantidad;
    }

}
