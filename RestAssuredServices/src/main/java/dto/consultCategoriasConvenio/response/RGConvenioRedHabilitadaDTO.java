package dto.consultCategoriasConvenio.response;

public class RGConvenioRedHabilitadaDTO {

    private static final long serialVersionUID = 1L;
    private RGRedHabilitadaDTO redHabilitadDTO;
    private int Id;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public RGRedHabilitadaDTO getRedHabilitadDTO() {
        return this.redHabilitadDTO;
    }
    public void setRedHabilitadDTO(RGRedHabilitadaDTO redHabilitadDTO) {
        this.redHabilitadDTO = redHabilitadDTO;
    }
}
