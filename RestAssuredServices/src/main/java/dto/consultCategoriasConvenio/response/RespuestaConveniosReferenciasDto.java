package dto.consultCategoriasConvenio.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaConveniosReferenciasDto {

    private static final long serialVersionUID = 1L;
    private Map<String, List<RGConvenioDTO>> categoriasConvenios;
    private List<RGConvenioReferenciaDTO> referencias;

    public void RespuestaConveniosReferenciasDTO() {
        categoriasConvenios = new HashMap<String, List<RGConvenioDTO>>();
        referencias = new ArrayList<RGConvenioReferenciaDTO>();
    }

    public Map<String, List<RGConvenioDTO>> getCategoriasConvenios() {
        return categoriasConvenios;
    }

    public void setCategoriasConvenios(Map<String, List<RGConvenioDTO>> categoriasConvenios) {
        this.categoriasConvenios = categoriasConvenios;
    }

    public List<RGConvenioReferenciaDTO> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<RGConvenioReferenciaDTO> referencias) {
        this.referencias = referencias;
    }
}
